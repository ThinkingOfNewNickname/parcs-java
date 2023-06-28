import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Node implements Serializable {
    public List<Point2D> points;

    public Node() {
        this.points = new ArrayList<>();
    }

    public Node(List<Point2D> points) {
        this.points = points;
    }
}
