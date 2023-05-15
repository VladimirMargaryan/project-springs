package billiards;

import java.util.ArrayList;
import java.util.List;

public class VerticalCircularBilliard extends Billiard implements ReversibilityTestable {

    private final double delta;

    public VerticalCircularBilliard(int numReflections, double delta) {
        super(numReflections);
        this.delta = delta;
    }

    @Override
    public List<Point> simulate() {
        final List<Point> reflectionPoints = new ArrayList<>();
        final double x = Math.random();
        final double y = Math.random();
        double px = Math.random() * 5.0 + 5.0;
        double py = Math.random() * 5.0 + 5.0;

        for (int i = 0; i < numReflections; i++) {
            reflectionPoints.add(new Point(x, y));

            final double intersectionX = x + px;
            final double intersectionY = y + py;
            final double intersectionDist = Math.sqrt(intersectionX * intersectionX + intersectionY * intersectionY);

            final double newX = intersectionX / intersectionDist;
            final double newY = intersectionY / intersectionDist;

            px = (newY * newY - newX * newX) * px - 2 * newX * newY * py;
            py = -2 * newX * newY * px + (newX * newX - newY * newY) * py;
        }

        return reflectionPoints;
    }

    @Override
    public boolean isPathReversible(List<Point> reflectionPoints) {
        for (int i = numReflections - 1; i >= 0; i--) {
            Point point = reflectionPoints.get(i);
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

                px = (newY * newY - newX * newX) * px - 2 * newX * newY * py;
                py = -2 * newX * newY * px + (newX * newX - newY * newY) * py;
            }
        }

        return true;
    }

    public double getDelta() {
        return delta;
    }
}
