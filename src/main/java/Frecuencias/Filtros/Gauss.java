package Frecuencias.Filtros;

import Herramientas.HerramientasImagen;
import gui.FFTFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Gauss {

    public static final double EULER = 2.718;

    private static double[][] cuadrante(int m) {
        double[][] cuadrante = new double[m][m];

        for(int i=0, x=m; i<m; i++, x++) {
            for(int j=0, y=m; j<m; j++, y++) {
                double d0 = Math.pow(x-m,2);
                double d1 = Math.pow(y-m,2);

                cuadrante[i][j] = Math.sqrt(d0+d1);
            }
        }

        return cuadrante;
    }

    public static double[][] calcularDistancias(int dimencion) {
        int m = dimencion/2;
        int me = m-1;
        double[][] cuadrante = cuadrante(m);
        double[][] distancias = new double[dimencion][dimencion];

        for(int x=me, i1=0, i2=dimencion-1; x>=0; x--, i1++, i2--) {
            for(int y=me, j1=0, j2=dimencion-1; y>=0; y--, j1++, j2--) {
                distancias[i1][j1] = cuadrante[x][y];
                distancias[i1][j2] = cuadrante[x][y];
                distancias[i2][j1] = cuadrante[x][y];
                distancias[i2][j2] = cuadrante[x][y];
            }
        }

        return distancias;
    }

    public static double[][] calcularFiltro(double[][] distancias, int distancia, boolean pasabajas) {
        int dimencion = distancias.length;

        double filtro[][] = new double[dimencion][dimencion];

        for(int x=0; x<dimencion; x++) {
            for(int y=0; y<dimencion; y++) {
                double d = distancias[x][y] * -1;

                double exp_arg = Math.pow(2*distancia, 2);
                double gauss = (pasabajas)? Math.exp(d/exp_arg): 1-Math.exp(d/exp_arg);

                filtro[x][y] = gauss;
                System.out.println();
            }
        }

        double[][] aux = new double[dimencion][dimencion];

        for(int j=0; j<dimencion; j++) {
            for(int i=0; i<dimencion; i++) {
                int x = (i + (dimencion / 2)) % dimencion;
                int y = (j + (dimencion / 2)) % dimencion;

                aux[i][j] = filtro[x][y];
            }
        }

        filtro = aux;

        return filtro;
    }

    public static void main(String[] args) {
        int size = 256;
        double[][] distancias = calcularDistancias(size);
        double[][] filtro = calcularFiltro(distancias, 5, true);

        BufferedImage bf = new BufferedImage(size,size, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                int tono = (int)(255 * filtro[i][j]);


                Color blanco = new Color(tono, tono, tono);
                int rgb = blanco.getRGB();


                bf.setRGB(i, j, rgb);
            }
            System.out.println();
        }

        Image image = HerramientasImagen.toImage(bf);

        FFTFrame frame = new FFTFrame(image);
        frame.setVisible(true);
        System.out.println();
    }
}
