package com.matlab.resultFunc;

public class Polynomial3Result implements FunctionCounter{
    private final double a0;
    private final double a1;
    private final double a2;
    private final double a3;

    public Polynomial3Result(double a0, double a1, double a2, double a3) {
        this.a0 = a0;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }

    @Override
    public double countFunc(double x) {
        double k = a0 + a1 * x + a2 * x * x + a3 * x * x * x;
        return a0 + a1 * x + a2 * x * x + a3 * x * x * x;
    }

    @Override
    public String getStringFunction() {
        return a3 + "x^3 + " + a2 + "x^2 + " + a1 + "x + " + a0;
    }
}
