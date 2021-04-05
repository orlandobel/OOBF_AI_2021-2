package gui.HistogramaImagen;

import Espacial.Histograma;
import Herramientas.HerramientasImagen;
import org.jfree.chart.*;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.NumberAxis;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HistogramaFrame extends JInternalFrame implements ActionListener {

    private JFreeChart grafica;
    private XYSeriesCollection series;

    private JMenuBar menu;
    private JMenuItem itemGuardar;

    private String title;

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

        menu = new JMenuBar();
        itemGuardar = new JMenuItem();

        itemGuardar.setText("Guardar histograma");
        itemGuardar.addActionListener(this);

        menu.add(itemGuardar);
        setJMenuBar(menu);

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

    public void crearGrafica(Color[] colores, String title) {
        this.title = "Histograma - " + title;

        grafica = ChartFactory.createXYAreaChart(
                this.title, "", "",
                series, PlotOrientation.VERTICAL, true, true, true);

        XYPlot plot = grafica.getXYPlot();
        NumberAxis XAxis = (NumberAxis)plot.getDomainAxis();
        XAxis.setRange(0, 255);

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

    @Override
    public void actionPerformed(ActionEvent evt) {
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setDialogTitle("Ruta de guardado");
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if(folderChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String folder = folderChooser.getSelectedFile().getAbsolutePath();

            try {
                OutputStream os = new FileOutputStream(folder+"/"+this.title);

                ChartUtils.writeChartAsPNG(os, grafica, 400, 370);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
