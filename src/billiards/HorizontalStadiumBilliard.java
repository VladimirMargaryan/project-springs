package billiards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class HorizontalStadiumBilliard extends Billiard implements UniformityTestable {

    private static final double EPSILON = 1e-6;

    private final double L;
    private final int M;

    public HorizontalStadiumBilliard(int numReflections, double L, int M) {
        super(numReflections);
        this.L = L;
        this.M = M;
    }

    @Override
    public List<Point> simulate() {
        final List<Point> reflectionPoints = new ArrayList<>();
        double x = Math.random();
        double y = Math.random();
        double px = Math.random() > 0.5 ? 1.0 : -1.0;
        double py = Math.random() > 0.5 ? 1.0 : -1.0;

        for (int i = 0; i < numReflections; i++) {
            reflectionPoints.add(new Point(x, y));

            if (y >= 0 && y <= 1 && (x <= 0 || x >= L)) {
                py = -py;
                x += px;
                y += py;
            } else {
                final double xc = x <= L / 2 ? 0 : L;
                final double intersectionX = x + px;
                final double intersectionY = y + py;

                final double dist = Math.sqrt((intersectionX - xc) * (intersectionX - xc) + intersectionY * intersectionY);

                if (Math.abs(dist - 1.0) < EPSILON) {
                    final double newX = intersectionX / dist;
                    final double newY = intersectionY / dist;

                    px = (newY * newY - (newX - xc) * (newX - xc)) * px - 2 * (newX - xc) * newY * py;
                    py = -2 * (newX - xc) * newY * px + ((newX - xc) * (newX - xc) - newY * newY) * py;

                    x = newX;
                    y = newY;
                } else {
                    x += px;
                    y += py;
                }
            }
        }

        return reflectionPoints;
    }

    @Override
    public void testUniformity(List<Point> reflectionPoints) {
        final int[] binCounts = new int[M];
        final double binSize = 1.0 / M;

        for (Point point : reflectionPoints) {
            double x = point.getX();

            if (x < 0) {
                x = 0;
            } else if (x > 1) {
                x = 1;
            }

            int binIndex = (int) (x / binSize);

            if (binIndex == M) {
                binIndex--;
            }

            binCounts[binIndex]++;
        }

        final double mean = (double) reflectionPoints.size() / M;
        final double variance = Arrays.stream(binCounts).mapToDouble(count -> (count - mean) * (count - mean)).sum() / M;

        final StringBuilder builder = new StringBuilder("\nBin Counts:");
        IntStream.range(0, M)
                .forEach(i -> builder
                        .append("\nBin ")
                        .append(i)
                        .append(": ")
                        .append(binCounts[i])
                );

        builder
                .append("\nMean: ")
                .append(mean)
                .append("\nVariance: ")
                .append(variance);

        System.out.println(builder);
    }

    public double getL() {
        return L;
    }

    public int getM() {
        return M;
    }
}
