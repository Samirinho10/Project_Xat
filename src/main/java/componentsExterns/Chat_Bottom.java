
package componentsExterns;

import dades.ClientSocketStream;
import dades.Connexio;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Missatges;
import model.Usuari;
import net.miginfocom.swing.MigLayout;


public class Chat_Bottom extends javax.swing.JPanel {

    private ClientSocketStream client;
    
    public static JIMSendTextPane txt;
    public static JButton botoEnviar;
            
    private Usuari usuari;
    
    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }
    
    public Chat_Bottom() {
        initComponents();
        init();
    }
    
    public void init(){
        setLayout(new MigLayout("fillx, filly", "0[fill]0[]0[]2", "2[fill]2"));
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        txt = new JIMSendTextPane();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
            }
        });
        txt.setHintText("Escriu un missatge aquí ...");
        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar();
        sb.setPreferredSize(new Dimension(2, 10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll, "w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]0", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.WHITE);
        botoEnviar = new JButton();
        botoEnviar.setBorder(null);
        botoEnviar.setContentAreaFilled(false);
        botoEnviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botoEnviar.setIcon(new ImageIcon(getClass().getResource("/send.png")));
//        botoEnviar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                String text = txt.getText().trim();
//                String usuariConnectat = ClientSocketStream.txtUsuariConnectat; //agafem l'usuari que ha iniciat sessió
//                Usuari usuari = Connexio.obtenirUsuariPerId(usuariConnectat);
//                String sala = "grup";
//                
//                Missatges missatge = new Missatges (usuari, sala, text);
//                
//                if (!text.equals("")) {
//                    Connexio.guardarMissatges(missatge);
//                    PublicEvent.getInstance().getEventChat().sendMessage(text);
//                    
//                    //enviar missatge al client
//                    
//                    
//                    txt.setText("");
//                    txt.grabFocus();
//                    refresh();
//                } else {
//                    txt.grabFocus();
//                }
//            }
//        });
        panel.add(botoEnviar);
        add(panel);
    }
    
    private void refresh() {
        revalidate();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
