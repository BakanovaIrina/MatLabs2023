package com.matlab.io;

import com.matlab.EpsCounter.TableCounter;
import com.matlab.plot.Graph;
import com.matlab.resultFunc.FunctionCounter;
import com.matlab.resultFunc.LinearResult;

import java.util.*;
import java.util.stream.Collectors;

public class ResultView {
    private final FunctionCounter functionCounter;

    public ResultView(FunctionCounter functionCounter) {
        this.functionCounter = functionCounter;
    }

    public String doTable(HashMap<Double, Double> input, TableCounter tableCounter){
        String res = "";
        tableCounter.countTable(input, functionCounter);

        res += "\n" + functionCounter.getStringFunction();

        res += "\nN\t";
        for (int i = 1; i <= input.size(); i++){
            res += i + "\t\t";
        }

        res += "\nX\t";

        TreeMap<Double, Double> sortedMap = new TreeMap<>();
        sortedMap.putAll(input);

        Set<Double> args = sortedMap.keySet();
        for(Double i: args){
            res += String.format("%.4f\t", i);
        }

        res += "\nY\t";

        ArrayList<Double> values = new ArrayList<>(sortedMap.values());
        for(Double i: values){
            res += String.format("%.4f\t", i);
        }

        res += "\nf\t";

        ArrayList<Double> f = tableCounter.getF();
        for (Double i: f){
            res += String.format("%.4f\t", functionCounter.countFunc(i));
        }

        res += "\ne\t";

        ArrayList<Double> e = tableCounter.getE();
        for (Double i: e){
            res += String.format("%.4f\t", i);
        }

        res += "\nМера отклонения S: " + tableCounter.getS();

        res += "\nСреднеквадратичное отклонение: " + tableCounter.getSigm() + "\n";

        if (functionCounter instanceof LinearResult){
            res += "Коэффициент Пирсона:" + tableCounter.getR() + "\n";
        }

        return res;
    }

    public void doGraph(HashMap<Double, Double> input, FunctionCounter functionCounter, String title){
        Set<Double> args = input.keySet();
        double maxX = Collections.max(args) + 1;
        double minX = Collections.min(args) - 1;

        Set<Double> values = input.values().stream().collect(Collectors.toSet());
        double maxY = Collections.max(values) + 1;
        double minY = Collections.min(values) - 1;


        Graph graphLinear = new Graph("Линейная аппроксимация");
        graphLinear.graph(minX, maxX, minY, maxY, input, functionCounter, title);
    }
}
