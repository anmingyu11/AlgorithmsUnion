package _java;

import java.util.Arrays;
import java.util.LinkedList;

import base.Base;

/**
 * Given a positive integer n,
 * find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...)
 * which sum to n.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 * <p>
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class _0279PerfectSquares extends Base {

    private abstract static class Solution {
        public abstract int numSquares(int n);
    }

    /**
     * Runtime: 20 ms, faster than 71.68% of Java online submissions for Perfect Squares.
     * Memory Usage: 34.4 MB, less than 50.75% of Java online submissions for Perfect Squares.
     */
    private static class Solution1 extends Solution {

        @Override
        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int i = 0; i <= n; ++i) {
                for (int j = 1; i + j * j <= n; ++j) {
                    dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
                }
            }
            return dp[n];
        }

    }

    /**
     * BFS
     * Runtime: 23 ms, faster than 53.65% of Java online submissions for Perfect Squares.
     * Memory Usage: 35.4 MB, less than 45.40% of Java online submissions for Perfect Squares.
     */
    private static class Solution2 extends Solution {

        @Override
        public int numSquares(int n) {
            if (n <= 0) {
                return 0;
            }
            int[] cntSquares = new int[n];
            LinkedList<Integer> squares = new LinkedList<>();
            LinkedList<Integer> queue = new LinkedList<>();

            for (int i = 1; i <= Math.sqrt(n); ++i) {
                squares.add(i * i);
                cntSquares[i * i - 1] = 1;
            }

            if (squares.getLast() == n) {
                return 1;
            }

            queue.addAll(squares);

            int count = 1;
            while (!queue.isEmpty()) {
                ++count;
                LinkedList<Integer> queue2 = new LinkedList<>();
                while (!queue.isEmpty()) {
                    int tmp = queue.removeFirst();
                    for (int square : squares) {
                        if (tmp + square == n) {
                            return count;
                        } else if (tmp + square < n && (cntSquares[tmp + square - 1] == 0)) {
                            cntSquares[tmp + square - 1] = count;
                            queue2.addLast(tmp + square);
                        } else if (tmp + square > n) {
                            break;
                        }
                    }
                }
                queue = queue2;
            }
            return 0;
        }

    }

    public static void main(String[] args) {
        int n1 = 12;
        int n2 = 13;

        Solution s = new Solution2();

        println(s.numSquares(n1)); //3
        println(s.numSquares(n2));//2
    }
}
