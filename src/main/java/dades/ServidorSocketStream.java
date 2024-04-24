package dades;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorSocketStream {

    public static void main(String[] args){

        try {
            System.out.println("Creant Socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr= new InetSocketAddress("localhost", 5050);
            serverSocket.bind(addr);
			
            while (true) {
                System.out.println("\nServidor escoltant... ja preparat");
                Socket newSocket = serverSocket.accept();

                //fil 1: atendre els clients que inicien sessió
                new atendre_clients(newSocket).start();
                
            }
			
        } catch(IOException e){
            e.printStackTrace();
        }
    }	
}