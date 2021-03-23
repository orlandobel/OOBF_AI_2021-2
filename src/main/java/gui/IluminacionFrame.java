package gui;

import Espacial.Iluminacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IluminacionFrame extends JInternalFrame {

    private JSlider iluminacionSlider;
    private JButton btn_actualizar;
    private JButton btn_reiniciar;

    private JFramePrincipal jfp;
    private Iluminacion iluminacion;

    public IluminacionFrame(JFramePrincipal jfp) {
        this.jfp = jfp;
        iluminacion = new Iluminacion(jfp.getActiveImageFrame());
        initComponents();
    }

    private void initComponents() {
        iluminacionSlider = new JSlider();
        btn_actualizar = new JButton();
        btn_reiniciar = new JButton();

        setTitle("Imagen Binarizada");
        setClosable(true);
        setIconifiable(true);

        iluminacionSlider.setMajorTickSpacing(10);
        iluminacionSlider.setMaximum(255);
        iluminacionSlider.setMinimum(-255);
        iluminacionSlider.setValue(0);

        btn_actualizar.setText("Actualizar iluminacion");
        btn_reiniciar.setText("Imagen reiniciar");

        btn_actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int referencia = iluminacionSlider.getValue();
                iluminacion.actualizarIluminacion(referencia);
            }
        });

        btn_reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfp.getActiveImageFrame().reiniciarImagen();
                iluminacionSlider.setValue(0);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(iluminacionSlider, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btn_actualizar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btn_reiniciar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(iluminacionSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18,18,18)
                                .addComponent(btn_actualizar)
                                .addGap(0,17,Short.MAX_VALUE)
                                .addComponent(btn_reiniciar)
                                .addGap(0,17, Short.MAX_VALUE))
        );

        pack();
    }
}
