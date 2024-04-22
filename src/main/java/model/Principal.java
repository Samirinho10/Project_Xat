
package model;

import componentsExterns.*;
import javax.swing.ImageIcon;


public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JLayeredPane();
        Home = new javax.swing.JPanel();
        Menu = new javax.swing.JPanel();
        MenuTop = new javax.swing.JLayeredPane();
        BtnXat = new componentsExterns.MenuButton();
        BtnUsuaris = new componentsExterns.MenuButton();
        BtnHistorial = new componentsExterns.MenuButton();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();
        chat = new componentsExterns.Chat();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setLayout(new java.awt.BorderLayout());

        MenuTop.setBackground(new java.awt.Color(229, 229, 229));
        MenuTop.setOpaque(true);
        MenuTop.setLayout(new java.awt.GridLayout(1, 3));

        BtnXat.setBackground(new java.awt.Color(229, 229, 229));
        BtnXat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/message.png")));
        BtnXat.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/message_selected.png")));
        BtnXat.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/message.png")));
        BtnXat.setSelected(true);
        BtnXat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnXatActionPerformed(evt);
            }
        });
        MenuTop.add(BtnXat);

        BtnUsuaris.setBackground(new java.awt.Color(229, 229, 229));
        BtnUsuaris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/group.png")));
        BtnUsuaris.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/group_selected.png")));
        BtnUsuaris.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/group.png")));
        BtnUsuaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUsuarisActionPerformed(evt);
            }
        });
        MenuTop.add(BtnUsuaris);

        BtnHistorial.setBackground(new java.awt.Color(229, 229, 229));
        BtnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/box.png")));
        BtnHistorial.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/box_selected.png")));
        BtnHistorial.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/box.png")));
        BtnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHistorialActionPerformed(evt);
            }
        });
        MenuTop.add(BtnHistorial);

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuList.setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 893, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuTop, javax.swing.GroupLayout.PREFERRED_SIZE, 210, Short.MAX_VALUE)
            .addComponent(sp)
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(MenuTop, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chat, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                .addContainerGap())
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chat, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        body.add(Home, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Navega");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHistorialActionPerformed
        if (!BtnHistorial.isSelected()) {
            BtnXat.setSelected(false);
            BtnUsuaris.setSelected(false);
            BtnHistorial.setSelected(true);
            mostrarHistorial();
        }
    }//GEN-LAST:event_BtnHistorialActionPerformed

    private void BtnUsuarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUsuarisActionPerformed
        if (!BtnUsuaris.isSelected()) {
            BtnXat.setSelected(false);
            BtnUsuaris.setSelected(true);
            BtnHistorial.setSelected(false);
            mostrarUsuaris();
        }
    }//GEN-LAST:event_BtnUsuarisActionPerformed

    private void BtnXatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnXatActionPerformed
        if (!BtnXat.isSelected()) {
            BtnXat.setSelected(true);
            BtnUsuaris.setSelected(false);
            BtnHistorial.setSelected(false);
            mostrarXat();
        }
    }//GEN-LAST:event_BtnXatActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    private void init() {
        //editar scroll bar
        sp.setVerticalScrollBar(new ScrollBar());
        
        //Assignar tamany del Frame
        int x = 1200; 
        int y = 800;

        setSize(x, y);
        setLocationRelativeTo(null);
        
        //mostrem els xats a l'obrir
        mostrarXat();
    }
    
    private void mostrarUsuaris() {
        menuList.removeAll();
        for (int i = 0; i < 20; i++) {
            Item_People itemPeople = new Item_People("Usuari " + i);
            menuList.add(itemPeople);
            itemPeople.setBounds(0, i * 50, 216, 50);
        }
        refreshMenuList();
    }
    
    private void mostrarXat() {
        menuList.removeAll();
        for (int i = 0; i < 20; i++) {
            Item_People itemPeople = new Item_People("Xat " + i);
            menuList.add(itemPeople);
            itemPeople.setBounds(0, i * 50, 216, 50);
        }
        refreshMenuList();
    }
    
    private void mostrarHistorial() {
        menuList.removeAll();
        for (int i = 0; i < 20; i++) {
            Item_People itemPeople = new Item_People("Missatge " + i);
            menuList.add(itemPeople);
            itemPeople.setBounds(0, i * 50, 216, 50);
        }
        refreshMenuList();
    }
    
    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentsExterns.MenuButton BtnHistorial;
    private componentsExterns.MenuButton BtnUsuaris;
    private componentsExterns.MenuButton BtnXat;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel Menu;
    private javax.swing.JLayeredPane MenuTop;
    private javax.swing.JLayeredPane body;
    private componentsExterns.Chat chat;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLayeredPane menuList;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
