package Espacial;

import Herramientas.HerramientasImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Filtros {

    public static BufferedImage calcularGrices(Image image) {
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

        return bi;
    }
}
