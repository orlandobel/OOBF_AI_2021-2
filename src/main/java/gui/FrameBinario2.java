package gui;

import Espacial.Binarizacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameBinario2 extends JInternalFrame {
    private ImageFrame iframe;

    private JButton btn_binarizar;
    private JButton btn_original;

    private JLabel labelNegro;
    private JLabel labelBlanco;

    private JSlider umbralSlider1;
    private JSlider umbralSlider2;

    private Binarizacion binarizador;

    public FrameBinario2(ImageFrame iframe) {
        this.iframe = iframe;
        this.binarizador = new Binarizacion(iframe);
        initComponents();
    }

    private void initComponents() {
        labelNegro = new JLabel();
        labelBlanco = new JLabel();

        umbralSlider1 = new JSlider();
        umbralSlider2 = new JSlider();

        btn_binarizar = new JButton();
        btn_original = new JButton();

        setTitle("Imagen Binarizada");
        setClosable(true);
        setIconifiable(true);

        labelNegro.setText("Umbral para negros");
        labelNegro.setHorizontalAlignment(SwingConstants.CENTER);
        labelNegro.setVerticalAlignment(SwingConstants.CENTER);

        labelBlanco.setText("Umbral para blancos");
        labelBlanco.setHorizontalAlignment(SwingConstants.CENTER);
        labelBlanco.setVerticalAlignment(SwingConstants.CENTER);

        umbralSlider1.setMajorTickSpacing(10);
        umbralSlider1.setMaximum(255);
        umbralSlider1.setPaintLabels(true);
        umbralSlider1.setPaintTicks(true);
        umbralSlider1.setMinimum(0);

        umbralSlider2.setMajorTickSpacing(10);
        umbralSlider2.setMaximum(255);
        umbralSlider2.setPaintLabels(true);
        umbralSlider2.setPaintTicks(true);
        umbralSlider2.setMinimum(0);

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
                        .addComponent(labelNegro, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(umbralSlider1, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                                .addContainerGap(18, Short.MAX_VALUE))

                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelBlanco, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                                .addContainerGap(18, Short.MAX_VALUE))

                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(umbralSlider2, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                                .addContainerGap(18, Short.MAX_VALUE))

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
                                .addGap(18,18,18)
                                .addComponent(labelNegro, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5,5,5)
                                .addComponent(umbralSlider1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18,18,18)
                                .addComponent(labelBlanco, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5,5,5)
                                .addComponent(umbralSlider2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18,18,18)
                                .addComponent(btn_binarizar)
                                .addGap(0,17,Short.MAX_VALUE)
                                .addComponent(btn_original)
                                .addGap(0,17, Short.MAX_VALUE))
        );

        pack();
    }

    private void binarizar() {
        int u1 = umbralSlider1.getValue();
        int u2 = umbralSlider2.getValue();

        Image image = binarizador.binarizazar(u1, u2, iframe.getImagenOriginal());
        iframe.setImage(image);
    }

    private void colocarOriginal() {
        iframe.reiniciarImagen();
    }
}
