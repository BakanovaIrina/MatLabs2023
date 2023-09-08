package com.matlab.func;

import java.util.ArrayList;
import java.util.List;

public class FuncManager {

    public Func getFunc(int id){
        Func f = null;
        switch (id) {
            case 1:
                f = x -> (Math.exp(x) - Math.pow(x, 4));
                break;
            case 2:
                f = x -> (Math.sin(x) + Math.cos(x));
                break;
            case 3:
                f = x -> (Math.pow(x, 3) + 10 * Math.pow(x, 2) - 7 * x + 10);
                break;
            case 4:
                f = x -> (Math.pow(x, 2) - 1.6);
                break;
            case 5:
                f = x -> (Math.pow(x, 3) - 6 * Math.pow(x, 2) + 11 * x - 6);
                break;
        }
        return f;
    }

    public SystemFunc getSystemFunc(int id, int number) {
        SystemFunc f = null;
        switch (id) {
            case 1:
                if (number == 0){
                    f = (x, y) -> (Math.pow(x, 2) + Math.pow(y, 2) - 4);
                }
                else
                    f = (x, y) -> (-3 * Math.pow(x, 2) + y);
                break;
            case 2:
                if(number == 0){
                    f = (x, y) -> (x * y - 1);
                }
                else
                    f = (x, y) -> (Math.pow(x, 3) + Math.pow(y, 3) - 4);
                break;
            case 3:
                if(number == 0){
                    f = (x, y) -> (2*y - 5*Math.exp(x) - 2);
                }
                else
                    f = (x, y) -> (Math.pow(x, 4) + y - 4);
                break;
        }

        return f;
    }

    public List<Func> getYFuncFromSystem(int id){
        List<Func> f = new ArrayList<>();
        Func f1;
        switch (id) {
            case 1:
                f1 = (x) -> (Math.sqrt(4 - x*x));
                f.add(f1);
                f1 = (x) -> ( - Math.sqrt(4 - x*x));
                f.add(f1);
                f1 = (x) -> (3 * Math.pow(x, 2));
                f.add(f1);
                break;
            case 2:
                f1 = (x) -> (1 / x);
                f.add(f1);
                f1 = (x) -> (Math.cbrt(4 - Math.pow(x, 3)));
                f.add(f1);
                break;
            case 3:
                f1 = (x) -> ((2 + 5 * Math.exp(x)) / 2);
                f.add(f1);
                f1 = (x) -> (4 - Math.pow(x, 4));
                f.add(f1);
                break;
        }

        return f;
    }
}
