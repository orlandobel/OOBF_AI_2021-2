package Morfologico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotationFrame extends JFrame {
    private JLabel label_degrees;
    private  JTextField input_degrees;
    private JButton btn_rotate;

    private MorfologicoMain mainclas;

    public RotationFrame(MorfologicoMain mainclas) {
        this.mainclas = mainclas;
        initComponents();
    }

    private void initComponents() {
        label_degrees = new JLabel("Angulo de rotacion");
        input_degrees = new JTextField();
        btn_rotate = new JButton("Rotar");

        btn_rotate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int degrees = Integer.parseInt(input_degrees.getText());
                mainclas.rotate(degrees);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_degrees)
                        .addGap(4,4,4)
                        .addComponent(input_degrees, GroupLayout.PREFERRED_SIZE,  50, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(8, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_rotate)
                        .addContainerGap(8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(label_degrees, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(input_degrees, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(8,8,8)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(btn_rotate, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(8, Short.MAX_VALUE)
                )
        );

        pack();
    }
}
