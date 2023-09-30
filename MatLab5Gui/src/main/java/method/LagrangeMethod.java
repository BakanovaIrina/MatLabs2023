package method;

import res.LagrangeRes;

import java.util.ArrayList;

public class LagrangeMethod {

    public LagrangeRes count(ArrayList<Double> args, ArrayList<Double> values, Double x){
        ArrayList<Double> L = new ArrayList<>();
        int countL = args.size();

        double li = 1;
        for(int i = 0; i < countL; i++){
            for(int j = 0; j < countL; j++){
                if(i != j){
                    li *= (x - args.get(j)) / (args.get(i) - args.get(j));
                }
            }

            L.add(li * values.get(i));
            li = 1;
        }

        double res = 0;
        for(Double a: L){
            res += a;
        }

        return new LagrangeRes(L, res);
    }
}
