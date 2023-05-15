package springs.converter;

import java.util.stream.IntStream;

public class ConverterFloat extends Converter {

    private static final int NUM_BITS = 32;

    public ConverterFloat() {
        super(NUM_BITS);
    }

    @Override
    protected double[] convertBitsToSprings(boolean[] bits) {
        return IntStream.range(0, NUM_BITS).mapToDouble(i -> bits[i] ? 1.0 : 0.0).toArray();
    }

    @Override
    public double evaluateDecimalValue(boolean[] bits) {
        if (bits.length != numBits) {
            throw new IllegalArgumentException("Invalid number of bits");
        }

        final int sign = bits[0] ? -1 : 1;
        final int exponent = IntStream
                .rangeClosed(1, 8)
                .map(i -> (int) (bits[i] ? Math.pow(2, 8 - i) : 0))
                .sum() - 127;

        final double fraction = IntStream
                .rangeClosed(9, 31)
                .mapToDouble(i -> bits[i] ? Math.pow(2, -i + 8) : 0)
                .sum() + 1;

        return sign * fraction * Math.pow(2, exponent);
    }
}