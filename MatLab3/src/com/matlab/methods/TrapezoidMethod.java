package com.matlab.methods;

import com.matlab.FuncCounter;
import com.matlab.io.Data;

public class TrapezoidMethod {
    private FuncCounter funcCounter;
    private final int k = 2;

    public TrapezoidMethod(int id){
        this.funcCounter = new FuncCounter(id);
    }


    public double count(Data data){
        double h = (data.getB() - data.getA()) / data.getN();
        double y0 = funcCounter.countFunc(data.getA());
        double yn = funcCounter.countFunc(data.getB());
        double x0 = data.getA();

        double sum = 0;
        double i = 1;

        while (i < data.getN()){
            sum += funcCounter.countFunc(x0 + h * i);
            i++;
        }

        return h * ((y0 + yn) / 2 + sum);
    }

    public int getK() {
        return k;
    }
}
