package _java;

import java.util.LinkedList;

import base.Base;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * Example 1:
 * <p>
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 * <p>
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class _0032LongestValidParentheses extends Base {
    private abstract static class Solution {
        public abstract int longestValidParentheses(String s);
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Longest Valid Parentheses.
     * Memory Usage: 38 MB, less than 74.44% of Java online submissions for Longest Valid Parentheses.
     * <p>
     * 精妙的想不到
     */
    private static class Solution1 extends Solution {

        @Override
        public int longestValidParentheses(String s) {
            final int n = s.length();
            int max = 0;
            int[] dp = new int[n];
            for (int i = 1; i < n; ++i) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {//i-1 is only )
                        dp[i] = (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
                    }
                    max = Math.max(max, dp[i]);
                }
            }
            return max;
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public int longestValidParentheses(String s) {
            final int n = s.length();
            int max = 0;
            LinkedList<Integer> stack = new LinkedList<>();
            stack.push(-1);
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '(') {
                    // 加左括号。
                    stack.push(i);
                } else {
                    // 弹出对应的左括号。
                    stack.pop();
                    if (stack.isEmpty()) {
                        // 没有对应的左括号，加入边界。
                        stack.push(i);
                    } else {
                        // 计算最长长度
                        max = Math.max(max, i-stack.peek());
                    }
                }
            }
            return max;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution2();
    }
}
