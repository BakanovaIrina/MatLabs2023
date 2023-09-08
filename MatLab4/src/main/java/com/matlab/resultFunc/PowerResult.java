package com.matlab.resultFunc;

import com.matlab.methods.Power;

public class PowerResult implements FunctionCounter{
    private final double a;
    private final double b;

    public PowerResult(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    @Override
    public double countFunc(double x) {
        return a * Math.pow(x, b);
    }

    @Override
    public String getStringFunction() {
        return a + "x^" + b;
    }
}
