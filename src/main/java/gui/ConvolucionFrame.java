package gui;

import Espacial.Convolucion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConvolucionFrame extends JInternalFrame {

    private boolean activo;

    private JFramePrincipal jfp;

    private JLabel labelKernel;
    private JComboBox<String> opciones;

    private JPanel panelKernel;

    private JTextField tf00;
    private JTextField tf01;
    private JTextField tf02;
    private JTextField tf10;
    private JTextField tf11;
    private JTextField tf12;
    private JTextField tf20;
    private JTextField tf21;
    private JTextField tf22;

    private JButton btnConvolucion;

    public ConvolucionFrame(JFramePrincipal jfp) {
        this.jfp = jfp;
        activo = false;
        initComponents();
    }

    private void initComponents() {
        setClosable(true);
        setIconifiable(true);

        String[] opt = {
                "Enfocar",
                "Desenfocar",
                "Realzar bordes",
                "Detectar bordes",
                "Repujado",
                "Kernel manual"
        };

        labelKernel = new JLabel();
        opciones = new JComboBox<>(opt);
        btnConvolucion = new JButton();
        panelKernel = new JPanel();

        tf00 = new JTextField();
        tf01 = new JTextField();
        tf02 = new JTextField();
        tf10 = new JTextField();
        tf11 = new JTextField();
        tf12 = new JTextField();
        tf20 = new JTextField();
        tf21 = new JTextField();
        tf22 = new JTextField();

        labelKernel.setText("Kernel de convolución: ");

        opciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(opciones.getSelectedIndex() == 5 && !activo) {
                    cambiarKernel(true);
                } else if(opciones.getSelectedIndex() != 5 && activo) {
                    cambiarKernel(false);
                }
            }
        });

        btnConvolucion.setText("Aplicar");
        btnConvolucion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicar();
            }
        });

        initTextFields(tf00);
        initTextFields(tf01);
        initTextFields(tf02);
        initTextFields(tf10);
        initTextFields(tf11);
        initTextFields(tf12);
        initTextFields(tf20);
        initTextFields(tf21);
        initTextFields(tf22);

        GroupLayout panelLayout = new GroupLayout(panelKernel);
        panelKernel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelLayout.createSequentialGroup()

                        .addComponent(tf00, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf01, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf02, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(tf10, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf11, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf12, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(tf20, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf21, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf22, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tf00, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf10, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf20, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tf01, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf11, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf21, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tf02, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf12, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(tf22, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(labelKernel, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(opciones, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(16, Short.MAX_VALUE)
                        )
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(btnConvolucion, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(16, Short.MAX_VALUE)
                        ))
                .addComponent(panelKernel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(16, Short.MAX_VALUE))

        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(labelKernel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(opciones, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(8, 8, 8)
                        .addComponent(btnConvolucion, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE)
                )
                .addComponent(panelKernel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void initTextFields(JTextField tf) {
        tf.setEditable(false);
        tf.setText("0");
        tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!Character.isDigit(e.getKeyChar()) &&
                        e.getKeyChar() != '-')
                    e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void cambiarKernel(boolean estado) {
        tf00.setEditable(estado);
        tf01.setEditable(estado);
        tf02.setEditable(estado);
        tf10.setEditable(estado);
        tf11.setEditable(estado);
        tf12.setEditable(estado);
        tf20.setEditable(estado);
        tf21.setEditable(estado);
        tf22.setEditable(estado);

        activo = estado;
    }

    private void aplicar() {
        ImageFrame iframe = jfp.getActiveImageFrame();
        Image image = iframe.getImage();
        int[][] kernel = null;

        if(opciones.getSelectedIndex() == 5) {
            kernel = new int[3][3];

            kernel[0][0] = Integer.parseInt(tf00.getText());
            kernel[0][1] = Integer.parseInt(tf01.getText());
            kernel[0][2] = Integer.parseInt(tf02.getText());
            kernel[1][0] = Integer.parseInt(tf10.getText());
            kernel[1][1] = Integer.parseInt(tf11.getText());
            kernel[1][2] = Integer.parseInt(tf12.getText());
            kernel[2][0] = Integer.parseInt(tf20.getText());
            kernel[2][1] = Integer.parseInt(tf21.getText());
            kernel[2][2] = Integer.parseInt(tf22.getText());
        }

        Image nimage = Convolucion.convolucion(image, opciones.getSelectedIndex(), 0, kernel);
        iframe.setImage(nimage);
    }
}
