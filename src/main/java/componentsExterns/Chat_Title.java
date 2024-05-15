
package componentsExterns;

import model.Usuari;

public class Chat_Title extends javax.swing.JPanel {

    private Usuari usuari;
    
    public Usuari getUsuari() {
        return usuari;
    }
    
    public Chat_Title() {
        initComponents();
    }
    
    public void setUserName(Usuari usuari) {
        lblUsuari.setText(usuari.getUsuari());
        
        usuari = dades.Connexio.obtenirUsuariPerId(usuari.getUsuari());
        boolean valorEstat = dades.Connexio.veureEstatSegonsUsuari(usuari.getUsuari());
        
        if (valorEstat == true) {
            estatActiu();
        } else {
            setEstatText("Desconnectat");
        }
    }
    
    public void setUserName(String usuari) {
        lblUsuari.setText(usuari);
        lblStatus.setText("");
    }

    public String getUserName() {
        return lblUsuari.getText();
    }
    
    public String getEstat() {
        return lblStatus.getText();
    }
    
    private void estatActiu() {
        lblStatus.setText("En línea");
        lblStatus.setForeground(new java.awt.Color(40, 147, 59));
    } 
    
    public void setEstatText(String text) {
        lblStatus.setText(text);
        lblStatus.setForeground(new java.awt.Color(160, 160, 160));
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        lblUsuari = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();

        layer.setLayout(new java.awt.GridLayout(0, 1));

        lblUsuari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsuari.setForeground(new java.awt.Color(63, 63, 63));
        lblUsuari.setText("Benvingut/da!");
        layer.add(lblUsuari);

        lblStatus.setForeground(new java.awt.Color(42, 145, 61));
        lblStatus.setText("Estàs en línia");
        layer.add(lblStatus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblUsuari;
    // End of variables declaration//GEN-END:variables
}
