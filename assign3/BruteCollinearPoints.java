import java.util.Arrays;

public class BruteCollinearPoints {
 private LineSegment[] segments;
 private int curLen;

 public BruteCollinearPoints(Point[] points) {
  segments = new LineSegment[points.length];
  curLen = 0;
  Point prevPoint = new Point(-322487, 423569);
  Point[] sortedPoints = points;
  Arrays.sort(sortedPoints);
  for (int i = 0; i < sortedPoints.length; i++) {
   if (sortedPoints[i].compareTo(prevPoint) == 0) {
    throw new java.lang.IllegalArgumentException("illegal argument: " + sortedPoints[i] + prevPoint);
   }
   prevPoint = sortedPoints[i];
   for (int j = i + 1; j < sortedPoints.length; j++) {
    for (int k = j + 1; k < sortedPoints.length; k++) {
     for (int m = k + 1; m < sortedPoints.length; m++) {
      if (j > i && k > j && m > k) {
       check4Points(sortedPoints[i], sortedPoints[j], sortedPoints[k], sortedPoints[m]);
      }
     }
    }
   }
  }
 }

 private void check4Points(Point a, Point b, Point c, Point d) {
  if (a.slopeTo(b) == a.slopeTo(c)) {
   if (a.slopeTo(b) == a.slopeTo(d)) {
    segments[curLen] = new LineSegment(a, d);
    curLen++;
   }
  }
 }

 public int numberOfSegments() {
  return curLen;
 }

 public LineSegment[] segments() {
  LineSegment[] segs = new LineSegment[curLen];
  for (int i = 0; i < curLen; i++) {
   segs[i] = segments[i];
  }
  return segs;
 }

 public static void main(String[] args) {

 }
}