package res;

import java.util.ArrayList;

public class RungeRes {
    private ArrayList<Double> x;
    private ArrayList<Double> y;
    private ArrayList<Double> f;
    private ArrayList<Double> k1;
    private ArrayList<Double> k2;
    private ArrayList<Double> k3;
    private ArrayList<Double> k4;
    private ArrayList<Double> runge;

    public RungeRes(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> f,
                    ArrayList<Double> k1, ArrayList<Double> k2, ArrayList<Double> k3, ArrayList<Double> k4) {
        this.x = x;
        this.y = y;
        this.f = f;
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
        this.k4 = k4;
    }

    public RungeRes() {
    }

    public ArrayList<Double> getX() {
        return x;
    }

    public ArrayList<Double> getY() {
        return y;
    }

    public ArrayList<Double> getK1() {
        return k1;
    }

    public ArrayList<Double> getK2() {
        return k2;
    }

    public ArrayList<Double> getK3() {
        return k3;
    }

    public ArrayList<Double> getK4() {
        return k4;
    }

    public ArrayList<Double> getRunge() {
        return runge;
    }

    public ArrayList<Double> getF() {
        return f;
    }

    public void setRunge(ArrayList<Double> runge) {
        this.runge = runge;
    }
}
