package Espacial;

import Herramientas.HerramientasImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Expansion {

    private static final double cLog = 255/Math.log(256);

    public static Image lineal(Image image, int r1, int r2) {
        //final int c = 255 / (r2 - r1);
        BufferedImage bi = HerramientasImagen.toBufferedImage(image);
        Color color;
        Color nColor;

        for (int x=0; x<bi.getWidth(); x++ ){
            for (int y = 0; y<bi.getHeight(); y++){
                color = new Color(bi.getRGB(x,y));

                int r;
                int g;
                int b;

                if (r2!=r1){
                    r = validar((color.getRed()-r1)*(255/(r2-r1)));
                    g = validar((color.getGreen()-r1)*(255/(r2-r1)));
                    b = validar((color.getBlue()-r1)*(255/(r2-r1)));
                }else{
                    r = validar((255)*(color.getRed()-r1));
                    g = validar((255)*(color.getGreen()-r1));
                    b = validar((255)*(color.getBlue()-r1));
                }

                nColor = new Color(r, g, b);

                bi.setRGB(x, y, nColor.getRGB());
            }
        }

        return HerramientasImagen.toImage(bi);
    }

    public static Image logaritmica(Image image) {
        BufferedImage bi = HerramientasImagen.toBufferedImage(image);
        Color color, nColor;

        for(int x=0; x<bi.getWidth(); x++) {
            for(int y=0; y<bi.getHeight(); y++) {
                color = new Color(bi.getRGB(x, y));

                int r = validar((int) (cLog * Math.log(1+color.getRed())));
                int g = validar((int) (cLog * Math.log(1+color.getGreen())));
                int b = validar((int) (cLog * Math.log(1+color.getBlue())));

                nColor = new Color(r, g, b);

                bi.setRGB(x, y, nColor.getRGB());
            }
        }

        return HerramientasImagen.toImage(bi);
    }

    public static Image logaritmica(Image image, int alpha) {
        BufferedImage bi = HerramientasImagen.toBufferedImage(image);
        Color color, nColor;
        final double c = alpha / Math.log(1 + alpha);

        for(int x=0; x<bi.getWidth(); x++) {
            for(int y=0; y<bi.getHeight(); y++) {
                color = new Color(bi.getRGB(x, y));

                int r = validar((int) (c * Math.log(1+color.getRed())));
                int g = validar((int) (c * Math.log(1+color.getGreen())));
                int b = validar((int) (c * Math.log(1+color.getBlue())));

                nColor = new Color(r, g, b);

                bi.setRGB(x, y, nColor.getRGB());
            }
        }

        return HerramientasImagen.toImage(bi);
    }

    public static Image exponencial(Image image, double exp) {
        BufferedImage bi = HerramientasImagen.toBufferedImage(image);
        Color color;
        Color nColor;

        for(int x=0; x<bi.getWidth(); x++) {
            for(int y=0;y<bi.getHeight(); y++) {
                color = new Color(bi.getRGB(x, y));

                int r = validar((int)(Math.pow(1+exp, color.getRed())/color.getRed()));
                int g = validar((int)(Math.pow(1+exp, color.getGreen())/color.getGreen()));
                int b = validar((int)(Math.pow(1+exp, color.getBlue())/color.getBlue()));

                nColor = new Color(r, g, b);

                bi.setRGB(x, y, nColor.getRGB());
            }
        }

        return HerramientasImagen.toImage(bi);
    }

    public static Image ecualizar(Image image, Histograma h) {
        BufferedImage bi = HerramientasImagen.toBufferedImage(image);
        Color color;
        Color nColor;

        int[] Tr = ecualizarCanal(h.getR(), h.getTotalR());
        int[] Tg = ecualizarCanal(h.getG(), h.getTotalG());
        int[] Tb = ecualizarCanal(h.getB(), h.getTotalB());

        for(int x=0; x<bi.getWidth(); x++) {
            for(int y=0; y<bi.getHeight(); y++) {
                color = new Color(bi.getRGB(x, y));

                int r = Tr[color.getRed()];
                int g = Tg[color.getGreen()];
                int b = Tb[color.getBlue()];

                nColor = new Color(r, g, b);

                bi.setRGB(x, y, nColor.getRGB());
            }
        }

        return HerramientasImagen.toImage(bi);
    }

    public static int[] ecualizarCanal(double[] h, int total) {
        double[] px = new double[256]; //probabilidad de ocurrencia de cada nivel ded intencidad
        double[] cdf = new double[256]; // funcion de distribución acumulativa
        int[] Tx = new int[256]; // transformada del cdf
        // calculo de probabilidad de ocurrencia de intencidades
        for(int i=0; i<256; i++)
            px[i] = h[i] / total;

        // caclulo de distribución acumulativa
        cdf[0] = px[0];
        for(int i=1; i<256; i++)
            cdf[i] = cdf[i-1] + px[i];

        //calculo de la transformada
        for(int i=0; i<256; i++)
            Tx[i] = validar((int) (255 * cdf[i]));

        return Tx;
    }
    
    private static int validar(int valor) {
        if(valor < 0)
            valor = -valor;

        if(valor > 255)
            return 255;

        return valor;
    }

}
