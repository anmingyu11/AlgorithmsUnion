package _java;

import java.util.Arrays;

import base.Base;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * <p>
 * Input: coins = [2], amount = 3
 * Output: -1
 */
public class _0322CoinChange extends Base {

    private abstract static class Solution {
        public abstract int coinChange(int[] coins, int amount);
    }

    /**
     * Runtime: 9 ms, faster than 90.20% of Java online submissions for Coin Change.
     * Memory Usage: 35 MB, less than 99.94% of Java online submissions for Coin Change.
     * <p>
     * 自底向上dp  + 状态机思想
     */
    private static class Solution1 extends Solution {

        @Override
        public int coinChange(int[] coins, int amount) {
            if (coins.length == 0) {
                return -1;
            }
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE - 1);
            dp[0] = 0;
            for (int i = 1; i <= amount; ++i) {
                for (int coin : coins) {
                    if (coin <= i) {
                        dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        int[] coins1 = {1, 2, 5};
        int[] coins2 = {2};
        int[] coins3 = {0};
        int[] coins4 = {2};

        println(s.coinChange(coins1, 11)); // 3
        println(s.coinChange(coins2, 3)); // -1
        println(s.coinChange(coins3, 1));// -1
        println(s.coinChange(coins4, 1));// -1
    }

}
