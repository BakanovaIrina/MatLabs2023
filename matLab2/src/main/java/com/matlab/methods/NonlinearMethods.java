package com.matlab.methods;

import com.matlab.func.Func;
import com.matlab.io.inputData.Data;
import com.matlab.res.*;

import java.util.ArrayList;
import java.util.List;

public class NonlinearMethods {
    public static final double h = 1e-6;

    public List<ResultIter> newtonMethod(Data data){
        List<ResultIter> res = new ArrayList<>();
        double xi = getX0Newton(data.getA(), data.getB(), data.getFunc());
        double xi1;
        int iteration = 0;

        boolean iterationProcess = true;
        while (iterationProcess){
            iteration++;
            double func_val = data.getFunc().calc(xi);
            double func_derivative_value = derivativeAtPoint(data.getFunc(), xi);
            xi1 = xi - func_val / func_derivative_value;

            double e = Math.abs(xi1 - xi);

            if(e <= data.getEps()){
                iterationProcess = false;
            }
            res.add(new ResultIterNewton(iteration, xi, func_val, func_derivative_value, xi1, e));
            xi = xi1;
        }

        return res;
    }

    private double getX0Newton(double a, double b, Func f){
        if(f.calc(a) * f.calc(b) > 0){
            return a;
        }
        else
            return b;
    }

    public List<StepMethodResult> stepMethod(double a, double b, Func f, int iterations){
        List<StepMethodResult> res = new ArrayList<>();
        double h = Math.max(0.25, (b - a) / iterations);
        double fx = f.calc(a);
        while (a < b){
            a+=h;
            if(f.calc(a) * fx <= 0){
                res.add(new StepMethodResult(a - h, a));
            }
            fx = f.calc(a);
        }
        return res;
    }

    //По методу центральной разности
    private double derivativeAtPoint(Func f, double x){
        return (f.calc(x + h) - f.calc(x - h)) / (2 * h);
    }


    public List<ResultIter> simpleIterationMethod(Data data){
        List<ResultIter> res = new ArrayList<>();
        double cur = data.getA();
        double max_dx = derivativeAtPoint(data.getFunc(), cur);
        while (cur < data.getB()){
            double cur_dx = derivativeAtPoint(data.getFunc(), cur);
            max_dx = Math.max(max_dx, cur_dx);
            cur += (data.getB() - data.getA()) / 10000;
        }

        double lamb = -1 / max_dx;
        int iterations = 0;
        double xi = data.getA();
        double func_value = data.getFunc().calc(xi);
        double xi1 = xi + lamb * func_value;

        while (Math.abs(xi - xi1) > data.getEps() &
                Math.abs(xi - xi1) >= (1-max_dx) * data.getEps() / max_dx) {
            iterations++;
            xi = xi1;
            func_value = data.getFunc().calc(xi);
            xi1 = xi + lamb * func_value;
            res.add(new ResultIterSimpleIterationMethod(iterations, xi, xi1, func_value, Math.abs(xi - xi1)));
        }

        return res;
    }

    public List<ResultIter> halfDivisionMethod(Data data){
        List<ResultIter> res = new ArrayList<>();

        double cur_a = data.getA();
        double cur_b = data.getB();
        int iterations = 0;

        double xi = (cur_a + cur_b) / 2;
        double fx = data.getFunc().calc(xi);

        res.add(new ResultIterHalfDivisionMethod(iterations, cur_a, cur_b, xi, data.getFunc().calc(cur_a),
                data.getFunc().calc(cur_b), fx, Math.abs(cur_b - cur_a)));

        while (Math.abs(cur_b - cur_a) > data.getEps()){
            iterations++;
            if(fx * data.getFunc().calc(cur_a) <= 0){
                cur_b = xi;
            }
            else {
                cur_a = xi;
            }
            xi = (cur_a + cur_b) / 2;
            fx = data.getFunc().calc(xi);

            res.add(new ResultIterHalfDivisionMethod(iterations, cur_a, cur_b, xi, data.getFunc().calc(cur_a),
                    data.getFunc().calc(cur_b), fx, Math.abs(cur_b - cur_a)));
        }

        return res;
    }
}
