
package model;

public class Usuari {
    
    private String usuari;
    private String contrasenya;
    private String estat; //en línea o desconnectat

    public Usuari(String usuari, String contrasenya, String estat) {
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.estat = estat;
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

}
