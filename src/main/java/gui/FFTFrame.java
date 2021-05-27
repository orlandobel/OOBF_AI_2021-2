package gui;

import listeners.InternalFrameListener;

import javax.swing.*;
import java.awt.*;

public class FFTFrame extends JFrame {

    /**
     *
     */
   private JLabel label_imagen;

    public FFTFrame(Image imagen) {
        initComponents();
        this.label_imagen.setIcon(new ImageIcon(imagen));
        setSize(imagen.getWidth(null),imagen.getHeight(null));
    }

    private void initComponents() {

        label_imagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label_imagen)
                                .addGap(0, 400, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(label_imagen)
                                .addGap(0, 300, Short.MAX_VALUE))
        );

        pack();
    }
}
