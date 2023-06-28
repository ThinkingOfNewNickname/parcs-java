import java.util.*;
import java.util.List;
import java.util.ArrayList;
import parcs.*;

public class ClosestPair implements AM {
    private static final long SEQUENTIAL_CUTOFF = 4;

    public void run(AMInfo info) {
        Node points = (Node)info.parent.readObject();
        
        int length = points.points.size();

        if (length <= SEQUENTIAL_CUTOFF) {
            Node closestPair = getClosestPairSequential(points);
            info.parent.write(closestPair);

            return;
        }

        int middle = length / 2;
        Node left = createSubNode(points, 0, middle);
        Node right = createSubNode(points, middle, length);

        point p1 = info.createPoint();
        channel c1 = p1.createChannel();
        p1.execute("ClosestPair");
        c1.write(left);

        point p2 = info.createPoint();
        channel c2 = p2.createChannel();
        p2.execute("ClosestPair");
        c2.write(right);

        Node closestPair = combine((Node)c1.readObject(),
                                   (Node)c2.readObject());

        info.parent.write(closestPair);
    }

    private Node createSubNode(Node points, int from, int to) {
        Node subNode = new Node();
        for (int i = from; i < to; i++) {
            subNode.points.add(points.points.get(i));
        }
        return subNode;
    }

    private Node getClosestPairSequential(Node points) {
        Node closestPair = new Node();
        closestPair.points.add(new Point2D(0, 0));
        closestPair.points.add(new Point2D(0, 0));

        double min = 10.0;

        for (int i = 0; i < points.points.size(); i++) {
            Point2D p1 = points.points.get(i);
            for (int j = i + 1; j < points.points.size(); j++) {
                Point2D p2 = points.points.get(j);
                double dist = p1.distance(p2);

                System.out.println("sequential " + "{" + p1.x + ", " + p1.y + "}, " + "{" + p2.x + ", " + p2.y + "}" + " " + dist);

                if (dist < min) {
                    min = dist;
                    closestPair.points.set(0, p1);
                    closestPair.points.set(1, p2);
                }
            }
        }
        return closestPair;
    }

    private Node combine(Node left, Node right) {
        Node closestPair = new Node();
        closestPair.points.add(new Point2D(0, 0));
        closestPair.points.add(new Point2D(0, 0));

        double min = 10.0;

        for (int i = 0; i < left.points.size(); i++) {
            Point2D p1 = left.points.get(i);
            for (int j = 0; j < right.points.size(); j++) {
                Point2D p2 = right.points.get(j);
                double dist = p1.distance(p2);

                System.out.println("combine " + "{" + p1.x + ", " + p1.y + "}, " + "{" + p2.x + ", " + p2.y + "}" + " " + dist);

                if (dist < min) {
                    min = dist;
                    closestPair.points.set(0, p1);
                    closestPair.points.set(1, p2);
                }
            }
        }

        return closestPair;
    }
}
