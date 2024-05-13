
package model;

import java.net.Socket;
import java.security.PublicKey;

public class Usuari {
    
    private String usuari;
    private String contrasenya;
    private boolean estat; //en línea o desconnectat
    private Socket socket;
    private PublicKey clauPublica;

    public Usuari(String usuari, String contrasenya, Socket socket) {
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.socket = socket;
    }
    
    public Usuari(String usuari, Socket socket, PublicKey clauPublica, boolean estat) {
        this.usuari = usuari;
        this.socket = socket;
        this.clauPublica = clauPublica;
        this.estat = estat;
    }
    
    public Usuari(String usuari, Socket socket, PublicKey clauPublica) {
        this.usuari = usuari;
        this.socket = socket;
        this.clauPublica = clauPublica;
    }

    public Usuari(String usuari, String contrasenya) {
        this.usuari = usuari;
        this.contrasenya = contrasenya;
    }

    public Usuari() {
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public boolean isEstat() {
        return estat;
    }

    public void setEstat(boolean estat) {
        this.estat = estat;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PublicKey getClauPublica() {
        return clauPublica;
    }

    public void setClauPublica(PublicKey clauPublica) {
        this.clauPublica = clauPublica;
    }
}
