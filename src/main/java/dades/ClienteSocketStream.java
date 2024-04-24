package dades;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import model.Usuari;
import vista.Sessio;


public class ClienteSocketStream {
	
    public static void main(String[] args){
        
        Scanner teclat = new Scanner(System.in);
    
        try{
            //Creant Socket client per connectar-nos al servidor
            Socket cs = new Socket("localhost", 5050);
		
            //Obtenim els fluxos d'entrada i sortida del socket
            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();
			
            //Obrim pantalla login
            Sessio sessioFrame = new Sessio();
            sessioFrame.setVisible(true);
            
            //Obtenim les credencials i les enviem al servidor
            Usuari usuari = sessioFrame.obtenirUsuariContrasenya();
            System.out.println("usuari: " + usuari.getUsuari());
            
//            String credencials = usuari + ":" + contrasenya + "\n";
//            System.out.println(credencials + "holajodsja");
//            os.write(credencials.getBytes());
           
            
            //Tanquem el socket
            cs.close();
			
    	} catch(IOException e){
            e.printStackTrace();
	}
    }
}
