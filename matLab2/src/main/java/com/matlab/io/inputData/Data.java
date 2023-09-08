package com.matlab.io.inputData;

import com.matlab.func.Func;

public class Data {

    private final int method;
    private final Func func;
    private final double a;
    private final double b;
    private final double eps;

    public Data(int method, Func func, double a, double b, double eps) {
        this.method = method;
        this.func = func;
        this.a = a;
        this.b = b;
        this.eps = eps;
    }


    public int getMethod() {
        return method;
    }

    public Func getFunc() {
        return func;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getEps() {
        return eps;
    }
}
