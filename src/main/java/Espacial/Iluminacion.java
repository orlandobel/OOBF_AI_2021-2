package Espacial;

import Herramientas.HerramientasImagen;
import gui.ImageFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Iluminacion {

    private ImageFrame iframe;

    public Iluminacion(ImageFrame iframe) {
        this.iframe = iframe;
    }

    public void actualizarIluminacion(int referencia) {
        BufferedImage bi = HerramientasImagen.toBufferedImage(iframe.getImagenOriginal());

        Color oldColor;
        Color newColor;

        for(int x=0; x<bi.getWidth(); x++) {
            for(int y=0; y<bi.getHeight(); y++) {
                oldColor = new Color(bi.getRGB(x, y));

                int r = newValue(oldColor.getRed(), referencia);
                int g = newValue(oldColor.getGreen(), referencia);
                int b = newValue(oldColor.getBlue(), referencia);

                newColor = new Color(r, g, b);

                bi.setRGB(x, y, newColor.getRGB());
            }
        }

        Image newImage = HerramientasImagen.toImage(bi);
        iframe.setImage(newImage);
    }

    private int newValue(int oldValue, int referencia) {
        int value = oldValue + referencia;

        if(value > 255)
            return 255;
        else if(value < 0)
            return 0;

        return value;
    }
}
