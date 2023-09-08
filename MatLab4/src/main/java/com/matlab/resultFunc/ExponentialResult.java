package com.matlab.resultFunc;

public class ExponentialResult implements FunctionCounter{
    private final double a;
    private final double b;

    public ExponentialResult(double a, double b) {
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
        return a * Math.exp(b * x);
    }

    @Override
    public String getStringFunction() {
        return a + "e^(" + b + "x)";
    }
}
