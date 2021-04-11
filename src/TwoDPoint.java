import java.util.List;
import java.util.ArrayList;

/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The coordinates of such a point is given by
 * exactly two doubles specifying its <code>x</code> and <code>y</code> values.
 */
public class TwoDPoint implements Point {
    private double x, y;

    public TwoDPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        return new double[]{x, y};
    }

    /**
     * Returns a list of <code>TwoDPoint</code>s based on the specified array of doubles. A valid argument must always
     * be an even number of doubles so that every pair can be used to form a single <code>TwoDPoint</code> to be added
     * to the returned list of points.
     *
     * @param coordinates the specified array of doubles.
     * @return a list of two-dimensional point objects.
     * @throws IllegalArgumentException if the input array has an odd number of doubles.
     */
    public static List<TwoDPoint> ofDoubles(double... coordinates) throws IllegalArgumentException {
        if (coordinates.length % 2 == 1)
            throw new IllegalArgumentException();

        List<TwoDPoint> output = new ArrayList<>(coordinates.length / 2);
        for (int i = 0; i < coordinates.length; i += 2)
            output.add(new TwoDPoint(coordinates[i], coordinates[i + 1]));

        return output;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof TwoDPoint)) return false;
        TwoDPoint point = (TwoDPoint) o;
        return point.coordinates()[0] == this.coordinates()[0] && point.coordinates()[1] == this.coordinates()[1];
    }
}
