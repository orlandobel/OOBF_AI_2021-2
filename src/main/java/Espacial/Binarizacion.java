package Espacial;

import Herramientas.HerramientasImagen;
import gui.ImageFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Binarizacion {

    private ImageFrame iframe;

    public Binarizacion(ImageFrame iframe) {
        this.iframe = iframe;
    }

    public void binarizazar(int umbral) {
        BufferedImage bi = HerramientasImagen.toBufferedImage(iframe.getImagenOriginal());

        Color color;
        Color nColor;

        for(int x=0;x<bi.getWidth();x++) {
            for(int y=0;y<bi.getHeight();y++) {
                color = new Color(bi.getRGB(x,y));

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                int m = r+g+b;
                m/=3;

                nColor = (m<umbral) ? Color.BLACK : Color.WHITE;

                bi.setRGB(x, y, nColor.getRGB());
            }
        }

        iframe.setImage(HerramientasImagen.toImage(bi));
    }

    public void binarizazar(int unegro, int ublanco) {
        BufferedImage bi = HerramientasImagen.toBufferedImage(iframe.getImagenOriginal());

        Color color;
        Color nColor;

        for(int x=0;x<bi.getWidth();x++) {
            for(int y=0;y<bi.getHeight();y++) {
                color = new Color(bi.getRGB(x,y));

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                int m = r+g+b;
                m/=3;

                if(m<unegro)
                    nColor = Color.BLACK;
                else if(m>ublanco)
                    nColor = Color.WHITE;
                else
                    nColor = color;

                bi.setRGB(x, y, nColor.getRGB());
            }
        }

        iframe.setImage(HerramientasImagen.toImage(bi));
    }
}
