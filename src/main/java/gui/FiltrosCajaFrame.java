package gui;

import Espacial.MinMax;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FiltrosCajaFrame extends JInternalFrame {

    private JFramePrincipal jfp;

    private JCheckBox check_minimo;
    private JCheckBox check_maximo;

    private JButton btn_aplicar;

    public FiltrosCajaFrame(JFramePrincipal jfp) {
        this.jfp = jfp;
        initComponents();
    }

    private void initComponents() {
        setClosable(true);
        setIconifiable(true);

        check_minimo = new JCheckBox("Filtro minimo");
        check_maximo = new JCheckBox("Filtro maximo");

        btn_aplicar = new JButton("Aplicar");

        btn_aplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicar();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(check_minimo, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                        .addGap(10,10,10)
                        .addComponent(check_maximo, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_aplicar, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(check_minimo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(check_maximo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )
                        .addComponent(btn_aplicar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
        );


        pack();
    }

    private void aplicar() {
        ImageFrame iframe = jfp.getActiveImageFrame();
        Image imagen = iframe.getImagenOriginal();

        boolean min = check_minimo.isSelected();
        boolean max = check_maximo.isSelected();

        Image nimagen = MinMax.filtrar(min, max, imagen);

        iframe.setImage(nimagen);
    }
}
