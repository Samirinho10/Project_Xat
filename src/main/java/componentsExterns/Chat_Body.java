package componentsExterns;

import java.awt.Adjustable;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.time.LocalTime;
import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;

public class Chat_Body extends javax.swing.JPanel {

    public Chat_Body() {
        initComponents();
        init();
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }

    public void addItemLeft(String text, String usuari, LocalTime hora) {
        Chat_Left item = new Chat_Left();
        item.setText(text);
        item.setTime(hora);
        item.setUserProfile(usuari);
        body.add(item, "wrap, w 100::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
    }

    public void addItemRight(String text, LocalTime hora) {
        Chat_Right item = new Chat_Right();
        item.setTime(hora);
        item.setText(text);
        body.add(item, "wrap, al right, w 100::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
        scrollToBottom();
    }
    
    public void addData(String data){
        Chat_Date item = new Chat_Date();
        item.setData(data);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }
    
    public void addUserConnected(String usuari){
        Chat_UsuariConnectat item = new Chat_UsuariConnectat();
        item.setUsuariConnectat(usuari);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }
    
    public void addUserDisconnected(String usuari){
        Chat_UsuariDesconnectat item = new Chat_UsuariDesconnectat();
        item.setUsuariDesconnectat(usuari);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }
    
    public void clearChat() {
        body.removeAll();
        repaint();
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setMinimumSize(new java.awt.Dimension(300, 0));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }
    
    public void scrollToTop() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener upScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMinimum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(upScroller);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
