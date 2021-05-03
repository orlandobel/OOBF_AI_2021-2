package Espacial;

import Herramientas.HerramientasImagen;

import java.awt.Image;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Convolucion {

    private static int[][] kirsch1 = {{-3, -3, 5}, {-3, 0, 5}, {-3, -3, 5}};
    private static int[][] kirsch2 = {{-3, 5, 5}, {-3, 0, 5}, {-3, -3, -3}};
    private static int[][] kirsch3 = {{5, 5, 5}, {-3, 0, -3}, {-3, -3, -3}};
    private static int[][] kirsch4 = {{5, 5, -3}, {5, 0, -3}, {-3, -3, -3}};
    private static int[][] kirsch5 = {{5, -3, -3}, {5, 0, -3}, {5, -3, -3}};
    private static int[][] kirsch6 = {{-3, -3, -3}, {5, 0, -3}, {5, 5, -3}};
    private static int[][] kirsch7 = {{-3, -3, -3}, {-3, 0, -3}, {5, 5, 5}};
    private static int[][] kirsch8 = {{-3, -3, -3}, {-3, 0, 5}, {-3, 5, 5}};
    public static int[][][] arregloMascaras = {kirsch1, kirsch2, kirsch3,
            kirsch4, kirsch5, kirsch6,
            kirsch7, kirsch8};

    public static Image aplicarKirsch(Image image) {
        BufferedImage[] bis = new BufferedImage[8];
        BufferedImage nbi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Color color;
        Color nColor;

        for(int i=0; i<8; i++) {
            bis[i] = HerramientasImagen.toBufferedImage(convolucion(image, 0, 0,arregloMascaras[i]));
        }

        for(int x=0; x<nbi.getWidth(); x++) {
            for(int y=0; y<nbi.getHeight(); y++) {
                int r = 0, g = 0, b = 0;

                for(BufferedImage bi : bis) {
                    color = new Color(bi.getRGB(x, y));

                    r+= color.getRed();
                    g+= color.getGreen();
                    b+= color.getBlue();
                }

                nColor = new Color(validar(r), validar(g), validar(b));

                nbi.setRGB(x, y, nColor.getRGB());
            }
        }

        /*for(int x=0; x<nbi.getWidth(); x++) {
            for(int y=0; y<nbi.getHeight(); y++) {
                int rgb = bis[0].getRGB(x, y);
                rgb += bis[1].getRGB(x, y);
                rgb += bis[2].getRGB(x, y);
                rgb += bis[3].getRGB(x, y);
                rgb += bis[4].getRGB(x, y);
                rgb += bis[5].getRGB(x, y);
                rgb += bis[6].getRGB(x, y);
                rgb += bis[7].getRGB(x, y);

                nbi.setRGB(x, y, rgb);
            }
        }*/

        return HerramientasImagen.toImage(nbi);
    }

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
