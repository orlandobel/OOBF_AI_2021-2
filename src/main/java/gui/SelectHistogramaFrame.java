package gui;

import Espacial.Histograma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectHistogramaFrame extends JInternalFrame implements ActionListener {

    private JCheckBox cb_rojo;
    private JCheckBox cb_verde;
    private JCheckBox cb_azul;
    private JCheckBox cb_grices;

    private JButton btn_histograma;

    private JFramePrincipal jfp;

    public SelectHistogramaFrame(JFramePrincipal jfp) {
        this.jfp = jfp;
        initComponents();
    }

    public SelectHistogramaFrame() {
        initComponents();
    }

    private void initComponents() {

        setClosable(true);
        setIconifiable(true);

        cb_rojo = new JCheckBox();
        cb_verde = new JCheckBox();
        cb_azul = new JCheckBox();
        cb_grices = new JCheckBox();

        btn_histograma = new JButton();

        cb_rojo.setText("Rojo");
        cb_verde.setText("Verde");
        cb_azul.setText("Azul");
        cb_grices.setText("Grices");

        cb_rojo.setSelected(true);
        cb_verde.setSelected(true);
        cb_azul.setSelected(true);
        cb_grices.setSelected(false);

        btn_histograma.setText("Calcular histograma");
        btn_histograma.addActionListener(this);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(cb_rojo, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(cb_verde, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(cb_azul, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(cb_grices, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btn_histograma, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_rojo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_verde, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_azul, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_grices))
                .addGap(18,18,18)
                .addComponent(btn_histograma)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String[] args) {
        SelectHistogramaFrame shf  = new SelectHistogramaFrame();
        shf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        shf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        ImageFrame iframe = jfp.getActiveImageFrame();
        String title = iframe.getTitle().split("\\.")[0];

        ArrayList<Color> colores = new ArrayList<>();
        Histograma h = new Histograma(iframe.getImage());
        HistogramaFrame hf = new HistogramaFrame();

        h.calcularHistogramas();

        if(cb_rojo.isSelected()) {
            hf.agregarSerie("Rojo", h.getR());
            colores.add(Color.RED);
        }

        if(cb_verde.isSelected()) {
            hf.agregarSerie("Verde", h.getG());
            colores.add(Color.GREEN);
        }

        if(cb_azul.isSelected()) {
            hf.agregarSerie("Azul", h.getB());
            colores.add(Color.BLUE);
        }

        if(cb_grices.isSelected()) {
            hf.agregarSerie("Grices", h.getGr());
            colores.add(Color.GRAY);
        }

        Color[] clrs = new Color[colores.size()];
        colores.toArray(clrs);

        hf.crearGrafica(clrs, title);
        hf.mostrarGrafica();

        this.jfp.getJdpPrincipal().add(hf);

        try {
            hf.setSelected(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
