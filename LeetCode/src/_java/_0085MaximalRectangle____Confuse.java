package _java;

import java.util.Arrays;

import base.Base;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest rectangle containing only 1's and return its area.
 * <p>
 * Example:
 *
 * <pre>
 * Input:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * </pre>
 * Output: 6
 */
public class _0085MaximalRectangle____Confuse extends Base {

    private abstract static class Solution {
        public abstract int maximalRectangle(char[][] matrix);
    }

    /**
     * Runtime: 45 ms, faster than 6.05% of Java online submissions for Maximal Rectangle.
     * Memory Usage: 42.9 MB, less than 93.23% of Java online submissions for Maximal Rectangle.
     */
    private static class Solution1 extends Solution {

        @Override
        public int maximalRectangle(char[][] matrix) {
            final int m = matrix.length;
            if (m < 1) {
                return 0;
            }
            final int n = matrix[0].length;
            if (n < 1) {
                return 0;
            }
            int[][] dp = new int[m][n];
            int max = 0;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == '1') {
                        dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;

                        int width = dp[i][j];
                        for (int k = i; k >= 0; --k) {
                            width = Math.min(width, dp[k][j]);
                            max = Math.max(max, width * (i - k + 1));
                        }
                    }
                }
            }

            return max;
        }

    }

    /**
     * Stack
     */
    private static class Solution3 extends Solution {

        @Override
        public int maximalRectangle(char[][] matrix) {
            final int m = matrix.length;
            if (m < 1) {
                return 0;
            }
            final int n = matrix[0].length;
            if (n < 1) {
                return 0;
            }

            int[] height = new int[n];
            int[] left = new int[n];
            int[] right = new int[n];

            Arrays.fill(right, n);

            int max = 0;
            for (int i = 0; i < m; ++i) {
                // cur_left is one greater than rightmost occurrence of zero we have encountered.
                // When we "expand" the rectangle to the left,
                // we know it can't expand past that point, otherwise it'll run into the zero.
                int curLeft = 0;
                // cur_right is the leftmost occurrence of zero we have encountered.
                int curRight = n;
                // update height;
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == '1') {
                        ++height[j];
                    } else {
                        height[j] = 0;
                    }
                }
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == '1') {
                        left[j] = Math.max(left[j], curLeft);
                    } else {
                        left[j] = 0;
                        curLeft = j + 1;
                    }
                }
                for (int j = n - 1; j >= 0; --j) {
                    if (matrix[i][j] == '1') {
                        right[j] = Math.min(right[j], curRight);
                    } else {
                        right[j] = n;
                        curRight = j;
                    }
                }
                for (int j = 0; j < n; ++j) {
                    max = Math.max(max, height[j] * (right[j] - left[j]));
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        char[][] m1 = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        char[][] m2 = {
                {'1'}
        };
        Solution s = new Solution3();

        println(s.maximalRectangle(m1));
        println(s.maximalRectangle(m2));
    }

}
