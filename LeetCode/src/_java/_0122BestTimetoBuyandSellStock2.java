package _java;

import base.Base;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit.
 * <p>
 * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 * <p>
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
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
public class _0122BestTimetoBuyandSellStock2 extends Base {

    private abstract static class Solution {
        public abstract int maxProfit(int[] prices);
    }

    /**
     * Runtime: 1 ms, faster than 80.94% of Java online submissions for Best Time to Buy and Sell Stock II.
     * Memory Usage: 35.6 MB, less than 84.83% of Java online submissions for Best Time to Buy and Sell Stock II.
     */
    private static class Solution1 extends Solution {

        @Override
        public int maxProfit(int[] A) {
            final int n = A.length;
            if (n < 2) {
                return 0;
            }
            int buy = A[0], gain = 0;
            for (int i = 1; i < n; ++i) {
                if (A[i] < A[i - 1]) {
                    gain += A[i - 1] - buy;
                    buy = A[i];
                }
            }
            gain += A[n - 1] - buy;
            return gain;
        }

    }

    public static void main(String[] args) {
        int[] a1 = {7, 1, 5, 3, 6, 4};
        int[] a2 = {1, 2, 3, 4, 5};
        int[] a3 = {7, 6, 4, 3, 1};

        Solution s = new Solution1();

        println(s.maxProfit(a1));//7
        println(s.maxProfit(a2));//4
        println(s.maxProfit(a3));//0
    }
}
