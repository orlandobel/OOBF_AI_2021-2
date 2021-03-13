package listeners;

import gui.FiltrosFrame;
import gui.JFramePrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FiltrosListener implements ActionListener {

    private JFramePrincipal jfp;

    public FiltrosListener(JFramePrincipal jfp) {
        this.jfp = jfp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FiltrosFrame ff = new FiltrosFrame(this.jfp);
        ff.setVisible(true);
        jfp.getJdpPrincipal().add(ff);
    }
}
