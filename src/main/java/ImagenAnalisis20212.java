import Espacial.Histograma;
import Herramientas.HerramientasImagen;

import java.awt.Image;

public class ImagenAnalisis20212 {

    public static void main(String[] args) {
        // visualización de la imagen dentro de la GUI
        Image imagen = HerramientasImagen.openImage();
        // para cuantización de la imagen vamos a un ocupar BufferedImage
        //BufferedImage bImagen = herramientas.HerramientasImagen.toBufferedImage(imagen);
        Histograma h = new Histograma(imagen);
        h.calcularHistogramas();

        System.out.println();
    }
}
