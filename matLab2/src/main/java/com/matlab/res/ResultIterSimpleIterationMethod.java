package com.matlab.res;

import com.matlab.res.ResultIter;

import java.text.DecimalFormat;

public class ResultIterSimpleIterationMethod implements ResultIter {
    private final int iter;
    private final double  xi;
    private final double  xi1;
    private final double func_value;
    private final double newEps;

    public ResultIterSimpleIterationMethod(int iter, double xi, double xi1, double func_value, double newEps) {
        this.iter = iter;
        this.xi = xi;
        this.xi1 = xi1;
        this.func_value = func_value;
        this.newEps = newEps;
    }


    @Override
    public String getLinearResult() {
        DecimalFormat format = new DecimalFormat( "0.000" );
        return Integer.toString(iter) + '\t' + format.format(xi) + '\t' + format.format(xi1) + '\t'
                + format.format(func_value) + '\t' + format.format(newEps);
    }

    @Override
    public double getX() {
        return xi1;
    }

    @Override
    public String getTitle() {
        return "iter" + '\t' + "xi" + '\t' + "xi+1" + '\t' + "f(x)" + '\t' + "|xi - xi+1|" + '\t' + '\n';
    }
}
