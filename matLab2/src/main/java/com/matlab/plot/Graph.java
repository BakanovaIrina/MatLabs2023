package com.matlab.plot;

import com.matlab.func.SystemFunc;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.matlab.func.Func;

import java.awt.*;
import java.util.List;


public class Graph extends ApplicationFrame {
    public static final double step = 0.01;

    public Graph(String title) {
        super(title);
    }

    public void graph(double a, double b, double x, double y, Func func) {
        draw(a, b, x, y, generateDataset(a, b, step, func));
    }

    public void graph(double a, double b, double x, double y, List<Func> func) {

        draw(a, b, x, y, generateDatasetForSystem(a, b, step, func));
    }

    public void draw(double a, double b, double x, double y, XYSeries series){
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
        if(y < 0){
            yAxis.setRange(- 10.0, 0);
        }
        else {
            yAxis.setRange(0, 10.0);
        }

        ValueAxis xAxis = plot.getDomainAxis();
        xAxis.setRange(- 10.0, 10.0);



        // Добавляем точку в серию
        //series.add(-1.5, 2.5);
        series.add(x, y);

        XYDotRenderer renderer = new XYDotRenderer();
        renderer.setDotHeight(5);
        renderer.setDotWidth(5);
        plot.setRenderer(renderer);

        ChartFrame frame = new ChartFrame("График", chart);
        frame.setVisible(true);
        frame.setSize(600, 400);
    }

    private XYSeries generateDataset(double from, double to, double step, Func f) {
        XYSeries series = new XYSeries(f.hashCode());
        for (double x = from; x < to + step; x += step) {
            series.add(x, f.calc(x));
        }
        return series;
    }

    private XYSeries generateDatasetForSystem(double from, double to, double step, List<Func> func){
        XYSeries series = new XYSeries(func.get(0).hashCode());
        for (double x = from; x < to + step; x += step) {
            for(Func f: func){
                series.add(x, f.calc(x));
            }
        }
        return series;
    }
}
