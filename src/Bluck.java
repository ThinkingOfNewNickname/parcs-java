import java.util.*;
import java.io.File;
import parcs.*;

public class Bluck {
    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("ClosestPair.jar");
        // Node node = generatePoints(curtask.findFile("input"));

        // Ideally we should parallel it as well, but it's not the point of this task
        // node.points.sort(Comparator.comparingDouble(p -> p.y));

        // System.out.println(node.points.size() + " points generated.");



        // Node node = new Node(0);
        Point2D p1 = new Point2D(0, 0);



        AMInfo info = new AMInfo(curtask, null);
        point p = info.createPoint();
        channel c = p.createChannel();
        p.execute("ClosestPair");
        c.write(p1);

        System.out.println("Waiting for result...");

        // Node closestPair = (Node)c.readObject();
        // Point2D p1 = closestPair.points.get(0);
        // Point2D p2 = closestPair.points.get(1);
        // double min = p1.distance(p2);

        // System.out.println("Result: " + min + " (" + p1 + ", " + p2 + ")");

        Point2D p2 = (Point2D)c.readObject();

        System.out.println("Result: points");

        curtask.end();
    }

    // public static Node generatePoints(String fileWithAmount) throws Exception {
    //     Scanner sc = new Scanner(new File(fileWithAmount));
    //     int N = sc.nextInt();

    //     Node points = new Node();
    //     for (int i = 0; i < N; i++) {
    //         points.points.add(new Point2D(Math.random(), Math.random()));
    //     }

    //     return points;
    // }
}
