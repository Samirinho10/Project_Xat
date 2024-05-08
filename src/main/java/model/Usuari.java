
package model;

import java.net.Socket;

public class Usuari {
    
    private String usuari;
    private String contrasenya;
    private String estat; //en línea o desconnectat
    private Socket sc;

    public Usuari(String usuari, String contrasenya, String estat, Socket cs) {
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.estat = estat;
        this.sc = sc;
    }
    
    public Usuari(String usuari, Socket cs) {
        this.usuari = usuari;
        this.sc = sc;
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

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public Socket getSc() {
        return sc;
    }

    public void setSc(Socket sc) {
        this.sc = sc;
    }
}
