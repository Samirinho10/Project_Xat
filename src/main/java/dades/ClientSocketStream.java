
package dades;

import static componentsExterns.Chat_Bottom.txt;
import componentsExterns.PublicEvent;
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


public class ClientSocketStream {
    public static String txtUsuariConnectat;

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
                
                Principal principalFrame = new Principal();
                principalFrame.setVisible(true);
                
                //Enviem el nostre usuari al servidor
                out.write(txtUsuariConnectat.getBytes());
                
                //Enviem la nostra clau pública
                out.write(bytesClauPublica);
                
                
                while (true) {

                    botoEnviar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            try {
                                String text = txt.getText().trim();
                                Usuari usuari = Connexio.obtenirUsuariPerId(txtUsuariConnectat);
                                String sala = principalFrame.chat.chat_Title.getUserName();

                                Missatges missatge = new Missatges(usuari, sala, text);

                                if (missatge.getIdSala().equals("Grup")) {
                                    if (!text.equals("")) {
                                        System.out.println("hola ho guardo a la base de dades");
                                        out.writeInt("MissatgeGrupal".getBytes().length);
                                        out.write("MissatgeGrupal".getBytes());

                                        out.writeInt(text.getBytes().length);
                                        out.write(text.getBytes());

                                        Connexio.guardarMissatges(missatge);
                                        PublicEvent.getInstance().getEventChat().sendMessage(text);

                                        txt.setText("");
                                        txt.grabFocus();

                                    } else {
                                        txt.grabFocus();
                                    }
                                } else {
                                    out.write("MissatgePrivat".getBytes());
                                    out.writeInt(text.getBytes().length);
                                    out.write(text.getBytes());

                                    PublicEvent.getInstance().getEventChat().sendMessage(text);

                                    txt.setText("");
                                    txt.grabFocus();
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                    principalFrame.BtnXat.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            System.out.println("Recargant llista usuaris connectats");

                        }
                    }); 
                
                    //fil rebre missatges del servidor
                    new RebreMissatges(socket).start();
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
            try {
                
                DataInputStream in = new DataInputStream(socket.getInputStream());
                
                byte[] bytesMissatge = new byte[in.readInt()];
                in.readFully(bytesMissatge);
                System.out.println("Missatge rebut: " + new String(bytesMissatge));
                
                //new RebreMissatges(socket).start();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
        }
    }   
}
