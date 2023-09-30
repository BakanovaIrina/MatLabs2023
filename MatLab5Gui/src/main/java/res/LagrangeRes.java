package res;

import java.util.ArrayList;

public class LagrangeRes {
    ArrayList<Double> L;
    Double value;

    public LagrangeRes(ArrayList<Double> l, Double value) {
        L = l;
        this.value = value;
    }

    public ArrayList<Double> getL() {
        return L;
    }

    public Double getValue() {
        return value;
    }
}
