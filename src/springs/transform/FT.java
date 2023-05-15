package springs.transform;

import java.util.stream.IntStream;

public class FT {

    private final double[] real;
    private final double[] imaginary;
    private final int N;

    public FT(double[] input) {
        this.N = input.length;
        this.real = new double[N];
        this.imaginary = new double[N];
        for (int k = 0; k < N; k++) {
            for (int n = 0; n < N; n++) {
                final double theta = -2 * Math.PI * k * n / N;
                real[k] += input[n] * Math.cos(theta);
                imaginary[k] += input[n] * Math.sin(theta);
            }
        }
    }

    public double[] getAmplitude() {
        final double[] amplitude = new double[N];
        IntStream.range(0, N)
                .forEach(k -> amplitude[k] = (Math.sqrt(real[k] * real[k] + imaginary[k] * imaginary[k]) / N));

        return amplitude;
    }
}