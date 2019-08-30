package _java;

import base.Base;

/**
 * There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x k cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color 0;
 * costs[1][2] is the cost of painting house 1 with color 2, and so on...
 * Find the minimum cost to paint all houses.
 * <p>
 * Note:
 * All costs are positive integers.
 * <p>
 * Example:
 * <p>
 * Input: [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 * Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 * Follow up:
 * Could you solve it in O(nk) runtime?
 */
public class _0265PaintHouse2 extends Base {

    private abstract static class Solution {
        public abstract int minCostII(int[][] costs);
    }

    /**
     * Runtime: 5 ms, faster than 21.07% of Java online submissions for Paint House II.
     * Memory Usage: 44 MB, less than 24.00% of Java online submissions for Paint House II.
     */
    private static class Solution1 extends Solution {

        @Override
        public int minCostII(int[][] costs) {
            final int N = costs.length;
            if (N < 1) {
                return 0;
            }
            final int K = costs[0].length;
            for (int i = 1; i < N; ++i) {
                for (int k = 0; k < K; ++k) {
                    int min = Integer.MAX_VALUE;
                    for (int j = 0; j < K; ++j) {
                        if (j != k) {
                            min = Math.min(min, costs[i - 1][j]);
                        }
                    }
                    costs[i][k] += min;
                }
            }
            int min = Integer.MAX_VALUE;
            for (int k = 0; k < K; ++k) {
                min = Math.min(min, costs[N - 1][k]);
            }
            return min;
        }
    }

    /**
     * Runtime: 2 ms, faster than 99.13% of Java online submissions for Paint House II.
     * Memory Usage: 42.7 MB, less than 32.00% of Java online submissions for Paint House II.
     */
    private static class Solution2 extends Solution {

        public int minCostII(int[][] costs) {
            final int N = costs.length;
            if (N < 1) {
                return 0;
            }
            final int K = costs[0].length;

            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, id1 = -1;
            // 初始化
            for (int k = 0; k < K; ++k) {
                int cost = costs[0][k];
                if (cost < min1) {
                    min2 = min1;
                    min1 = cost;
                    id1 = k;
                } else if (cost < min2) {
                    min2 = cost;
                }
            }

            for (int i = 1; i < N; ++i) {
                int m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE, idx = -1;
                for (int k = 0; k < K; ++k) {
                    int cost = costs[i][k] + (k != id1 ? min1 : min2);
                    if (cost < m1) {
                        m2 = m1;
                        m1 = cost;
                        idx = k;
                    } else if (cost < m2) {
                        m2 = cost;
                    }
                }
                min1 = m1;
                min2 = m2;
                id1 = idx;
            }

            return min1;
        }

    }

    public static void main(String[] args) {
        int[][] colors1 = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        Solution s = new Solution2();
        println(s.minCostII(colors1));// 10
    }

}