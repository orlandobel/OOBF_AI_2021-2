package gui;

import Espacial.Convolucion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConvolucionFrame extends JInternalFrame {
    private JFramePrincipal jfp;

    private JSpinner s00;
    private JSpinner s01;
    private JSpinner s02;
    private JSpinner s10;
    private JSpinner s11;
    private JSpinner s12;
    private JSpinner s20;
    private JSpinner s21;
    private JSpinner s22;

    private JLabel labelOffset;
    private JLabel labelDivisor;

    private JSpinner sOffset;
    private JSpinner sDivisor;

    private JPanel panelKernel;
    private JPanel panelModificadores;

    private JButton btnConvolucion;
    private JButton btnReiniciarMatriz;

    public ConvolucionFrame(JFramePrincipal jfp) {
        this.jfp = jfp;
        initComponents();
    }

    private void initComponents() {
        setClosable(true);
        setIconifiable(true);

        s00 = new JSpinner();
        s01 = new JSpinner();
        s02 = new JSpinner();
        s10 = new JSpinner();
        s11 = new JSpinner();
        s12 = new JSpinner();
        s20 = new JSpinner();
        s21 = new JSpinner();
        s22 = new JSpinner();

        labelOffset = new JLabel();
        labelDivisor = new JLabel();

        sOffset = new JSpinner();
        sDivisor = new JSpinner();

        panelKernel = new JPanel();
        panelModificadores = new JPanel();

        labelOffset.setText("Offset:");
        labelDivisor.setText("Divisor:");

        btnConvolucion = new JButton();
        btnReiniciarMatriz = new JButton();

        btnConvolucion.setText("Aplicar");
        btnConvolucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicar();
            }
        });

        btnReiniciarMatriz.setText("Reiniciar matriz");
        btnReiniciarMatriz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciar();
            }
        });

        GroupLayout panelLayout = new GroupLayout(panelKernel);
        panelKernel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelLayout.createSequentialGroup()

                        .addComponent(s00, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s01, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s02, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(s10, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s11, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s12, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(s20, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s21, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s22, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(s00, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s10, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s20, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(s01, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s11, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s21, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(s02, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s12, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s22, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout mlayout = new GroupLayout(panelModificadores);
        panelModificadores.setLayout(mlayout);
        mlayout.setHorizontalGroup(
                mlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(mlayout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(labelOffset, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addGap(8,8,8)
                        .addComponent(sOffset, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                        .addGap(8,8,8)
                        .addComponent(labelDivisor, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addGap(8,8,8)
                        .addComponent(sDivisor, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
        );
        mlayout.setVerticalGroup(
                mlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelOffset, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(sOffset, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelDivisor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(sDivisor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(panelKernel)
                        .addContainerGap(16, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(panelModificadores)
                        .addContainerGap(16, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(btnConvolucion, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(8,8,8)
                        .addComponent(btnReiniciarMatriz, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))

        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(panelKernel)
                        .addGap(8,8,8)
                        .addComponent(panelModificadores)
                        .addGap(8,8,8)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(btnConvolucion, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(8,8,8)
                                .addComponent(btnReiniciarMatriz, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(16, Short.MAX_VALUE)
                )
                .addComponent(panelKernel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void reiniciar() {
        s00.setValue(0);
        s01.setValue(0);
        s02.setValue(0);
        s10.setValue(0);
        s11.setValue(0);
        s12.setValue(0);
        s20.setValue(0);
        s21.setValue(0);
        s22.setValue(0);
        sOffset.setValue(0);
        sDivisor.setValue(0);
    }

    private void aplicar() {
        ImageFrame iframe = jfp.getActiveImageFrame();
        Image io = iframe.getImagenOriginal();
        int offset = Integer.parseInt(sOffset.getValue().toString());
        int divisor = Integer.parseInt(sDivisor.getValue().toString());
        int[][] kernel = {
                {
                    Integer.parseInt(s00.getValue().toString()),
                    Integer.parseInt(s01.getValue().toString()),
                    Integer.parseInt(s02.getValue().toString()),
                },
                {
                        Integer.parseInt(s10.getValue().toString()),
                        Integer.parseInt(s11.getValue().toString()),
                        Integer.parseInt(s12.getValue().toString()),
                },
                {
                        Integer.parseInt(s20.getValue().toString()),
                        Integer.parseInt(s21.getValue().toString()),
                        Integer.parseInt(s22.getValue().toString()),
                }
        };

        Image ni = Convolucion.convolucion(io, offset, divisor, kernel);
        iframe.setImage(ni);
    }
}
