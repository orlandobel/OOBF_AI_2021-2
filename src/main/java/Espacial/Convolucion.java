package Espacial;

import Herramientas.HerramientasImagen;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Convolucion {

    private static final int[][] kernelEnfocar = {
            { 0, -1, 0},
            { -1, 5, -1},
            { 0, -1, 0}
    };

    private static final int [][] kernelDesenfoque = {
            { 1, 1, 1},
            { 1, 1, 1},
            { 1, 1, 1}
    };

    private static final int[][] kernelRealzarBordes = {
            { 0, 0, 0},
            { -1, 1, 0},
            { 0, 0, 0}
    };

    private static final int[][] kernelDetectarBordes = {
            { 0, 1, 0 },
            { 1, -4, 1 },
            { 0, 1, 0 }
    };

    private static final int[][] kernelRepujado = {
            { -2, -1, 0 },
            { -1, 1, 1 },
            { 0, 1, 2 }
    };

    public static Image convolucion(Image image, int opcion, int offset, @Nullable int[][] ker) {
        int[][] kernel = (opcion == 5)? ker : setKernel(opcion);

        BufferedImage bi = HerramientasImagen.toBufferedImage(image);
        BufferedImage nbi = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int x=0; x<bi.getWidth(); x++) {
            for(int y=0; y<bi.getHeight(); y++) {
                int rgb = nuevoTono(x, y, bi, kernel, offset);

                nbi.setRGB(x, y, rgb);
            }
        }

        return HerramientasImagen.toImage(nbi);
    }

    private static int nuevoTono(int x, int y, BufferedImage bi, int[][] kernel, int offset) {
        Color color;
        Color ncolor;

        int r = 0, g = 0, b = 0, div =0;


        for(int i=0, f = x-1; i<kernel.length; i++, f++) {
            for(int j=0, c=y-1; j<kernel[0].length; j++, c++) {
                if(kernel[i][j] != 0) {
                    div++;

                    try {
                        color = new Color(bi.getRGB(f, c));

                        r += color.getRed()*kernel[i][j];
                        g += color.getGreen()*kernel[i][j];
                        b += color.getBlue()*kernel[i][j];

                        System.out.println();
                    } catch(Exception e) {

                    }
                }
            }
        }

        /*r = validar(r);
        g = validar(g);
        b = validar(b);*/

        if(div != 0) {
            r /= div;
            b /= div;
            r /= div;
        }

        ncolor = new Color(validar(r+offset), validar(g+offset), validar(b+offset));

        return ncolor.getRGB();
    }

    private static int[][] setKernel(int opcion) {
        int[][] kernel = null;

        switch(opcion) {
            case 0:
                kernel = kernelEnfocar;
                break;
            case 1:
                kernel = kernelDesenfoque;
                break;
            case 2:
                kernel = kernelRealzarBordes;
                break;
            case 3:
                kernel = kernelDetectarBordes;
                break;
            case 4:
                kernel = kernelRepujado;
                break;
        }

        return kernel;
    }

    private static int validar(int tono) {
        if(tono > 255)
            return 255;

        if(tono < 0)
            return 0;

        return tono;
    }
}
