package com.mycompany.xat;

import dades.ClientSocketStream;
import dades.ServidorSocketStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Xat {

    private static boolean isServerRunning(String host, int port) {
        try (Socket socket = new Socket()) {
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            socket.connect(socketAddress, 5050);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 5050;
        boolean servidorTancat = !isServerRunning(host, port);

        if (servidorTancat) {
            // Iniciar el servidor en un fil separat per permetre que el client es connecti correctament
            Thread serverThread = new Thread(() -> {
                ServidorSocketStream.main(args);
            });
            serverThread.start();

            // Donar al servidor algun temps per iniciar-se
            try {
                Thread.sleep(2000);  // Ajusta aquest retard segons sigui necessari
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // Ara inicia el client
            ClientSocketStream.main(args);
        } 
    }
}

