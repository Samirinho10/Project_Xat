package vista;

import dades.Connexio;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import javax.swing.border.AbstractBorder;
import model.Usuari;

public class Sessio extends javax.swing.JFrame {

    public Sessio() {
        initComponents();
        setResizable(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtUsuari = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtContrasenya = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtServidor = new javax.swing.JTextField();
        acces = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Login.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, null, java.awt.Color.gray));
        Login.setForeground(new java.awt.Color(255, 255, 255));
        Login.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Login.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inicia Sessió");

        txtUsuari.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtUsuari.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsuari.setToolTipText("Introdueix l'usuari");
        txtUsuari.setBorder(null);
        txtUsuari.setCaretColor(new java.awt.Color(102, 102, 255));
        txtUsuari.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtUsuari.setMargin(new Insets(5, 20, 5, 5));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Contrasenya");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Usuari");

        txtContrasenya.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtContrasenya.setBorder(null);
        txtContrasenya.setCaretColor(new java.awt.Color(102, 102, 255));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Servidor");

        txtServidor.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtServidor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtServidor.setToolTipText("Introdueix l'usuari");
        txtServidor.setBorder(null);
        txtServidor.setCaretColor(new java.awt.Color(102, 102, 255));
        txtServidor.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtUsuari.setMargin(new Insets(5, 20, 5, 5));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtUsuari)
            .addComponent(txtContrasenya, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(txtServidor)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel4});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtContrasenya, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtContrasenya, txtUsuari});

        acces.setBackground(new java.awt.Color(102, 102, 255));
        acces.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        acces.setForeground(new java.awt.Color(255, 255, 255));
        acces.setText("Accedir");
        acces.setToolTipText("Registrar usuari");
        acces.setBorder(null);
        acces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accesActionPerformed(evt);
            }
        });
        acces.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                accesKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(acces, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)))
                .addContainerGap())
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(acces, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public class RoundBorder extends AbstractBorder {

        private final int radius;

        public RoundBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            g2.fillRoundRect(x, y, width, height, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius / 2, this.radius / 2, this.radius / 2, this.radius / 2);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = this.radius / 2;
            return insets;
        }
    }
    
    public boolean iniciSessio() {
        String usuari = txtUsuari.getText();
        String contrasenya = new String(txtContrasenya.getPassword());
        String servidor = txtServidor.getText();
        
        Usuari usuariConnectat = new Usuari(usuari, contrasenya);

        if (Connexio.verificarCreedencials(usuariConnectat)) {
            
            if (!isValidIPAddress(servidor)) {
                System.out.println(servidor + " no es una dirección IP válida.");
                JOptionPane.showMessageDialog(null, "La IP del servidor " + servidor + " no és una IP vàlida.", 
                        "Informació", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            
            this.dispose();
            return true;
            
        } else if (Connexio.existeixUsuari(usuariConnectat)){
            JOptionPane.showMessageDialog(null, "Contrasenya incorrecta. Torna a provar-ho", 
                    "Informació", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            int resultat = JOptionPane.showConfirmDialog(
            	this,
            	"Aquest usuari no existeix. Vols afegir-lo?",
            	"Inici de Sessió",
            	JOptionPane.YES_OPTION
            );

            if (resultat == JOptionPane.YES_OPTION) {
                Connexio.inserirUsuari(usuariConnectat);
                JOptionPane.showMessageDialog(null, "Usuari afegit correctament. Ara pots iniciar sessió amb les seves credencials.", 
                        "Informació", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return false;
    }

    private void usuariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariActionPerformed

    }//GEN-LAST:event_usuariActionPerformed

    private void contrasenyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contrasenyaActionPerformed

    }//GEN-LAST:event_contrasenyaActionPerformed

    private void accesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accesActionPerformed
        iniciSessio();
    }//GEN-LAST:event_accesActionPerformed

    private void accesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            iniciSessio();
        }
    }//GEN-LAST:event_accesKeyPressed

    public static boolean isValidIPAddress(String ip) {
        try {
            InetAddress inet = InetAddress.getByName(ip);
            return inet.getHostAddress().equals(ip);
            
        } catch (UnknownHostException e) {
            return false;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Login;
    private javax.swing.JButton acces;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPasswordField txtContrasenya;
    public javax.swing.JTextField txtServidor;
    public javax.swing.JTextField txtUsuari;
    // End of variables declaration//GEN-END:variables
}
