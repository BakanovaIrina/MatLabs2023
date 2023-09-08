package com.matlab.methods;

import com.matlab.FuncCounter;
import com.matlab.io.Data;
import com.matlab.result.Result;

public class SimpsonMethod {
    private FuncCounter funcCounter;
    private final int k = 4;

    public SimpsonMethod(int id) {
        this.funcCounter = new FuncCounter(id);
    }

    public double count(Data data){
        double yi;
        double h = (data.getB() - data.getA()) / data.getN();
        double i = 0;
        double res = 0;

        while (i <= data.getN()){
            yi = funcCounter.countFunc(data.getA() + h * i);
            if(i == 0 || i == data.getN())
                res += yi;
            else if(i % 2 == 0)
                res += yi * 2;
            else
                res += yi * 4;

            i++;
        }
        return res * h / 3;
    }

    public int getK() {
        return k;
    }
}
