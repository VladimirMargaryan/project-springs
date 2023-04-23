package converter;

import transform.FT;

public abstract class Converter {
    protected int numBits;
    protected double[] frequencies;

    public Converter(int numBits) {
        this.numBits = numBits;
        this.frequencies = new double[numBits];
    }

    protected abstract double[] convertBitsToSprings(boolean[] bits);

    public abstract double evaluateDecimalValue(boolean[] bits);

    public double[] computeOscillations(boolean[] bits) {
        final double[] springs = convertBitsToSprings(bits);
        final double[] oscillations = new double[springs.length];
        for (int i = 0; i < oscillations.length; i++) {
            final double omega = 2 * Math.PI * i / oscillations.length;
            for (int j = 0; j < springs.length; j++) {
                oscillations[i] += springs[j] * Math.cos(omega * j);
            }
        }

        return oscillations;
    }


    public void calculateFrequencyAmplitudes(boolean[] bits) {
        final double[] oscillations = computeOscillations(bits);
        final FT ft = new FT(oscillations);
        this.frequencies = ft.getAmplitude();
        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i] = frequencies[i] / oscillations.length;
        }
    }
}