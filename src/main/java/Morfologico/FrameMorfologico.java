package Morfologico;

import Herramientas.HerramientasImagen;
import listeners.InternalFrameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameMorfologico extends JFrame {
    private static final long serialVersionUID = 1L;

    private Image imagen;

    private JLabel label;

    private int height;
    private int width;

    public FrameMorfologico(Image imagen) {
        this.imagen = imagen;
        InitComponents();
        repaintImage();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public FrameMorfologico(int height, int width, String title) {
        this.height = height;
        this.width = width;
        InitComponents();

        setTitle(title);
    }

    private void InitComponents() {
        setBackground(new Color(102,102,225));
        label = new JLabel();

        this.add(this.label);
        this.repaint();
    }

    private void repaintImage() {
        height = imagen.getHeight(null);
        width = imagen.getWidth(null);

        ImageIcon ic = new ImageIcon(imagen);
        label.setIcon(ic);
        label.setBounds(0, 0, width, height);
        label.repaint();

        this.setSize(width, height);
        this.repaint();
    }

    public void setImagen(BufferedImage buffer) {
        Image imagen = HerramientasImagen.toImage(buffer);
        this.imagen = imagen;
        repaintImage();
    }

    public void setImage(Image imagen) {
        this.imagen = imagen;
        this.repaintImage();
    }

    public Image getImage() {
        return this.imagen;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
