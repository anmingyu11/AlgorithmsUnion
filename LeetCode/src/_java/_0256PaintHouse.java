package _java;

import base.Base;

/**
 * There are a row of n houses,
 * each house can be painted with one of the three colors:
 * red, blue or green. The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Find the minimum cost to paint all houses.
 * <p>
 * Note:
 * All costs are positive integers.
 * <p>
 * Example:
 * Input: [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 */
public class _0256PaintHouse extends Base {

    private abstract static class Solution {
        public abstract int minCost(int[][] costs);
    }

    /**
     * BruteForce
     */
    private static class Solution1 extends Solution {

        private int[][] costs;

        public int minCost(int[][] costs) {
            this.costs = costs;
            if (this.costs.length < 1) {
                return 0;
            }
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < 3; ++i) {
                minCost = Math.min(minCost, costs[0][i] + minCostAux(1, i));
            }
            return minCost;
        }

        private int minCostAux(int level, int lastIndex) {
            if (level == costs.length) {
                return 0;
            }
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < 3; ++i) {
                if (i != lastIndex) {
                    minCost = Math.min(minCost,
                            costs[level][i] + minCostAux(level + 1, i));
                }
            }
            return minCost;
        }

    }

    private static class Solution2 extends Solution {

        private int[][] costs;
        private Integer[][] dp;

        public int minCost(int[][] costs) {
            this.costs = costs;
            if (this.costs.length < 1) {
                return 0;
            }
            dp = new Integer[costs.length][3];
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < 3; ++i) {
                minCost = Math.min(minCost, costs[0][i] + minCostAux(1, i));
            }
            return minCost;
        }

        private int minCostAux(int level, int lastIndex) {
            if (level == costs.length) {
                return 0;
            }
            if (dp[level][lastIndex] != null) {
                return dp[level][lastIndex];
            }
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < 3; ++i) {
                if (i != lastIndex) {
                    minCost = Math.min(minCost,
                            costs[level][i] + minCostAux(level + 1, i));
                } else {
                    dp[level][i] = Integer.MAX_VALUE;
                }
            }
            dp[level][lastIndex] = minCost;
            return minCost;
        }

    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Paint House.
     * Memory Usage: 40.8 MB, less than 64.71% of Java online submissions for Paint House.
     * Paint House
     */
    private static class Solution3 extends Solution {

        public int minCost(int[][] costs) {
            final int n = costs.length;
            if (n < 1) {
                return 0;
            }
            for (int i = 1; i < n; ++i) {
                costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
                costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
                costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
            }
            return Math.min(Math.min(costs[n - 1][1], costs[n - 1][2]), costs[n - 1][0]);
        }

    }

    public static void main(String[] args) {
        int[][] colors1 = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        Solution s = new Solution3();
        println(s.minCost(colors1));// 10
    }
}