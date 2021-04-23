package Espacial;

import Herramientas.HerramientasImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Histograma {

    private Image imagen;

    private double[] r;
    private double[] g;
    private double[] b;
    private double[] gr;

    private int totalPixeles;
    private int totalR;
    private int totalG;
    private int totalB;

    public Histograma(Image imagen) {
        this.imagen = imagen;

        this.r = new double[256];
        this.g = new double[256];
        this.b = new double[256];
        this.gr = new double[556];
    }

    public void calcularHistogramas() {
        BufferedImage biGrices = Filtros.calcularGrices(this.imagen); // BufferedImage en escala de grices
        BufferedImage bi = HerramientasImagen.toBufferedImage(this.imagen); // BuferedImage de imagen original
        Color color;
        Color gris;

        // recorrer la imagen
        for(int x=0; x<bi.getWidth();x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                // extraer el color
                color = new Color(bi.getRGB(x, y));
                gris = new Color(biGrices.getRGB(x, y));

                int r = color.getRed(); // (0-255)
                int g = color.getGreen(); // (0-255)
                int b = color.getBlue(); // (0-255)
                int gr = gris.getRed(); // (0-255)

                this.r[r]++;
                this.g[g]++;
                this.b[b]++;
                this.gr[gr]++;

                totalPixeles++;

                if(r != 0)
                    totalR++;

                if(g != 0)
                    totalG++;

                if(b != 0)
                    totalB++;
            }
        }
    }

    /**
     * @return the r
     */
    public double[] getR() {
        return r;
    }

    /**
     * @return the g
     */
    public double[] getG() {
        return g;
    }

    /**
     * @return the b
     */
    public double[] getB() {
        return b;
    }

    public double[] getGr() {
        return gr;
    }

    public int getTotalPixeles() {
        return this.totalPixeles;
    }

    public int getTotalR() {
        return this.totalR;
    }

    public int getTotalG() {
        return this.totalG;
    }

    public int getTotalB() {
        return this.totalB;
    }

}
