package com.matlab;

public class FuncManager {

    public Func getFunc(int id){
        Func f = null;
        switch (id) {
            case 1:
                f = x -> (Math.pow(x, 2));
                break;
            case 2:
                f = x -> (Math.pow(x, 5) - Math.pow(x, 2) + 4 * x - 6);
                break;
            case 3:
                f = x -> (1 / (Math.pow(x, 2) + 1));
                break;
            case 4:
                f = x -> ( 1 / Math.sqrt(Math.pow(Math.sin(x), 2) + 16));
                break;
            case 5:
                f = x -> (Math.sqrt(Math.pow(x, 2) + 5) - Math.pow(x, 2));
                break;
        }
        return f;
    }
}
