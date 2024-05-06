
package dades;

import componentsExterns.Chat_Bottom;
import static componentsExterns.Chat_Bottom.cmd;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            Thread.sleep(100);
            
            // atenc al client
            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();
            
            cmd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        String missatge = Chat_Bottom.txt.getText();
                        os.write(missatge.getBytes());
                        System.out.println("missatge enviat: " + missatge);
                    } catch (IOException ex) {
                        Logger.getLogger(enviarMissatgesAlServidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(atendre_clients.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}