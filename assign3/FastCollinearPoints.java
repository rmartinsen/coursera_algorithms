import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
 private LineSegment[] segments;
 private int segLength;

 public FastCollinearPoints(Point[] points) {
  segments = new LineSegment[points.length];
  segLength = 0;

  for (int i = 0; i < points.length; i++) {
   Point origin = points[i];
   Comparator < Point > comp = origin.slopeOrder();
   Arrays.sort(points, i, points.length, comp);
   addLineSegments(origin, points, i);
  }
 }

 private void addLineSegments(Point origin, Point[] points, int start) {
  int length = 1;
  double slope = -99999;
  for (int i = start; i < points.length; i++) {
   double curSlope = origin.slopeTo(points[i]);
   if (slope == curSlope) {
    length++;
   } else {
    slope = curSlope;
    length = 1;
   }
   if (length >= 3) {
    //                 System.out.println(origin + " " + points[i] + points[i-1] + points[i-2]);
    Point first = getMin(origin, points[i], points[i - 1], points[i - 2]);
    Point last = getMax(origin, points[i], points[i - 1], points[i - 2]);
    LineSegment segment = new LineSegment(first, last);
  
    if (segLength >= segments.length) {
        resizeSegments();
    }
    
    segments[segLength] = segment;
    segLength++;
   }
  }
 }
 
  private void resizeSegments() {
    LineSegment[] newSegments = new LineSegment[segments.length * 2];
    for(int i = 1; i < segments.length; i++) {
       newSegments[i] = segments[i];   
    }
    
    segments = newSegments;
 }

 private Point getMax(Point a, Point b, Point c, Point d) {
  if (a.compareTo(b) >= 0 && a.compareTo(c) >= 0 && a.compareTo(d) >= 0) {
   return a;
  } else if (b.compareTo(c) >= 0 && b.compareTo(d) >= 0) {
   return b;
  } else if (c.compareTo(d) >= 0) {
   return c;
  } else {
   return d;
  }
 }

 private Point getMin(Point a, Point b, Point c, Point d) {
  if (a.compareTo(b) <= 0 && a.compareTo(c) <= 0 && a.compareTo(d) <= 0) {
   return a;
  } else if (b.compareTo(c) <= 0 && b.compareTo(d) <= 0) {
   return b;
  } else if (c.compareTo(d) <= 0) {
   return c;
  } else {
   return d;
  }
 }

 public int numberOfSegments() {
  return segLength;
 }

 public LineSegment[] segments() {
  LineSegment[] segs = new LineSegment[segLength];
  for (int i = 0; i < segLength; i++) {
   segs[i] = segments[i];
  }
  return segs;
 }

// public static void main(String[] args) {
//  Point a = new Point(3000, 4000);
//  Point b = new Point(6000, 7000);
//  Point c = new Point(14000, 15000);
//  Point d = new Point(20000, 21000);
//  Point[] points = {
//   a,
//   b,
//   c,
//   d
//  };
//  FastCollinearPoints fcp = new FastCollinearPoints(points);
// }

//     public static void main(String[] args) {
// 
//     // read the n points from a file
//     In in = new In(args[0]);
//     int n = in.readInt();
//     Point[] points = new Point[n];
//     for (int i = 0; i < n; i++) {
//         int x = in.readInt();
//         int y = in.readInt();
//         points[i] = new Point(x, y);
//     }
// 
//     // draw the points
//     StdDraw.enableDoubleBuffering();
//     StdDraw.setXscale(0, 32768);
//     StdDraw.setYscale(0, 32768);
//     for (Point p : points) {
//         p.draw();
//     }
//     StdDraw.show();
// 
//     // print and draw the line segments
//     FastCollinearPoints collinear = new FastCollinearPoints(points);
//     for (LineSegment segment : collinear.segments()) {
//         StdOut.println(segment);
//         segment.draw();
//     }
//     StdDraw.show();
// }
}