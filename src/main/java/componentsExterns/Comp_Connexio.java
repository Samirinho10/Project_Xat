/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package componentsExterns;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.Socket;
import java.security.Key;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;
import vista.Principal;

/**
 *
 * @author Samir
 */
public class Comp_Connexio extends javax.swing.JFrame {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Key clauAES;
    public static String txtUsuariConnectat;
    public static Principal principalFrame;
    public static List<String> totalUsuarisBBDD;

    public Comp_Connexio() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        ip = new components.Text_Placeholder();
        port = new components.Text_Placeholder();
        nombbdd = new components.Text_Placeholder();
        usuariComp = new components.Text_Placeholder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        contrasenyaComp = new components.Text_Placeholder();
        jLabel5 = new javax.swing.JLabel();
        cargarDades = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ip.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ip.setToolTipText("");
        ip.setPlaceholder("Introdueix la IP");
        ip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipActionPerformed(evt);
            }
        });

        port.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        port.setPlaceholder("Introdueix el port");
        port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portActionPerformed(evt);
            }
        });

        nombbdd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombbdd.setPlaceholder("Introdueix el nom");
        nombbdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombbddActionPerformed(evt);
            }
        });

        usuariComp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuariComp.setPlaceholder("Nom d'usuari");
        usuariComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariCompActionPerformed(evt);
            }
        });

        jLabel1.setText("Ip Servidor");

        jLabel2.setText("Port");

        jLabel3.setText("Nom BBDD");

        jLabel4.setText("Usuari");

        contrasenyaComp.setPlaceholder("Contrasenya");
        contrasenyaComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contrasenyaCompActionPerformed(evt);
            }
        });

        jLabel5.setText("Contrasenya");

        cargarDades.setText("Cargar dades");
        cargarDades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarDadesActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(cargarDades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ip, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(port, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombbdd, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usuariComp, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(contrasenyaComp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombbdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuariComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contrasenyaComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cargarDades, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipActionPerformed

    }//GEN-LAST:event_ipActionPerformed

    private void portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portActionPerformed

    }//GEN-LAST:event_portActionPerformed

    private void contrasenyaCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contrasenyaCompActionPerformed

    }//GEN-LAST:event_contrasenyaCompActionPerformed

    private void nombbddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombbddActionPerformed

    }//GEN-LAST:event_nombbddActionPerformed

    private void usuariCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariCompActionPerformed

    }//GEN-LAST:event_usuariCompActionPerformed

    private void cargarDadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarDadesActionPerformed
        String ipAddress = ip.getText();
        String portNumber = port.getText();
        String dbName = nombbdd.getText();
        String username = usuariComp.getText();
        String password = contrasenyaComp.getText();

        String uri = "mongodb://" + username + ":" + password + "@" + ipAddress + ":" + portNumber ;

        MongoClient mongoClient = null;

        try {
            // Conectar a la base de datos MongoDB
            mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase(dbName);

            // Obtener y mostrar los nombres de las colecciones
            MongoIterable<String> collections = database.listCollectionNames();
            StringBuilder collectionNames = new StringBuilder("Colecciones en la base de datos:\n");
            for (String collection : collections) {
                collectionNames.append(collection).append("\n");
            }

            JOptionPane.showMessageDialog(this, collectionNames.toString(), "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Mostrar los datos de cada colección
            StringBuilder data = new StringBuilder();
            for (String collectionName : collections) {
                MongoCollection<Document> collection = database.getCollection(collectionName);
                MongoIterable<Document> documents = collection.find();
                data.append("Datos en la colección ").append(collectionName).append(":\n");
                for (Document doc : documents) {
                    data.append(doc.toJson()).append("\n");
                }
            }

            // Mostrar datos de todas las colecciones
            JOptionPane.showMessageDialog(this, data.toString(), "Datos de las Colecciones", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            // Manejar excepción
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }//GEN-LAST:event_cargarDadesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Comp_Connexio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comp_Connexio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comp_Connexio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comp_Connexio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Comp_Connexio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cargarDades;
    private components.Text_Placeholder contrasenyaComp;
    private components.Text_Placeholder ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private components.Text_Placeholder nombbdd;
    private components.Text_Placeholder port;
    private components.Text_Placeholder usuariComp;
    // End of variables declaration//GEN-END:variables
}
