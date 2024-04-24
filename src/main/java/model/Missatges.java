
package model;

import java.util.Date;

public class Missatges {
    
    private int idMissatge;
    private int idUsuari;
    private int idSala;
    private String missatge;
    private Date data;

    public Missatges(int idMissatge, int idUsuari, int idSala, String missatge, Date data) {
        this.idMissatge = idMissatge;
        this.idUsuari = idUsuari;
        this.idSala = idSala;
        this.missatge = missatge;
        this.data = data;
    }

    public int getIdMissatge() {
        return idMissatge;
    }

    public void setIdMissatge(int idMissatge) {
        this.idMissatge = idMissatge;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    
}
