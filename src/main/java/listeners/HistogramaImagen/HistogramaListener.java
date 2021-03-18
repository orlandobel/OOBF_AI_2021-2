package listeners.HistogramaImagen;

import Espacial.Histograma;
import gui.HistogramaImagen.FramePrincipal;
import gui.HistogramaImagen.HistogramaFrame;
import gui.ImageFrame;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistogramaListener implements ActionListener {
    private FramePrincipal fp;
    private ImageFrame iframe;

    public HistogramaListener(FramePrincipal fp, ImageFrame iframe) {
        this.fp = fp;
        this.iframe = iframe;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem)e.getSource();
        Image image = iframe.getImage();
        if(image != null) {
            Histograma h = new Histograma(image);
            h.calcularHistogramas();

            if(item.getText().equals("En gráficas separadas")) {
                DefaultCategoryDataset datasetR = new DefaultCategoryDataset();
                DefaultCategoryDataset datasetG = new DefaultCategoryDataset();
                DefaultCategoryDataset datasetB = new DefaultCategoryDataset();

                double[] r = h.getR();
                double[] g = h.getG();
                double[] b = h.getB();

                for(int i=0;i<256;i++) {
                    datasetR.addValue(r[i], "", ""+i);
                    datasetG.addValue(g[i], "", ""+i);
                    datasetB.addValue(b[i], "", ""+i);
                }

                HistogramaFrame hrf = new HistogramaFrame("Rojo", Color.RED, datasetR);
                HistogramaFrame hgf = new HistogramaFrame("Verde", Color.GREEN, datasetG);
                HistogramaFrame hbf = new HistogramaFrame("Azul", Color.BLUE, datasetB);

                hrf.setVisible(true);
                fp.getJdp().add(hrf);

                hgf.setVisible(true);
                fp.getJdp().add(hgf);

                hbf.setVisible(true);
                fp.getJdp().add(hbf);
            } else if(item.getText().equals("En una sola gráfica")) {

            }


        } else {
            System.out.println("Imagen vacia");
        }
    }
}
