package dp;

import base.Base;

public class BestTimeToBuyAndSellStock3 extends Base {

    static class Solution1 {

        public static int maxProfit(int[] prices) {
            final int pricesLen = prices.length;
            if (pricesLen == 0) {
                return 0;
            }

            int[][] dp = new int[3][pricesLen];
            for (int k = 1; k <= 2; k++) {

                for (int i = 1; i < pricesLen; i++) {
                    int min = prices[0];
                    for (int j = 1; j <= i; j++) {
                        min = Math.min(min, prices[j] - dp[k - 1][j - 1]);
                    }
                    dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
                }
            }

            return dp[2][pricesLen - 1];
        }

    }

    public static void main(String[] args) {
        int[] stock1 = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int[] stock2 = new int[]{1, 2, 3, 4, 5};
        int[] stock3 = new int[]{7, 6, 4, 3, 1};

        println(Solution1.maxProfit(stock1));
        println(Solution1.maxProfit(stock2));
        println(Solution1.maxProfit(stock3));
    }
}
