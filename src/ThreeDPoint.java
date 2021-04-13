/**
 * An unmodifiable point in the three-dimensional space. The coordinates are specified by exactly three doubles (its
 * <code>x</code>, <code>y</code>, and <code>z</code> values).
 */
public class ThreeDPoint implements Point {
    private double x, y, z;

    public ThreeDPoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @return the (x,y,z) coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        return new double[]{x, y, z};
    }

    @Override
    public double distanceFromOrigin() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ThreeDPoint)) return false;
        ThreeDPoint point = (ThreeDPoint) o;
        return point.coordinates()[0] == this.coordinates()[0] && point.coordinates()[1] == this.coordinates()[1] &&
                point.coordinates()[2] == this.coordinates()[2];
    }
}
