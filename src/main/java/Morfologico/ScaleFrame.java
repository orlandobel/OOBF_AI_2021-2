package Morfologico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScaleFrame extends JFrame {
    private JLabel label_x;
    private JLabel label_y;

    private JTextField input_x;
    private JTextField input_y;

    private JButton btn_scale;

    private MorfologicoMain mainclass;

    public ScaleFrame(MorfologicoMain mainclass) {
        this.mainclass = mainclass;
        initComponents();
    }

    private void initComponents() {
        label_x = new JLabel("Escalar x:");
        label_y = new JLabel("Escalar Y");

        input_x = new JTextField();
        input_y = new JTextField();

        btn_scale = new JButton("Aplicar");

        btn_scale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nx = Integer.parseInt(input_x.getText());
                int ny = Integer.parseInt(input_y.getText());

                mainclass.scale(nx, ny);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_x)
                        .addGap(4,4,4)
                        .addComponent(input_x, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(16,16,16)
                        .addComponent(label_y)
                        .addGap(4,4,4)
                        .addComponent(input_y, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(8, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_scale)
                        .addContainerGap(8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(label_x, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addComponent(input_x, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_y, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addComponent(input_y, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                        .addGap(8,8,8)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(btn_scale, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }
}
