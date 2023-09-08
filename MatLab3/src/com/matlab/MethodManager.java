package com.matlab;

import com.matlab.io.Data;
import com.matlab.methods.RectangleMethods;
import com.matlab.methods.SimpsonMethod;
import com.matlab.methods.TrapezoidMethod;
import com.matlab.result.Result;

public class MethodManager {
    private final RectangleMethods rm;
    private final TrapezoidMethod tm;
    private final SimpsonMethod sm;

    public MethodManager(int id) {
        this.rm = new RectangleMethods(id);
        this.tm = new TrapezoidMethod(id);
        this.sm = new SimpsonMethod(id);
    }

    public Result countWhileNotRungeCondition(Data data){
        Data I0;
        Data I1 = new Data(data, data.getN() * 2);

        double res0;
        double res1 = 0;

        double curEps = data.getEps() + 1;

        while (curEps > data.getEps()){
            I0 = I1;
            I1 = new Data(I1, I1.getN() * 2);

            switch (data.getMethod()){
                case 1:
                    res0 = rm.countLeft(I0);
                    res1 = rm.countLeft(I1);
                    curEps = Math.abs(res0 - res1) / (Math.pow(2, rm.getK()));
                    break;
                case 2:
                    res0 = rm.countCentre(I0);
                    res1 = rm.countCentre(I1);
                    curEps = Math.abs(res0 - res1) / (Math.pow(2, rm.getK()));
                    break;
                case 3:
                    res0 = rm.countRight(I0);
                    res1 = rm.countRight(I1);
                    curEps = Math.abs(res0 - res1) / (Math.pow(2, rm.getK()));
                    break;
                case 4:
                    res0 = tm.count(I0);
                    res1 = tm.count(I1);
                    curEps = Math.abs(res0 - res1) / (Math.pow(2, tm.getK()));
                    break;
                case 5:
                    res0 = sm.count(I0);
                    res1 = sm.count(I1);
                    curEps = Math.abs(res0 - res1) / (Math.pow(2, sm.getK()));
                    break;
                default:
                    System.out.println("Такого метода не существует");
                    System.exit(0);
            }
        }

        return new Result(res1, I1.getN());
    }
}
