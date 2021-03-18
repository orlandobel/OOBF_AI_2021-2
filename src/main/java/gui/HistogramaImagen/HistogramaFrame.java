package gui.HistogramaImagen;

import Espacial.Histograma;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class HistogramaFrame extends JInternalFrame {

    private JFreeChart grafica;
    private XYSeriesCollection series;

    public HistogramaFrame(String titulo, Color color, DefaultCategoryDataset dataset) {
        super(titulo);

        setClosable(true);
        setIconifiable(true);

         grafica = ChartFactory.createBarChart(
                title,
                "",
                "Frecuencia",
                dataset,
                PlotOrientation.VERTICAL,
                false, false, false);

        CategoryPlot plot = grafica.getCategoryPlot();
        plot.setBackgroundPaint(SystemColor.inactiveCaption);

        ((BarRenderer)plot.getRenderer()).setBarPainter(new StandardBarPainter());

        BarRenderer render = (BarRenderer)plot.getRenderer();
        render.setSeriesPaint(0, color);

        ChartPanel chartPanel = new ChartPanel(grafica);
        chartPanel.setPreferredSize(new Dimension(500, 367));

        setContentPane(chartPanel);

        pack();
    }

    public HistogramaFrame() {
        grafica = null;
        this.series = new XYSeriesCollection();
    }

    public HistogramaFrame(Histograma histograma) {
        DefaultCategoryDataset datasetR = new DefaultCategoryDataset();
        DefaultCategoryDataset datasetG = new DefaultCategoryDataset();
        DefaultCategoryDataset datasetB = new DefaultCategoryDataset();

        double[] r = histograma.getR();
        double[] g = histograma.getG();
        double[] b = histograma.getB();

        for(int i=0;i<256;i++) {
            datasetR.addValue(r[i], "", ""+i);
            datasetG.addValue(g[i], "", ""+i);
            datasetB.addValue(b[i], "", ""+i);
        }
    }

    public void agregarSerie(String nombre, double[] data) {
        XYSeries serie = new XYSeries(nombre);

        for(int i=0;i<data.length;i++){
            serie.add(i, data[i]);
        }


    }

}
