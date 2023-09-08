package com.matlab;

public class FuncCounter {
    private FuncManager funcManager;
    private Func func;

    public FuncCounter(int id) {
        FuncManager funcManager = new FuncManager();
        func = funcManager.getFunc(id);
    }

    public double countFunc(double x){
        return func.calc(x);
    }
}
