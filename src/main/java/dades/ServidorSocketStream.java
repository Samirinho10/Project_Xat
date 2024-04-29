package dades;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServidorSocketStream {
    
    private static List<String> usuarisConnectats = new ArrayList<>();

    public static void main(String[] args){

        try {
            System.out.println("Creant Socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr= new InetSocketAddress("localhost", 5050);
            serverSocket.bind(addr);
			
            while (true) {
                System.out.println("\nServidor escoltant... ja preparat");
                Socket newSocket = serverSocket.accept();
                
                InputStream is = newSocket.getInputStream();
                OutputStream os = newSocket.getOutputStream();
                
                byte[] usuariBytes = new byte[50];
                is.read(usuariBytes);
                String usuari = new String(usuariBytes).trim();
                
                System.out.println("S'ha connectat l'usuari " + usuari);
                
                //Afegim el nou usuari a la llista d'usuaris connectats
                usuarisConnectats.add(usuari);

                // Enviem la llista d'usuaris connectats als clients
                StringBuilder userListBuilder = new StringBuilder();
                
                for (String nouUsuari : usuarisConnectats) {
                    userListBuilder.append(nouUsuari).append(",");
                }
                String userListString = userListBuilder.toString();

                // Enviem la llista d'usuaris connectats als clients
                os.write(userListString.getBytes());
                //System.out.println("Usuaris actualment connectats: " + userListString);

                //fil 1: atendre els clients que inicien sessió
                new atendre_clients(newSocket).start();
                
            }
			
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}