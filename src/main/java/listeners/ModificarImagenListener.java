package listeners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Espacial.Binarizacion;
import Espacial.Histograma;
import gui.*;
import gui.HistogramaImagen.HistogramaFrame;

public class ModificarImagenListener implements ActionListener {

    private JFramePrincipal jfp;

    public ModificarImagenListener(JFramePrincipal jfp) {
        this.jfp = jfp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem)e.getSource();
        ImageFrame internal = this.jfp.getActiveImageFrame();
        //JInternalFrame iNuevo;

        switch(item.getName()) {
            case "mp":
                nuevoFrame(new ModificarFrame(internal));
                break;
            case "copiar":
                nuevoFrame(new CopiarFrame(internal, this.jfp));
                break;
            case "filtros":
                nuevoFrame(new FiltrosFrame(this.jfp));
                break;
            case "histograma":
                nuevoFrame(new SelectHistogramaFrame(this.jfp));
                /*Histograma h = new Histograma(internal.getImage());
                HistogramaFrame hf = new HistogramaFrame();

                h.calcularHistogramas();

                hf.agregarSerie("Rojo", h.getR());
                hf.agregarSerie("Verde", h.getG());
                hf.agregarSerie("Azul", h.getB());

                Color[] colores = {Color.RED, Color.GREEN, Color.BLUE};

                hf.crearGrafica(colores);
                hf.mostrarGrafica();

                jfp.getJdpPrincipal().add(hf);*/
                break;
            case "binarizar":
                nuevoFrame(new FrameBinario(internal));
                break;
            case "binarizar2":
                nuevoFrame(new FrameBinario2(internal));
                break;
            case "binarizar3":
                ImageFrame iframe = jfp.getActiveImageFrame();
                Image nimage = Binarizacion.binarizar(iframe.getImagenOriginal());
                iframe.setImage(nimage);
                break;
            case "binarizar4":
                ImageFrame ifr = jfp.getActiveImageFrame();
                Histograma h = new Histograma(ifr.getImagenOriginal());
                h.calcularHistogramas();
                Image nimg = Binarizacion.binarizarOtsu(ifr.getImagenOriginal(), h.getR());
                ifr.setImage(nimg);
                break;
            case "iluminacion":
                nuevoFrame(new IluminacionFrame(this.jfp));
                break;
            default:
                System.out.println(item.getText());
        }
    }

    private void nuevoFrame(JInternalFrame frame) {
            frame.setVisible(true);
            jfp.getJdpPrincipal().add(frame);

            try {
                frame.setSelected(true);
            } catch(Exception e) {
                e.printStackTrace();
            }
    }
    
}
