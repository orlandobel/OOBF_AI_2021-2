package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Herramientas.HerramientasImagen;



public class CopiarFrame extends JInternalFrame {

    private ImageFrame internal;
    private JFramePrincipal jfp;

    private JButton button1;
    
    private JLabel labelX;
    private JLabel labelY;
    private JLabel labelAlto;
    private JLabel labelAncho;

    private JTextField textX;
    private JTextField textY;
    private JTextField textAlto;
    private JTextField textAncho;
    
    public CopiarFrame(ImageFrame internal, JFramePrincipal jfp) {
        super("Copiar - "+internal.getTitle());
        this.jfp = jfp;
        this.internal = internal;
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(102,102,225));
        setClosable(true);
        setIconifiable(true);

        labelX = new JLabel();
        labelY = new JLabel();
        labelAlto = new JLabel();
        labelAncho = new JLabel();

        textX = new JTextField();
        textY = new JTextField();
        textAlto = new JTextField();
        textAncho = new JTextField();

        button1 = new JButton();

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int alto = Integer.parseInt(textAlto.getText());
                int ancho = Integer.parseInt(textAncho.getText());
                
                int x1 = Integer.parseInt(textX.getText());
                int y1 = Integer.parseInt(textY.getText());

                if(x1>=0 && x1<internal.getWidth() && y1>=0 && y1<internal.getHeight()) {
                    if(ancho+x1 < internal.getWidth() && alto+y1 < internal.getHeight()) {
                        BufferedImage bi = HerramientasImagen.toBufferedImage(internal.getImage());
                        BufferedImage nbi = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

                        int y2 = 0;

                        for(int i=y1;i<y1+alto;i++) {
                            int x2 = 0;
                            for(int j=x1;j<x1+ancho;j++) {
                                int rgb = bi.getRGB(j, i);
                                nbi.setRGB(x2, y2, rgb);
                                x2++;
                            }
                            y2++;
                        }

                        Image nuevaImagen = HerramientasImagen.toImage(nbi);
                        String titulo = HerramientasImagen.imageName;

                        ImageFrame ifr = new ImageFrame(titulo+" - cortado",nuevaImagen, jfp.getListener());
                        ifr.setVisible(true);
                        jfp.getJdpPrincipal().add(ifr);
                    } else {
                        System.out.println("ERROR 2");
                    }
                } else {
                    System.out.println("ERROR 1");
                }
            }
        });

        labelX.setText("X");
        labelY.setText("Y");
        labelAlto.setText("Alto");
        labelAncho.setText("Ancho");
        
        button1.setText("Copiar");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelAlto, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(textAlto, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addComponent(labelAncho, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(textAncho, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelX, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(textX, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addComponent(labelY, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(textY, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelAlto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textAlto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelAncho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textAncho, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelX, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textX, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelY, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textY, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(5, Short.MAX_VALUE))
        );

        pack();
    }

}
