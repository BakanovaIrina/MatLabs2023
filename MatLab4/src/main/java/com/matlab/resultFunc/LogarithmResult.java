package com.matlab.resultFunc;

public class LogarithmResult implements FunctionCounter{
    private final double a;
    private final double b;

    public LogarithmResult(double a, double b) {
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
        return a * Math.log(x) + b;
    }

    @Override
    public String getStringFunction() {
        return a + " * Ln(x) + " + b;
    }
}
