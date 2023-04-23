import converter.Converter;
import converter.Converter8Bit;
import converter.ConverterFloat;
import converter.ConverterInt;

public class Main {
    public static void main(String[] args) {

        final Converter converter8Bit = new Converter8Bit();
        final boolean[] bits8 = new boolean[]{true, false, true, false, false, true, true, false};

        final double expectedValue8Bit = 166.0;
        final double actualValue8Bit = converter8Bit.evaluateDecimalValue(bits8);

        System.out.println("Actual 8 bit value: " + actualValue8Bit);
        System.out.println("Expected 8 bit value: " + expectedValue8Bit);
        System.out.println(actualValue8Bit == expectedValue8Bit ? "Values equal" : "Values not equal");
        System.out.println();

        final Converter converterFloat = new ConverterFloat();
        final boolean[] bitsFloat = new boolean[]{false, false, true, true, false, false, true, false, false, false, false, true,
                false, true, false, true, true, true, true, true, false, false, false, true, false, true, true, true,
                false, false, false, true};

        final float expectedValueFloat = 8.7278389E-9f;
        final float actualValueFloat = (float) converterFloat.evaluateDecimalValue(bitsFloat);

        System.out.println("Actual float value: " + actualValueFloat);
        System.out.println("Expected float value: " + expectedValueFloat);
        System.out.println(actualValueFloat == expectedValueFloat ? "Values equal" : "Values not equal");
        System.out.println();

        final Converter converterInt = new ConverterInt();
        final boolean[] bitsInt = new boolean[]{false, false, true, true, false, false, true, false, true, true, false,
                false, true, false, false, true, true, true, true, true, true, false, false, false};

        final int expectedValueInt = 3328504;
        final int actualValueInt = (int) converterInt.evaluateDecimalValue(bitsInt);

        System.out.println("Actual int value: " + actualValueInt);
        System.out.println("Expected int value: " + expectedValueInt);
        System.out.println(actualValueInt == expectedValueInt ? "Values equal" : "Values not equal");
        System.out.println();
    }
}