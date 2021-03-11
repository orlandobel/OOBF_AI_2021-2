package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import gui.CopiarFrame;
import gui.ImageFrame;
import gui.JFramePrincipal;

public class CopiarActionListener implements ActionListener {

    private JFramePrincipal jfp;

    public CopiarActionListener(JFramePrincipal jfp) {
        this.jfp = jfp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JMenuItem item = (JMenuItem)e.getSource();

        if(item.getText().equals("Copiar fragmento de imagen")) {
            ImageFrame internal = (ImageFrame)this.jfp.getJdpPrincipal().getSelectedFrame();
            CopiarFrame iNuevo = new CopiarFrame(internal, this.jfp);
            iNuevo.setVisible(true);
            this.jfp.getJdpPrincipal().add(iNuevo);
        } else {
            System.out.println(item.getText());
        }
    }
    
}
