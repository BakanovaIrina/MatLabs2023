package com.matlab.result;

public class Result {
    private final double value;
    private final int n;

    public Result(double value, int n) {
        this.value = value;
        this.n = n;
    }

    public double getValue() {
        return value;
    }

    public int getN() {
        return n;
    }
}
