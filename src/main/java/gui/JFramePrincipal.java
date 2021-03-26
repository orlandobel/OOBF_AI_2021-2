package gui;

import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

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
    private JMenu submenuBinarizacion;

    private JMenuItem itemAbrirImagen;
    private JMenuItem itemModificar;
    private JMenuItem itemCopiar;
    private JMenuItem itemHistogramaCompleto;
    private JMenuItem itemFiltros;
    private JMenuItem itemBinarizar;
    private JMenuItem itemBinarizar2;
    private JMenuItem itemBinarizarAutomatico;
    private JMenuItem itemIluminacion;

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
        submenuBinarizacion = new JMenu();

        itemAbrirImagen = new JMenuItem();
        itemModificar = new JMenuItem();
        itemCopiar = new JMenuItem();
        itemHistogramaCompleto = new JMenuItem();
        itemFiltros = new JMenuItem();
        itemBinarizar = new JMenuItem();
        itemBinarizar2 = new JMenuItem();
        itemBinarizarAutomatico = new JMenuItem();
        itemIluminacion = new JMenuItem();


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

        /* -- Nombres de menus y submenus -- */
        menuFile.setText("Imagen");
        menuEspacial.setText("Espacial");
        submenuBinarizacion.setText("Binarizacion");
        /* --------------------------------- */

        itemAbrirImagen.setText("Abrir imagen");
        itemAbrirImagen.setName("open");
        itemAbrirImagen.addActionListener(new ImageFrameListener(this));
        menuFile.add(itemAbrirImagen);

        initJMenuItem(itemModificar, "Modificar pixeles", "mp", menuEspacial);
        initJMenuItem(itemCopiar, "Copiar fragmento de imagen", "copiar", menuEspacial);
        initJMenuItem(itemHistogramaCompleto, "Histograma", "histograma", menuEspacial);
        initJMenuItem(itemFiltros, "Filtros", "filtros", menuEspacial);

        menuEspacial.add(submenuBinarizacion);
        initJMenuItem(itemBinarizar, "Binarizar imagen", "binarizar", submenuBinarizacion);
        initJMenuItem(itemBinarizar2, "Binarizar imagen con 2 umbrales", "binarizar2", submenuBinarizacion);
        initJMenuItem(itemBinarizarAutomatico, "Auto binarizar", "binarizar3", submenuBinarizacion);

        initJMenuItem(itemIluminacion, "Iluminacion", "iluminacion", menuEspacial);

        /* -- Añadir menus a la barra de menus -- */
        menubar.add(menuFile);
        menubar.add(menuEspacial);
        /* -------------------------------------- */

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

    private void initJMenuItem(JMenuItem item, String text, String name, JMenu menu) {
        item.setText(text);
        item.setName(name);
        item.addActionListener(mlistener);

        menu.add(item);
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
