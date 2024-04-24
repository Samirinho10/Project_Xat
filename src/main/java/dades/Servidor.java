
package dades;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;

public class Servidor {
    
    public static void main (String[] args) throws Exception {

        String host = "localhost";
        int port = 22222;

        // Generem clau simètrica AES....
        System.out.println("Generem clau simètrica...");
        KeyGenerator generador = KeyGenerator.getInstance("AES");
        Key clauAES= generador.generateKey();

        // Server socket stream
        ServerSocket ssocket = new ServerSocket (port);
        System.out.println("Acceptant connexions...");
        Socket socket = ssocket.accept();
        System.out.println("Client connectat...");

        // Utilitzem la classe DataOtputStream té més métodes útils per transmetre fluxes de dades que al
        // transmetre claus ens és útil ( .readInt .redFully 
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        // llegim clau pública
        byte[] bytesClave = new byte[in.readInt()];
        in.readFully(bytesClave);
        System.out.println("Rebem clau Pública del client.");

        //regenerem clau
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(bytesClave);
        PublicKey clavePublica = kf.generatePublic(x509Spec);

        // Xifrem la clau simètrica amb la clau pública del client
        Cipher cifradorRSA = Cipher.getInstance("RSA");
        cifradorRSA.init(Cipher.ENCRYPT_MODE, clavePublica);
        byte[] bytesClau = clauAES.getEncoded();
        byte[] clauAESXif = cifradorRSA.doFinal(bytesClau);
        System.out.println("Xifrada  la clau simètrica, la enviem..");

        // Enviar la clau simètrica xifrada	    
        out.writeInt(clauAESXif.length);
        out.write(clauAESXif);

        // Ara ja podem xifrar amb AES i enviar
        String Missatge="Pica, però no és pebre, té dents i no mosega";

        //Xifrar missatge i enviar
        Cipher cifradorAES = Cipher.getInstance("AES");
        cifradorAES.init(Cipher.ENCRYPT_MODE, clauAES);
        byte[] missatgeXifAES = cifradorAES.doFinal(Missatge.getBytes());

        //Enviar el missatge xifrat amb la clau AES
        out.writeInt(missatgeXifAES.length);
        out.write(missatgeXifAES);

        in.close();
        out.close();
        socket.close();
        ssocket.close();
    }
}
