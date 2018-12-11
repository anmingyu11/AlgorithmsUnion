package dp;

import java.util.Stack;

import base.Base;

public class MaximalRectangle extends Base {

    static class Solution1 {

        public static int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int maxArea = 0;
            final int colLen = matrix[0].length;
            final int rowLen = matrix.length;
            int[] height = new int[colLen];

            //initial
            for (int i = 0; i < rowLen; ++i) {
                resetHeight(matrix[i], height);
                maxArea = Math.max(largestRec(height), maxArea);
            }

            return maxArea;
        }


        private static void resetHeight(char[] matrixRow, int[] height) {
            for (int i = 0; i < matrixRow.length; ++i) {
                if (matrixRow[i] == '1') {
                    height[i] += 1;
                } else {
                    height[i] = 0;
                }
            }
        }

        private static int largestRec(int[] height) {
            if (height == null || height.length == 0) return 0;
            final int n = height.length;
            Stack<Integer> s = new Stack<>();
            int i = 0, max = 0;
            while (i < n) {
                int h = height[i];
                if (s.empty() || h >= height[s.peek()]) {
                    s.push(i++);
                } else {
                    int tp = s.pop();
                    max = Math.max(max, height[tp] * (s.empty() ? i : (i - 1 - s.peek())));
                }
            }

            while (!s.empty()) {
                int tp = s.pop();
                max = Math.max(max, height[tp] * (s.empty() ? i : (i - 1 - s.peek())));
            }

            return max;
        }
    }

    static class LargestRectangle {
        // The main function to find the maximum rectangular area under given
        // histogram with n bars
        static int getMaxArea(int hist[]) {
            final int n = hist.length;
            // Create an empty stack. The stack holds indexes of hist[] array
            // The bars stored in stack are always in increasing order of their
            // heights.
            //创建一个空栈。 栈保存hist []数组的索引。 存储在堆栈中的bar总是按其高度的递增顺序排列。
            Stack<Integer> s = new Stack<>();

            int max_area = 0; // Initialize max area
            int tp;  // To store top of stack
            int area_with_top; // To store area with top bar as the smallest bar

            // Run through all bars of given histogram
            int i = 0;
            while (i < n) {
                // If this bar is higher than the bar on top stack, push it to stack
                // 如果这个bar 比栈顶的bar要大，那么压入栈中
                if (s.empty() || hist[s.peek()] <= hist[i]) {
                    s.push(i++);

                    // If this bar is lower than top of stack, then calculate area of rectangle
                    // with stack top as the smallest (or minimum height) bar. 'i' is
                    // 'right index' for the top and element before top in stack is 'left index'
                    // 如果此bar比栈顶元素小，则计算矩形区域，栈顶元素为最小（或最小高度）bar。
                    // 'i'是顶部的'右侧索引'，栈顶部的元素是'左侧索引'
                } else {
                    tp = s.pop();  // store the top index,and pop it.

                    // Calculate the area with hist[tp] stack as smallest bar
                    area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

                    // update max area, if needed
                    max_area = Math.max(area_with_top, max_area);
                }
            }

            // Now pop the remaining bars from stack and calculate area with every
            // popped bar as the smallest bar
            while (!s.empty()) {
                tp = s.peek();
                s.pop();
                area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

                max_area = Math.max(max_area, area_with_top);
            }

            return max_area;

        }
    }

    public static void main(String[] args) {
        char[][] m = new char[4][5];
        m[0] = new char[]{'1', '0', '1', '0', '0'};
        m[1] = new char[]{'1', '0', '1', '1', '1'};
        m[2] = new char[]{'1', '1', '1', '1', '1'};
        m[3] = new char[]{'1', '0', '0', '1', '0'};

        println(Solution1.maximalRectangle(m));
        //println(LargestRectangle.getMaxArea(new int[]{2, 4, 4, 2, 2, 0, 4}));

    }
}
