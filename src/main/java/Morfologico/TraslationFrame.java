package Morfologico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TraslationFrame extends JFrame {
    private JLabel label_t1;
    private JLabel label_t2;

    private JTextField input_t1;
    private JTextField input_t2;

    private JButton btn_aplicar;

    private MorfologicoMain mainclass;

    public TraslationFrame(MorfologicoMain mainclass) {
        this.mainclass = mainclass;
        initComponents();
    }

    private void initComponents() {
        label_t1 = new JLabel("T1:");
        label_t2 = new JLabel("T2");

        input_t1 = new JTextField();
        input_t2 = new JTextField();

        btn_aplicar = new JButton("Aplicar");

        btn_aplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int t1 = Integer.parseInt(input_t1.getText());
                int t2 = Integer.parseInt(input_t2.getText());

                mainclass.traslate(t1, t2);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_t1)
                        .addGap(4,4,4)
                        .addComponent(input_t1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(16,16,16)
                        .addComponent(label_t2)
                        .addGap(4,4,4)
                        .addComponent(input_t2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(8, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_aplicar)
                        .addContainerGap(8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(label_t1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addComponent(input_t1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label_t2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addComponent(input_t2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                        .addGap(8,8,8)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(btn_aplicar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }
}
