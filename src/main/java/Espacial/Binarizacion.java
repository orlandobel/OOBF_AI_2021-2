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

    public static Image binarizazar(int umbral, Image image) {
        BufferedImage bi = HerramientasImagen.toBufferedImage(image);

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

        return HerramientasImagen.toImage(bi);
    }

    public static Image binarizazar(int unegro, int ublanco, Image image) {
        BufferedImage bi = HerramientasImagen.toBufferedImage(image);

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

        return HerramientasImagen.toImage(bi);
    }

    public static Image binarizar(Image image) {
        BufferedImage bi = Filtros.calcularGrices(image); // Obtener escala de grices
        image = HerramientasImagen.toImage(bi); // Imagen en escala de grices

        // calculo de histograma para obtener la moda
        Histograma h = new Histograma(image);
        h.calcularHistogramas();
        double[] valores = h.getR();

        int u = 127; // umbral de binarizacion
        int uaux = 0; //umbral auxiliar

        while(u != uaux) {
            uaux = u;

            int m1, m2; //m1 = media por debajo del umbral, m2 media por encima del umbral

            int s1 = (int)valores[0]; // suma para media por debajo del umbral
            int s2 = 0; // suma para media por enciam del umbral

            int a1 = 0; // auxiliar para media por debajo del umbral
            int a2 = 0; //auxiliar para media por encima del umbral

            for(int i=1; i<=u; i++) {
                s1 += (int)valores[i]*i;
                a1 += (int)valores[i];
            }


            m1 = (a1<=0)? 0 : s1/a1;

            for(int i=u+1; i<255; i++) {
                a2 += (int)valores[i];
                s2 += (int)valores[i]*i;
            }

            m2 = (a2<=0)? 255 : s2/a2;

            u = (m1 + m2) / 2;

            System.out.println("m1="+m1);
            System.out.println("m2="+m2);
            System.out.println("u="+u);
        }

        return Binarizacion.binarizazar(u, image);
    }
}
