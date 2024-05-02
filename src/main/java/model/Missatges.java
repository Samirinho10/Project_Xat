
package model;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;

public class Missatges {
    
    private ObjectId idMissatge;
    private Usuari idUsuari;
    private String idSala;
    private String missatge;
    private LocalDateTime data;

    public Missatges(Usuari idUsuari, String idSala, String missatge) {
        this.idMissatge = new ObjectId();
        this.idUsuari = idUsuari;
        this.idSala = idSala;
        this.missatge = missatge;
        this.data = LocalDateTime.now();
    }

    public ObjectId getIdMissatge() {
        return idMissatge;
    }

    public void setIdMissatge(ObjectId idMissatge) {
        this.idMissatge = idMissatge;
    }

    public Usuari getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(Usuari idUsuari) {
        this.idUsuari = idUsuari;
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
