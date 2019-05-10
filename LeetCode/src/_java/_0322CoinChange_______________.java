package _java;

import base.Base;

// You are given coins of different denominations and a total amount of money amount.
// Write a function to compute the fewest number of coins that you need to make up that amount.
// If that amount of money cannot be made up by any combination of the coins, return -1.
public class _0322CoinChange_______________ extends Base {

    private abstract static class Solution {
        public abstract int coinChange(int[] coins, int amount);
    }

    private static class Solution1 extends Solution {

        @Override
        public int coinChange(int[] coins, int amount) {
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
