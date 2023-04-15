package model;

import java.util.Arrays;

public class Spring {

    private double k = 1.0;

    public Spring() {}

    public Spring(double k) {
        this.k = k;
    }

    public double getStiffness() {
        return k;
    }

    private void setStiffness(double k) {
        this.k = k;
    }

    public double[] move(double t, double dt, double x0, double v0) {
        final int n = (int) (t / dt) + 1;
        final double[] x = new double[n];
        final double omega = Math.sqrt(k);
        final double B = v0 / omega;
        Arrays.setAll(x, i -> x0 * Math.cos(omega * i * dt) + B * Math.sin(omega * i * dt));

        return x;
    }

    public double[] move(double t, double dt, double x0) {
        return move(t, dt, x0, 0.0);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0) {
        return move(t0, t1, dt, x0, v0, 1.0);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
        final int n = (int) ((t1 - t0) / dt) + 1;
        final double[] x = new double[n];
        final double omega = Math.sqrt(k / m);
        final double B = v0 / omega - x0 * omega;
        Arrays.setAll(x, i -> x0 * Math.cos(omega * (i * dt - t0)) + B * Math.sin(omega * (i * dt - t0)));

        return x;
    }

    public Spring inSeries(Spring that) {
        final double newK = this.k + that.k;

        return new Spring(newK);
    }

    public Spring inParallel(Spring that) {
        final double newK = 1.0 / (1.0 / this.k + 1.0 / that.k);

        return new Spring(newK);
    }
}