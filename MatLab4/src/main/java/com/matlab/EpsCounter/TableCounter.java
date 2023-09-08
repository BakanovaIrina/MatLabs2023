package com.matlab.EpsCounter;

import com.matlab.resultFunc.FunctionCounter;
import com.matlab.resultFunc.LinearResult;

import java.util.*;

public class TableCounter {
    private ArrayList<Double> f;
    private ArrayList<Double> e;
    private double S;
    private double sigm;
    private double r;

    public TableCounter() {
        this.f = new ArrayList<>();
        this.e = new ArrayList<>();
    }

    public void countTable(HashMap<Double, Double> input, FunctionCounter functionCounter){
        TreeMap<Double, Double> sortedMap = new TreeMap<>();
        sortedMap.putAll(input);

        Set<Double> args = sortedMap.keySet();
        ArrayList<Double> values = new ArrayList<>(sortedMap.values());

        for(Double i: args){
            f.add(functionCounter.countFunc(i));
        }

        S = 0;
        double curE;
        for (Double i: args){
            curE = functionCounter.countFunc(i) - input.get(i);
            S += curE * curE;
            e.add(curE);
        }

        sigm = Math.sqrt(S / input.size());

        if (functionCounter instanceof LinearResult){
            double x_ = 0;
            double y_ = 0;
            for(Double i: args){
                x_ += i;
                y_ += input.get(i);
            }
            x_ /= args.size();
            y_ /= args.size();

            double r1 = 0;
            double r2 = 0;
            double r3 = 0;
            for(Double i: args){
                r1 += (i - x_) * (input.get(i) - y_);
                r2 += (i - x_) * (i - x_);
                r3 += (input.get(i) - y_) * (input.get(i) - y_);
            }
            r = r1 / Math.sqrt(r2 * r3);
        }
    }

    public ArrayList<Double> getF() {
        return f;
    }

    public ArrayList<Double> getE() {
        return e;
    }

    public double getS() {
        return S;
    }

    public double getSigm() {
        return sigm;
    }

    public double getR() {
        return r;
    }
}
