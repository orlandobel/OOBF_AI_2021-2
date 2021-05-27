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
                /*int x1 = (2 * x) + 2;
                int y1 = (2 * y) - 2;
                p = p + x1 + 1 - y1;*/

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

    public static void main(String[] args) {
        int[] cuadrante = Ideal.obtenerCuadranteCirculo(10);
        final int size = 128;
        int limit = size - 1;
        BufferedImage bi = new BufferedImage(size,size, BufferedImage.TYPE_INT_RGB);

        for(int x=0;x<size;x++) {
            for(int y=0;y<size;y++) {
                if((x == y)) {
                    bi.setRGB(x, y, Color.GREEN.getRGB());
                    bi.setRGB(x, limit-y, Color.GREEN.getRGB());
                }
                else {
                    bi.setRGB(x, y, Color.RED.getRGB());
                }
            }
        }

        int m = 128/2; // punto medio
        int me = m-1; // medio espejo

        int negro = Color.BLACK.getRGB();
        int blanco = Color.WHITE.getRGB();

        for(int i=0, p=m, q=me; i<cuadrante.length; i++, p++, q--) {
            for(int j=0; j<cuadrante[i]; j++) {
                bi.setRGB(p, me-j, negro);
                bi.setRGB(p, m+j, negro);
                bi.setRGB(q, me-j, negro);
                bi.setRGB(q, m+j, negro);
            }
        }

        bi.setRGB(m, m, blanco);
        bi.setRGB(m, me, blanco);
        bi.setRGB(me, m , blanco);
        bi.setRGB(me, me, blanco);

        Image image = HerramientasImagen.toImage(bi);

        FFTFrame frame = new FFTFrame(image);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
