import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class Ordering {

    /**
     * A comparator for two-dimensional shapes, based on the vertex with the least x-value. That is, sorting with this
     * comparator must order all the shapes in a collection in increasing order of their least x-valued vertex.
     */
    static class XLocationShapeComparator implements Comparator<TwoDShape> {

        @Override
        public int compare(TwoDShape o1, TwoDShape o2) {
            return 0; // TODO
        }
    }

    static <T> void copy(List<? extends TwoDShape> source, List<TwoDShape> destination) {
        destination.addAll(source);
    }


    /**
     * PLEASE READ ALL THE COMMENTS IN THIS CODE CAREFULLY BEFORE YOU START WRITING YOUR OWN CODE.
     */
    public static void main(String[] args) {
        List<TwoDPoint> l = Arrays.asList(new TwoDPoint(-6.8,7.77), new TwoDPoint(4.2,3.69),
                                          new TwoDPoint(6.7, -5.22), new TwoDPoint(-3.4,-2.5));
        Quadrilateral quad = new Quadrilateral(l);
        System.out.println(quad.area());

        List<TwoDPoint> t = Arrays.asList(new TwoDPoint(15, 0), new TwoDPoint(13, 100), new TwoDPoint(-3.5, -7));
        Triangle triangle = new Triangle(t);
        List<TwoDPoint> returnedTriangle = (List<TwoDPoint>) triangle.getPosition();
        System.out.println(returnedTriangle);

        List<TwoDShape> shapes = new ArrayList<>();
        List<Point> points = new ArrayList<>();

        /* ====== SECTION 1 ====== */
        /* uncomment the following block and fill in the "..." constructors to create actual instances. If your
         * implementations are correct, then the code should compile and yield the expected results of the various
         * shapes being ordered by their smallest x-coordinate, area, volume, surface area, etc. */

        shapes.add(new Circle(0, 0, 4));
        shapes.add(new Triangle(Arrays.asList(new TwoDPoint(0, 0), new TwoDPoint(3, 0), new TwoDPoint(0, 4))));
        shapes.add(new Quadrilateral(Arrays.asList(new TwoDPoint(0, 0), new TwoDPoint(5, 0),
                                                   new TwoDPoint(5, 5), new TwoDPoint(0, 5))));

        copy(new ArrayList<Circle>(), shapes); // note-1 //

        /*
        // sorting 2d shapes according to various criteria
        shapes.sort(new XLocationShapeComparator());
        Collections.sort(shapes); // TODO: Must sort the two-dimensional shapes in increasing of their area

        // sorting 2d points according to various criteria
        // TODO: Implement a static nested class so that uncommenting the following line works. The XLocationPointComparator must sort all the points in a collection in increasing order of their x-values.
        // points.sort(new XLocationPointComparator());
        Collections.sort(points); // TODO: Must sort the points in increasing order of their distance from the origin
         */

        /* ====== SECTION 2 ====== */
        /* if your changes to copy() are correct, uncommenting the following block will also work as expected note that
         * copy() should work for the line commented with 'note-1' above while at the same time also working with the
         * lines commented with 'note-2', 'note-3', and 'note-4' below. */

        /*
        List<Number>       numbers   = new ArrayList<>();
        List<Double>       doubles   = new ArrayList<>();
        Set<Triangle>      triangles = new HashSet<>();
        Set<Quadrilateral> quads     = new LinkedHashSet<>();

        copy(doubles, numbers); // note-2 //
        copy(quads, shapes);   // note-3 //
        copy(triangles, shapes); // note-4 //
         */

        /* ====== SECTION 3 ====== */
        /* uncomment the following lines of code and fill in the "..." constructors to create actual instances. You may
         * test your code with more instances (the two lines are provided just as an example that different types of
         * shapes can be added). If your implementations are correct, the code should compile and print the various
         * shapes in their human-readable string forms. Note that you have to implement a certain method in the classes
         * that implement the TwoDShape interface, so that the printed values are indeed in a human-readable form. These
         * are defined as follows:
         *
         * Circle centered at (x,y) of radius r: "Circle[center: x,y; radius: r]"
         * Triangle with three vertices: "Triangle[(x1, y1), (x2, y2), (x3, y3)]"
         * Quadrilateral with four vertices: "Quadrilateral[(x1, y1), (x2, y2), (x3, y3), (x4, y4)]"
         *
         * For triangles and quadrilaterals, the vertex ordering is specified in the documentation of their respective
         * getPosition methods. Each point must be represented up to two decimal places. For the purpose of this assignment,
         * you may safely assume that no test input will be used in grading where a vertex has more than two decimal places.
         */

        /*List<TwoDShape> lst = new ArrayList<>();
        lst.add(new Circle(...));
        lst.add(new Triangle(...));
        printAllAndReturnLeast(lst, new Printer());
         */
    }

    // TODO: There's a lot wrong with this method. correct it so that it can work properly with SECTION 3 of the main method written above.
    // NOTE: This method may compile after you implement just one thing, but pay attention to the warnings in your IDE.
    // Just because the method compiles doesn't mean it is fully correct.
    /**
     * This method prints each element of a list of various types of two-dimensional shapes (i.e., {@link TwoDShape}, as
     * defined in the {@link Printer<TwoDShape>#print} method. When the printing process is complete, it returns the
     * least instance, as per the natural order of the {@link TwoDShape} instances. SECTION 1 in the main method above
     * defines this natural order.
     *
     * Note that the natural ordering of shapes is not provided to you. This is something you must implement as part of
     * the assignment.
     *
     * @param aList the list of provided two-dimensional shape instances
     * @param aPrinter the specified printer instance
     * @return the least element from <code>aList</code>, as per the natural ordering of the shapes
     */
    /*
     static TwoDShape printAllAndReturnLeast(List<TwoDShape> aList, AbstractPrinter<Printer<TwoDShape>> aPrinter) {
        TwoDShape least = aList.get(0);
        for (TwoDShape t : aList) {
            if (least.compareTo(t) < 0)
                least = t;
            aPrinter.print(t);
        }
        return least;
    }
     */
}
