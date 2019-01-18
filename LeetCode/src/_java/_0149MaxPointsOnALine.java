package _java;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    // Brute Force 以边为中心 failed in [[0,0],[1,65536],[65536,0]] 乘法溢出
    private static class Solution1 extends Solution {

        public int maxPoints(Point[] points) {
            // 边界：一共两个点你还判断个鸡毛共线
            final int len = points.length;
            if (len < 3) {
                return len;
            }

            int max = 0;
            for (int i = 0; i < len - 1; ++i) {
                for (int j = i + 1; j < len; ++j) {
                    // points[i] 和 points[j]
                    // 这里需要一个bool值的slope 来判断斜率是否存在，不存在的话设置为false,后续单独处理
                    boolean slope = true;
                    int dX = points[i].x - points[j].x;
                    int dY = points[i].y - points[j].y;
                    int interceptDX = 0;
                    if (dX == 0) {
                        // 这时候两个点连线是垂直于x轴的，木有斜率
                        slope = false;
                    } else {
                        // 这个是点斜式的变形， 等式左侧是(截距*dx),自己在演算纸上验算一下吧,就不详细说了
                        interceptDX = dX * points[i].y - dY * points[i].x;
                    }

                    int count = 0;
                    for (int k = 0; k < len; ++k) {
                        if (slope) {
                            // 将k点的x和y带入看直线方程是否有解。
                            if (interceptDX == dX * points[k].y - dY * points[k].x) {
                                ++count;
                            }
                        } else {
                            if (points[k].x == points[i].x) {
                                ++count;
                            }
                        }
                    }
                    max = Math.max(max, count);
                }
            }

            return max;
        }

    }

    //Brute Force 以点为中心 [[0,0],[94911151,94911150],[94911152,94911151]] 精度不够。
    private static class Solution2 extends Solution {

        public int maxPoints(Point[] points) {
            // 同上
            final int len = points.length;
            if (len < 3) {
                return len;
            }

            int max = 0;
            // 创建一个hash表来存储斜率对应的点的数量。
            Map<Double, Integer> map = new HashMap<>();
            for (int i = 0; i <= len - 2; ++i) {
                map.clear();
                int samePoint = 0;// 与pi 重合的点
                int sameLine = 1;// 和pi共线的最大点数
                for (int j = i + 1; j <= len - 1; ++j) {
                    Double slope = null;
                    if (points[i].x == points[j].x) {
                        // 垂直与x轴的情况
                        slope = Double.POSITIVE_INFINITY;
                        // 重合的点
                        if (points[i].y == points[j].y) {
                            ++samePoint;
                            continue;
                        }
                    } else {
                        if (points[i].y == points[j].y) {
                            // 这个涉及一个 -0 和 +0的问题， 在float和double里，+0是大于-0的，这个跟小数的实现有关系，有兴趣自行查一下
                            slope = 0.0;
                        } else {
                            // 求斜率
                            slope = 1.0 * (points[i].y - points[j].y) / (points[i].x - points[j].x);
                        }
                    }

                    Integer slopeCount = map.get(slope);
                    if (slopeCount != null) {
                        map.put(slope, slopeCount + 1);
                        slopeCount += 1;
                    } else {
                        slopeCount = 2;
                        map.put(slope, slopeCount);
                    }

                    sameLine = Math.max(sameLine, slopeCount);
                }
                // 共线的点加上重合的点
                max = Math.max(max, sameLine + samePoint);
            }

            return max;
        }

    }

    // 最大公约数代替除法
    private static class Solution3 extends Solution {

        public int hashCode(int dx, int dy) {
            return Objects.hashCode(dx) ^ Objects.hashCode(dy);
        }

        public int maxPoints(Point[] points) {
            final int len = points.length;
            if (len < 3) {
                return len;
            }

            int max = 0;
            for (int i = 0; i < len - 1; ++i) {
                Map<Integer, Integer> map = new HashMap<>(len);
                int samePoint = 0;
                int sameLine = 1;
                for (int j = i + 1; j < len; ++j) {
                    if (points[i].x == points[j].x && points[i].y == points[j].y) {
                        ++samePoint;
                        continue;
                    }
                    int dx = points[i].x - points[j].x;
                    int dy = points[i].y - points[j].y;
                    int gcd = gcd(dx, dy);

                    int commX = dx / gcd, commY = dy / gcd;
                    int hash = hashCode(commX, commY);
                    Integer val = map.get(hashCode(commX, commY));
                    if (val != null) {
                        map.put(hashCode(commX, commY), val + 1);
                        ++val;
                    } else {
                        val = 2;
                        map.put(hashCode(commX, commY), val);
                    }
                    sameLine = Math.max(sameLine, val);
                }
                max = Math.max(max, samePoint + sameLine);
            }

            return max;
        }

        int gcd(int a, int b) {
            int r = 0;
            while (b != 0) {
                r = a % b;
                a = b;
                b = r;
            }
            return a;
        }
    }

    private static class Solution4 extends Solution {

        /*
         *  A line is determined by two factors,say y=ax+b
         *
         *  If two points(x1,y1) (x2,y2) are on the same line(Of course).

         *  Consider the gap between two points.

         *  We have (y2-y1)=a(x2-x1),a=(y2-y1)/(x2-x1) a is a rational, b is canceled since b is a constant

         *  If a third point (x3,y3) are on the same line. So we must have y3=ax3+b

         *  Thus,(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

         *  Since a is a rational, there exists y0 and x0, y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

         *  So we can use y0&x0 to track a line;
         */

        public int maxPoints(Point[] points) {
            if (points == null) return 0;
            if (points.length <= 2) return points.length;

            Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
            int result = 0;
            for (int i = 0; i < points.length; i++) {
                map.clear();
                int overlap = 0, max = 0;
                for (int j = i + 1; j < points.length; j++) {
                    int x = points[j].x - points[i].x;
                    int y = points[j].y - points[i].y;
                    if (x == 0 && y == 0) {
                        overlap++;
                        continue;
                    }
                    int gcd = generateGCD(x, y);
                    if (gcd != 0) {
                        x /= gcd;
                        y /= gcd;
                    }

                    if (map.containsKey(x)) {
                        if (map.get(x).containsKey(y)) {
                            map.get(x).put(y, map.get(x).get(y) + 1);
                        } else {
                            map.get(x).put(y, 1);
                        }
                    } else {
                        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
                        m.put(y, 1);
                        map.put(x, m);
                    }
                    max = Math.max(max, map.get(x).get(y));
                }
                result = Math.max(result, max + overlap + 1);
            }
            return result;


        }

        private int generateGCD(int a, int b) {
            if (b == 0) return a;
            else return generateGCD(b, a % b);

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
                new Point(-2, -1), new Point(0, -11), new Point(0, -9)
        };
        Point[] points4 = new Point[]{
                new Point(0, 0), new Point(1, 65536), new Point(65536, 0)
        };
        Point[] points5 = new Point[]{
                new Point(1, 1), new Point(1, 1), new Point(1, 1)
        };
        Point[] points6 = new Point[]{
                new Point(0, 0), new Point(94911151, 94911150), new Point(94911152, 94911151)
        };


        Solution s = new Solution3();
        println(s.maxPoints(points1));// 3
        println(s.maxPoints(points2));// 4
        println(s.maxPoints(points3));// 6
        println(s.maxPoints(points4));// 2
        println(s.maxPoints(points5));// 3
        println(s.maxPoints(points6));// 2
    }
}
