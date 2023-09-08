package com.matlab.methods;

import com.matlab.resultFunc.Polynomial3Result;

import java.util.HashMap;
import java.util.Map;

public class Polynomial3 {
    public Polynomial3Result count(HashMap<Double, Double> input){
        double SX = 0;
        double SX2 = 0;
        double SX3 = 0;
        double SX4 = 0;
        double SX5 = 0;
        double SX6 = 0;
        double SY = 0;
        double SXY = 0;
        double SX2Y = 0;
        double SX3Y = 0;
        int n = 0;

        for (Map.Entry entry : input.entrySet()) {
            double arg = (double) entry.getKey();
            double value = (double) entry.getValue();
            SX += arg;
            SX2 += arg * arg;
            SX3 += arg * arg * arg;
            SX4 += arg * arg * arg * arg;
            SX5 += arg * arg * arg * arg * arg;
            SX6 += arg * arg * arg * arg * arg * arg;
            SY += value;
            SXY += arg * value;
            SX2Y += arg * arg * value;
            SX3Y += arg * arg * arg * value;
            n++;
        }

        double d = count4matrix(n, SX, SX2, SX3,
                                SX, SX2, SX3, SX4,
                                SX2, SX3, SX4, SX5,
                                SX3, SX4, SX5, SX6);
        double d1 = count4matrix(SY, SX, SX2, SX3,
                                SXY, SX2, SX3, SX4,
                                SX2Y, SX3, SX4, SX5,
                                SX3Y, SX4, SX5, SX6);
        double d2 = count4matrix(n, SY, SX2, SX3,
                                SX, SXY, SX3, SX4,
                                SX2, SX2Y, SX4, SX5,
                                SX3, SX3Y, SX5, SX6);
        double d3 = count4matrix(n, SX, SY, SX3,
                                SX, SX2, SXY, SX4,
                                SX2, SX3, SX2Y, SX5,
                                SX3, SX4, SX3Y, SX6);
        double d4 = count4matrix(n, SX, SX2, SY,
                                SX, SX2, SX3, SXY,
                                SX2, SX3, SX4, SX2Y,
                                SX3, SX4, SX5, SX3Y);


        double a0 = d1 / d;
        double a1 = d2 / d;
        double a2 = d3 / d;
        double a3 = d4 / d;

        return new Polynomial3Result(a0, a1, a2, a3);
    }

    private double count3matrix(double a11, double a12, double a13,
                                double a21, double a22, double a23,
                                double a31, double a32, double a33){
        return (a11 * a22 * a33 + a21 * a32 * a13 + a12 * a31 * a23 - a13 * a22 * a31 - a12 * a21 * a33 - a11 * a32 * a23);
    }

    private double count4matrix(double a11, double a12, double a13, double a14,
                                double a21, double a22, double a23, double a24,
                                double a31, double a32, double a33, double a34,
                                double a41, double a42, double a43, double a44){
        return a11 * count3matrix(a22, a23, a24,
                                  a32, a33, a34,
                                  a42, a43, a44)
                - a12 * count3matrix(a21, a23, a24,
                                    a31, a33, a34,
                                    a41, a43, a44)
                + a13 * count3matrix(a21, a22, a24,
                                     a31, a32, a34,
                                     a41, a42, a44)
                - a14 * count3matrix(a21, a22, a23,
                                     a31, a32, a33,
                                     a41, a42, a43);
    }
}
