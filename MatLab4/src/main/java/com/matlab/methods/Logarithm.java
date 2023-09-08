package com.matlab.methods;

import com.matlab.resultFunc.LogarithmResult;

import java.util.HashMap;
import java.util.Map;

public class Logarithm {
    public LogarithmResult count(HashMap<Double, Double> input){
        double SLnX = 0;
        double SLnXX = 0;
        double SY = 0;
        double SLnXY = 0;
        int n = 0;

        for (Map.Entry entry : input.entrySet()) {
            double arg = Math.log((double) entry.getKey());
            double value = (double) entry.getValue();
            SLnX += arg;
            SLnXX += arg * arg;
            SY += value;
            SLnXY += arg * value;
            n++;
        }

        double a = (n * SLnXY - SLnX * SY) / (n * SLnXX - SLnX * SLnX);
        double b = (SY * SLnXX - SLnXY * SLnX) / (n * SLnXX - SLnX * SLnX);


        return new LogarithmResult(a, b);
    }
}
