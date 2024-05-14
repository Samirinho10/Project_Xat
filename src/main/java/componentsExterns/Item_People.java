package componentsExterns;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import model.Usuari;

public class Item_People extends JPanel {

    private String name;
    
    public Item_People(String name) {
        this.name = name;
        initComponents();
        lb.setText(name);
        init();
    }
    
    public Item_People(Usuari usuari) {
        this.name = usuari.getUsuari();
        initComponents();
        lb.setText(usuari.getUsuari());
        
        boolean valorEstat = dades.Connexio.veureEstatSegonsUsuari(usuari.getUsuari());
        
        activeStatus.setActive(valorEstat);
        init();
    }

    public String getName() {
        return name;
    }
    
    public void activarEstat() {
        activeStatus.setActive(true);
    }
    
    public void desactivarEstat() {
        activeStatus.setActive(false);
    }

    private void init() {
        addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(242, 242, 242));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(new Color(230, 230, 230));
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        imageAvatar1 = new componentsExterns.ImageAvatar();
        activeStatus = new componentsExterns.ActiveStatus();

        setBackground(new java.awt.Color(229, 229, 229));
        setMaximumSize(new java.awt.Dimension(245, 60));
        setPreferredSize(new java.awt.Dimension(245, 60));
        setRequestFocusEnabled(false);

        lb.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lb.setText("Name");
        lb.setMaximumSize(new java.awt.Dimension(20, 19));
        lb.setRequestFocusEnabled(false);

        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/profile.png")));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(activeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(activeStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentsExterns.ActiveStatus activeStatus;
    private componentsExterns.ImageAvatar imageAvatar1;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
