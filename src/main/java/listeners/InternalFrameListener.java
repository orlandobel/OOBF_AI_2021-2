package listeners;

import gui.ImageFrame;
import gui.JFramePrincipal;

import javax.swing.event.InternalFrameEvent;
import java.beans.PropertyVetoException;

public class InternalFrameListener implements javax.swing.event.InternalFrameListener {

    private final JFramePrincipal jfp;

    public InternalFrameListener(JFramePrincipal jfp) {
        this.jfp = jfp;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        System.out.println("onInternalFrameActivated");
        if(e.getInternalFrame() instanceof ImageFrame) {
            System.out.println("internalFrameActivated entra a la condicion");
            ImageFrame iframe = (ImageFrame)e.getInternalFrame();
            jfp.setActiveImageFrame(iframe);
        }
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {

    }
}
