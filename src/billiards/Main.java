package billiards;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final HorizontalStadiumBilliard stadiumBilliard = new HorizontalStadiumBilliard(100, 0.2, 10);
        final List<Point> SBReflectionPoints = stadiumBilliard.simulate();
        stadiumBilliard.testUniformity(SBReflectionPoints);

        System.out.println();

        final HorizontalCircularBilliard horizontalCircularBilliard = new HorizontalCircularBilliard(10, 1e-4);
        final List<Point> HCBReflectionPoints = horizontalCircularBilliard.simulate();
        System.out.println(
                whetherReversible(
                        "HorizontalCircularBilliard",
                        horizontalCircularBilliard.isPathReversible(HCBReflectionPoints)
                ));

        final VerticalCircularBilliard verticalCircularBilliard = new VerticalCircularBilliard(10, 1e-6);
        final List<Point> VCBReflectionPoints = verticalCircularBilliard.simulate();
        System.out.println(
                whetherReversible(
                        "VerticalCircularBilliard",
                        verticalCircularBilliard.isPathReversible(VCBReflectionPoints)
                ));
    }

    private static String whetherReversible(String billiardName, boolean isReversible) {
        return String.format("%s: Path %s reversible", billiardName, isReversible ? "is" : "is not");
    }
}
