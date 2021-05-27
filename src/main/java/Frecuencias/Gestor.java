package Frecuencias;

import java.awt.*;
import java.awt.image.BufferedImage;

import Espacial.Convolucion;
import Frecuencias.HerramientasColor.CanalColor;
public class Gestor {

    private BufferedImage bio; // BufferedImage original
    private NumeroComplejo[][] io; // Imagen original en complejos (de rojos)
    private NumeroComplejo[][] it; // Imagen transformada (en rojos)

    private int h;
    private int w;

    public Gestor(BufferedImage bi) {
        bio = bi;

        h = bi.getHeight();
        w = bi.getWidth();

        io = obtenerDatosPorCanal(CanalColor.ROJO);
    }
    public NumeroComplejo[][] obtenerDatosPorCanal(CanalColor color) {
        NumeroComplejo[][] aux = new NumeroComplejo[w][h];

        for(int y=0; y<h; y ++) {
            for(int x=0; x<w; x++) {
                aux[x][y] = new NumeroComplejo(HerramientasColor.obtenerValorPorCanal(bio.getRGB(x, y), color), 0);
            }
        }

        return aux;
    }

    public BufferedImage obtenerImagenFrecuencias(boolean reajusteCuadrante) {
        BufferedImage aux = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        FFT fft = new FFT();

        NumeroComplejo[][] transformada = fft.calcularTF(io, false);
        it = transformada;

        for(int y=0; y<h; y++) {
            for(int x=0; x<w; x++) {
                int ejeX = reajusteCuadrante ? (x + (w / 2)) % w : x;
                int ejeY = reajusteCuadrante ? (y + (h / 2)) % h : y;

                int color1 = aux.getRGB(x, y);
                int color2 = obtenerColorRealDeFrecuencia(ejeX, ejeY, transformada, CanalColor.ROJO);
                int rgb = HerramientasColor.acumularColor(color1, color2);

                aux.setRGB(x, y, rgb);
            }
        }

        return aux;
    }

    private int obtenerColorRealDeFrecuencia(int x, int y, NumeroComplejo[][] transformada, CanalColor canal) {
        int color = (int) Math.abs(transformada[x][y].getR());
        color = Espacial.Convolucion.validar(color);
        color = HerramientasColor.obtenerRGBPorCanal(color, canal);

        return color;
    }

    public BufferedImage obtenerImagenEspacial() {
        BufferedImage buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        FFT fft = new FFT();

        NumeroComplejo[][] inversa = fft.calcularTF(it, true);

        for(int y=0; y<h; y++) {
            for(int x=0; x<w; x++) {
                int color = (int)Math.abs(inversa[x][y].getR());
                color = Convolucion.validar(color);
                color = HerramientasColor.obtenerRGBPorCanal(color, CanalColor.ROJO);

                int rgb = HerramientasColor.acumularColor(buffer.getRGB(x, y), color);

                buffer.setRGB(x, y, rgb);
            }
        }

        return buffer;
    }
}
