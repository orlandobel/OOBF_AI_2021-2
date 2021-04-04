package Espacial;

import Herramientas.HerramientasImagen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Binarizacion {


    /* ----------------- Bunarizzación con un umbral ----------------- */
    public static Image binarizar(int umbral, Image image) {
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

    /* ----------------- Bunarizzación dos un umbral ----------------- */
    public static Image binarizar(int unegro, int ublanco, Image image) {
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

    /* ----------------- Bunarizzación automatica iterativa ----------------- */
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
        }

        System.out.println("Umbral="+u);

        return Binarizacion.binarizar(u, image);
    }

    /* ----------------- Bunarizzación automatica de otsu ----------------- */
    public static Image binarizarOtsu(Image image, double[] h) {
        int totalHistograma = 0; // Suma de todos los valores del histograma
        int top = 256; // valos maximo permitido
        int sumaBB = 0;
        int wb = 0;
        double maximo = 0; // interferencia maxima permitida
        int wf;
        int mf;
        double valor;
        int umbral = 0;
        int suma1 = 0;
        int[] rango = new int[top];

        for(int i=0;i<h.length;i++) totalHistograma+=(int)h[i];
        for(int i=0;i<top;i++) rango[i]=i;

        for(int i=0;i<h.length;i++) suma1+=rango[i]*h[i];

        for(int i=1;i<top;i++) {
            wf = totalHistograma - wb;

            if(wb > 0 && wf > 0) {
                mf = (suma1 - sumaBB) / wf;

                valor = wb*wf*((sumaBB/wb)-mf)*((sumaBB/wb)-mf);

                if(valor >= maximo) {
                    umbral = i;
                    maximo = valor;
                }
            }

            wb = wb + (int)h[i];
            sumaBB = sumaBB + (i-1) * (int)h[i];
        }

        System.out.println("Umbral="+umbral);

        return Binarizacion.binarizar(umbral, image);
    }
}
