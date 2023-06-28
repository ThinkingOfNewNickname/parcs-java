import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import parcs.*;

public class ClosestPair implements AM {
    private static final long SEQUENTIAL_CUTOFF = 4;

    public void run(AMInfo info) {
        Node points = (Node)info.parent.readObject();
        
        info.parent.write(new Node());


        // int length = points.length;

        // if (length <= SEQUENTIAL_CUTOFF) {
        //     Point2D.Double[] closestPair = getClosestPairSequential(points);
        //     info.parent.write(closestPair);

        //     return;
        // }

        // int middle = length / 2;
        // Point2D.Double[] left = Arrays.copyOfRange(points, 0, middle);
        // Point2D.Double[] right = Arrays.copyOfRange(points, middle, points.length);

        // point p1 = info.createPoint();
        // channel c1 = p1.createChannel();
        // p1.execute("ClosestPair");
        // c1.write(left);

        // point p2 = info.createPoint();
        // channel c2 = p2.createChannel();
        // p2.execute("ClosestPair");
        // c2.write(right);

        // Point2D.Double[] closestPair = combine((Point2D.Double[])c1.readObject(),
        //                                        (Point2D.Double[])c2.readObject());

        // info.parent.write(closestPair);
    }

    private Point2D.Double[] getClosestPairSequential(Point2D.Double[] points) {
        Point2D.Double[] closestPair = new Point2D.Double[2];
        double min = Double.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {
            Point2D.Double p1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point2D.Double p2 = points[j];
                double dist = p1.distance(p2);
                if (dist < min) {
                    min = dist;
                    closestPair[0] = p1;
                    closestPair[1] = p2;
                }
            }
        }
        return closestPair;
    }

    private Point2D.Double[] combine(Point2D.Double[] left, Point2D.Double[] right) {
        Point2D.Double[] closestPair = new Point2D.Double[2];
        double min = Double.MAX_VALUE;

        for (int i = 0; i < left.length; i++) {
            Point2D.Double p1 = left[i];
            for (int j = 0; j < right.length; j++) {
                Point2D.Double p2 = right[j];
                double dist = p1.distance(p2);
                if (dist < min) {
                    min = dist;
                    closestPair[0] = p1;
                    closestPair[1] = p2;
                }
            }
        }

        return closestPair;
    }
}
