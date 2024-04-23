
package negoci;

import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class Client {
    
    public static void main (String[] args) throws Exception {

        // PORT i IP del SERVIDOR
        String host = "localhost";
        int port = 22222;
        
        // Connectem a socket
        Socket socol = new Socket (host,port);
        DataOutputStream out = new DataOutputStream(socol.getOutputStream());
        DataInputStream in = new DataInputStream(socol.getInputStream());
        
        // a. Generar doble clau		    
	KeyPairGenerator generadorRSA = KeyPairGenerator.getInstance("RSA");
        //generadorRSA.initialize(1024);
        KeyPair clauRSA = generadorRSA.genKeyPair();
        System.out.println("Generada la clau asimètrica.");

        // Enviar la clau pública
        byte[] bytesClave = clauRSA.getPublic().getEncoded();
        out.writeInt(bytesClave.length);
        out.write(bytesClave);

        //Rebre clau privada: clau asimètrica xifrada amb la pública del client
        //Llegir del sòcol i desxifrar amb la meva privada 
        bytesClave = new byte[in.readInt()];
        in.readFully(bytesClave);
        System.out.println("Rebuda clau privada xifrada...");

        // Desxifrar clau privada
        Cipher cifradorRSA = Cipher.getInstance("RSA");
        cifradorRSA.init(Cipher.DECRYPT_MODE, clauRSA.getPrivate());

        byte[] bytesClauAES= cifradorRSA.doFinal(bytesClave);
        System.out.println("Ja tenim la clau");
        System.out.println("Clau privada..." );

        // Regenerar la clau AES per poder xif i desxif
        SecretKey clauAES = new SecretKeySpec(bytesClauAES, 0, bytesClauAES.length, "AES");
		    
        //Rebre missatge encriptat
        byte[] missatgeXifAES = new byte[in.readInt()];
        in.readFully(missatgeXifAES);
        System.out.println("Missatge encriptat: " + new String(missatgeXifAES));

        //Desxifrar missatge amb clau AES
        Cipher AESCipher = Cipher.getInstance("AES");
        AESCipher.init(Cipher.DECRYPT_MODE, clauAES);
        byte[] missatgeDesxif = AESCipher.doFinal(missatgeXifAES);
        System.out.println("Missatge desxifrat: " + new String(missatgeDesxif));

        in.close();
        out.close();
        socol.close();
    }
}

