package componentsExterns;

import model.Missatges;
import model.Usuari;

public interface EventChat {

    public void sendMessage(String missatge);
                
    public void receiveMessage(String missatge, Usuari usuari);
               
}
