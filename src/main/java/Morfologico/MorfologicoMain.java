package Morfologico;

import Herramientas.HerramientasImagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MorfologicoMain {
    private int[] position;

    private int heigth;
    private int width;

    private Image background;
    private BufferedImage canbas;
    private BufferedImage image;

    private FrameMorfologico frame;

    public MorfologicoMain() {
        position = new int[2];
       initComponents();
    }

    private BufferedImage initbackground() {
        BufferedImage bf_bg = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);
        int white = Color.WHITE.getRGB();

        for(int i=0;i<width;i++) {
            for(int j=0;j<heigth;j++) {
                bf_bg.setRGB(i,j, white);
            }
        }

        return bf_bg;
    }

    private void initComponents() {
        this.heigth = 700;
        this.width = 700;

        this.canbas = initbackground();
        this.background = HerramientasImagen.toImage(canbas);

        this.frame = new FrameMorfologico(background);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu();
        JMenu morfMenu = new JMenu();

        JMenuItem openImageItem = new JMenuItem();
        JMenuItem traslateItem = new JMenuItem();
        JMenuItem rotateItem = new JMenuItem();
        JMenuItem scaleItem = new JMenuItem();

        fileMenu.setText("File");
        morfMenu.setText("Transformar");

        openImageItem.setText("Open");
        fileMenu.add(openImageItem);

        traslateItem.setText("Trasladar");
        rotateItem.setText("Rotar");
        scaleItem.setText("Escalar");

        morfMenu.add(traslateItem);
        morfMenu.add(rotateItem);
        morfMenu.add(scaleItem);

        menubar.add(fileMenu);
        menubar.add(morfMenu);

        frame.setJMenuBar(menubar);

        openImageItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openImage();
            }
        });

        traslateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTraslationFrame();
            }
        });

        rotateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRotationFrame();
            }
        });

        scaleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openScaleFrame();
            }
        });
    }

    private void openTraslationFrame() {
        new TraslationFrame(this).setVisible(true);
    }

    private void openRotationFrame() {
        new RotationFrame(this).setVisible(true);
    }

    private void openScaleFrame() {
        new ScaleFrame(this).setVisible(true);
    }

    private void openImage() {
        canbas = HerramientasImagen.toBufferedImage(background);
        Image image = HerramientasImagen.openImage();
        this.image = HerramientasImagen.toBufferedImage(image);

        for(int x=0; x<this.image.getWidth(); x++) {
            for(int y=0; y<this.image.getHeight(); y++) {
                try {
                    int color = this.image.getRGB(x, y);

                    canbas.setRGB(x, y, color);
                } catch(Exception e) {

                }
            }
        }

        frame.setImagen(this.canbas);
    }

    public void traslate(int t1, int t2) {
        canbas = HerramientasImagen.toBufferedImage(background);
        position[0] += t1;
        position[1] += t2;

        this.canbas = Transformer.traslate(position[0], position[1], canbas, image);

        frame.setImagen(canbas);
    }

    public void rotate(int degrees) {
        canbas = HerramientasImagen.toBufferedImage(background);

        canbas = Transformer.rotate(position[0], position[1], canbas, image, degrees);

        frame.setImagen(canbas);
    }

    public void scale(int nx, int ny) {
        canbas = HerramientasImagen.toBufferedImage(background);

        canbas = Transformer.scale(position[0], position[1], canbas, image, nx, ny);

        frame.setImagen(canbas);
    }

    public static void main(String[] args) {
        MorfologicoMain mm = new MorfologicoMain();
        //mm.setVisible(true);
        /*Image io = HerramientasImagen.openImage();
        BufferedImage bfio = HerramientasImagen.toBufferedImage(io);
        FrameMorfologico frae1 = new FrameMorfologico(io);
        frae1.setVisible(true);

        BufferedImage bfit = Traslacion.trasladar(600,250, bfio);
        Image it = HerramientasImagen.toImage(bfit);
        FrameMorfologico frame2 = new FrameMorfologico(it);
        frame2.setVisible(true);*/
    }
}
