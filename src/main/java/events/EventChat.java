package events;

import model.Usuari;

public interface EventChat {

    public void sendMessage(String missatge);
                
    public void receiveMessage(String missatge, Usuari usuari);
    
    public void userConnected(String usuari);
    
    public void userDisconnected(String usuari);
               
}
