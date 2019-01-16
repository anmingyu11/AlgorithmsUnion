package _java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import base.Base;


public class _0149MaxPointsOnALine extends Base {
    private static class Point {
        int x, y;

        public Point() {
            this.x = 0;
            this.y = 0;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private abstract static class Solution {

        abstract public int maxPoints(Point[] points);
    }

    private static class Solution1 extends Solution {

        Map<Double, HashSet<Point>> map = new HashMap<>();
        int max;

        @Override
        public int maxPoints(Point[] points) {
            if (points.length <= 2) {
                return points.length;
            }
            for (int i = 0; i < points.length - 1; ++i) {
                backTrack(points, i);
            }
            for (Double slope : map.keySet()){
                println("slope : " + slope  + " size : " + map.get(slope).size());
            }
            return max;
        }

        private void backTrack(Point[] points, int pos) {
            for (int i = pos + 1; i < points.length; ++i) {
                double slope = slope(points[pos], points[i]);
                HashSet<Point> set = map.get(slope);
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(points[pos]);
                set.add(points[i]);
                map.put(slope, set);
                max = Math.max(set.size(), max);
            }

        }

        private double slope(Point p1, Point p2) {
            return (double) (p1.y - p2.y) / (double) (p1.x - p2.x);
        }
    }

    public static void main(String[] args) {
        Point[] points1 = new Point[]{new Point(1, 1), new Point(2, 2), new Point(3, 3)};
        Point[] points2 = new Point[]{new Point(1, 1), new Point(3, 2), new Point(5, 3),
                new Point(4, 1), new Point(2, 3), new Point(1, 4)};
        Point[] points3 = new Point[]{
                new Point(0, -12), new Point(5, 2), new Point(2, 5),
                new Point(0, -5), new Point(1, 5), new Point(2, -2),
                new Point(5, -4), new Point(3, 4), new Point(-2, 4),
                new Point(-1, 4), new Point(0, -5), new Point(0, -8),
                new Point(-2, -1), new Point(0, -11), new Point(0, -9)};

        Solution s = new Solution1();
        //println(s.maxPoints(points1));
        //println(s.maxPoints(points2));
        println(s.maxPoints(points3));
    }
}
