package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Herramientas.HerramientasImagen;
import gui.ImageFrame;
import gui.JFramePrincipal;

import java.awt.Image;

public class InternalFrameListener implements ActionListener {

    private JFramePrincipal jfp;

    public InternalFrameListener(JFramePrincipal jfp) {
        this.jfp = jfp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Image image = HerramientasImagen.openImage();
        ImageFrame nuevo = new ImageFrame(image);
        nuevo.setVisible(true);
        this.jfp.getJdpPrincipal().add(nuevo);
    }
    
}
