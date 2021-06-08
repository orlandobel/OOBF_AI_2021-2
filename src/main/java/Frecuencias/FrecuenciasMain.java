package Frecuencias;

import Espacial.Filtros;
import Herramientas.HerramientasImagen;
import gui.FFTFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrecuenciasMain {
    public static void main(String[] args) {
        Image image = HerramientasImagen.openImage();
        Image grices = Filtros.calcularGrices(image);

        FFTFrame frame = new FFTFrame(grices);
        frame.setVisible(true);

        BufferedImage bi = HerramientasImagen.toBufferedImage(grices);
        Gestor gestor = new Gestor(bi);

        BufferedImage bit = gestor.obtenerImagenFrecuencias(true);
        Image ir = HerramientasImagen.toImage(bit);
        FFTFrame frame2 = new FFTFrame(ir);
        frame2.setVisible(true);

        BufferedImage bif = gestor.aplicarIdeal(52, true, false);
        //BufferedImage bif = gestor.aplicarButterwoth(20, 2, true, true);
        Image ifl = HerramientasImagen.toImage(bif);
        FFTFrame frame3 = new FFTFrame(ifl);
        frame3.setVisible(true);


        BufferedImage bii = gestor.obtenerImagenEspacial();
        Image ii = HerramientasImagen.toImage(bii);
        FFTFrame frame4 = new FFTFrame(ii);
        frame4.setVisible(true);
    }
}
