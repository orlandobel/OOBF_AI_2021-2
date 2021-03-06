package gui;

import listeners.InternalFrameListener;

import javax.swing.*;

import java.awt.*;
public class ImageFrame extends JInternalFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Image imagenOriginal;
    private Image imagen;

    private JLabel label;

    private int height;
    private int width;

    private boolean closable;

    private InternalFrameListener listener;

    public ImageFrame(String titulo, Image imagen, InternalFrameListener listener) {
        super(titulo);

        this.imagenOriginal = imagen;
        this.imagen = imagen;
        this.closable = true;
        this.listener = listener;

        InitComponents();
        repaintImage();
    }

    public ImageFrame() {
        this.closable = true;
        InitComponents();
    }

    public ImageFrame(boolean closable) {
        this.closable = closable;
        InitComponents();
    }

    private void InitComponents() {
        setBackground(new Color(102,102,225));
        setClosable(this.closable);
        setIconifiable(this.closable);

        label = new JLabel();

        addInternalFrameListener(listener);

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

    public void setImageOriginal(Image imagen) {
        this.imagenOriginal = imagen;
        this.imagen = imagen;
        this.repaintImage();
    }

    public void reiniciarImagen() {
        this.setImage(this.imagenOriginal);
    }

    public Image getImagenOriginal() {
        return this.imagenOriginal;
    }

    public Image getImage() {
        return this.imagen;
    }
}
