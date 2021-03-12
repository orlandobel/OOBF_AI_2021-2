package gui;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import listeners.CopiarActionListener;
import listeners.InternalFrameListener;
import listeners.ModificarImagenListener;

public class JFramePrincipal extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JDesktopPane jdpPrincipal;
    
    private JMenuBar menubar;
    private JMenu menu1;
    private JMenu menu2;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;

    public JFramePrincipal () {
        initComponents();
    }

    private void initComponents() {
        jdpPrincipal = new JDesktopPane();
        menubar = new JMenuBar();
        menu1 = new JMenu();
        menu2 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout jDeskGroupLayout = new GroupLayout(jdpPrincipal);
        jdpPrincipal.setLayout(jDeskGroupLayout);
        jDeskGroupLayout.setHorizontalGroup(
            jDeskGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        jDeskGroupLayout.setVerticalGroup(
            jDeskGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );

        menu1.setText("Imagen");
        menuItem1.setText("Abrir imagen");
        menuItem1.addActionListener(new InternalFrameListener(this));
        menu1.add(menuItem1);

        menu2.setText("Espacial");
        menuItem2.setText("Modificar pixeles");
        menuItem2.addActionListener(new ModificarImagenListener(this));
        menu2.add(menuItem2);

        menuItem3.setText("Copiar fragmento de imagen");
        menuItem3.addActionListener(new CopiarActionListener(this));
        menu2.add(menuItem3);

        menubar.add(menu1);
        menubar.add(menu2);

        setJMenuBar(menubar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal)
        );

        pack();
    }

    public JDesktopPane getJdpPrincipal() {
        return this.jdpPrincipal;
    }

    public void setJdpPrincipal(JDesktopPane jdpPrincipal) {
        this.jdpPrincipal = jdpPrincipal;
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFramePrincipal().setVisible(true);;
            }
        });
    }
    
}
