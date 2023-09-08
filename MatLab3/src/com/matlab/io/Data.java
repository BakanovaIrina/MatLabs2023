package com.matlab.io;

public class Data {
    private final int method;
    private final double a;
    private final double b;
    private final double eps;
    private final int functionId;
    private int n;

    public Data(int method, double a, double b, double eps, int functionId, int n) {
        this.method = method;
        this.a = a;
        this.b = b;
        this.eps = eps;
        this.functionId = functionId;
        this.n = n;
    }

    public Data(Data data, int n) {
        this.method = data.getMethod();
        this.a = data.getA();
        this.b = data.getB();
        this.eps = data.getEps();
        this.functionId = data.getFunctionId();
        this.n = n;
    }

    public int getMethod() {
        return method;
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

    public int getFunctionId() {
        return functionId;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
