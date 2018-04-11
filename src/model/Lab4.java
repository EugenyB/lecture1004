package model;

public class Lab4 {
    public double f(double x) {
        return Math.sin(x);
    }

    public double max(double[] x) {
        double res = x[0];
        for (double v : x) {
            if(v>res) res = v;
        }
        return res;
    }

    public double min(double[] x) {
        double res = x[0];
        for (double v : x) {
            if(v<res) res = v;
        }
        return res;
    }
}
