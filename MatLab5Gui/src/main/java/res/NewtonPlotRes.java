package res;

import method.NewtonMethod;

import java.util.ArrayList;

public class NewtonPlotRes {
    private ArrayList<Double> args;
    private ArrayList<Double> values;
    private boolean isCorrect;

    public NewtonPlotRes(ArrayList<Double> args, ArrayList<Double> values, double a, double b) {
        this.args = new ArrayList<>();
        this.values = new ArrayList<>();
        NewtonMethod newtonMethod = new NewtonMethod();
        double d[][] = newtonMethod.countDyn(values);

        double h = (b - a) / 200;
        while (a < b){
            this.args.add(a);
            this.values.add(newtonMethod.countCur(args, d, a));
            a += h;
        }

        isCorrect = true;
    }

    public NewtonPlotRes() {
        isCorrect = false;
    }

    public ArrayList<Double> getArgs() {
        return args;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
