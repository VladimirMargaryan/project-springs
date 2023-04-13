package model;

public class Spring {

    private double k;

    public Spring() {
        this.k = 1d;
    }

    public Spring(double k) {
        this.k = k;
    }

    public double getK() {
        return k;
    }

    private void setK(double k) {
        this.k = k;
    }

    public Spring inSeries(Spring that) {
        //TODO: Implement method
        return null;
    }

    public Spring inParallel(Spring that) {
        //TODO: Implement method
        return null;
    }

    public double[] move(double t, double dt, double x0, double v0) {
        //TODO: Implement method
        return null;
    }

    public double[] move(double t, double dt, double x0) {
        return move(t, dt, x0, 0);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0) {
        //TODO: Implement method
        return null;
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
        //TODO: Implement method
        return null;
    }
}
