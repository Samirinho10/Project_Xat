
package com.mycompany.xat;

import dades.ClientSocketStream;
import dades.ServidorSocketStream;

public class Xat {

    public static void main(String[] args) {
        
        boolean servidorTancat = true;
        
        if (servidorTancat) {
            ServidorSocketStream.main(args);
            servidorTancat = false;
        }
        
        ClientSocketStream.main(args);
    }
}
