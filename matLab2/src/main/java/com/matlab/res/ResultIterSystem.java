package com.matlab.res;

import java.text.DecimalFormat;

public class ResultIterSystem implements ResultIter{

    private final int iter;
    private final double deltaX;
    private final double deltaY;
    private final double x;
    private final double y;
    private final double newEps1;
    private final double newEps2;

    public ResultIterSystem(int iter, double deltax, double deltaY, double x, double y, double newEps1,
                            double newEps2) {
        this.iter = iter;
        this.deltaX = deltax;
        this.deltaY = deltaY;
        this.x = x;
        this.y = y;
        this.newEps1 = newEps1;
        this.newEps2 = newEps2;
    }

    @Override
    public String getLinearResult() {

        DecimalFormat format = new DecimalFormat( "0.000" );
        return Integer.toString(iter) + '\t' + format.format(deltaX) + '\t'
                + format.format(deltaY) + '\t' + format.format(x) + '\t'
                + format.format(y) + '\t' + format.format(newEps1) + '\t' + format.format(newEps1);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public String getTitle() {
        return"iter" + '\t' + "deltaX" + '\t' + "deltaY" + '\t' + "x" + '\t' + "y" + '\t' +
                "|xi - xi+1|" + '\t' + "|yi - yi+1|" + '\t' + '\n';
    }

    public double getY() {
        return y;
    }
}
