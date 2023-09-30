package res;

public class NewtonRes {
    private double res;
    double[][] dyn;
    private boolean isCorrect;

    public NewtonRes(double res, double[][] dyn) {
        this.res = res;
        this.dyn = dyn;
        this.isCorrect = true;
    }

    public NewtonRes(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public NewtonRes(double res, double[][] dyn, boolean isCorrect) {
        this.res = res;
        this.dyn = dyn;
        this.isCorrect = isCorrect;
    }

    public double getRes() {
        return res;
    }

    public double[][] getDyn() {
        return dyn;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
