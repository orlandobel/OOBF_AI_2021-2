package gui.HistogramaImagen;

import gui.ImageFrame;
import gui.JFramePrincipal;
import listeners.HistogramaImagen.AbrirListener;
import listeners.HistogramaImagen.HistogramaListener;

import javax.swing.*;
import java.awt.*;

public class FramePrincipal extends JFrame {

    private JDesktopPane jdpPrincipal;
    private ImageFrame iframe;

    private JMenuBar menubar;
    private JMenu menuFile;
    private JMenu menuEspacial;
    private JMenuItem itemAbrir;
    private JMenuItem itemHistograma;

    public FramePrincipal() {
        initComponents();
    }

    private void initComponents() {
        jdpPrincipal = new JDesktopPane();
        iframe = new ImageFrame(false);
        iframe.setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout jdpLayout = new GroupLayout((jdpPrincipal));
        jdpPrincipal.setLayout(jdpLayout);
        jdpLayout.setHorizontalGroup(
                jdpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0,720, Short.MAX_VALUE)
        );
        jdpLayout.setVerticalGroup(
                jdpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0,720, Short.MAX_VALUE)
        );

        menubar = new JMenuBar();
        menuFile = new JMenu("Archivo");
        menuEspacial = new JMenu("Espacial");
        itemAbrir = new JMenuItem("Abrir imagen");
        itemHistograma = new JMenuItem("Calcular histogramas");

        itemAbrir.addActionListener(new AbrirListener(iframe));
        menuFile.add(itemAbrir);

        itemHistograma.addActionListener(new HistogramaListener(this, this.iframe));
        menuEspacial.add(itemHistograma);

        menubar.add(menuFile);
        menubar.add(menuEspacial);

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

        jdpPrincipal.add(iframe);

        pack();
    }

    public JDesktopPane getJdp() {
        return this.jdpPrincipal;
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
            @Override
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

}
