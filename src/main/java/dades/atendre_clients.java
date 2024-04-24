
package dades;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class atendre_clients extends Thread {

    private Socket newSocket = null;
    public int comptador = 1;

    public atendre_clients (Socket cs, int comptador) {
        newSocket = cs;
        this.comptador = comptador;
    }
    
    public void run() {
        
        try {
            Thread.sleep(10000);
            
            // atenc al client
            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

            byte[] usuariConnectat = new byte[50];	
            is.read(usuariConnectat);

            System.out.println("S'ha connectat l'usuari " + new String(usuariConnectat));

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(atendre_clients.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
