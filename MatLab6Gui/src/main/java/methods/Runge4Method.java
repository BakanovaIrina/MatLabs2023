package methods;

import FuncCounter.FuncCounter;
import res.RungeRes;

import java.util.ArrayList;

public class Runge4Method {
    FuncCounter counter;

    public Runge4Method(FuncCounter counter) {
        this.counter = counter;
    }

    public RungeRes doMethod(double x0, double xn, double y0, double h, double eps, int id){
        boolean isCorrect = false;
        RungeRes countedResH = new RungeRes();
        ArrayList<Double> runge = new ArrayList<>();
        double curR = 0;
        while (!isCorrect){
            countedResH = count(x0, xn, y0, h, id);
            RungeRes countedResH2 = count(x0, xn, y0, h / 2, id);
            ArrayList<Double> x = countedResH.getX();

            for(int i = 0; i < x.size(); i++){
                curR = (countedResH.getY().get(i) - countedResH2.getY().get(i * 2)) / (Math.pow(2, 1) - 1);
                runge.add(curR);
                if(curR > eps){
                    h = h / 2;
                    isCorrect = false;
                    break;
                }
                else {
                    isCorrect = true;
                }
            }
        }

        countedResH.setRunge(runge);
        return countedResH;
    }

    public RungeRes count(double x0, double xn, double y0, double h, int id){
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<Double> f = new ArrayList<>();
        ArrayList<Double> k1 = new ArrayList<>();
        ArrayList<Double> k2 = new ArrayList<>();
        ArrayList<Double> k3 = new ArrayList<>();
        ArrayList<Double> k4 = new ArrayList<>();

        double curX = x0;
        double curY = y0;
        double curK1, curK2, curK3, curK4;
        while (curX - xn <= 0.00001){
            x.add(curX);
            y.add(curY);
            f.add(counter.count(curX, curY, id));

            curK1 = h * counter.count(curX, curY, id);
            k1.add(curK1);
            curK2 = h * counter.count(curX + h / 2, curY + curK1 / 2, id);
            k2.add(curK2);
            curK3 = h * counter.count(curX + h / 2, curY + curK2 / 2, id);
            k3.add(curK3);
            curK4 = h * counter.count(curX + h, curY + curK3, id);
            k4.add(curK4);


            curX += h;
            double j = curK1 + 2 * curK2 + 2 * curK3 + curK4;
            curY = curY + j / 6;
        }

        return new RungeRes(x, y, f, k1, k2, k3, k4);

    }
}
