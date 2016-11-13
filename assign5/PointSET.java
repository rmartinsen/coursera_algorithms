import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;


public class PointSET {
    private SET<Point2D> points;
    
    public PointSET() {
        points = new SET<Point2D>();
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenRadius(0.01);
    }
    
    public boolean isEmpty() {
       return points.size() == 0;   
    }
    
    public void insert(Point2D p) {
        points.add(p);   
    }
    
    public int size() {
        return points.size();   
    }
    
    public boolean contains(Point2D p) {
       return points.contains(p);   
    }
    
    public void draw() {
        for (Point2D p: points) {
            StdDraw.point(p.x(), p.y());
        }
    }
    
    public Iterable<Point2D> range(RectHV rect){
        SET<Point2D> matches = new SET<Point2D>();
        for (Point2D p: points) {
            if (p.x() >= rect.xmin() && p.x() <= rect.xmax() && p.y() >= rect.ymin() && p.y() <= rect.ymax()) {
               matches.add(p);   
            }
        }
        return matches;
    }
    
    public Point2D nearest( Point2D p) {
        if( isEmpty() ) { return null; };
        Point2D closest = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        for (Point2D comparePoint: points){
            if(p.distanceTo(comparePoint) < closestDistance) {
                closest = comparePoint;
                closestDistance = p.distanceTo(comparePoint);
            }
        }
        return closest;
    }
}
