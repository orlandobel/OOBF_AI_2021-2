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

        Image ii = gestor.obtenerImagenEspacial();
        FFTFrame frame3 = new FFTFrame(ii);
        frame3.setVisible(true);
    }
}
