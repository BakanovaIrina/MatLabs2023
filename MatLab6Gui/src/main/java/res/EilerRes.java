package res;

import java.util.ArrayList;

public class EilerRes {
    private ArrayList<Double> x;
    private ArrayList<Double> y;
    private ArrayList<Double> f;
    private ArrayList<Double> runge;

    public EilerRes(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> f) {
        this.x = x;
        this.y = y;
        this.f = f;
    }

    public EilerRes() {
    }

    public ArrayList<Double> getX() {
        return x;
    }

    public ArrayList<Double> getY() {
        return y;
    }

    public ArrayList<Double> getF() {
        return f;
    }

    public ArrayList<Double> getRunge() {
        return runge;
    }

    public double getLastY(){
        return y.get(y.size() - 1);
    }

    public void setRunge(ArrayList<Double> runge) {
        this.runge = runge;
    }
}
