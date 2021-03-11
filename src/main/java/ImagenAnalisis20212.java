import Herramientas.HerramientasImagen;
import gui.ImageFrame;

import java.awt.Image;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImagenAnalisis20212 {

    public static void main(String[] args) {
        Image imagen = HerramientasImagen.openImage();
        BufferedImage bi = HerramientasImagen.toBufferedImage(imagen);

        final Color MARCADOR = new Color(65,255, 3);
        bi.setRGB(100,100, MARCADOR.getRGB());

        imagen = HerramientasImagen.toImage(bi);

        new ImageFrame(imagen);
    }
}
