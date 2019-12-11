package _java;

import base.Base;

public class _1266MinimumTimeVisitingAllPoints extends Base {

    private static class Solution {
        public int minTimeToVisitAllPoints(int[][] points) {
            final int m = points.length;
            if (m < 1) {
                return 0;
            }
            int step = 0;
            int cur1 = points[0][0], cur2 = points[0][1];
            for (int i = 1; i < m; ++i) {
                int next1 = points[i][0], next2 = points[i][1];
                while (cur1 != next1 || cur2 != next2) {
                    if (cur1 > next1) {
                        --cur1;
                    } else if (cur1 < next1) {
                        ++cur1;
                    }
                    if (cur2 > next2) {
                        --cur2;
                    } else if (cur2 < next2) {
                        ++cur2;
                    }
                    ++step;
                }
            }
            return step;
        }
    }

    public static void main(String[] args) {
        int[][] points1 = {{1, 1}, {3, 4}, {-1, 0}};
        int[][] points2 = {{3, 2}, {-2, 2}};
        Solution s = new Solution();
        println(s.minTimeToVisitAllPoints(points1));//7
        println(s.minTimeToVisitAllPoints(points2));//5
    }
}
