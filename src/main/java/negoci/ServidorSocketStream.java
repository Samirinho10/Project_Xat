package negoci;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorSocketStream {

    /*
        Funcions del servidor:
            - El client es connecta i envia el seu nom al servidor.
            - El servidor li retorna el nombre de connexions.
            - El servidor espera un nou missatge format per 3 parts (un número, un símbol [+, -, * o /] i un altre número
              Fa l'operació i retorna el resultat al client

        Tecnologia de comunicacions:
            Tecnologia orientada a connexió, els clients i servidors s'intercanvien 
            diferents missatges. Sockets stream.

        Protocol de nivell d'aplicació:
            Missatge 1 Nom
            Qui envia? Client
            Quan? En el moment de connexió
            Què? El seu nom

            Missatge 2 Nombre de conexions
            Qui envia? Servidor
            Quan? En el moment que rep el missatge del client connectat
            Què? El nombre de conexions que té
    
            Missatge 3 Valors Operació
            Qui envia? Client
            Quan? En el moment que rep el missatge del servidor amb el nombre de conexions
            Què? Una operació i dos nombres que ha demanat per terminal
    
            Missatge 4 Resultat Operació
            Qui envia? Servidor
            Quan? En el moment que rep el missatge del client amb els valors de l'operació
            Què? El resultat de l'operació
    */
    
    public static void main(String[] args){

        try {
            System.out.println("Creant Socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr= new InetSocketAddress("localhost", 5050);
            serverSocket.bind(addr);
			
            int comptador = 1;
			
            while (true) {
                System.out.println("\nServidor escoltant... ja preparat");
                Socket newSocket = serverSocket.accept();

                new atendre_clients(newSocket, comptador).start();
                
                ++comptador;
            }
			
        } catch(IOException e){
            e.printStackTrace();
        }
    }	
}