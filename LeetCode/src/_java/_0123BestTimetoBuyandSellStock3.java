package _java;

import java.util.Arrays;

import base.Base;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * <p>
 * Note: You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again).
 * <p>
 * Example 1:
 * <p>
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 * <p>
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 * engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class _0123BestTimetoBuyandSellStock3 extends Base {

    private abstract static class Solution {
        public abstract int maxProfit(int[] prices);
    }

    /**
     * 状态机:
     * This approach can be used for all the problems based on stock prices.
     * <p>
     * The idea is to design a state machine that correctly describes the problem statement.
     * <p>
     * Intuition behind the state diagram:
     * We begin at state 0, where we can either rest (i.e. do nothing) or buy stock at a given price.
     * <p>
     * If we choose to rest, we remain in state 0
     * If we buy, we spend some money (price of the stock on that day) and go to state 1
     * From state 1, we can once again choose to do nothing or we can sell our stock.
     * <p>
     * If we choose to rest, we remain in state 1
     * If we sell, we earn some money (price of the stock on that day) and go to state 2
     * This completes one transaction for us. Remember, we can only do atmost 2 transactions.
     * <p>
     * From state 2, we can choose to do nothing or buy more stock.
     * <p>
     * If we choose to rest, we remain in state 2
     * If we buy, we go to state 3
     * From state 3, we can once again choose to do nothing or we can sell our stock for the last time.
     * <p>
     * If we choose to rest, we remain in state 3
     * If we sell, we have utilized our allowed transactions and reach the final state 4
     * <p>
     * --------------
     * <p>
     * We can create 4 variables,
     * one for each state excluding the initial state since that's always 0,
     * initializing s1 to -prices[0] and the rest to INT_MIN since they will get overwritten later.
     * <p>
     * To reach s1, we either stay in s1 or we buy stock for the first time.
     * To reach s2, we either stay in s2 or we sell from s1 and come to s2
     * Similarly for s3 and s4.
     * <p>
     * In the end, we return s4 or more accurately, max(0,s4) since we initialize s4 to INT_MIN.
     * <p>
     * This idea works for all problems on stocks,
     * as long as our state diagram is correct, we can code it up like this.
     * <p>
     * Side Note: Technically, this is a dynammic programming approach and we should actually be doing s2[i] = max(s2[i-1], s1[i-1]+prices[i]) but we can be rest assured that the overwritten value of s1 will always be better than the previous one and hence we do not need temporary variables.
     */
    private static class Solution1 extends Solution {

        @Override
        public int maxProfit(int[] prices) {
            final int n = prices.length;
            if (n == 0) {
                return 0;
            }
            int s1 = -prices[0], s2 = Integer.MIN_VALUE, s3 = Integer.MIN_VALUE, s4 = Integer.MIN_VALUE;
            for (int i = 0; i < n; ++i) {
                s1 = Math.max(s1, -prices[i]);
                s2 = Math.max(s2, s1 + prices[i]);
                s3 = Math.max(s3, s2 - prices[i]);
                s4 = Math.max(s4, s3 + prices[i]);
            }

            return Math.max(0, s4);
        }
    }

    /**
     * 状态机2:
     * 扩展到t次
     * 技巧十分值得学习
     * Runtime: 3 ms, faster than 28.64% of Java online submissions for Best Time to Buy and Sell Stock III.
     * Memory Usage: 35.9 MB, less than 73.22% of Java online submissions for Best Time to Buy and Sell Stock III.
     */
    private static class Solution2 extends Solution {

        @Override
        public int maxProfit(int[] prices) {
            final int n = prices.length;
            final int t = 2;
            if (n == 0) {
                return 0;
            }
            int[] stock = new int[t * 2];
            Arrays.fill(stock, Integer.MIN_VALUE);
            stock[0] = -prices[0];
            for (int i = 1; i < n; ++i) {
                stock[0] = Math.max(stock[0], -prices[i]);
                // stock[j] 第j次交易,偶数次为买,奇数次为卖,stock[j]为当前最大收益
                for (int j = 1; j < t * 2; j += 2) {
                    // 卖
                    stock[j] = Math.max(stock[j], stock[j - 1] + prices[i]);
                    // 买
                    if (j + 1 < t * 2) {
                        stock[j + 1] = Math.max(stock[j + 1], stock[j] - prices[i]);
                    }
                }
            }
            return Math.max(0, stock[t * 2 - 1]);
        }
    }

    /**
     * Runtime: 1150 ms, faster than 5.00% of Java online submissions for Best Time to Buy and Sell Stock III.
     * Memory Usage: 37.9 MB, less than 59.49% of Java online submissions for Best Time to Buy and Sell Stock III.
     */
    private static class Solution3 extends Solution {

        @Override
        public int maxProfit(int[] p) {
            final int n = p.length;
            if (n == 0) {
                return 0;
            }
            int[][] dp = new int[3][n + 1];
            for (int k = 1; k <= 2; ++k) {
                for (int i = 1; i <= n; ++i) {
                    for (int j = i + 1; j <= n; ++j) {
                        dp[k][j] = Math.max(dp[k][j - 1], Math.max(dp[k][j], p[j - 1] - p[i - 1] + dp[k - 1][i - 1]));
                    }
                }
            }
            return dp[2][n];
        }

    }

    /**
     * Runtime: 1 ms, faster than 97.60% of Java online submissions for Best Time to Buy and Sell Stock III.
     * Memory Usage: 37.9 MB, less than 59.33% of Java online submissions for Best Time to Buy and Sell Stock III.
     */
    private static class Solution4 extends Solution {

        @Override
        public int maxProfit(int[] p) {
            final int n = p.length;
            if (n == 0) {
                return 0;
            }
            int[][] dp = new int[3][n];
            for (int k = 1; k <= 2; ++k) {
                int min = p[0];
                for (int i = 1; i < p.length; ++i) {
                    //dp[k-1][i-1] 代表在i买时之前的的最大获利 min(p[i] - dp[k-1,i-1]) p[i]越小,说明买的价格越小,获利越大, p[i] - min
                    min = Math.min(min, p[i] - dp[k - 1][i - 1]);
                    dp[k][i] = Math.max(dp[k][i - 1], p[i] - min);
                }
            }
            return dp[2][n - 1];
        }

    }

    /**
     * 很难想
     */
    private static class Solution5 extends Solution {

        @Override
        public int maxProfit(int[] prices) {
            if (prices.length == 0) {
                return 0;
            }
            int[] dp = new int[3];
            int[] min = new int[3];
            Arrays.fill(min, prices[0]);
            for (int i = 1; i < prices.length; i++) {
                for (int k = 1; k <= 2; k++) {
                    min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
                    dp[k] = Math.max(dp[k], prices[i] - min[k]);
                }
            }

            return dp[2];
        }
    }

    public static void main(String[] args) {
        int[] a1 = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] a2 = {1, 2, 3, 4, 5};
        int[] a3 = {7, 6, 4, 3, 1};
        int[] a4 = {1, 4, 2};

        Solution s = new Solution2();

        println(s.maxProfit(a1)); // 6
        println(s.maxProfit(a2)); // 4
        println(s.maxProfit(a3)); // 0
        println(s.maxProfit(a4)); // 3
    }
}
