package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import Herramientas.HerramientasImagen;

import java.awt.Image;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class ModificarFrame extends JInternalFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ImageFrame internal;

    private JButton button1;
    private JTextField textFieldX;
    private JTextField textFieldY;

    public ModificarFrame(ImageFrame internal) {
        super("Modificar - "+internal.getTitle());
        this.internal = internal;
        initComponents();
    }

    private void initComponents() {
        textFieldX = new JTextField();
        textFieldY = new JTextField();

        button1 = new JButton();

        setBackground(new Color(102,102,225));
        setClosable(true);
        setIconifiable(true);

        button1.setText("Modificar");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textFieldX, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textFieldY, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldX, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldY, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        this.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(textFieldX.getText());
                int y = Integer.parseInt(textFieldY.getText());
                
                BufferedImage bi = HerramientasImagen.toBufferedImage(internal.getImage());
                Color color = new Color(133,249,45);
                
                for(int j=x;j<x+20;j++) {
                    for(int m=y;m<y+20;m++) {
                        bi.setRGB(j, m, color.getRGB());
                    }
                }

                Image nueva = HerramientasImagen.toImage(bi);
                internal.setImage(nueva);
            }
        });

        pack();
    }
}
