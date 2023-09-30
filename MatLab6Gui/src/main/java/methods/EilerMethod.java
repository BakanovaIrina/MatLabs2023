package methods;


import FuncCounter.FuncCounter;
import res.EilerRes;

import java.util.ArrayList;

public class EilerMethod {
    FuncCounter counter;

    public EilerMethod(FuncCounter counter) {
        this.counter = counter;
    }

    public EilerRes doMethod(double x0, double xn, double y0, double h, double eps, int id){
        boolean isCorrect = false;
        EilerRes countedResH = new EilerRes();
        ArrayList<Double> runge = new ArrayList<>();
        double curR = 0;
        while (!isCorrect){
            countedResH = count(x0, xn, y0, h, id);
            EilerRes countedResH2 = count(x0, xn, y0, h / 2, id);
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

    public EilerRes count(double x0, double xn, double y0, double h, int id){
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<Double> f = new ArrayList<>();

        double curX = x0;
        double curY = y0;
        double curF;
        while (curX - xn <= 0.00001){
            x.add(curX);
            y.add(curY);
            curF = counter.count(curX, curY, id);
            f.add(curF);
            curX += h;
            curY = curY + h * curF;
        }

        return new EilerRes(x, y, f);

    }
}
