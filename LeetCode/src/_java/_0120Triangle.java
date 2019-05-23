package _java;

import java.util.List;

import base.Base;
import base.util.ArraysUtil;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * <p>
 * For example, given the following triangle
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * <p>
 * Note:
 * <p>
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class _0120Triangle extends Base {

    private abstract static class Solution {
        public abstract int minimumTotal(List<List<Integer>> triangle);
    }

    /**
     * DP
     * Runtime: 2 ms, faster than 93.51% of Java online submissions for Triangle.
     * Memory Usage: 35.7 MB, less than 98.64% of Java online submissions for Triangle.
     */
    private static class Solution1 extends Solution {

        @Override
        public int minimumTotal(List<List<Integer>> triangle) {
            final int n = triangle.size();
            if (n < 2) {
                return triangle.get(0).get(0);
            }
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; ++i) {
                dp[n - 1][i] = triangle.get(n - 1).get(i);
            }
            for (int i = n - 2; i >= 0; --i) {
                for (int j = 0; j < triangle.get(i).size(); ++j) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
                }
            }
            return dp[0][0];
        }
    }

    /**
     * Runtime: 2 ms, faster than 93.51% of Java online submissions for Triangle.
     * Memory Usage: 35.9 MB, less than 98.39% of Java online submissions for Triangle.
     * 设计的太精妙了
     */
    private static class Solution2 extends Solution {

        @Override
        public int minimumTotal(List<List<Integer>> triangle) {
            final int n = triangle.size();
            if (n < 2) {
                return triangle.get(0).get(0);
            }
            int[] dp = new int[n + 1];
            for (int i = n - 1; i >= 0; --i) {
                for (int j = 0; j < triangle.get(i).size(); ++j) {
                    dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
                }
            }
            return dp[0];
        }

    }

    public static void main(String[] args) {
        int[][] a1 = {
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        };

        Solution s = new Solution2();

        println(s.minimumTotal(ArraysUtil.int2List(a1)));//11
    }
}
