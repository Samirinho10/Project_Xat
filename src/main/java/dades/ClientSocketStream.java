
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.crypto.Cipher;
import javax.swing.JOptionPane;


public class ClientSocketStream {
    public static String txtUsuariConnectat;
    public static Principal principalFrame;
    public static List<String> totalUsuarisBBDD;
    
    public static String getTxtUsuariConnectat() {
        return txtUsuariConnectat;
    }
    
    public static void main(String[] args){
    
        try {
            
            // Creant Socket client per connectar-nos al servidor
            String host = "localhost";
            int port = 5050;

            Socket socket = new Socket(host, port);

            // Obtenim els fluxos d'entrada i sortida del socket
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

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

            if (sessioFrame.iniciSessio()) {
                txtUsuariConnectat = sessioFrame.txtUsuari.getText();
                System.out.println("Soc " + txtUsuariConnectat);
                
                principalFrame = new Principal();
                principalFrame.setVisible(true);
                
                //Enviem el nostre usuari al servidor
                out.write(txtUsuariConnectat.getBytes());
                
                //Enviem la nostra clau pública
                out.write(bytesClauPublica);
                
                //Action listener de quan envio un missatge
                botoEnviar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
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
                                    
                                    out.writeInt("MissatgeGrupal".getBytes().length);
                                    out.write("MissatgeGrupal".getBytes());
                                    
                                    out.writeInt(text.getBytes().length);
                                    out.write(text.getBytes());
                                    
                                    String usuariRemitent = txtUsuariConnectat;
                                    out.writeInt(usuariRemitent.getBytes().length);
                                    out.write(usuariRemitent.getBytes());
                                    
                                    Connexio.guardarMissatges(missatge);

                                    txt.setText("");
                                    txt.grabFocus();

                                } else {
                                    txt.grabFocus();
                                }
                            } else {
                                System.out.println("he entrat al else de missatge privat");
                                
                                if (!text.equals("")) {
                                    out.writeInt("MissatgePrivat".getBytes().length);
                                    out.write("MissatgePrivat".getBytes());
                                    
                                    out.writeInt(text.getBytes().length);
                                    out.write(text.getBytes());
                                    
                                    String URemitent = txtUsuariConnectat;
                                    out.writeInt(URemitent.getBytes().length);
                                    out.write(URemitent.getBytes());

                                    Usuari Udestinatari = obtenirUsuariPerId(sala);
                                    out.writeInt(Udestinatari.getUsuari().getBytes().length);
                                    out.write(Udestinatari.getUsuari().getBytes());
                                    
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
                            
                            out.writeInt("desconnectar".getBytes().length);
                            out.write("desconnectar".getBytes());
                            
                            out.writeInt(txtUsuariConnectat.getBytes().length);
                            out.write(txtUsuariConnectat.getBytes());
                            
                            System.out.println("mhe desconnectat");
                            
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                
                while (true) {
                    
                    if (in.available() > 0) {
                        new RebreMissatges(socket).start();
                        System.out.println("he entrat enviar missatge");
                    }

                    Thread.sleep(100);
                }  
            }

        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
    
    static class RebreMissatges extends Thread {
        private Socket socket;
        
        public RebreMissatges(Socket socket) {
           this.socket = socket;
        }

        public void run() {
            System.out.println("rebo missatges");
            
            try {
                totalUsuarisBBDD = dades.Connexio.obtenirLlistatUsuaris();
                
                DataInputStream in = new DataInputStream(socket.getInputStream());
                
                byte[] bytesMissatge = new byte[in.readInt()];
                in.readFully(bytesMissatge);
                String missatge = new String(bytesMissatge).trim();
                
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
                    String usuariDesconnectat = new String(bytesUsuariDesconnectat).trim();

                    if (sala.equals("Grup") && !estat.equals("Historial")) {
                        PublicEvent.getInstance().getEventChat().userDisconnected(usuariDesconnectat);
                    }
                    
                    return;
                }
                
                byte[] bytesUsuariRemitent = new byte[in.readInt()];
                in.readFully(bytesUsuariRemitent);
                System.out.println("Missatge rebut de: " + new String(bytesUsuariRemitent));
                
                Usuari u = obtenirUsuariPerId(new String(bytesUsuariRemitent));
                
                if (u.getUsuari().equals(txtUsuariConnectat)) {
                    PublicEvent.getInstance().getEventChat().sendMessage(new String(bytesMissatge), LocalTime.now());
                } else {
                    PublicEvent.getInstance().getEventChat().receiveMessage(new String(bytesMissatge), u, LocalTime.now());
                }
                
                new RebreMissatges(socket).start();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
        }
    }
    
    public static byte[] encriptarMissatge(String missatge, KeyPair parellDeClaus){
        try {
            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.ENCRYPT_MODE, parellDeClaus.getPrivate());
            byte[] mXif = rsaCipher.doFinal(missatge.getBytes());
            
            System.out.println(mXif);
            
            return mXif;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static String desencriptarMissatge(byte[] missatgeXifrat, KeyPair parellDeClaus){

        try {
            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.DECRYPT_MODE, parellDeClaus.getPublic());
            byte[] mDes = rsaCipher.doFinal(missatgeXifrat);
            
            String missatgeDesxifrat = new String(mDes);
            System.out.println(mDes);
            
            return missatgeDesxifrat;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;  
    }  
}
