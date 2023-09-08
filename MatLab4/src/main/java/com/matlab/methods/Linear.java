package com.matlab.methods;

import com.matlab.resultFunc.LinearResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Linear {
    public LinearResult count(HashMap<Double, Double> input){
        /*
        Set<Double> args = input.keySet();
        System.out.println("Ключи: " + args);

        ArrayList<Double> values = new ArrayList<>(input.values());
        System.out.println("Значения: " + values);

         */

        double SX = 0;
        double SXX = 0;
        double SY = 0;
        double SXY = 0;
        int n = 0;

        for (Map.Entry entry : input.entrySet()) {
            double arg = (double) entry.getKey();
            double value = (double) entry.getValue();
            SX += arg;
            SXX += arg * arg;
            SY += value;
            SXY += arg * value;
            n++;
        }

        double a = (SXY * n - SX * SY) / (SXX * n - SX * SX);
        double b = (SXX * SY - SX * SXY) / (SXX * n - SX * SX);

        return new LinearResult(a, b);
    }
}
