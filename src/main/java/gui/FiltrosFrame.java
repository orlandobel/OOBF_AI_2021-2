package gui;

import Herramientas.HerramientasImagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class FiltrosFrame extends JInternalFrame {

    private JFramePrincipal jfp;

    private JButton btn_negativo;
    private JButton btn_grices;
    private JButton btn_bw; // Boton para filtro de blanco y negro (black and white)


    public FiltrosFrame(JFramePrincipal jfp) {
        super("Filtros");
        this.jfp = jfp;
        initComponents();
    }

    private void initComponents() {
        btn_negativo = new JButton();
        btn_grices = new JButton();
        btn_bw = new JButton();

        btn_negativo.setText("Negativo");
        btn_grices.setText("Escala de grices");
        btn_bw.setText("Blanco y Negro");

        setClosable(true);
        setIconifiable(true);

        btn_negativo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_negativoActionPermorm(e);
            }
        });

        btn_grices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_gricesActionPermorm(e);
            }
        });

        btn_bw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_bwActionPermorm(e);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btn_negativo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btn_grices, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btn_bw, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btn_negativo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addGap(8,8,8)
                    .addComponent(btn_grices, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addGap(8,8,8)
                    .addComponent(btn_bw, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addGap(8,8,8))
        );

        pack();
    }

    private void btn_negativoActionPermorm(ActionEvent e) {
        ImageFrame iframe = jfp.getActiveImageFrame();
        Image image = iframe.getImage();

        BufferedImage bi = HerramientasImagen.toBufferedImage(image);
        Color color;

        for(int x=0;x<image.getWidth(null);x++) {
            for(int y=0;y<image.getHeight(null);y++) {
                int rgb = bi.getRGB(x, y);
                color  = new Color(rgb);

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                bi.setRGB(x, y, new Color(255-r, 255-g, 255-b).getRGB());
            }
        }

        image = HerramientasImagen.toImage(bi);
        iframe.setImage(image);
    }

    private void btn_gricesActionPermorm(ActionEvent e) {
        ImageFrame iframe = jfp.getActiveImageFrame();
        Image image = iframe.getImage();

        BufferedImage bi = HerramientasImagen.toBufferedImage(image);
        Color color;

        for(int x=0;x<image.getWidth(null);x++) {
            for(int y=0;y<image.getHeight(null);y++) {
                int rgb = bi.getRGB(x, y);
                color = new Color(rgb);

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                int m = (r + g + b) / 3;
                bi.setRGB(x, y, new Color(m, m, m).getRGB());
            }
        }

        image = HerramientasImagen.toImage(bi);
        iframe.setImageOriginal(image);
    }

    private void btn_bwActionPermorm(ActionEvent e) {
        ImageFrame iframe = jfp.getActiveImageFrame();
        Image image = iframe.getImage();

        BufferedImage bi = HerramientasImagen.toBufferedImage(image);
        Color color;
        Color c;

        for(int x=0;x<image.getWidth(null);x++) {
            for(int y=0;y<image.getHeight(null);y++) {
                int rgb = bi.getRGB(x, y);
                color  = new Color(rgb);

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                int s = r+g+b;

                c = (s>380)? Color.WHITE : Color.BLACK;

                bi.setRGB(x, y, c.getRGB());
            }
        }

        image = HerramientasImagen.toImage(bi);
        iframe.setImage(image);
    }
}
