
package dades;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class enviarMissatgesAlServidor extends Thread {

    private Socket newSocket = null;

    public enviarMissatgesAlServidor (Socket cs) {
        newSocket = cs;
    }
    
    public void run() {
        
        try {
            Thread.sleep(10000);
            
            OutputStream os = newSocket.getOutputStream();
            enviarMissatge(os, "Aquest és un missatge de prova al servidor.");

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(atendre_clients.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    private void enviarMissatge(OutputStream os, String missatge) throws IOException {
        os.write(missatge.getBytes());
    }
}