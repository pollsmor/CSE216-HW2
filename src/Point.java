/**
 * An interface to represent a single point in a geometric space.
 */
public interface Point extends Comparable<Point> {

    /**
     * @return the coordinates of this point as an array of doubles, specifying its location in the geometric space.
     */
    double[] coordinates();

    double distanceFromOrigin();

    @Override
    default int compareTo(Point o) {
        return (int) Math.floor(this.distanceFromOrigin() - o.distanceFromOrigin());
    }
}