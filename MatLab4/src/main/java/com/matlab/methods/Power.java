package com.matlab.methods;

import com.matlab.resultFunc.LinearResult;
import com.matlab.resultFunc.PowerResult;

import java.util.HashMap;
import java.util.Map;

//Степенная функция
public class Power {
    public PowerResult count(HashMap<Double, Double> input){

        double SLgX = 0;
        double SLgXX = 0;
        double SLgY = 0;
        double SLgXY = 0;
        int n = 0;

        for (Map.Entry entry : input.entrySet()) {
            double arg = Math.log10((double) entry.getKey());
            double value = Math.log10((double) entry.getValue());
            SLgX += arg;
            SLgXX += arg * arg;
            SLgY += value;
            SLgXY += arg * value;
            n++;
        }

        double Lga = (SLgY * SLgXX - SLgXY * SLgX) / (n * SLgXX - SLgX * SLgX);
        double b = (n * SLgXY - SLgX * SLgY) / (n * SLgXX - SLgX * SLgX);

        double a = Math.pow(10, Lga);

        return new PowerResult(a, b);
    }
}
