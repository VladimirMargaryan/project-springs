package springs.model;

public class Spring {

    private double k;

    public Spring() {
        this.k = 1;
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
        final double newK = (that.getK() * this.k) / (that.getK() + this.k);
        return new Spring(newK);
    }

    public Spring inParallel(Spring that) {
        final double newK = that.getK() + this.k;
        return new Spring(newK);
    }

    public double[] move(double t, double dt, double x0, double v0) {
        final double w = Math.sqrt(k);
        final double[] xt = new double[(int) Math.ceil((t) / dt)];

        for (int i = 0, j = 0; i <= t; i += dt, j++) {
            xt[j] = x0 * Math.cos(w * i) + (v0 / w) * Math.sin(w * i);
        }

        return xt;
    }

    public double[] move(double t, double dt, double x0) {
        return getCoordinates(t, dt, x0, Math.sqrt(k), 0, 0);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0) {
        return getCoordinates(t1, dt, x0, Math.sqrt(k), t0, v0);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
        return getCoordinates(t1, dt, x0, Math.sqrt(k / m), t0, v0);
    }

    private double[] getCoordinates(double t, double dt, double x0, double w, double t0, double v0) {
        final double[] coordinates = new double[(int) Math.ceil((t - t0) / dt)];

        for (int j = 0; t0 <= t; t0 += dt, j++) {
            coordinates[j] = x0 * Math.cos(w * t0) + (v0 / w) * Math.sin(w * t0);
        }

        return coordinates;
    }
}