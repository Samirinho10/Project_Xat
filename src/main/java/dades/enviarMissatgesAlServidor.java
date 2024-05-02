
package dades;

import componentsExterns.Chat_Bottom;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class enviarMissatgesAlServidor extends Thread {

    private Socket newSocket = null;
    private final boolean clicEnBotonCmd;

    public enviarMissatgesAlServidor(Socket cs, boolean clicEnBotonCmd) {
        newSocket = cs;
        this.clicEnBotonCmd = clicEnBotonCmd;
    }
    
     public void run() {
        try {
            Thread.sleep(10000);
            if (clicEnBotonCmd) {
                OutputStream os = newSocket.getOutputStream();
                String missatge = Chat_Bottom.txt.getText();
                enviarMissatge(os, missatge);
            }
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