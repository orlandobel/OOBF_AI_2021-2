package gui.HistogramaImagen;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.awt.*;

public class HistogramaFrame extends JInternalFrame {

    public HistogramaFrame(String titulo, Color color, DefaultCategoryDataset dataset) {
        super(titulo);

        setClosable(true);
        setIconifiable(true);

        JFreeChart histogramChart = ChartFactory.createBarChart(
                title,
                "",
                "Frecuencia",
                dataset,
                PlotOrientation.VERTICAL,
                false, false, false);

        CategoryPlot plot = histogramChart.getCategoryPlot();
        plot.setBackgroundPaint(SystemColor.inactiveCaption);

        ((BarRenderer)plot.getRenderer()).setBarPainter(new StandardBarPainter());

        BarRenderer render = (BarRenderer)plot.getRenderer();
        render.setSeriesPaint(0, color);

        ChartPanel chartPanel = new ChartPanel(histogramChart);
        chartPanel.setPreferredSize(new Dimension(500, 367));

        setContentPane(chartPanel);

        pack();
    }

}
