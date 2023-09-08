package com.matlab.methods;

import com.matlab.resultFunc.ExponentialResult;
import com.matlab.resultFunc.PowerResult;

import java.util.HashMap;
import java.util.Map;

public class Exponential {
    public ExponentialResult count(HashMap<Double, Double> input){
        double SX = 0;
        double SXX = 0;
        double SLnY = 0;
        double SXLnY = 0;
        int n = 0;

        for (Map.Entry entry : input.entrySet()) {
            double arg = (double) entry.getKey();
            double value = Math.log((double) entry.getValue());
            SX += arg;
            SXX += arg * arg;
            SLnY += value;
            SXLnY += arg * value;
            n++;
        }

        double Lna = (SLnY * SXX - SXLnY * SX) / (n * SXX - SX * SX);
        double b = (n * SXLnY - SX * SLnY) / (n * SXX - SX * SX);

        double a = Math.exp(Lna);

        return new ExponentialResult(a, b);
    }
}
