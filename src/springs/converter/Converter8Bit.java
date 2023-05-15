package springs.converter;

import java.util.stream.IntStream;

public class Converter8Bit extends Converter {

    private static final int NUM_BITS = 8;

    public Converter8Bit() {
        super(NUM_BITS);
    }

    @Override
    protected double[] convertBitsToSprings(boolean[] bits) {
        return IntStream.range(0, NUM_BITS).mapToDouble(i -> bits[i] ? 1.0 : 0.0).toArray();
    }

    @Override
    public double evaluateDecimalValue(boolean[] bits) {
        return IntStream.range(0, NUM_BITS).mapToDouble(i -> bits[i] ? Math.pow(2, NUM_BITS - 1 - i) : 0).sum();
    }
}