package com.matlab.res;

import com.matlab.res.ResultIter;

import java.text.DecimalFormat;

public class ResultIterHalfDivisionMethod implements ResultIter {
    private final int iter;
    private final double a;
    private final double b;
    private final double x;
    private final double fa;
    private final double fb;
    private final double fx;
    private final double newEps;

    public ResultIterHalfDivisionMethod(int iter, double a, double b, double x, double fa, double fb,
                                        double fx, double newEps) {
        this.iter = iter;
        this.a = a;
        this.b = b;
        this.x = x;
        this.fa = fa;
        this.fb = fb;
        this.fx = fx;
        this.newEps = newEps;
    }

    @Override
    public String getLinearResult() {
        DecimalFormat format = new DecimalFormat( "0.000" );
        return Integer.toString(iter) + '\t' + format.format(a) + '\t' + format.format(b) +
                '\t' + format.format(x) + '\t' + format.format(fa) + '\t' + format.format(fb) +
                '\t' + format.format(fx) + '\t' + format.format(newEps);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public String getTitle() {
        return  "iter" + '\t' + "a  " + '\t' + "b  " + '\t' + "    x    " + '\t' + "f(a) " + '\t' + "f(b) " + '\t' +
                " f(x)" + '\t' + "|xi - xi+1|" + '\t' + '\n';
    }
}
