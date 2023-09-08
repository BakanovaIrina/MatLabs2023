package com.matlab.plot;

import com.matlab.resultFunc.FunctionCounter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.HashMap;
import java.util.Set;


public class Graph extends ApplicationFrame {
    public static final double step = 0.01;

    public Graph(String title) {
        super(title);
    }

    public void graph(double xMin, double xMax, double yMin, double yMax,
                      HashMap<Double, Double> input, FunctionCounter func, String title) {
        draw(xMin, xMax, yMin, yMax, input, generateDataset(xMin, xMax, step, func), title);
    }

    public void draw(double xMin, double xMax, double yMin, double yMax,
                     HashMap<Double, Double> input,  XYSeries series, String title){
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);


        // Создаем график на основе набора данных
        JFreeChart chart = ChartFactory.createXYLineChart(
                "График",
                "X",
                "Y",
                dataset
        );

        XYPlot plot = chart.getXYPlot();
        ValueAxis yAxis = plot.getRangeAxis();

        if(yMin < 0){
            yAxis.setRange(yMin, 0);
        }
        else {
            yAxis.setRange(0, yMax);
        }



        ValueAxis xAxis = plot.getDomainAxis();
        xAxis.setRange(xMin, xMax);

        //Добавляем точки в серию
        Set<Double> args = input.keySet();
        for(Double i: args){
            series.add(i, input.get(i));
        }

        XYDotRenderer renderer = new XYDotRenderer();
        renderer.setDotHeight(5);
        renderer.setDotWidth(5);
        plot.setRenderer(renderer);

        ChartFrame frame = new ChartFrame(title, chart);
        frame.setVisible(true);
        frame.setSize(600, 400);
    }

    private XYSeries generateDataset(double from, double to, double step, FunctionCounter f) {
        XYSeries series = new XYSeries(f.hashCode());
        for (double x = from; x < to + step; x += step) {
            series.add(x, f.countFunc(x));
        }
        return series;
    }
}
