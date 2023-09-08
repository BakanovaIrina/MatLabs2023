package com.matlab.methods;

import com.matlab.func.SystemFunc;
import com.matlab.io.inputData.SystemData;
import com.matlab.res.ResultIterSystem;
import com.matlab.res.ResultMatrix;

import java.util.ArrayList;
import java.util.List;

public class SystemMethods {
    public static final double h = 1e-6;

    //По методу центральной разности
    private SystemFunc derivative(SystemFunc f, boolean dx) {
        if (dx) {
            return (x, y) -> ((f.calc(x + h, y) - f.calc(x - h, y)) / 2*h);
        } else {
            return (x, y) -> ((f.calc(x, y + h) - f.calc(x, y - h)) / 2*h);
        }
    }

    private ResultMatrix solveMatrix(double[][] matrix){
        double y = (matrix[0][0] * matrix[1][2] - matrix[1][0] * matrix[0][2]) /
                (matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1]);
        double x = (matrix[0][2] - matrix[0][1] * y) / matrix[0][0];
        return new ResultMatrix(x, y);
    }

    public List<ResultIterSystem> systemNewtonMethod(SystemData data) {
        List<ResultIterSystem> res = new ArrayList<>();
        SystemFunc f1d_x = derivative(data.getFunc1(), true);
        SystemFunc f1d_y = derivative(data.getFunc1(), false);
        SystemFunc f2d_x = derivative(data.getFunc2(), true);
        SystemFunc f2d_y = derivative(data.getFunc2(), false);

        int iteration = 0;
        double xi = data.getX();
        double yi = data.getY();
        double x0;
        double y0;

        do {
            double[][] matrix = new double[][]{
                    {f1d_x.calc(xi, yi), f1d_y.calc(xi, yi), -data.getFunc1().calc(xi, yi)},
                    {f2d_x.calc(xi, yi), f2d_y.calc(xi, yi), -data.getFunc2().calc(xi, yi)}
            };

            ResultMatrix resultMatrix = solveMatrix(matrix);
            x0 = xi;
            y0 = yi;

            xi = x0 + resultMatrix.getX();
            yi = y0 + resultMatrix.getY();

            iteration++;

            res.add(new ResultIterSystem(iteration, resultMatrix.getX(), resultMatrix.getY(),
                    xi, yi, Math.abs(xi - x0), Math.abs(yi - y0)));


        }
        while (Math.abs(xi - x0) > data.getEps() || Math.abs(yi - y0) > data.getEps());

        return res;
    }
}
