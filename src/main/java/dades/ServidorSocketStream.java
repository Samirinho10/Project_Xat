
package dades;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuari;

public class ServidorSocketStream {
    
    private static List<Usuari> clientsConnectats = new ArrayList<>();
    
    public static void main(String[] args) {

        try {
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
                
                Usuari u = new Usuari (usuari, newSocket, clauPublicaClient);
                clientsConnectats.add(u);
                
//                if (clientsConnectats.size() > 1) {
//                    new EnviarMissatgeUsuariConnectat(clientsConnectats, u).start();
//                    new EnviarMissatgeUsuariDesconnectat(clientsConnectats, u).start();
//                }
                
                System.out.println("Actualment hi ha connectats els usuaris següents:");
                for (Usuari client : clientsConnectats) {
                    System.out.println(client.getUsuari());
                }

                new RebreMissatges(clientsConnectats, newSocket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServidorSocketStream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(ServidorSocketStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static class EnviarMissatgeUsuariConnectat extends Thread {

        private List<Usuari> clientsConnectats;
        private Usuari u;
        
        public EnviarMissatgeUsuariConnectat(List<Usuari> clientsConnectats, Usuari u) {
            this.clientsConnectats = clientsConnectats;
            this.u = u;
        }

        public void run() {
            
            for (Usuari client : clientsConnectats) {
                try {
                    DataOutputStream out = new DataOutputStream(client.getSocket().getOutputStream());
                    
                    String missatgeUsuariConnectat = "S'ha connectat l'usuari " + client.getUsuari() + ".";
                    out.write(missatgeUsuariConnectat.getBytes());
                    out.flush();
                    
                } catch (IOException e) {
                    System.err.println("Error a l'escriure al socket de l'usuari " + client.getUsuari());
                    clientsConnectats.remove(client);
                    
                    e.printStackTrace();
                }
            }
        }
    }
    
    static class EnviarMissatgeUsuariDesconnectat extends Thread {

        private List<Usuari> clientsConnectats;
        private Usuari uDesconnectat;
        
        public EnviarMissatgeUsuariDesconnectat(List<Usuari> clientsConnectats, Usuari uDesconnectat) {
            this.clientsConnectats = clientsConnectats;
            this.uDesconnectat = uDesconnectat;
        }

        public void run() {
            
            for (Usuari client : clientsConnectats) {
                try {
                    if (!clientsConnectats.contains(uDesconnectat)) {
                        DataOutputStream out = new DataOutputStream(client.getSocket().getOutputStream());
                    
                        String missatgeUsuariDesconnectat = "S'ha desconnectat l'usuari " + client.getUsuari() + ".";
                        out.write(missatgeUsuariDesconnectat.getBytes());
                        out.flush();
                    }
                    
                } catch (IOException e) {
                    System.err.println("Error a l'escriure al socket de l'usuari " + client.getUsuari());
                    clientsConnectats.remove(client);
                    
                    e.printStackTrace();
                }
            }
        }
    }
    
    static class RebreMissatges extends Thread {

        private List<Usuari> clientsConnectats;
        private Socket socket;
        
        public RebreMissatges(List<Usuari> clientsConnectats, Socket socket) {
            this.clientsConnectats = clientsConnectats;
            this.socket = socket;
        }

        public void run() {
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());

                //Rebem el missatge del que vol fer
                byte[] missatgeOpcioBytes = new byte[in.readInt()];
                in.readFully(missatgeOpcioBytes);
                String missatgeOpcio = new String(missatgeOpcioBytes).trim();
                System.out.println(missatgeOpcio);
                
                //Rebem el contingut del missatge que l'usuari vol enviar
                byte[] missatgeClient = new byte[in.readInt()];
                in.readFully(missatgeClient);
                System.out.println("He rebut el missatge: " + new String(missatgeClient));
                
                byte[] usuariRemitentBytes = new byte[in.readInt()];
                in.readFully(usuariRemitentBytes);
                String usuariRemitent = new String(usuariRemitentBytes).trim();
                        
                System.out.println("He rebut el missatge de: " + usuariRemitent);
                
                if (missatgeOpcio.equals("MissatgeGrupal")) {
                    System.out.println("He entrat al condicionat grup");
                    
                    for (Usuari usuari : clientsConnectats) {
                        try {
                            Socket socketUsuariDesti = usuari.getSocket();
                            DataOutputStream out = new DataOutputStream(socketUsuariDesti.getOutputStream());

                            out.writeInt(missatgeClient.length);
                            out.write(missatgeClient);
                            
                            out.writeInt(usuariRemitentBytes.length);
                            out.write(usuariRemitentBytes);

                            out.flush();
                            
                            System.out.println("he enviat el missatge a tots els usuaris: " + new String(missatgeClient));

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
                    String usuariDestinatari = new String(usuariDestinatariBytes).trim();
                    
                    System.out.println("Envio el missatge a: " + usuariDestinatari);
                    
                    for (Usuari usuari : clientsConnectats) {
                        
                        if (usuari.getUsuari().equals(usuariDestinatari) || usuari.getUsuari().equals(usuariRemitent)) {
                            try {
                                Socket socketUsuariDesti = usuari.getSocket();
                                DataOutputStream out = new DataOutputStream(socketUsuariDesti.getOutputStream());

                                out.writeInt(missatgeClient.length);
                                out.write(missatgeClient);

                                out.writeInt(usuariRemitentBytes.length);
                                out.write(usuariRemitentBytes);

                                out.flush();

                                System.out.println("he enviat el missatge: " + new String(missatgeClient) + " a l'usuari " + usuari.getUsuari());
                                System.out.println(" hola hola");
                            } catch (IOException e) {
                                System.err.println("Error a l'enviar el missatge a l'usuari: " + usuari.getUsuari());
                                e.printStackTrace();
                            }
                        }
                    }
                    
                    
                } else if (missatgeOpcio.equals("actualitzarUsuarisConnectats")) {
                    
                } else if (missatgeOpcio.equals("desconnectar")) {
                    
                }
                
                new RebreMissatges(clientsConnectats, socket).start();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }   
}

//beritja.cicles@gmail.com

