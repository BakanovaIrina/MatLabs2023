package methods;

import FuncCounter.FuncCounter;
import methods.Runge4Method;
import res.AdamsRes;
import res.RungeRes;

import java.util.ArrayList;
import java.util.Collections;

public class AdamsMethod {
    FuncCounter counter;
    Runge4Method runge4Method;

    public AdamsMethod(FuncCounter counter, Runge4Method runge4Method) {
        this.counter = counter;
        this.runge4Method = runge4Method;
    }

    public AdamsRes doMethod(double x0, double xn, double y0, double h, double eps, int id){
        boolean isCorrect = false;
        int i = 0;
        AdamsRes adamsRes = new AdamsRes();
        while (!isCorrect){
            adamsRes = count(x0, xn, y0, h, id);
            double maxEps = Collections.max(adamsRes.geteAll());
            if(maxEps < eps){
                isCorrect = true;
            }
            else {
                h = h / 2;
                i++;
            }

            if(i > 5){
                return new AdamsRes();
            }
        }
        return adamsRes;
    }

    public AdamsRes count(double x0, double xn, double y0, double h, int id){
        RungeRes res = countFirst4(x0, x0 + 4 * h, y0, h, id);
        ArrayList<Double> f = res.getF();
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<Double> eY = new ArrayList<>();
        ArrayList<Double> epsAll = new ArrayList<>();

        x.addAll(res.getX());
        y.addAll(res.getY());

        for(int i = 0; i < 5; i++){
            epsAll.add(Math.abs(res.getY().get(i) - counter.countExact(x0 + h * i, id)));
            eY.add(counter.countExact(x0 + h * i, id));
        }


        double fi, fi1, fi2, fi3;

        fi = f.get(3);
        fi1 = f.get(2);
        fi2 = f.get(1);
        fi3 = f.get(0);

        double df1 = fi - fi1;
        double df2 = fi - 2 * fi1 + fi2;
        double df3 = fi - 3 * fi1 + 3 * fi2 - fi3;

        double curY = res.getY().get(f.size() - 1);
        double curX = res.getX().get(f.size() - 1);

        curY = curY + h * fi + h * h * df1 / 2 + 5 * h * h * h * df2 / 12 + 3 * h * h * h * h * df3 / 8;
        curX += h;
        while (curX - xn <= 0.0001){
            x.add(curX);
            y.add(curY);
            epsAll.add(Math.abs(curY - counter.countExact(curX, id)));
            eY.add(counter.countExact(curX, id));

            fi3 = fi2;
            fi2 = fi1;
            fi1 = fi;
            fi = counter.count(curX, curY, id);

            df1 = fi - fi1;
            df2 = fi - 2 * fi1 + fi2;
            df3 = fi - 3 * fi1 + 3 * fi2 - fi3;

            curX += h;
            curY = curY + h * fi + h * h * df1 / 2 + 5 * h * h * h * df2 / 12 + 3 * h * h * h * h * df3 / 8;
        }

        return new AdamsRes(x, y, eY, epsAll);

    }

    public RungeRes countFirst4(double x0, double xn, double y0, double h, int id){
        return runge4Method.count(x0, xn, y0, h, id);
    }
}
