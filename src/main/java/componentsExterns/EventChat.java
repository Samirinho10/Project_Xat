package componentsExterns;

import model.Missatges;
import model.Usuari;

public interface EventChat {

    public void sendMessage(String missatge);
                
    public void receiveMessage(Missatges missatge, Usuari usuari);
               
}
