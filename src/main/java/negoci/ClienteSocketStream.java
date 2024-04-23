package negoci;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocketStream {
	
    public static void main(String[] args){
        
        Scanner teclat = new Scanner(System.in);
    
        try{
            //Creant Socket client per connectar-nos al servidor
            Socket cs = new Socket("localhost", 5050);
		
            //Obtenim els fluxos d'entrada i sortida del socket
            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();
			
            //Enviem un missatge al servidor
            String usuari = "samir";
            os.write(usuari.getBytes());
            
            System.out.println("T'has connectat");
            
            //Tanquem el socket
            cs.close();
			
    	} catch(IOException e){
            e.printStackTrace();
	}
    }
}
