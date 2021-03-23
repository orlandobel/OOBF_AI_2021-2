package gui;

import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameEvent;

import listeners.*;

import java.awt.*;

public class JFramePrincipal extends JFrame  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JDesktopPane jdpPrincipal;
    private InternalFrameListener internalListener;
    private ImageFrame activeImageFrame;
    
    private JMenuBar menubar;

    private JMenu menuFile;
    private JMenu menuEspacial;

    private JMenuItem itemAbrirImagen;
    private JMenuItem itemModificar;
    private JMenuItem itemCopiar;
    private JMenuItem itemHistogramaCompleto;
    private JMenuItem itemFiltros;
    private JMenuItem itemBinarizar;

    private ModificarImagenListener mlistener;

    public JFramePrincipal () {
        internalListener = new InternalFrameListener(this);
        initComponents();
    }

    private void initComponents() {
        mlistener = new ModificarImagenListener(this);
        jdpPrincipal = new JDesktopPane();

        menubar = new JMenuBar();

        menuFile = new JMenu();
        menuEspacial = new JMenu();

        itemAbrirImagen = new JMenuItem();
        itemModificar = new JMenuItem();
        itemCopiar = new JMenuItem();
        itemHistogramaCompleto = new JMenuItem();
        itemFiltros = new JMenuItem();
        itemBinarizar = new JMenuItem();


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout jDeskGroupLayout = new GroupLayout(jdpPrincipal);
        jdpPrincipal.setLayout(jDeskGroupLayout);
        jDeskGroupLayout.setHorizontalGroup(
            jDeskGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jDeskGroupLayout.setVerticalGroup(
            jDeskGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        menuFile.setText("Imagen");
        itemAbrirImagen.setText("Abrir imagen");
        itemAbrirImagen.setName("open");
        itemAbrirImagen.addActionListener(new ImageFrameListener(this));
        menuFile.add(itemAbrirImagen);

        menuEspacial.setText("Espacial");
        itemModificar.setText("Modificar pixeles");
        itemModificar.setName("mp"); // mp == modificar pixeles
        itemModificar.addActionListener(mlistener);
        menuEspacial.add(itemModificar);

        itemCopiar.setText("Copiar fragmento de imagen");
        itemCopiar.setName("copiar");
        itemCopiar.addActionListener(mlistener);
        menuEspacial.add(itemCopiar);

        itemHistogramaCompleto.setText("Histograma");
        itemHistogramaCompleto.setName("histograma");
        itemHistogramaCompleto.addActionListener(mlistener);
        menuEspacial.add(itemHistogramaCompleto);

        itemFiltros.setText("Filtros");
        itemFiltros.setName("filtros");
        itemFiltros.addActionListener(mlistener);
        menuEspacial.add(itemFiltros);

        itemBinarizar.setText("Binarizar imagen");
        itemBinarizar.setName("binarizar");
        itemBinarizar.addActionListener(mlistener);
        menuEspacial.add(itemBinarizar);

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

        pack();
    }

    public JDesktopPane getJdpPrincipal() {
        return this.jdpPrincipal;
    }

    public InternalFrameListener getListener() {
        return this.internalListener;
    }

    public ImageFrame getActiveImageFrame() {
        return this.activeImageFrame;
    }

    public void setActiveImageFrame(ImageFrame iframe) {
        this.activeImageFrame = iframe;
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
