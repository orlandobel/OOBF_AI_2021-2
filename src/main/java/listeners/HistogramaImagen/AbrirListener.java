package listeners.HistogramaImagen;

import Herramientas.HerramientasImagen;
import gui.ImageFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AbrirListener implements ActionListener {

    private ImageFrame iframe;

    public AbrirListener(ImageFrame frame) {
        this.iframe = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Image imagen  = HerramientasImagen.openImage();
        iframe.setImage(imagen);
    }
}
