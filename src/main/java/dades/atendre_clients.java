
package dades;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class atendre_clients extends Thread {

    private Socket newSocket = null;

    public atendre_clients (Socket cs) {
        newSocket = cs;
    }
    
    public void run() {
        
        try {
            Thread.sleep(10000);
            
            // atenc al client
            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(atendre_clients.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
