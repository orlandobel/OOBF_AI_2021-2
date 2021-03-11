package gui;

import javax.swing.*;

import java.awt.*;
public class ImageFrame extends JInternalFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Image imagen;

    private JLabel label;

    private int height;
    private int width;

    public ImageFrame(Image imagen) {
        this.imagen = imagen;
        InitComponents();
        repaintImage();
    }

    public ImageFrame() {
        InitComponents();
    }

    private void InitComponents() {
        height = 100;
        width = 100;

        setBackground(new Color(102,102,225));
        setClosable(true);
        setIconifiable(true);

        label = new JLabel();

        this.setSize(width, height);
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

    public void setImage(Image imagen) {
        this.imagen = imagen;
        this.repaintImage();
    }

    public Image getImage() {
        return this.imagen;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public static void main(String[] args) {
        new ImageFrame();
    }
}
