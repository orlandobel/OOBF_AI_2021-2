package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
            case "mp":
                nuevoFrame(new ModificarFrame(internal));
                break;
            case "copiar":
                nuevoFrame(new CopiarFrame(internal, this.jfp));
                break;
            case "filtros":
                nuevoFrame(new FiltrosFrame(this.jfp));
                break;
            case "binarizar":
                nuevoFrame(new FrameBinario(internal));
                break;
            default:
                System.out.println(item.getText());
        }
    }

    private void nuevoFrame(JInternalFrame frame) {
            frame.setVisible(true);
            jfp.getJdpPrincipal().add(frame);
    }
    
}
