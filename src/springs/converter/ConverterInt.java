package springs.converter;

import java.util.stream.IntStream;

public class ConverterInt extends Converter {

    private static final int NUM_BITS = 24;

    public ConverterInt() {
        super(NUM_BITS);
    }

    @Override
    protected double[] convertBitsToSprings(boolean[] bits) {
        return IntStream.range(0, NUM_BITS).mapToDouble(i -> bits[i] ? 1.0 : 0.0).toArray();
    }

    @Override
    public double evaluateDecimalValue(boolean[] bits) {
        if (bits.length != NUM_BITS) {
            throw new IllegalArgumentException("Invalid number of bits");
        }

        return IntStream.range(0, NUM_BITS).filter(i -> bits[i]).map(i -> (int) Math.pow(2, NUM_BITS - i - 1)).sum();
    }
}