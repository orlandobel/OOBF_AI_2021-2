package Frecuencias.Filtros;

import Herramientas.HerramientasImagen;
import gui.FFTFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ideal {

    public static ArrayList<Integer> calcularOctanteCirculo(int octante, int r) {
        ArrayList<Integer> borde = new ArrayList<Integer>();

        int x = 1;
        int y = r;
        //borde.add(y);

        double p = 1.25 - r;

        while(x < y) {
            borde.add(y);

            if(p < 0) {
                x++;
                int x1 = 2 * x;
                p = p + x1 + 1;
            } else {
                p = p + 2*(x - y) +1;

                y--;
                x++;
            }
        }

        if(borde.size() < octante)
            borde.add(y);

        return borde;
    }

    public static int[] obtenerCuadranteCirculo(int radio) {
        int[] cuadrante = new int[radio];
        int perimetro_circulo = (int)Math.PI*2*radio;
        int limite_octante = (int)perimetro_circulo / 8;

        ArrayList<Integer> octante = calcularOctanteCirculo(limite_octante, radio);


        for(int i=0; i<octante.size(); i++) {
            int valor = octante.get(i);
            cuadrante[i] = valor;

            if(i+1<octante.size() && valor != octante.get(i+1) && i != 0) {
                cuadrante[valor-1] = i+1;
            }

            if(i+1 == octante.size() && cuadrante[valor-1] == 0) {
                cuadrante[valor-1] = i+1;
            }
        }

        return cuadrante;
    }

    public static int[][] obtenerFiltro(int[] cuadrante, int dimencion, boolean ajuste_encuadre) {
        int[][] filtro = new int[dimencion][dimencion];

        int m = dimencion/2; // punto medio
        int me = m-1; // medio espejo


        for(int i=0, p=m, q=me; i<cuadrante.length; i++, p++, q--) {
            for(int j=0; j<cuadrante[i]; j++) {
                filtro[p][me-j] = 1;
                filtro[p][m+j] = 1;
                filtro[q][me-j] = 1;
                filtro[q][m+j] = 1;
            }
        }

        if(ajuste_encuadre) {
            int[][] aux = new int[dimencion][dimencion];


            for(int j=0; j<dimencion; j++) {
                for(int i=0; i<dimencion; i++) {
                    int x = (i + (dimencion / 2)) % dimencion;
                    int y = (j + (dimencion / 2)) % dimencion;

                    aux[i][j] = filtro[x][y];
                }
            }

            filtro = aux;
        }

        return filtro;
    }

    public static void main(String[] args) {
        final int size = 256;
        int limit = size - 1;

        int[] cuadrante = Ideal.obtenerCuadranteCirculo(28);
        int[][] filtro = obtenerFiltro(cuadrante, size, true);
        BufferedImage bi = new BufferedImage(size,size, BufferedImage.TYPE_INT_RGB);

        int negro = Color.BLACK.getRGB();
        int blanco = Color.WHITE.getRGB();

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(filtro[i][j] == 1) {
                    bi.setRGB(i, j, blanco);
                } else {
                    bi.setRGB(i, j, negro);
                }
            }
        }

        Image image = HerramientasImagen.toImage(bi);

        FFTFrame frame = new FFTFrame(image);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
