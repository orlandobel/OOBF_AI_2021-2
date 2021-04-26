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
import java.awt.event.ActionListener;

public class JFramePrincipal extends JFrame  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JDesktopPane jdpPrincipal;
    private ImageFrame activeImageFrame;
    
    private JMenuBar menubar;

    private JMenu menuFile;
    private JMenu menuEspacial;
    private JMenu submenuBinarizacion;
    private JMenu subsubmenuBinarizacion; // sub sub menu para binarizaciones automaticas

    private JMenuItem itemAbrirImagen;
    private JMenuItem itemGuardar;
    private JMenuItem itemRestaurar;

    private JMenuItem itemModificar;
    private JMenuItem itemCopiar;
    private JMenuItem itemHistogramaCompleto;
    private JMenuItem itemFiltros;
    private JMenuItem itemBinarizar;
    private JMenuItem itemBinarizar2;
    private JMenuItem itemBinarizarAutomatico;
    private JMenuItem itemBinarizarOtsu;
    private JMenuItem itemIluminacion;
    private JMenuItem itemExpancion;
    private JMenuItem itemConvolucion;

    private ImageFrameListener iframeListener;
    private InternalFrameListener internalListener;
    private ModificarImagenListener mlistener;

    public JFramePrincipal () {
        internalListener = new InternalFrameListener(this);
        initComponents();
    }

    private void initComponents() {
        iframeListener = new ImageFrameListener(this);
        mlistener = new ModificarImagenListener(this);
        jdpPrincipal = new JDesktopPane();

        menubar = new JMenuBar();

        menuFile = new JMenu();
        menuEspacial = new JMenu();
        submenuBinarizacion = new JMenu();
        subsubmenuBinarizacion = new JMenu();

        itemAbrirImagen = new JMenuItem();
        itemGuardar = new JMenuItem();
        itemRestaurar = new JMenuItem();
        itemModificar = new JMenuItem();
        itemCopiar = new JMenuItem();
        itemHistogramaCompleto = new JMenuItem();
        itemFiltros = new JMenuItem();
        itemBinarizar = new JMenuItem();
        itemBinarizar2 = new JMenuItem();
        itemBinarizarAutomatico = new JMenuItem();
        itemBinarizarOtsu = new JMenuItem();
        itemIluminacion = new JMenuItem();
        itemExpancion = new JMenuItem();
        itemConvolucion = new JMenuItem();

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
        menuFile.setText("Imágen");
        menuEspacial.setText("Espacial");
        submenuBinarizacion.setText("Binarización");
        subsubmenuBinarizacion.setText("Binarización automatica");
        /* --------------------------------- */

        /* -- items del menu file --*/
        initJMenuItem(itemAbrirImagen, "Abrir imagen", "open", menuFile, iframeListener);
        initJMenuItem(itemGuardar, "Guardar ultimo seleccionado", "save", menuFile, iframeListener);
        initJMenuItem(itemRestaurar, "Restaurar imagen original", "restaurar", menuFile, iframeListener);
        /* -------------------------*/

        /* ----------------------------------------- items del menu espacial -----------------------------------------*/
        initJMenuItem(itemModificar, "Modificar pixeles", "mp", menuEspacial, mlistener);
        initJMenuItem(itemCopiar, "Copiar fragmento de imágen", "copiar", menuEspacial, mlistener);
        initJMenuItem(itemHistogramaCompleto, "Histograma", "histograma", menuEspacial, mlistener);
        initJMenuItem(itemFiltros, "Filtros", "filtros", menuEspacial, mlistener);
        initJMenuItem(itemBinarizar, "Binarizar imágen", "binarizar", submenuBinarizacion, mlistener);
        initJMenuItem(itemBinarizar2, "Binarizar imágen con 2 umbrales", "binarizar2", submenuBinarizacion, mlistener);
        initJMenuItem(itemBinarizarAutomatico, "Método iterativo", "binarizar3", subsubmenuBinarizacion, mlistener);
        initJMenuItem(itemBinarizarOtsu, "Método otsu", "binarizar4", subsubmenuBinarizacion, mlistener);
        initJMenuItem(itemIluminacion, "Iluminacion", "iluminacion", menuEspacial, mlistener);
            /* -- añadiendo submenus -- */
            submenuBinarizacion.add(subsubmenuBinarizacion);
            menuEspacial.add(submenuBinarizacion);
            /* ------------------------ */
        initJMenuItem(itemExpancion, "Expandir imagen", "exp", menuEspacial, mlistener);
        initJMenuItem(itemConvolucion, "Convolucion", "conv", menuEspacial, mlistener);
        /* -----------------------------------------------------------------------------------------------------------*/



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

    private void initJMenuItem(JMenuItem item, String text, String name, JMenu menu, ActionListener listener) {
        item.setText(text);
        item.setName(name);
        item.addActionListener(listener);

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
