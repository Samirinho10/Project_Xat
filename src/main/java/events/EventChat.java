package events;

import java.time.LocalTime;
import model.Usuari;

public interface EventChat {

    public void sendMessage(String missatge, LocalTime hora);
                
    public void receiveMessage(String missatge, Usuari usuari, LocalTime hora);
    
    public void userConnected(String usuari);
    
    public void userDisconnected(String usuari);
               
}
