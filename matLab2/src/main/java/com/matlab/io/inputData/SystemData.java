package com.matlab.io.inputData;

import com.matlab.func.Func;
import com.matlab.func.SystemFunc;

import java.util.List;

public class SystemData {

    private final int method;
    private final SystemFunc func1;
    private final SystemFunc func2;
    private final List<Func> f1;
    private final double x;
    private final double y;
    private final double eps;

    public SystemData(int method, SystemFunc func1, SystemFunc func2, List<Func> f1, double x, double y, double eps) {
        this.method = method;
        this.func1 = func1;
        this.func2 = func2;
        this.f1 = f1;
        this.x = x;
        this.y = y;
        this.eps = eps;
    }

    public int getMethod() {
        return method;
    }

    public SystemFunc getFunc1() {
        return func1;
    }

    public SystemFunc getFunc2() {
        return func2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getEps() {
        return eps;
    }

    public List<Func> getF1() {
        return f1;
    }
}
