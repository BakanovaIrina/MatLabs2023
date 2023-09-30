package res;

import java.util.ArrayList;

public class AdamsRes {
    private ArrayList<Double> x;
    private ArrayList<Double> y;
    private ArrayList<Double> eY;
    private ArrayList<Double> eAll;
    private boolean isCorrect;

    public AdamsRes(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> eY, ArrayList<Double> eAll) {
        this.x = x;
        this.y = y;
        this.eY = eY;
        this.eAll = eAll;
        isCorrect = true;
    }

    public AdamsRes() {
        isCorrect = false;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public ArrayList<Double> geteY() {
        return eY;
    }

    public ArrayList<Double> getX() {
        return x;
    }

    public ArrayList<Double> getY() {
        return y;
    }

    public ArrayList<Double> geteAll() {
        return eAll;
    }
}
