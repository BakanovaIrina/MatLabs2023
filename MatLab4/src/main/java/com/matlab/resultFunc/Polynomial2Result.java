package com.matlab.resultFunc;

public class Polynomial2Result implements FunctionCounter{
    private final double a0;
    private final double a1;
    private final double a2;

    public Polynomial2Result(double a0, double a1, double a2) {
        this.a0 = a0;
        this.a1 = a1;
        this.a2 = a2;
    }

    public double getA0() {
        return a0;
    }

    public double getA1() {
        return a1;
    }

    public double getA2() {
        return a2;
    }

    @Override
    public double countFunc(double x) {
        return a0 + a1 * x + a2 * x * x;
    }

    @Override
    public String getStringFunction() {
        return a2 + "x^2 + " + a1 + "x + " + a0;
    }
}
