import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class Triangle implements TwoDShape, Positionable {

    List<TwoDPoint> vertices;

    public Triangle(List<TwoDPoint> vertices) {
        this.vertices = vertices;
        setPosition(this.vertices);
    }

    /**
     * Sets the position of this triangle according to the first three elements in the specified list of points. The
     * triangle is formed on the basis of these three points taken in a clockwise manner on the two-dimensional
     * x-y plane. If the input list has more than three elements, the subsequent elements are ignored.
     *
     * @param points the specified list of points.
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        if (points.size() < 3 || !(points.get(0) instanceof TwoDPoint) ||
                                 !(points.get(1) instanceof TwoDPoint) || !(points.get(2) instanceof TwoDPoint))
            throw new IllegalArgumentException();

        double x1 = points.get(0).coordinates()[0];
        double y1 = points.get(0).coordinates()[1];
        double x2 = points.get(1).coordinates()[0];
        double y2 = points.get(1).coordinates()[1];
        double x3 = points.get(2).coordinates()[0];
        double y3 = points.get(2).coordinates()[1];

        // Case where all three points are at the same spot, just return as is
        if (x1 == x2 && x2 == x3 && y1 == y2 && y2 == y3)
            return;

        List<TwoDPoint> vertices = new ArrayList<>(3);
        List<Point> copyOfPoints = new ArrayList<>(points);

        // Find 1st pair, located as far to the bottom left as possible
        double leastXCoord = Math.min(Math.min(x1, x2), x3);
        List<Double> ycoordsPairedWithLeastXCoord = new ArrayList<>(3);
        if (x1 == leastXCoord) ycoordsPairedWithLeastXCoord.add(y1);
        if (x2 == leastXCoord) ycoordsPairedWithLeastXCoord.add(y2);
        if (x3 == leastXCoord) ycoordsPairedWithLeastXCoord.add(y3);
        vertices.add(new TwoDPoint(leastXCoord, Collections.min(ycoordsPairedWithLeastXCoord)));
        // Remove the relevant point from the input list for easier calculations later on
        if (x1 == leastXCoord && y1 == Collections.min(ycoordsPairedWithLeastXCoord)) copyOfPoints.remove(0);
        else if (x2 == leastXCoord && y2 == Collections.min(ycoordsPairedWithLeastXCoord)) copyOfPoints.remove(1);
        else copyOfPoints.remove(2);

        // 2nd pair will be located somewhere *above* the 3rd pair, this is assuming a pair has been removed from points
        x1 = copyOfPoints.get(0).coordinates()[0];
        y1 = copyOfPoints.get(0).coordinates()[1];
        x2 = copyOfPoints.get(1).coordinates()[0];
        y2 = copyOfPoints.get(1).coordinates()[1];
        double maxYCoord = Math.max(y1, y2);
        List<Double> xcoordsPairedWithMaxYCoord = new ArrayList<>(2);
        if (y1 == maxYCoord) xcoordsPairedWithMaxYCoord.add(x1);
        if (y2 == maxYCoord) xcoordsPairedWithMaxYCoord.add(x2);
        vertices.add(new TwoDPoint(Collections.min(xcoordsPairedWithMaxYCoord), maxYCoord));
        // Remove the relevant point f rom the input list, remaining point will be the last one
        if (y1 == maxYCoord && x1 == Collections.min(xcoordsPairedWithMaxYCoord)) copyOfPoints.remove(0);
        else copyOfPoints.remove(1);

        // 3rd pair
        vertices.add(new TwoDPoint(copyOfPoints.get(0).coordinates()[0], copyOfPoints.get(0).coordinates()[1]));

        this.vertices = vertices;
    }

    /**
     * Retrieve the position of an object as a list of points. The points are be retrieved and added to the returned
     * list in a clockwise manner on the two-dimensional x-y plane, starting with the point with the least x-value. If
     * two points have the same least x-value, then the clockwise direction starts with the point with the lower y-value.
     *
     * @return the retrieved list of points.
     */
    @Override
    public List<? extends Point> getPosition() {
        return vertices;
    }

    /**
     * @return the number of sides of this triangle, which is always set to three
     */
    @Override
    public int numSides() {
        return 3;
    }

    /**
     * Checks whether or not a list of vertices forms a valid triangle. The <i>trivial</i> triangle, where all three
     * corner vertices are the same point, is considered to be an invalid triangle.
     *
     * @param vertices the list of vertices to check against, where each vertex is a <code>Point</code> type.
     * @return <code>true</code> if <code>vertices</code> is a valid collection of points for a triangle, and
     * <code>false</code> otherwise. For example, three vertices are in a straight line is invalid.
     */
    @Override
    public boolean isMember(List<? extends Point> vertices) {
        if (vertices.size() < 3) return false;

        double x1 = vertices.get(0).coordinates()[0];
        double y1 = vertices.get(0).coordinates()[1];
        double x2 = vertices.get(1).coordinates()[0];
        double y2 = vertices.get(1).coordinates()[1];
        double x3 = vertices.get(2).coordinates()[0];
        double y3 = vertices.get(2).coordinates()[1];

        // Case where triangle's 3 points are in a straight line
        if ((x1 == x2 && x2 == x3 && x1 == x3) || (y1 == y2 && y2 == y3 && y1 == y3))
            return false;

        // Case where at least 2 of the triangle's points are at the same spot
        if (vertices.get(0).equals(vertices.get(1)) || vertices.get(0).equals(vertices.get(2)) || vertices.get(1).equals(vertices.get(2)))
            return false;

        return true;
    }

    /**
     * This method snaps each vertex of this triangle to its nearest integer-valued x-y coordinate. For example, if
     * a corner is at (0.8, -0.1), it will be snapped to (1,0). The resultant triangle will thus have all three
     * vertices in positions with integer x and y values. If the snapping procedure described above results in this
     * triangle becoming invalid (e.g., all corners collapse to a single point), then it is left unchanged. Snapping is
     * an in-place procedure, and the current instance is modified.
     */
    public void snap() {
        List<TwoDPoint> verticesCopy = new ArrayList<>(vertices);   // Return this in case vertices should not be modified
        for (int i = 0; i < 3; i++) {
            double xcoord = vertices.get(i).coordinates()[0]; // Get just decimal part of a double
            double ycoord = vertices.get(i).coordinates()[1];
            vertices.set(i, new TwoDPoint(Math.round(xcoord), Math.round(ycoord)));
        }

        // Check that none of the vertices are equal
        if (!isMember(vertices)) vertices = verticesCopy;
    }

    /**
     * @return the area of this triangle
     */
    public double area() {
        // Get coordinates for the three points
        double x1 = vertices.get(0).coordinates()[0];
        double y1 = vertices.get(0).coordinates()[1];
        double x2 = vertices.get(1).coordinates()[0];
        double y2 = vertices.get(1).coordinates()[1];
        double x3 = vertices.get(2).coordinates()[0];
        double y3 = vertices.get(2).coordinates()[1];

        // Get distances of the three lines in the triangle for Heron's formula
        double a = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));   // Distance of line from p1 to p2
        double b = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));   // Distance of line from p2 to p3
        double c = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));   // Distance of line from p1 to p3
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    /**
     * @return the perimeter (i.e., the total length of the boundary) of this triangle
     */
    public double perimeter() {
        double output = 0.0;

        // Get coordinates for the three points
        double x1 = vertices.get(0).coordinates()[0];
        double y1 = vertices.get(0).coordinates()[1];
        double x2 = vertices.get(1).coordinates()[0];
        double y2 = vertices.get(1).coordinates()[1];
        double x3 = vertices.get(2).coordinates()[0];
        double y3 = vertices.get(2).coordinates()[1];

        // Get distances of the three lines in the triangle
        output += Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));   // Distance of line from p1 to p2
        output += Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));   // Distance of line from p2 to p3
        output += Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));   // Distance of line from p1 to p3

        return output;
    }
}
