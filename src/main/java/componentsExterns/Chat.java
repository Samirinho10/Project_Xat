
package componentsExterns;

import model.Missatges;
import model.Usuari;

public class Chat extends javax.swing.JPanel {
    
    public Chat() {
        initComponents();
        init();
    }
    
    private void init(){
        PublicEvent.getInstance().addEventChat(new EventChat() {
            public void sendMessage(String missatge) {
                chat_Body.addItemRight(missatge);
            }

            public void receiveMessage(Missatges missatge, Usuari usuari) {
                if (chat_Title.getUsuari().equals(missatge.getIdUsuari())) {
                    chat_Body.addItemLeft(missatge.getMissatge(), usuari.getUsuari());
                }
            }
        });
    }
    
    public void setUsuari(Usuari usuari) {
        chat_Title.setUserName(usuari);
        chat_Bottom.setUsuari(usuari);
        chat_Body.clearChat();
    }

//    public void updateUser(Usuari usuari) {
//        chatTitle.updateUser(usuari);
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chat_Title = new componentsExterns.Chat_Title();
        chat_Body = new componentsExterns.Chat_Body();
        chat_Bottom = new componentsExterns.Chat_Bottom();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chat_Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(chat_Body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(chat_Bottom, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chat_Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chat_Body, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chat_Bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentsExterns.Chat_Body chat_Body;
    private componentsExterns.Chat_Bottom chat_Bottom;
    private componentsExterns.Chat_Title chat_Title;
    // End of variables declaration//GEN-END:variables
}
