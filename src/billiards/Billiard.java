package billiards;

import java.util.List;

public abstract class Billiard {

    protected final int numReflections;

    protected Billiard(int numReflections) {
        this.numReflections = numReflections;
    }

    public abstract List<Point> simulate();

    public int getNumReflections() {
        return numReflections;
    }
}