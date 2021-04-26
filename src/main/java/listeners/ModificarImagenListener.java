package listeners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Espacial.Binarizacion;
import Espacial.Expansion;
import Espacial.Histograma;
import gui.*;

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
            case "mp": // modificar pixeles
                nuevoFrame(new ModificarFrame(internal));
                break;

            case "copiar": // cortar fragmento de imagen
                nuevoFrame(new CopiarFrame(internal, this.jfp));
                break;

            case "filtros": // filtros negativo, escala de grices y binario (umbral fijo)
                nuevoFrame(new FiltrosFrame(this.jfp));
                break;

            case "histograma": // calculo de histogramas
                nuevoFrame(new SelectHistogramaFrame(this.jfp));
                break;

            case "binarizar": // binarización con un umbral
                nuevoFrame(new FrameBinario(internal));
                break;

            case "binarizar2": // binarización con dos umbrales
                nuevoFrame(new FrameBinario2(internal));
                break;

            case "binarizar3": // umbraliazdor metodo iterativco
                ImageFrame iframe = jfp.getActiveImageFrame();
                Image nimage = Binarizacion.binarizar(iframe.getImagenOriginal());
                iframe.setImage(nimage);
                setImageTitle(iframe, "binarizado iterativo");
                break;

            case "binarizar4": // umbralizador metodod e otsu
                ImageFrame ifr = jfp.getActiveImageFrame();
                Histograma h = new Histograma(ifr.getImagenOriginal());
                h.calcularHistogramas();
                Image nimg = Binarizacion.binarizarOtsu(ifr.getImagenOriginal(), h.getR());
                ifr.setImage(nimg);
                setImageTitle(ifr, "binarizado otsu");
                break;

            case "iluminacion": // cambio de la iluminación de la imagen
                nuevoFrame(new IluminacionFrame(this.jfp));
                break;

            case "exp":
                nuevoFrame(new ExpandirFrame(this.jfp));
                break;
            case "conv":
                nuevoFrame(new ConvolucionFrame(this.jfp));
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

    private void setImageTitle(ImageFrame iframe, String modTitle) {
        String title = iframe.getTitle();
        String[] split = title.split("\\.");

        title = split[0] + " - " + modTitle + "." + split[1];

        iframe.setTitle(title);
    }
    
}
