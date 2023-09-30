package FuncCounter;

public class FuncCounter {

    public double count(Double x, Double y, int id){
        double res = 0;
        switch (id){
            case 1:
                res = count1(x, y);
                break;
            case 2:
                res = count2(x, y);
                break;
            case 3:
                res = count3(x, y);
                break;
        }

        return res;
    }

    private double count1(Double x, Double y){
        return y + (1 + x) * y*y;
    }

    private double count2(Double x, Double y){
        return 2 * x;
    }

    private double count3(Double x, Double y){
        return x * x - 2 * y;
    }

    public double countExact(Double x, int id){
        double res = 0;
        switch (id){
            case 1:
                res = - 1 / x;
                break;
            case 2:
                res = x * x;
                break;
            case 3:
                res = 3 * Math.exp(-2 * x) / 4 + (x * x) / 2 - x / 2 + 1 / 4;
                break;
        }

        return res;
    }
}
