
package provesFils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.Usuari;

public class ServidorSocketStream {
    
    private static List<Usuari> clientsConnectats = new ArrayList<>();
    
    public static void main(String[] args) {

        try {
            System.out.println("Creant Socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5050);
            serverSocket.bind(addr);

            while (true) {
                System.out.println("\nServidor escoltant... ja preparat");
                Socket newSocket = serverSocket.accept();
                
                InputStream is = newSocket.getInputStream();
                OutputStream os = newSocket.getOutputStream();
                
                // Llegir el nom d'usuari del client
                byte[] usuariBytes = new byte[50];
                is.read(usuariBytes);
                String usuari = new String(usuariBytes).trim();

                Usuari u = new Usuari (usuari, newSocket);
                clientsConnectats.add(u);
                
                if (clientsConnectats.size() > 1) {
                    System.out.println("Actualment hi ha connectats els usuaris següents");
                    for (Usuari client : clientsConnectats) {
                        System.out.println(client.getUsuari());
                    }
                }

                new RebreMissatges().start();
                new EnviarMissatges().start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static class RebreMissatges extends Thread {

        public RebreMissatges() {
            
        }

        public void run() {
            
        }
    }
    
    static class EnviarMissatges extends Thread {

        public EnviarMissatges() {
            
        }

        public void run() {
            
        }
    }
}

//beritja.cicles@gmail.com

