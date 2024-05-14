
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
                        if (evt.getPropertyName().equals("calendar")) {
                            //Netegem el contingut
                            principalFrame.chat.chat_Body.clearChat();
                                    
                            Calendar selectedDate = (Calendar) evt.getNewValue();
                            
                            Date dataInput = selectedDate.getTime();
                            System.out.println("Data seleccionada: " + dataInput);
                            
                            List<Missatges> llistaMissatges = dades.Connexio.obtenirMissatgesPerData(dataInput);
                            
                            System.out.println("llista size: " + llistaMissatges.size());
                            
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            String dataFormatejadaToString = formatter.format(dataInput);
                            principalFrame.chat.chat_Body.addData(dataFormatejadaToString);
                            
                            for (Missatges missatge : llistaMissatges) {
                                Usuari usuari = missatge.getIdUsuari();
                                String missatgeContingut = missatge.getMissatge();
                                
                                LocalDateTime dataMissatge = missatge.getData();
                                LocalTime horaMissatge = dataMissatge.toLocalTime();

                                if (usuari.getUsuari().equals(txtUsuariConnectat)) {
                                    PublicEvent.getInstance().getEventChat().sendMessage(missatgeContingut, horaMissatge);
                                } else {
                                    PublicEvent.getInstance().getEventChat().receiveMessage(missatgeContingut, usuari, horaMissatge);
                                }
                            }
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
            String sala = principalFrame.chat.chat_Title.getUserName();
            String estat = principalFrame.chat.chat_Title.getEstat();
            
            try {
                totalUsuarisBBDD = dades.Connexio.obtenirLlistatUsuaris();
                
                DataInputStream in = new DataInputStream(socket.getInputStream());
                
                byte[] bytesMissatge = new byte[in.readInt()];
                in.readFully(bytesMissatge);
                String missatge = new String(bytesMissatge).trim();
                
                System.out.println("Missatge rebut: " + missatge);
                
                if (missatge.equals("novaConnexio") || totalUsuarisBBDD.contains(missatge)) {
                    new RebreEstatConnexions(socket, missatge).start();
                    System.out.println("he entrat enviar missatge");
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
    
    static class RebreEstatConnexions extends Thread {
        private Socket socket;
        private String usuariConnectat;
        
        public RebreEstatConnexions(Socket socket, String usuariConnectat) {
           this.socket = socket;
           this.usuariConnectat = usuariConnectat;
        }

        public void run() {

            try {
                System.out.println("rebo estat ");
            
                DataInputStream in = new DataInputStream(socket.getInputStream());

                String sala = principalFrame.chat.chat_Title.getUserName();
                String estat = principalFrame.chat.chat_Title.getEstat();
                
                if (sala.equals("Grup") && !estat.equals("Historial")) {
                    PublicEvent.getInstance().getEventChat().userConnected(usuariConnectat);
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }   
}
