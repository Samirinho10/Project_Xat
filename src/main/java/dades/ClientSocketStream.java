package dades;

import static componentsExterns.Chat_Bottom.txt;
import events.PublicEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import model.Missatges;
import model.Usuari;
import vista.Principal;
import vista.Sessio;
import static componentsExterns.Chat_Bottom.botoEnviar;
import static dades.Connexio.obtenirUsuariPerId;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.InvalidKeyException;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class ClientSocketStream {

    public static String txtUsuariConnectat;
    public static Principal principalFrame;
    public static List<String> totalUsuarisBBDD;

    public static String getTxtUsuariConnectat() {
        return txtUsuariConnectat;
    }
<<<<<<< Updated upstream
    
    public static void main(String[] args){     
    
        try {
            
=======

    public static void main(String[] args) {

        try {
            // Creant Socket client per connectar-nos al servidor
            String host = "192.168.1.72";
            int port = 7878;

            Socket socket = new Socket(host, port);

            // Obtenim els fluxos d'entrada i sortida del socket
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

>>>>>>> Stashed changes
            //Generar doble clau	    
            KeyPairGenerator generadorRSA = KeyPairGenerator.getInstance("RSA");
            KeyPair clauRSA = generadorRSA.genKeyPair();
            System.out.println("Generada la clau asimètrica.");
            byte[] bytesClauPublica = clauRSA.getPublic().getEncoded();

            // Mostrar pantalla inici de sessió
            Sessio sessioFrame = new Sessio();
            sessioFrame.setVisible(true);
            
            // Esperem a que es tanqui la pantalla per assegurar que l'usuari anota les dades
            while(sessioFrame.isVisible()) {
                Thread.sleep(100);
            }
            
            // Creant Socket client per connectar-nos al servidor
            String host = sessioFrame.txtServidor.getText();
            int port = 7878;

            Socket socket = new Socket(host, port);

            // Obtenim els fluxos d'entrada i sortida del socket
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            //Si les credencials són correctes, agafem les dades de l'usuari i obrim la pantalla Principal del xat
            if (sessioFrame.iniciSessio()) {
                txtUsuariConnectat = sessioFrame.txtUsuari.getText();
                System.out.println("Soc " + txtUsuariConnectat);
                
                principalFrame = new Principal();
                principalFrame.setVisible(true);
                
                //Enviem el nostre usuari al servidor
                out.write(txtUsuariConnectat.getBytes());
                
                //Enviem la nostra clau pública
                out.write(bytesClauPublica);
                
                //Rebre clau simètrica xifrada amb la pública del client
                //Llegir del socol i desxifrar amb la meva privada 
                byte[] bytesClave = new byte[in.readInt()];
                in.readFully(bytesClave);
                System.out.println("Rebuda clau simètrica xifrada...");

                // Desxifrar clau simètrica
                Cipher cifradorRSA = Cipher.getInstance("RSA");
                cifradorRSA.init(Cipher.DECRYPT_MODE, clauRSA.getPrivate());

                byte[] bytesClauAES = cifradorRSA.doFinal(bytesClave);
                System.out.println("Ja tenim la clau simètrica AES");

                // Regenerar la clau AES per poder xifrar i desxifrar
                Key clauAES = new SecretKeySpec(bytesClauAES, 0, bytesClauAES.length, "AES");

                //Action listener per enviar un missatge al fer clic al botoEnviar
                botoEnviar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        enviarMissatge(out, clauAES);
                    }
                });

                //Key Listener per enviar el missatge al premer la tecla INTRO
                txt.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent ke) {
                        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                            ke.consume(); //evitem que es faci un salt de línia
                            enviarMissatge(out, clauAES);
                        }
                    }
                });

                //mostrar historial de missatges grupals
                Principal.calendari.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        String sala = principalFrame.chat.chat_Title.getUserName();

                        if (evt.getPropertyName().equals("calendar")) {
                            //Netegem el contingut
                            principalFrame.chat.chat_Body.clearChat();

                            Calendar selectedDate = (Calendar) evt.getNewValue();

                            Date dataInput = selectedDate.getTime();
                            System.out.println("Data seleccionada: " + dataInput);

                            List<Missatges> llistaMissatges = dades.Connexio.obtenirMissatgesPerData(dataInput);

                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            String dataFormatejadaToString = formatter.format(dataInput);
                            principalFrame.chat.chat_Body.addData(dataFormatejadaToString);

                            System.out.println("llista size: " + llistaMissatges.size());
                            if (llistaMissatges.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "No hi ha missatges del dia " + dataFormatejadaToString + " a la base de dades.",
                                        "Informació", JOptionPane.INFORMATION_MESSAGE);
                            }

                            for (Missatges missatge : llistaMissatges) {
                                Usuari usuari = missatge.getIdUsuari();
                                String missatgeContingut = missatge.getMissatge();

                                LocalDateTime dataMissatge = missatge.getData();
                                LocalTime horaMissatge = dataMissatge.toLocalTime();

                                if (usuari.getUsuari().equals(txtUsuariConnectat) && !sala.equals("Benvingut/da")) {
                                    PublicEvent.getInstance().getEventChat().sendMessage(missatgeContingut, horaMissatge);
                                } else {
                                    PublicEvent.getInstance().getEventChat().receiveMessage(missatgeContingut, usuari, horaMissatge);
                                }
                            }
                        }
                    }
                });

                //Que fer si em desconnecto?
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    @Override
                    public void run() {

                        try {
                            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                            out.writeInt(encriptarMissatge("desconnectar", clauAES).length);
                            out.write(encriptarMissatge("desconnectar", clauAES));

                            out.writeInt(encriptarMissatge(txtUsuariConnectat, clauAES).length);
                            out.write(encriptarMissatge(txtUsuariConnectat, clauAES));

                            System.out.println("m'he desconnectat");

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                while (true) {

                    if (in.available() > 0) {
                        new RebreMissatges(socket, clauAES).start();
                        System.out.println("he entrat enviar missatge");
                    }

                    Thread.sleep(100);
                }
            }

        } catch (IOException | InterruptedException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    static class RebreMissatges extends Thread {

        private Socket socket;
        private Key clauAES;

        public RebreMissatges(Socket socket, Key clauAES) {
            this.socket = socket;
            this.clauAES = clauAES;
        }

        public void run() {
            System.out.println("rebo missatges");

            try {
                totalUsuarisBBDD = dades.Connexio.obtenirLlistatUsuaris();

                DataInputStream in = new DataInputStream(socket.getInputStream());

                byte[] bytesMissatge = new byte[in.readInt()];
                in.readFully(bytesMissatge);
                String missatge = desencriptarMissatge(bytesMissatge, clauAES).trim();

                System.out.println("Missatge rebut: " + missatge);

                if (missatge.equals("novaConnexio") || totalUsuarisBBDD.contains(missatge)) {
                    String sala = principalFrame.chat.chat_Title.getUserName();
                    String estat = principalFrame.chat.chat_Title.getEstat();

                    if (sala.equals("Grup") && !estat.equals("Historial")) {
                        PublicEvent.getInstance().getEventChat().userConnected(missatge);
                    }

                    return;
                }

                if (missatge.equals("DesconneccioUsuaris")) {
                    String sala = principalFrame.chat.chat_Title.getUserName();
                    String estat = principalFrame.chat.chat_Title.getEstat();

                    byte[] bytesUsuariDesconnectat = new byte[in.readInt()];
                    in.readFully(bytesUsuariDesconnectat);
                    String usuariDesconnectat = desencriptarMissatge(bytesUsuariDesconnectat, clauAES).trim();

                    if (sala.equals("Grup") && !estat.equals("Historial")) {
                        PublicEvent.getInstance().getEventChat().userDisconnected(usuariDesconnectat);
                    }

                    return;
                }

                byte[] bytesUsuariRemitent = new byte[in.readInt()];
                in.readFully(bytesUsuariRemitent);
                String usuariRemitent = desencriptarMissatge(bytesUsuariRemitent, clauAES).trim();
                System.out.println("Missatge rebut de: " + usuariRemitent);

                Usuari u = obtenirUsuariPerId(usuariRemitent);

                if (u != null && u.getUsuari().equals(txtUsuariConnectat)) {
                    PublicEvent.getInstance().getEventChat().sendMessage(missatge, LocalTime.now());
                } else if (u != null) {
                    PublicEvent.getInstance().getEventChat().receiveMessage(missatge, u, LocalTime.now());
                }

                new RebreMissatges(socket, clauAES).start();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void enviarMissatge(DataOutputStream out, Key clauAES) {

        try {
            String text = txt.getText().trim();
            Usuari usuari = Connexio.obtenirUsuariPerId(txtUsuariConnectat);
            String sala = principalFrame.chat.chat_Title.getUserName();
            String estat = principalFrame.chat.chat_Title.getEstat();

            if (estat.equals("Estàs en línia")) {
                JOptionPane.showMessageDialog(null, "Hola " + txtUsuariConnectat + ". "
                        + "Selecciona una sala per començar a xatejar.",
                        "Informació", JOptionPane.INFORMATION_MESSAGE);

                return;
            }

            Missatges missatge = new Missatges(usuari, sala, text);

            if (missatge.getIdSala().equals("Grup") && !estat.equals("Historial")) {
                if (!text.equals("")) {
                    System.out.println("hola ho guardo a la base de dades");

                    out.writeInt(encriptarMissatge("MissatgeGrupal", clauAES).length);
                    out.write(encriptarMissatge("MissatgeGrupal", clauAES));

                    out.writeInt(encriptarMissatge(text, clauAES).length);
                    out.write(encriptarMissatge(text, clauAES));

                    String usuariRemitent = txtUsuariConnectat;
                    out.writeInt(encriptarMissatge(usuariRemitent, clauAES).length);
                    out.write(encriptarMissatge(usuariRemitent, clauAES));

                    Connexio.guardarMissatges(missatge);

                    txt.setText("");
                    txt.grabFocus();

                } else {
                    txt.grabFocus();
                }
            } else {
                System.out.println("he entrat al else de missatge privat");

                if (!text.equals("")) {
                    out.writeInt(encriptarMissatge("MissatgePrivat", clauAES).length);
                    out.write(encriptarMissatge("MissatgePrivat", clauAES));

                    out.writeInt(encriptarMissatge(text, clauAES).length);
                    out.write(encriptarMissatge(text, clauAES));

                    String URemitent = txtUsuariConnectat;
                    out.writeInt(encriptarMissatge(URemitent, clauAES).length);
                    out.write(encriptarMissatge(URemitent, clauAES));

                    Usuari Udestinatari = obtenirUsuariPerId(sala);
                    out.writeInt(encriptarMissatge(Udestinatari.getUsuari(), clauAES).length);
                    out.write(encriptarMissatge(Udestinatari.getUsuari(), clauAES));

                    txt.setText("");
                    txt.grabFocus();

                    System.out.println("soc el client, ho he fet tot");

                } else {
                    txt.grabFocus();
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static byte[] encriptarMissatge(String missatge, Key clauAES) {
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

    public static String desencriptarMissatge(byte[] missatgeXifAES, Key clauAES) {

        try {
            Cipher AESCipher = Cipher.getInstance("AES");
            AESCipher.init(Cipher.DECRYPT_MODE, clauAES);
            byte[] missatgeDesxif = AESCipher.doFinal(missatgeXifAES);

            String missatgeDesxifrat = new String(missatgeDesxif);
            System.out.println("\nMissatge desxifrat: " + missatgeDesxifrat);

            return missatgeDesxifrat;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
