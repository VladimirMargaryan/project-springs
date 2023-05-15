package billiards;

import java.util.ArrayList;
import java.util.List;

public class HorizontalCircularBilliard extends Billiard implements ReversibilityTestable {

    private final double delta;

    public HorizontalCircularBilliard(int numReflections, double delta) {
        super(numReflections);
        this.delta = delta;
    }

    @Override
    public List<Point> simulate() {
        final List<Point> reflectionPoints = new ArrayList<>();
        double x = Math.random();
        double y = Math.random();
        double px = Math.random() - 0.5;
        double py = Math.random() - 0.5;

        for (int i = 0; i < numReflections; i++) {
            reflectionPoints.add(new Point(x, y));

            final double intersectionX = x + px;
            final double intersectionY = y + py;
            final double intersectionDist = Math.sqrt(intersectionX * intersectionX + intersectionY * intersectionY);

            x = intersectionX / intersectionDist;
            y = intersectionY / intersectionDist;

            px = (y * y - x * x) * px - 2 * x * y * py;
            py = -2 * x * y * px + (x * x - y * y) * py;
        }

        return reflectionPoints;
    }

    @Override
    public boolean isPathReversible(List<Point> reflectionPoints) {
        for (int i = numReflections - 1; i >= 0; i--) {
            final Point point = reflectionPoints.get(i);
            double x = point.getX();
            double y = point.getY();

            double px = -x;
            double py = -y;

            for (int j = i; j < numReflections; j++) {
                final Point nextPoint = reflectionPoints.get(j);
                final double intersectionX = x + px;
                final double intersectionY = y + py;
                final double intersectionDist = Math.sqrt(intersectionX * intersectionX + intersectionY * intersectionY);

                final double newX = intersectionX / intersectionDist;
                final double newY = intersectionY / intersectionDist;

                if (Math.abs(newX - nextPoint.getX()) > delta || Math.abs(newY - nextPoint.getY()) > delta) {
                    return false;
                }

                x = newX;
                y = newY;

                px = (y * y - x * x) * px - 2 * x * y;
                py = -2 * x * y * px + (x * x - y * y) * py;
            }
        }

        return true;
    }

    public double getDelta() {
        return delta;
    }
}