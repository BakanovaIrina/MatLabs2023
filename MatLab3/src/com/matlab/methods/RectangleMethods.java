package com.matlab.methods;

import com.matlab.FuncCounter;
import com.matlab.io.Data;

public class RectangleMethods {
    private FuncCounter funcCounter;
    private final int k = 2;

    public RectangleMethods(int id) {
        this.funcCounter = new FuncCounter(id);
    }

    public double countLeft(Data data){
        double h = (data.getB() - data.getA()) / data.getN();
        double cur_x = data.getA();
        double res = 0;

        while (cur_x < data.getB()){
            res+= funcCounter.countFunc(cur_x) * h;

            cur_x += h;
        }

        return res;
    }

    public double countCentre(Data data){
        double h = (data.getB() - data.getA()) / data.getN();
        double xi = data.getA();
        double xi1 = xi + h;
        double res = 0;

        while (xi1 <= data.getB()){
            res += h * funcCounter.countFunc((xi + xi1) / 2);
            xi = xi1;
            xi1 += h;
        }

        return res;
    }

    public double countRight(Data data){
        double h = (data.getB() - data.getA()) / data.getN();
        double cur_x = data.getA() + h;
        double res = 0;

        while (cur_x <= data.getB()){
            res+= funcCounter.countFunc(cur_x) * h;

            cur_x += h;
        }

        return res;
    }

    public int getK() {
        return k;
    }
}
