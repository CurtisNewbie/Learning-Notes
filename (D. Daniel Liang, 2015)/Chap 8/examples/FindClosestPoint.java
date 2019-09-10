import java.util.*;

/**
 * Given the coordinates of a number of points, find the closest to the centre
 */
public class FindClosestPoint {

    public static void main(String[] args) {

        // the coordinates of a number of points
        double[][] points = { { -1.0, 3.0 }, { -1.0, -1.0 }, { 1.0, 1.0 }, { 2.0, 0.5 }, { 2.0, -1.0 }, { 3.0, 3.0 },
                { 4.0, 2.0 }, { 4.0, -0.5 } };

        for (double[] row : points) {
            System.out.println(row[0] + " " + row[1]);
        }

        // find shortest distance
        double shortestDis = distance(points[0][0], points[0][1]);
        for (double[] p : points) {
            double dist = distance(p[0], p[1]);

            if (dist < shortestDis) {
                shortestDis = dist;
            }
        }
        System.out.println("Shortest distance is: " + shortestDis);

        // find the points
        ArrayList<double[]> shortestPoints = new ArrayList<>();
        int n = 0;
        for (double[] p : points) {
            double dist = distance(p[0], p[1]);

            if (dist - shortestDis < 0.0000000001) {
                double[] thePoint = p;
                thePoint[0] = p[0];
                thePoint[1] = p[1];
                shortestPoints.add(thePoint);
                n++;
            }
        }
        System.out.println("Closest points are: ");
        for (double[] p : shortestPoints) {
            System.out.println(p[0] + " " + p[1]);
        }

    }

    /**
     * Calculate the distance to the centre(0 , 0)
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return distance to the centre
     */
    public static double distance(double x, double y) {
        // equation: hyp^2 = adj^2 + opp^2
        double dist = Math.sqrt(x * x + y * y);

        return Math.abs(dist);
    }
}