package dades;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServidorSocketStream {
    
    public static Scanner teclat = new Scanner(System.in);
    
    private static List<String> usuarisConnectats = new ArrayList<>();
    
    public static void main(String[] args) {

        try {
            System.out.println("Creant Socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 5050);
            serverSocket.bind(addr);

            while (true) {
                System.out.println("\nServidor escoltant... ja preparat");
                Socket newSocket = serverSocket.accept();

                tractarClient(newSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void tractarClient(Socket clientSocket) {
        try {
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();

            // Llegir el nom d'usuari del client
            byte[] usuariBytes = new byte[50];
            is.read(usuariBytes);
            String usuari = new String(usuariBytes).trim();

            System.out.println("S'ha connectat l'usuari " + usuari);

            // Afegir el nou usuari a la llista d'usuaris connectats
            usuarisConnectats.add(usuari);

            // Enviar la llista d'usuaris connectats als clients
            StringBuilder userListBuilder = new StringBuilder();

            for (String nouUsuari : usuarisConnectats) {
                userListBuilder.append(nouUsuari).append(",");
            }
            String userListString = userListBuilder.toString();
            os.write(userListString.getBytes());

            // fil: rebre missatges del client
            new RebreMissatges(clientSocket, is).start();

            // fil: enviar missatges al client
            new EnviarMissatges(clientSocket, os).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class RebreMissatges extends Thread {
        private Socket socket;
        private InputStream is;

        public RebreMissatges(Socket socket, InputStream is) {
            this.socket = socket;
            this.is = is;
        }

        public void run() {
            try {
                Scanner scanner = new Scanner(is);
                while (true) {
                    // Verificar si hay una línea disponible antes de intentar leerla
                    if (scanner.hasNextLine()) {
                        String missatge = scanner.nextLine();
                        System.out.println("Missatge rebut del client: " + missatge);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class EnviarMissatges extends Thread {
        private Socket socket;
        private OutputStream os;

        public EnviarMissatges(Socket socket, OutputStream os) {
            this.socket = socket;
            this.os = os;
        }

        public void run() {
            try {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String missatge = scanner.nextLine();
                    missatge += "\n";
                    os.write(missatge.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
