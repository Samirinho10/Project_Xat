
package dades;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import model.Usuari;

public class ServidorSocketStream {
    
    private static List<Usuari> clientsConnectats = new ArrayList<>();
    
    public static void main(String[] args) {

        try {
            
            // Generem clau simètrica AES....
            System.out.println("Generem clau simètrica...");
            KeyGenerator generador = KeyGenerator.getInstance("AES");
            Key clauAES = generador.generateKey();
            System.out.println("clauAES: " + clauAES);
            
            System.out.println("Creant Socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5050);
            serverSocket.bind(addr);
            
            while (true) {
                System.out.println("\nServidor escoltant... ja preparat");
                Socket newSocket = serverSocket.accept();
                
                DataOutputStream out = new DataOutputStream(newSocket.getOutputStream());
                DataInputStream in = new DataInputStream(newSocket.getInputStream());             
                
                // Llegir el nom d'usuari del client
                byte[] usuariBytes = new byte[50];
                in.read(usuariBytes);
                String usuari = new String(usuariBytes).trim();
                
                //Llegir clau pública del client
                byte[] clauPublicaBytes = new byte[455];
                in.read(clauPublicaBytes);
                
                //regenerem clau pública
                KeyFactory kf = KeyFactory.getInstance("RSA");
                X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(clauPublicaBytes);
                PublicKey clauPublicaClient = kf.generatePublic(x509Spec);
                
                // Xifrem la clau simètrica amb la clau pública del client
                Cipher cifradorRSA = Cipher.getInstance("RSA");
                cifradorRSA.init(Cipher.ENCRYPT_MODE, clauPublicaClient);
                byte[] bytesClau = clauAES.getEncoded();
                byte[] clauAESXif = cifradorRSA.doFinal(bytesClau);
                System.out.println("Xifrada  la clau simètrica, la enviem..");
                
                // Enviar la clau simètrica xifrada	    
                out.writeInt(clauAESXif.length);
                out.write(clauAESXif);
                
                //Creem un nou objecte usuari amb les dades del nou client connectat 
                Usuari u = new Usuari (usuari, newSocket, clauPublicaClient, true);
                //guardem a la base de dades que l'usuari està actiu
                dades.Connexio.actualitzarEstatUsuari(u, true);
                //Afegim el nou client connectat a la llista d'usuaris actius
                clientsConnectats.add(u);
                
                if (clientsConnectats.size() > 1) {
                    String missatgeUsuariConnectat = "novaConnexio";
                    out.writeInt(encriptarMissatge(missatgeUsuariConnectat, clauAES).length);
                    out.write(encriptarMissatge(missatgeUsuariConnectat, clauAES));
                    System.out.println(missatgeUsuariConnectat + " he entrat");
                    new EnviarMissatgeUsuariConnectat(clientsConnectats, u, clauAES).start();
                }
                
                System.out.println("Actualment hi ha connectats els usuaris següents:");
                for (Usuari client : clientsConnectats) {
                    System.out.println(client.getUsuari());
                }

                new RebreMissatges(clientsConnectats, newSocket, clauAES).start();
            }

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        } finally {
            try {
                dades.Connexio.posarTotsElsUsuarisInactius();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    static class EnviarMissatgeUsuariConnectat extends Thread {

        private List<Usuari> clientsConnectats;
        private Usuari u;
        private Key clauAES;
        
        public EnviarMissatgeUsuariConnectat(List<Usuari> clientsConnectats, Usuari u, Key clauAES) {
            this.clientsConnectats = clientsConnectats;
            this.u = u;
            this.clauAES = clauAES;
        }

        public void run() {
            
            for (Usuari client : clientsConnectats) {
                try {
                    
                    if (!client.getUsuari().equals(u.getUsuari())) {
                        DataOutputStream out = new DataOutputStream(client.getSocket().getOutputStream());

                        String missatgeUsuariConnectat = u.getUsuari();
                        out.writeInt(encriptarMissatge(missatgeUsuariConnectat, clauAES).length);
                        out.write(encriptarMissatge(missatgeUsuariConnectat, clauAES));
                        out.flush();

                        System.out.println("he enviat el missatge a tots els clients. Nou usuari connectat: " + u.getUsuari());
                    }
                    
                } catch (IOException e) {
                    System.err.println("Error a l'escriure al socket de l'usuari " + client.getUsuari());
                    clientsConnectats.remove(client);
                    dades.Connexio.posarUsuariInactiu(client.getUsuari());
                    
                    e.printStackTrace();
                }
            }
        }
    }
    
    static class EnviarMissatgeUsuariDesconnectat extends Thread {

        private List<Usuari> clientsConnectats;
        private Usuari uDesconnectat;
        private Key clauAES;
        
        public EnviarMissatgeUsuariDesconnectat(List<Usuari> clientsConnectats, Usuari uDesconnectat, Key clauAES) {
            this.clientsConnectats = clientsConnectats;
            this.uDesconnectat = uDesconnectat;
            this.clauAES = clauAES;
        }

        public void run() {
            
            for (Usuari client : clientsConnectats) {
                try {
                    if (!clientsConnectats.contains(uDesconnectat)) {
                        DataOutputStream out = new DataOutputStream(client.getSocket().getOutputStream());
                    
                        String missatgeUsuariDesconnectat = "S'ha desconnectat l'usuari " + client.getUsuari() + ".";
                        out.write(encriptarMissatge(missatgeUsuariDesconnectat, clauAES));
                        out.flush();
                    }
                    
                } catch (IOException e) {
                    System.err.println("Error a l'escriure al socket de l'usuari " + client.getUsuari());
                    clientsConnectats.remove(client);
                    dades.Connexio.posarUsuariInactiu(client.getUsuari());
                    
                    e.printStackTrace();
                }
            }
        }
    }
    
    static class RebreMissatges extends Thread {

        private List<Usuari> clientsConnectats;
        private Socket socket;
        private Key clauAES;
        
        public RebreMissatges(List<Usuari> clientsConnectats, Socket socket, Key clauAES) {
            this.clientsConnectats = clientsConnectats;
            this.socket = socket;
            this.clauAES = clauAES;
        }

        public void run() {
            try {
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());

                //Rebem el missatge del que vol fer
                byte[] missatgeOpcioBytes = new byte[in.readInt()];
                in.readFully(missatgeOpcioBytes);
                String missatgeOpcio = desencriptarMissatge(missatgeOpcioBytes, clauAES).trim();
                System.out.println(missatgeOpcio);
                
                if (missatgeOpcio.equals("desconnectar")) {
                    
                    System.out.println("He entrat actualitzarConnexions");
                    
                    byte[] usuaridesconnectatBytes = new byte[in.readInt()];
                    in.readFully(usuaridesconnectatBytes);
                    String usuaridesconnectat = desencriptarMissatge(usuaridesconnectatBytes, clauAES).trim();
                    
                    //treiem l'usuari que s'ha desconnectat de la llista i actualitzem l'estat a la BBDD
                    Usuari uDesconnectat = dades.Connexio.obtenirUsuariPerId(usuaridesconnectat);
                    clientsConnectats.remove(uDesconnectat);
                    dades.Connexio.posarUsuariInactiu(usuaridesconnectat);
                    
                    
                    for (Usuari usuari : clientsConnectats) {
                        
                        if (!usuari.getUsuari().equals(usuaridesconnectat)) {
                            try {
                                Socket socketUsuariDesti = usuari.getSocket();
                                out = new DataOutputStream(socketUsuariDesti.getOutputStream());

                                out.writeInt(encriptarMissatge("DesconneccioUsuaris", clauAES).length);
                                out.write(encriptarMissatge("DesconneccioUsuaris", clauAES));
                                
                                out.writeInt(encriptarMissatge(usuaridesconnectat, clauAES).length);
                                out.write(encriptarMissatge(usuaridesconnectat, clauAES));
                                
                                out.flush();

                                System.out.println("he enviat el missatge de l'usuari desconnectat");
                            
                            } catch (IOException e) {
                                System.err.println("Error a l'enviar el missatge a l'usuari: " + usuari.getUsuari());
                                e.printStackTrace();
                            }
                        }
                    }
                }
                
                //Rebem el contingut del missatge que l'usuari vol enviar
                byte[] missatgeClientBytes = new byte[in.readInt()];
                in.readFully(missatgeClientBytes);
                String missatgeClient = desencriptarMissatge(missatgeClientBytes, clauAES).trim();
                System.out.println("He rebut el missatge: " + missatgeClient);
                
                byte[] usuariRemitentBytes = new byte[in.readInt()];
                in.readFully(usuariRemitentBytes);
                String usuariRemitent = desencriptarMissatge(usuariRemitentBytes, clauAES).trim();
                        
                System.out.println("He rebut el missatge de: " + usuariRemitent);
                
                if (missatgeOpcio.equals("MissatgeGrupal")) {
                    System.out.println("He entrat al condicionat grup");
                    
                    for (Usuari usuari : clientsConnectats) {
                        try {
                            Socket socketUsuariDesti = usuari.getSocket();
                            out = new DataOutputStream(socketUsuariDesti.getOutputStream());

                            out.writeInt(encriptarMissatge(missatgeClient, clauAES).length);
                            out.write(encriptarMissatge(missatgeClient, clauAES));
                            
                            out.writeInt(encriptarMissatge(usuariRemitent, clauAES).length);
                            out.write(encriptarMissatge(usuariRemitent, clauAES));
                            
                            out.flush();
                            
                            System.out.println("he enviat el missatge a tots els usuaris: " + missatgeClient);

                            System.out.println(usuari.getUsuari());
                            
                        } catch (IOException e) {
                            System.err.println("Error a l'enviar el missatge a l'usuari: " + usuari.getUsuari());
                            e.printStackTrace();
                        }
                    }
                
                } else if (missatgeOpcio.equals("MissatgePrivat")) {
                    
                    System.out.println("He entrat al condicionat privat");
                    
                    byte[] usuariDestinatariBytes = new byte[in.readInt()];
                    in.readFully(usuariDestinatariBytes);
                    String usuariDestinatari = desencriptarMissatge(usuariDestinatariBytes, clauAES).trim();
                    
                    System.out.println("Envio el missatge a: " + usuariDestinatari);
                    
                    for (Usuari usuari : clientsConnectats) {
                        
                        if (usuari.getUsuari().equals(usuariDestinatari) || usuari.getUsuari().equals(usuariRemitent)) {
                            try {
                                Socket socketUsuariDesti = usuari.getSocket();
                                out = new DataOutputStream(socketUsuariDesti.getOutputStream());

                                out.writeInt(encriptarMissatge(missatgeClient, clauAES).length);
                                out.write(encriptarMissatge(missatgeClient, clauAES));
                                
                                out.writeInt(encriptarMissatge(new String(usuariRemitentBytes), clauAES).length);
                                out.write(encriptarMissatge(new String(usuariRemitentBytes), clauAES));
                                
                                out.flush();

                                System.out.println("he enviat el missatge: " + missatgeClient + " a l'usuari " + usuari.getUsuari());
                            
                            } catch (IOException e) {
                                System.err.println("Error a l'enviar el missatge a l'usuari: " + usuari.getUsuari());
                                e.printStackTrace();
                            }
                        }
                    }
                }
                
                new RebreMissatges(clientsConnectats, socket, clauAES).start();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static byte[] encriptarMissatge(String missatge, Key clauAES){
        try {
            Cipher cifradorAES = Cipher.getInstance("AES");
            cifradorAES.init(Cipher.ENCRYPT_MODE, clauAES);
            byte[] mXifAES = cifradorAES.doFinal(missatge.getBytes());
            
            System.out.println(mXifAES);
            
            return mXifAES;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static String desencriptarMissatge(byte[] missatgeXifAES, Key clauAES){

        try {
            Cipher AESCipher = Cipher.getInstance("AES");
            AESCipher.init(Cipher.DECRYPT_MODE, clauAES);
            byte[] missatgeDesxif = AESCipher.doFinal(missatgeXifAES);
            
            String missatgeDesxifrat = new String(missatgeDesxif);
            System.out.println("Missatge desxifrat: " + missatgeDesxifrat);
            
            return missatgeDesxifrat;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;  
    }  
}

