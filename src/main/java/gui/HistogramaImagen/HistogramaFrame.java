package gui.HistogramaImagen;

import Espacial.Histograma;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
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
        setClosable(true);
        setIconifiable(true);
    }

    public void agregarSerie(String nombre, double[] data) {
        XYSeries serie = new XYSeries(nombre);

        for(int i=0;i<data.length;i++){
            serie.add(i, data[i]);
        }

        series.addSeries(serie);
    }

    public void crearGrafica(Color[] colores) {
        grafica = ChartFactory.createXYAreaChart(
                "Histograma de la imagen", "Intensidad", "Frecuencia",
                series, PlotOrientation.VERTICAL, true, true, true);

        XYPlot plot = grafica.getXYPlot();
        XYAreaRenderer render;
        for(int i=0; i<colores.length;i++) {
            render = (XYAreaRenderer)plot.getRenderer();
            render.setSeriesPaint(i, colores[i]);
        }
    }

    public void mostrarGrafica() {
        ChartPanel panel = new ChartPanel(grafica);
        //panel.setSize(500,370);
        setTitle("Histograma de colores");
        setSize(500,370);
        setContentPane(panel);
        setVisible(true);
    }
}
