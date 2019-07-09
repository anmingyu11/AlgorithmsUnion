package _java;

import java.util.LinkedList;

import base.Base;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * <p>
 * Example:
 * <p>
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class _0084LargestRectangleinHistogram____Confuse extends Base {

    private abstract static class Solution {
        public abstract int largestRectangleArea(int[] heights);
    }

    /**
     * Runtime: 496 ms, faster than 5.77% of Java online submissions for Largest Rectangle in Histogram.
     * Memory Usage: 41.1 MB, less than 81.62% of Java online submissions for Largest Rectangle in Histogram.
     * <p>
     * Brute Force
     */
    private static class Solution1 extends Solution {

        @Override
        public int largestRectangleArea(int[] heights) {
            final int n = heights.length;
            int max = 0;
            for (int i = 0; i < n; ++i) {
                int minHeight = heights[i];
                for (int j = i; j < n; ++j) {
                    minHeight = Math.min(heights[j], minHeight);
                    max = Math.max(max, minHeight * (j - i + 1));
                }
            }
            return max;
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public int largestRectangleArea(int[] heights) {
            return 0;
        }
    }

    private static class Solution3 extends Solution {

        @Override
        public int largestRectangleArea(int[] heights) {
            LinkedList<Integer> stack = new LinkedList<>();
            return 0;
        }

    }

    public static void main(String[] args) {
        int[] a1 = {2, 1, 5, 6, 2, 3};

        Solution s = new Solution1();

        println(s.largestRectangleArea(a1));// 10
    }

}
