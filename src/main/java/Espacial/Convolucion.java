package Espacial;

import Herramientas.HerramientasImagen;

import java.awt.Image;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Convolucion {

    public static Image convolucion(Image image, int offset, int divisor, int[][] kernel) {
        BufferedImage bi = HerramientasImagen.toBufferedImage(image);
        BufferedImage nbi = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int x=0; x<bi.getWidth(); x++) {
            for(int y=0; y<bi.getHeight(); y++) {
                int rgb = nuevoTono(x, y, bi, kernel, offset, divisor);

                nbi.setRGB(x, y, rgb);
            }
        }

        return HerramientasImagen.toImage(nbi);
    }

    private static int nuevoTono(int x, int y, BufferedImage bi, int[][] kernel, int offset, int divisor) {
        Color color;
        Color ncolor;

        int r = 0;
        int g = 0;
        int b = 0;

        for(int i=0, f = x-1; i<kernel.length; i++, f++) {
            for(int j=0, c=y-1; j<kernel[0].length; j++, c++) {
                if(kernel[i][j] != 0) {
                    try {
                        color = new Color(bi.getRGB(f, c));
                        int valor = kernel[i][j];

                        r += color.getRed()*valor;
                        g += color.getGreen()*valor;
                        b += color.getBlue()*valor;

                    } catch(Exception e) {

                    }
                }
            }
        }

        if(divisor != 0) {
            r /= divisor;
            g /= divisor;
            b /= divisor;
        }

        r = validar(r+offset);
        g = validar(g+offset);
        b = validar(b+offset);

        ncolor = new Color(r, g, b);

        return ncolor.getRGB();
    }

    private static int validar(int tono) {
        if(tono > 255)
            return 255;

        if(tono < 0)
            return 0;

        return tono;
    }
}
