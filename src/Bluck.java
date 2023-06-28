import java.awt.geom.Point2D;
import java.util.*;
import java.io.File;
import parcs.*;

public class Bluck {
    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("ClosestPair.jar");
        Point2D.Double[] points = generatePoints(curtask.findFile("input"));

        // Ideally we should parallel it as well, but it's not the point of this task
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));

        System.out.println(points.length + " points generated.");

        AMInfo info = new AMInfo(curtask, null);
        point p = info.createPoint();
        channel c = p.createChannel();
        p.execute("ClosestPair");
        c.write(points);

        System.out.println("Waiting for result...");

        Point2D.Double[] closestPair = (Point2D.Double[])c.readObject();
        double min = closestPair[0].distance(closestPair[1]);

        System.out.println("Result: " + min + " (" + closestPair[0] + ", " + closestPair[1] + ")");

        curtask.end();
    }

    public static Point2D.Double[] generatePoints(String fileWithAmount) throws Exception {
        Scanner sc = new Scanner(new File(fileWithAmount));
        int N = sc.nextInt();

        Point2D.Double[] points = new Point2D.Double[N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point2D.Double(Math.random(), Math.random());
        }

        return points;
    }
}
