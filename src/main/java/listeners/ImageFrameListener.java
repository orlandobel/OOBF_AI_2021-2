package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Herramientas.HerramientasImagen;
import gui.ImageFrame;
import gui.JFramePrincipal;

import java.awt.Image;
import java.beans.PropertyVetoException;

public class ImageFrameListener implements ActionListener {

    private JFramePrincipal jfp;

    public ImageFrameListener(JFramePrincipal jfp) {
        this.jfp = jfp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Image image = HerramientasImagen.openImage();
        String titulo = HerramientasImagen.imageName;

        ImageFrame nuevo = new ImageFrame(titulo, image, jfp.getListener());
        nuevo.setVisible(true);
        this.jfp.getJdpPrincipal().add(nuevo);

        try {
            nuevo.setSelected(true);
        } catch (PropertyVetoException propertyVetoException) {
            propertyVetoException.printStackTrace();
        }
    }
    
}
