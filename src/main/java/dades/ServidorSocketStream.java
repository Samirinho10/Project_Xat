package dades;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorSocketStream {

    /*
        Funcions del servidor:
            - Un cop entrem, el servidor informarà dels usuaris que estan connectats en aquell grup.
    
            - Quan entri un usuari nou al grup el servidor informarà del nou usuari.
    
            - Quan un usuari enviï un missatge a tot el grup, el servidor mostrarà 
                a tots els usuaris del grup el missatge públic i confirmant a 
                l’usuari origen que s’ha enviat correctament
    
            - El servidor comunicarà dos clients quan realitzin la petició l’usuari 
                destí. Enviant així a l’usuari destí el missatge desitjat i 
                confirmant a l’usuari origen de que s’ha enviat correctament.
    
            - El servidor avisarà a tots els usuaris quan un usuari es desconnecti.
    
            - El servidor ha de ser capaç d’atendre diversos clients alhora.
    
            - El servidor ha de mostrar una conversa de xat d’una data ja passada quan el client ho demani.

    
        Tecnologia de comunicacions:
            Tecnologia orientada a connexió, els clients i servidors s'intercanvien 
            diferents missatges. Sockets stream.

        Protocol de nivell d'aplicació:
            Missatge 1: Usuaris connectats
            Qui envia? Servidor
            Quan? En el moment que es connecti un client
            Què? Informar dels usuaris connectats en aquell moment

            Missatge 2 Nou usuari connectat
            Qui envia? Servidor
            Quan? En el moment que es connecti un nou client
            Què? Informar sobre quin usuari nou s'ha connectat
    
            Missatge 3 Missatge privat
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
                System.out.println("Client " + newSocket.getInetAddress() + " connectat");

                new atendre_clients(newSocket, comptador).start();
                
                ++comptador;
            }
			
        } catch(IOException e){
            e.printStackTrace();
        }
    }	
}