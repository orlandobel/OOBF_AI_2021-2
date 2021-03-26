package gui;

import Espacial.Binarizacion;
import Herramientas.HerramientasImagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class FrameBinario extends JInternalFrame {

    private ImageFrame iframe;

    private JButton btn_binarizar;
    private JButton btn_original;
    private JSlider umbralSlider;


    public FrameBinario(ImageFrame iframe) {
        this.iframe = iframe;
        initComponents();
    }

    private void initComponents() {
        umbralSlider = new JSlider();
        btn_binarizar = new JButton();
        btn_original = new JButton();

        setTitle("Imagen Binarizada");
        setClosable(true);
        setIconifiable(true);

        umbralSlider.setMajorTickSpacing(10);
        umbralSlider.setMaximum(255);
        umbralSlider.setPaintLabels(true);
        umbralSlider.setPaintTicks(true);
        umbralSlider.setMinimum(15);

        btn_binarizar.setText("Convertir");
        btn_original.setText("Imagen original");

        btn_binarizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                binarizar();
            }
        });

        btn_original.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colocarOriginal();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(umbralSlider, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btn_binarizar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_original, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(umbralSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18,18,18)
                        .addComponent(btn_binarizar)
                        .addGap(0,17,Short.MAX_VALUE)
                        .addComponent(btn_original)
                        .addGap(0,17, Short.MAX_VALUE))
        );

        pack();
    }

    private void binarizar() {
        int u = umbralSlider.getValue();
        Image image = Binarizacion.binarizazar(u, iframe.getImagenOriginal());
        iframe.setImage(image);
    }

    private void colocarOriginal() {
        iframe.reiniciarImagen();
    }
}
