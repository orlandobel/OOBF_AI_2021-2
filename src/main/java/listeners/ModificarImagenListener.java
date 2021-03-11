package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import gui.ImageFrame;
import gui.JFramePrincipal;
import gui.ModificarFrame;

public class ModificarImagenListener implements ActionListener {

    private JFramePrincipal jfp;

    public ModificarImagenListener(JFramePrincipal jfp) {
        this.jfp = jfp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem)e.getSource();

        if(item.getText().equals("Modificar pixeles")) {
            ImageFrame internal = (ImageFrame)this.jfp.getJdpPrincipal().getSelectedFrame();
            ModificarFrame iNuevo = new ModificarFrame(internal);
            iNuevo.setVisible(true);
            this.jfp.getJdpPrincipal().add(iNuevo);
        } else {
            System.out.println(item.getText());
        }
        
    }
    
}
