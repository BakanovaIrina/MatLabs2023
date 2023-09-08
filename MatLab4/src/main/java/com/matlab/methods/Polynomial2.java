package com.matlab.methods;

import com.matlab.resultFunc.Polynomial2Result;

import java.util.HashMap;
import java.util.Map;

public class Polynomial2 {
    public Polynomial2Result count(HashMap<Double, Double> input){

        double SX = 0;
        double SX2 = 0;
        double SX3 = 0;
        double SX4 = 0;
        double SY = 0;
        double SXY = 0;
        double SX2Y = 0;
        int n = 0;

        for (Map.Entry entry : input.entrySet()) {
            double arg = (double) entry.getKey();
            double value = (double) entry.getValue();
            SX += arg;
            SX2 += arg * arg;
            SX3 += arg * arg * arg;
            SX4 += arg * arg * arg * arg;
            SY += value;
            SXY += arg * value;
            SX2Y += arg * arg * value;
            n++;
        }

        double d = n * SX2 * SX4 + 2 * SX * SX2 * SX3 - Math.pow(SX2, 3) - SX * SX * SX4 - n * SX3 * SX3;
        double d1 = SX2 * SX4 * SY + SX2 * SX3 * SXY + SX * SX3 * SX2Y - SX2 * SX2 * SX2Y - SX3 * SX3 * SY - SX * SX4 * SXY;
        double d2 = n * SX4 * SXY + SX * SX2 * SX2Y + SX2 * SX3 * SY - SX2 * SX2 * SXY - n * SX3 * SX2Y - SX * SX4 * SY;
        double d3 = n * SX2 * SX2Y + SX * SX3 * SY + SX * SX2 * SXY - SX2 * SX2 * SY - n * SX3 * SXY - SX * SX * SX2Y;

        double a0 = d1 / d;
        double a1 = d2 / d;
        double a2 = d3 / d;

        return new Polynomial2Result(a0, a1, a2);
    }
}
