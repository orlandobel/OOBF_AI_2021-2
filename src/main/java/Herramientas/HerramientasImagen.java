package Herramientas;

import gui.ImageFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HerramientasImagen {

    public static String imageName;

    public static Image openImage (){

        try {
            // definir los filtros para lectura
            FileNameExtensionFilter filtro =
                    new FileNameExtensionFilter("Imagenes","jpg","jpeg","png","bmp");
            // crear un selector de archivos
            JFileChooser selector = new JFileChooser();
            // agregar el filtro al selector
            selector.addChoosableFileFilter(filtro);
            // especificar que solo se puedan abrir archivos
            selector.setFileSelectionMode(JFileChooser.FILES_ONLY);

            //ejecutar el selector de imagenes

            int res = selector.showOpenDialog(null);

            if (res == 1 ){

                return null;

            }

            File archivo = selector.getSelectedFile();
            imageName = archivo.getName();

            BufferedImage  bi = ImageIO.read(archivo);

            return toImage(bi);
        } catch (IOException ex) {
            //Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Image toImage (BufferedImage bi){
        return bi.getScaledInstance(bi.getWidth(),bi.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public static BufferedImage toBufferedImage(Image imagen) {
        if(imagen instanceof BufferedImage)
            return (BufferedImage)imagen;

        BufferedImage bi = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), BufferedImage.TYPE_INT_RGB);

        Graphics2D nueva = bi.createGraphics();
        nueva.drawImage(imagen, 0, 0, null);
        nueva.dispose();

        return bi;
    }

    public static void saveImage(ImageFrame iframe) {
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setDialogTitle("Ruta de guardado");
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        Image image = iframe.getImage();
        String title = iframe.getTitle();
        String format = title.split("\\.")[1];

        if(folderChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String folder = folderChooser.getSelectedFile().getAbsolutePath();

            try {
                File file = new File(folder+"/"+title);

                BufferedImage bi = HerramientasImagen.toBufferedImage(image);
                ImageIO.write(bi, format, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
