package dades;

import componentsExterns.Chat_Bottom;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.Principal;
import vista.Sessio;


public class ClientSocketStream {
    public static String txtUsuariConnectat;
    public static Scanner teclat = new Scanner(System.in);
	
    public static void main(String[] args){
    
        try {
            // Creant Socket client per connectar-nos al servidor
            Socket cs = new Socket("localhost", 5050);

            // Obtenim els fluxos d'entrada i sortida del socket
            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();

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
                os.write(txtUsuariConnectat.getBytes());
                
                //Esperem la llista d'usuaris connectats
                byte[] llistaUsuarisConnectatsBytes = new byte[1024];
                int bytesLlegits = is.read(llistaUsuarisConnectatsBytes);
                String llistaUsuarisString = new String(llistaUsuarisConnectatsBytes, 0, bytesLlegits);
                String[] llistaUsuaris = llistaUsuarisString.split(",");
                
                System.out.println("Usuaris actualment connectats:");
                for (String usuari : llistaUsuaris) {
                    System.out.println(usuari);
                }

                // fil: llegir missatges del servidor
                new RebreMissatges(cs, is).start();

                // fil: enviar missatges al servidor
                new EscriureMissatges(os, teclat).start();
            }
			
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class RebreMissatges extends Thread {
        private Socket socket;
        private InputStream is;

        public RebreMissatges(Socket socket, InputStream is) {
            this.socket = socket;
            this.is = is;
        }

        public void run() {
            try {
                Scanner scanner = new Scanner(is);
                while (true) {
                    if (scanner.hasNextLine()) {
                        String missatge = scanner.nextLine();
                        System.out.println("Missatge rebut del servidor: " + missatge);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class EscriureMissatges extends Thread {
        private OutputStream os;
        private Scanner scanner;

        public EscriureMissatges(OutputStream os, Scanner scanner) {
            this.os = os;
            this.scanner = scanner;
        }

        public void run() {
            try {
                while (true) {
                    
                    if (scanner.hasNextLine()) {
                        String missatge = scanner.nextLine();
                        os.write(missatge.getBytes());
                        os.write('\n');
                    }
                    
                    Thread.sleep(100);
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientSocketStream.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}