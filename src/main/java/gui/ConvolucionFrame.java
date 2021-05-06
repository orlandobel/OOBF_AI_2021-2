package gui;

import Espacial.Convolucion;
import Espacial.Mascaras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConvolucionFrame extends JInternalFrame {
    private JFramePrincipal jfp;

    private JSpinner s00;
    private JSpinner s01;
    private JSpinner s02;
    private JSpinner s03;
    private JSpinner s04;
    private JSpinner s10;
    private JSpinner s11;
    private JSpinner s12;
    private JSpinner s13;
    private JSpinner s14;
    private JSpinner s20;
    private JSpinner s21;
    private JSpinner s22;
    private JSpinner s23;
    private JSpinner s24;
    private JSpinner s30;
    private JSpinner s31;
    private JSpinner s32;
    private JSpinner s33;
    private JSpinner s34;
    private JSpinner s40;
    private JSpinner s41;
    private JSpinner s42;
    private JSpinner s43;
    private JSpinner s44;

    private JComboBox<String> cmatriz;

    private JLabel labelOffset;
    private JLabel labelDivisor;

    private JSpinner sOffset;
    private JSpinner sDivisor;

    private JPanel panelKernel;
    private JPanel panelModificadores;

    private JButton btnConvolucion;
    private JButton btnKirsch;
    private JButton btnReiniciarMatriz;

    public ConvolucionFrame(JFramePrincipal jfp) {
        this.jfp = jfp;
        initComponents();
    }

    private void initComponents() {
        setClosable(true);
        setIconifiable(true);

        String[] opciones = {
                "Manual",
                "Enfoque",
                "Desenfoque",
                "Realce de bordes",
                "Repujado",
                "Detectar bordes",
                "Filtro tipo sobel",
                "Filtro tipo sharpen",
                "Filtro norte",
                "Filtro este",
                "Filtro de gauss"
        };

        s00 = new JSpinner();
        s01 = new JSpinner();
        s02 = new JSpinner();
        s03 = new JSpinner();
        s04 = new JSpinner();
        s10 = new JSpinner();
        s11 = new JSpinner();
        s12 = new JSpinner();
        s13 = new JSpinner();
        s14 = new JSpinner();
        s20 = new JSpinner();
        s21 = new JSpinner();
        s22 = new JSpinner();
        s23 = new JSpinner();
        s24 = new JSpinner();
        s30 = new JSpinner();
        s31 = new JSpinner();
        s32 = new JSpinner();
        s33 = new JSpinner();
        s34 = new JSpinner();
        s40 = new JSpinner();
        s41 = new JSpinner();
        s42 = new JSpinner();
        s43 = new JSpinner();
        s44 = new JSpinner();

        labelOffset = new JLabel();
        labelDivisor = new JLabel();

        sOffset = new JSpinner();
        sDivisor = new JSpinner();

        cmatriz = new JComboBox(opciones);

        panelKernel = new JPanel();
        panelModificadores = new JPanel();

        btnConvolucion = new JButton();
        btnKirsch = new JButton();
        btnReiniciarMatriz = new JButton();

        labelOffset.setText("Offset:");
        labelDivisor.setText("Divisor:");

        btnConvolucion.setText("Aplicar");
        btnConvolucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicar();
            }
        });

        cmatriz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarMatriz();
            }
        });

        btnKirsch.setText("Aplicar mascara Kirsch");
        btnKirsch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirsch();
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
                        .addComponent(s02, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s03, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s04, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(s10, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s11, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s12, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s13, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s14, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(s20, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s21, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s22, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s23, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s24, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(s30, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s31, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s32, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s33, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s34, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(s40, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s41, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s42, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s43, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s44, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(s20, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s30, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s40, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(s01, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s11, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s21, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s31, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s41, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(s02, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s12, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s22, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s32, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s42, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(s03, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s13, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s23, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s33, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s43, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(s04, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s14, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s24, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s34, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(s44, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(cmatriz)
                    .addContainerGap(16, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(btnConvolucion, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(8,8,8)
                        .addComponent(btnKirsch, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(cmatriz)
                        .addGap(8,8,8)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(btnConvolucion, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(8,8,8)
                                .addComponent(btnKirsch, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
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

    private void cambiarMatriz() {
        int[][] mascara = {
                { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0 }
        };

        switch(cmatriz.getSelectedIndex()) {
            case 1:
                mascara = Mascaras.enfoque;
                break;
            case 2:
                mascara = Mascaras.desenfoque;
                break;
            case 3:
                mascara = Mascaras.realceBordes;
                break;
            case 4:
                mascara = Mascaras.repujado;
                break;
            case 5:
                mascara = Mascaras.detectarBordes;
                break;
            case 6:
                mascara = Mascaras.filtroSobel;
                break;
            case 7:
                mascara = Mascaras.filtroSharpen;
                break;
            case 8:
                mascara = Mascaras.filtroNorte;
                break;
            case 9:
                mascara = Mascaras.filtroEste;
                break;
            case 10:
                mascara = Mascaras.filtroGauss;
                break;
            default: break;
        }

        s00.setValue(mascara[0][0]);
        s01.setValue(mascara[0][1]);
        s02.setValue(mascara[0][2]);
        s03.setValue(mascara[0][3]);
        s04.setValue(mascara[0][4]);
        s10.setValue(mascara[1][0]);
        s11.setValue(mascara[1][1]);
        s12.setValue(mascara[1][2]);
        s13.setValue(mascara[1][3]);
        s14.setValue(mascara[1][4]);
        s20.setValue(mascara[2][0]);
        s21.setValue(mascara[2][1]);
        s22.setValue(mascara[2][2]);
        s23.setValue(mascara[2][3]);
        s24.setValue(mascara[2][4]);
        s30.setValue(mascara[3][0]);
        s31.setValue(mascara[3][1]);
        s32.setValue(mascara[3][2]);
        s33.setValue(mascara[3][3]);
        s34.setValue(mascara[3][4]);
        s40.setValue(mascara[4][0]);
        s41.setValue(mascara[4][1]);
        s42.setValue(mascara[4][2]);
        s43.setValue(mascara[4][3]);
        s44.setValue(mascara[4][4]);
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

    private void kirsch() {
        ImageFrame iframe = jfp.getActiveImageFrame();
        Image io = iframe.getImagenOriginal();

        Image ni = Convolucion.aplicarKirsch(io);
        iframe.setImage(ni);
    }
}
