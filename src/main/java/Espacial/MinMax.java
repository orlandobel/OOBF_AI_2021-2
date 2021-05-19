package Espacial;

import Herramientas.HerramientasImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MinMax {
    public static Image filtrar(boolean minimo, boolean maximo, Image imagen) {
        Image imagen_gris = Filtros.calcularGrices(imagen);
        BufferedImage bi = HerramientasImagen.toBufferedImage(imagen);
        BufferedImage big = HerramientasImagen.toBufferedImage(imagen_gris);
        BufferedImage nbi = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);

        Color nColor;
        if(minimo) {
            for(int x=0; x<bi.getWidth(); x++) {
                for(int y=0; y<bi.getHeight(); y++) {
                    //System.out.println("pixel("+x+","+y+")");
                    nColor = aplicarMinimo(bi, big, x, y);
                    nbi.setRGB(x, y, nColor.getRGB());
                }
            }
        }

        if(minimo && maximo)
            bi = nbi;

        if(maximo) {
            for(int x=0; x<bi.getWidth(); x++) {
                for(int y=0; y<bi.getHeight(); y++) {
                    nColor = aplicarMaximo(bi, big, x, y);
                    nbi.setRGB(x, y, nColor.getRGB());
                }
            }
        }

        return HerramientasImagen.toImage(nbi);
    }

    private static Color aplicarMaximo(BufferedImage bi, BufferedImage big, int x, int y) {
        int xmax = 0;
        int ymax = 0;
        int gmax = 0;

        Color gris;
        for(int i=x-1; i<x+1; i++) {
            for(int j=y-1; j<y+1; j++) {
                //System.out.println("Cilclo "+i);
                try {

                    gris = new Color(big.getRGB(i, j));

                    if(gris.getRed() > gmax) {
                        gmax = gris.getRed();
                        xmax = i;
                        ymax = j;
                    }
                } catch(Exception e) {

                }

            }
        }

        return new Color(bi.getRGB(xmax, ymax));
    }

    private static Color aplicarMinimo(BufferedImage bi, BufferedImage big, int x, int y) {
        int xmin = 0;
        int ymin = 0;
        int gmin = 256;

        Color gris;
        for(int i=x-1; i<x+1; i++) {
            for(int j=y-1; j<y+1; j++) {
                //System.out.println("Cilclo "+i);
                try {
                    gris = new Color(big.getRGB(i, j));

                    if(gris.getRed() < gmin) {
                        gmin = gris.getRed();
                        xmin = i;
                        ymin = j;
                    }
                } catch(Exception e) {

                }

            }
        }

        return new Color(bi.getRGB(xmin, ymin));
    }
}
