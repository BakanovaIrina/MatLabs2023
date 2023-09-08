package com.matlab.resultFunc;

public class LinearResult implements FunctionCounter{
    private final double a;
    private final double b;

    public LinearResult(double a, double b) {
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
        return a * x + b;
    }

    @Override
    public String getStringFunction() {
        return a + "x + " + b;
    }
}
