package transform;

public class FT {
    private final int n;
    private final double[] x;
    private final double[] real;
    private final double[] imaginary;


    public FT() {
        this.n = 0;
        this.x = null;
        this.real = null;
        this.imaginary = null;
    }


    public FT(double[] x) {
        this.n = x.length;
        this.x = new double[n];
        System.arraycopy(x, 0, this.x, 0, n);
        this.real = new double[n];
        this.imaginary = new double[n];
        transform();
    }

    public double[] getReal() {
        return real;
    }

    public double[] getImaginary() {
        return imaginary;
    }

    private void transform() {
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                double angle = 2 * Math.PI * k * j / n;
                real[k] += x[j] * Math.cos(angle);
                imaginary[k] -= x[j] * Math.sin(angle);
            }
        }
    }
}