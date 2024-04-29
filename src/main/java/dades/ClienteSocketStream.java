package dades;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import vista.Sessio;


public class ClienteSocketStream {
	
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
                String txtUsuari = sessioFrame.txtUsuari.getText();
                System.out.println("Soc " + txtUsuari);
                
                //Enviem el nostre usuari al servidor
                os.write(txtUsuari.getBytes());
                
                //Esperem la llista d'usuaris connectats
                byte[] llistaUsuarisConnectatsBytes = new byte[1024];
                int bytesLlegits = is.read(llistaUsuarisConnectatsBytes);
                String llistaUsuarisString = new String(llistaUsuarisConnectatsBytes, 0, bytesLlegits);
                String[] llistaUsuaris = llistaUsuarisString.split(",");
                
                System.out.println("Usuaris actualment connectats:");
                for (String usuari : llistaUsuaris) {
                    System.out.println(usuari);
                }
            }
        
            cs.close();
			
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
