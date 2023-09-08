package com.matlab.res;

import com.matlab.res.ResultIter;

import java.text.DecimalFormat;

public class ResultIterNewton implements ResultIter {
    private final int iter;
    private final double  xi;
    private final double func_value;
    private final double func_derivative_value;
    private final double xi1;
    private final double newEps;

    public ResultIterNewton(int iter, double xi, double func_value, double func_derivative_value,
                            double xi1, double newEps) {
        this.iter = iter;
        this.xi = xi;
        this.func_value = func_value;
        this.func_derivative_value = func_derivative_value;
        this.xi1 = xi1;
        this.newEps = newEps;
    }

    @Override
    public String getLinearResult() {
        DecimalFormat format = new DecimalFormat( "0.000" );
        return Integer.toString(iter) + '\t' + format.format(xi) + '\t' + format.format(func_value) +
                '\t' + format.format(func_derivative_value) + '\t' + format.format(xi1) + '\t' + format.format(newEps);
    }

    @Override
    public double getX() {
        return xi1;
    }

    @Override
    public String getTitle() {
        return "iter" + '\t' + "xi" + '\t' + "f(x)" + '\t' + "f'(x)" + '\t' + "xi+1" +
                '\t' + "|xi - xi+1|" + '\t' + '\n';
    }
}
