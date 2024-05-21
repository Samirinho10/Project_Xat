package vista;

import componentsExterns.*;
import dades.Connexio;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BoxLayout;
import static java.lang.System.exit;
import model.Usuari;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Dimension;


public class Principal extends javax.swing.JFrame {

    public static JCalendar calendari = new JCalendar();
    
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
        jMenuItemSortir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemPrincipal = new javax.swing.JMenuItem();
        jMenuItemGrup = new javax.swing.JMenuItem();
        jMenuItemHistorial = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(800, 500));

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setLayout(new java.awt.BorderLayout());

        MenuTop.setBackground(new java.awt.Color(229, 229, 229));
        MenuTop.setOpaque(true);
        MenuTop.setLayout(new java.awt.GridLayout(1, 3));

        BtnXat.setBackground(new java.awt.Color(229, 229, 229));
        BtnXat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/message.png")));
        BtnXat.setToolTipText("Fes clic per actualitzar l'estat dels usuaris");
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
        menuList.setMaximumSize(new java.awt.Dimension(214, 32767));
        menuList.setMinimumSize(new java.awt.Dimension(214, 0));

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 898, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuTop, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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

        chat.setPreferredSize(new java.awt.Dimension(600, 681));

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chat, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
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

        jMenuItemSortir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemSortir.setText("Sortir");
        jMenuItemSortir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSortirActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSortir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Vés a");

        jMenuItemPrincipal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemPrincipal.setText("Principal");
        jMenuItemPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrincipalActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemPrincipal);

        jMenuItemGrup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemGrup.setText("Xats Grupals");
        jMenuItemGrup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGrupActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemGrup);

        jMenuItemHistorial.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemHistorial.setText("Historial");
        jMenuItemHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHistorialActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemHistorial);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Configuració");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("BBDD");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

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

    private void jMenuItemSortirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSortirActionPerformed
        exit(0);
    }//GEN-LAST:event_jMenuItemSortirActionPerformed

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
            mostrarGrup();
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

    private void jMenuItemGrupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGrupActionPerformed
        if (!BtnUsuaris.isSelected()) {
            BtnXat.setSelected(false);
            BtnUsuaris.setSelected(true);
            BtnHistorial.setSelected(false);
            mostrarGrup();
        }
    }//GEN-LAST:event_jMenuItemGrupActionPerformed

    private void jMenuItemPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrincipalActionPerformed
        if (!BtnXat.isSelected()) {
            BtnXat.setSelected(true);
            BtnUsuaris.setSelected(false);
            BtnHistorial.setSelected(false);
            mostrarXat();
        }
    }//GEN-LAST:event_jMenuItemPrincipalActionPerformed

    private void jMenuItemHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHistorialActionPerformed
        if (!BtnHistorial.isSelected()) {
            BtnXat.setSelected(false);
            BtnUsuaris.setSelected(false);
            BtnHistorial.setSelected(true);
            mostrarHistorial();
        }
    }//GEN-LAST:event_jMenuItemHistorialActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //Obrir JFrame
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void init() {
        //editar scroll bar
        sp.setVerticalScrollBar(new ScrollBar());

        //Assignar tamany del Frame
        int x = 800;
        int y = 750;

        setSize(x, y);
        setLocationRelativeTo(null);

        //mostrem els xats a l'obrir
        mostrarXat();
    }

    private void mostrarXat() {
        menuList.removeAll();
        menuList.setLayout(new BoxLayout(menuList, BoxLayout.Y_AXIS));

        List<String> llistaUsuaris = Connexio.obtenirLlistatUsuaris();
        for (String usuari : llistaUsuaris) {
            
            Usuari u = dades.Connexio.obtenirUsuariPerId(usuari);
            
            Item_People itemPeople = new Item_People(u);
            menuList.add(itemPeople);
            itemPeople.setBounds(0, menuList.getComponentCount() * 50, 213, 10);
            
            itemPeople.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    chat.chat_Title.setUserName(u);
                    chat.chat_Body.clearChat();
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(new Color(242, 242, 242));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(new Color(230, 230, 230));
                }
            });
        }

        refreshMenuList();
    }

    private void mostrarGrup() {
        menuList.removeAll();
        menuList.setLayout(new BoxLayout(menuList, BoxLayout.Y_AXIS));
        for (int i = 0; i < 1; i++) {
            Item_People itemPeople = new Item_People("Grup");
            menuList.add(itemPeople);
            itemPeople.setBounds(0, i * 50, 213, 50);
            
            itemPeople.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    chat.chat_Title.setUserName("Grup");
                    chat.chat_Body.clearChat();
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(new Color(242, 242, 242));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(new Color(230, 230, 230));
                }
            });
        }
        refreshMenuList();
    }

    private void mostrarHistorial() {
        menuList.removeAll();
        menuList.setLayout(new BoxLayout(menuList, BoxLayout.Y_AXIS));
        
        chat.chat_Title.setUserName("Grup");
        chat.chat_Title.setEstatText("Historial");
        chat.chat_Body.clearChat();
        
        calendari.setPreferredSize(new Dimension(245, 170));
        calendari.setMaximumSize(new Dimension(245, 170)); 
        menuList.add(calendari, BorderLayout.CENTER);
        
        refreshMenuList();
    }

    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public componentsExterns.MenuButton BtnHistorial;
    public componentsExterns.MenuButton BtnUsuaris;
    public componentsExterns.MenuButton BtnXat;
    public javax.swing.JPanel Home;
    public javax.swing.JPanel Menu;
    public javax.swing.JLayeredPane MenuTop;
    public javax.swing.JLayeredPane body;
    public componentsExterns.Chat chat;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemGrup;
    private javax.swing.JMenuItem jMenuItemHistorial;
    private javax.swing.JMenuItem jMenuItemPrincipal;
    private javax.swing.JMenuItem jMenuItemSortir;
    private javax.swing.JLayeredPane menuList;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
