package method;

import res.NewtonRes;

import java.util.ArrayList;

public class NewtonMethod {

    public NewtonRes count(ArrayList<Double> args, ArrayList<Double> values, Double x){
        double res;

        double[][] dyn = countDyn(values);

        for(int i = 0; i < values.size(); i++){
            dyn[0][i] = values.get(i);
        }

        for(int i = 1; i < values.size(); i++){
            for(int j = 0; j < values.size() - i; j++){
                dyn[i][j] = dyn[i - 1][j + 1] - dyn[i - 1][j];
            }
        }

        if(x <= args.get(args.size() / 2)){
            res = count1(args, x, dyn);
        }
        else {
            res = count2(args, x, dyn);
        }

        if(Double.isNaN(res) || Double.isInfinite(res)){
            return new NewtonRes(res, dyn, false);
        }


        return new NewtonRes(res, dyn);
    }

    public double[][] countDyn(ArrayList<Double> values){
        double[][] dyn = new double[values.size()][values.size()];

        for(int i = 0; i < values.size(); i++){
            dyn[0][i] = values.get(i);
        }

        for(int i = 1; i < values.size(); i++){
            for(int j = 0; j < values.size() - i; j++){
                dyn[i][j] = dyn[i - 1][j + 1] - dyn[i - 1][j];
            }
        }

        return dyn;

    }

    public double countCur(ArrayList<Double> args, double[][] dyn, Double x){
        double res;
        if(x <= args.get(args.size() / 2)){
            res = count1(args, x, dyn);
        }
        else {
            res = count2(args, x, dyn);
        }

        return res;
    }

    private double count1(ArrayList<Double> args, Double x, double[][] dyn){
        double h = args.get(1) - args.get(0);

        double t = (x - args.get(0)) / h;

        double answer = dyn[0][0];

        int fact = 1;
        int k = 1;

        for(int i = 1; i < args.size(); i++){
            answer += (t * dyn[i][0]) / fact;
            fact *= i + 1;
            t *= (t - k);
            k++;
        }

        return answer;
    }

    private double count2(ArrayList<Double> args, Double x, double[][] dyn){
        double h = args.get(1) - args.get(0);
        double t = (x - args.get(args.size() - 1)) / h;

        int j = args.size() - 1;
        double answer = dyn[0][j];
        int fact = 1;
        j--;
        int k = 1;

        for(int i = 1; i < args.size() - 1; i++){
            answer+= t * dyn[i][j] / fact;
            fact *= i + 1;
            j--;
            t *= (t + k);
            k++;
        }

        return answer;
    }
}
