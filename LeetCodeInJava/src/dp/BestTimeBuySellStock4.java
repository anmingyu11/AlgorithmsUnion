package dp;

import java.util.Arrays;

import base.Base;

public class BestTimeBuySellStock4 extends Base {

    static abstract class Solution {
        public abstract int maxProfit(int k, int[] prices);
    }

    static class Solution1 extends Solution {

        @Override
        public int maxProfit(int k, int[] p) {
            final int pLen = p.length;
            final int kLen = k + 1;

            if (pLen == 0) {
                return 0;
            }

            if (k >= pLen / 2) {
                int maxPre = 0;

                for (int i = 1; i < pLen; ++i) {
                    if (p[i] > p[i - 1]) {
                        maxPre += p[i] - p[i - 1];
                    }
                }
                return maxPre;
            }

            int[] dp = new int[kLen];
            int[] min = new int[kLen];

            Arrays.fill(min, p[0]);

            for (int i = 1; i < pLen; ++i) {
                for (int j = 1; j < kLen; ++j) {
                    min[j] = Math.min(min[j], p[i] - dp[j - 1]);
                    dp[j] = Math.max(dp[j], p[i] - min[j]);
                }
            }

            return dp[k];
        }

    }

    static class Solution2 extends Solution {

        @Override
        public int maxProfit(int k, int[] prices) {
            int n = prices.length;
            if (n <= 1)
                return 0;

            //if k >= n/2, then you can make maximum number of transactions.
            if (k >= n / 2) {
                int maxPro = 0;
                for (int i = 1; i < n; i++) {
                    if (prices[i] > prices[i - 1])
                        maxPro += prices[i] - prices[i - 1];
                }
                return maxPro;
            }

            int[][] dp = new int[k + 1][n];
            for (int i = 1; i <= k; i++) {
                int localMax = dp[i - 1][0] - prices[0];
                for (int j = 1; j < n; j++) {
                    dp[i][j] = Math.max(dp[i][j - 1], prices[j] + localMax);
                    localMax = Math.max(localMax, dp[i - 1][j] - prices[j]);
                }
            }
            return dp[k][n - 1];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 4, 1};
        int[] arr2 = new int[]{3, 2, 6, 5, 0, 3};
        int[] arr3 = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int[] arr4 = new int[]{1, 2, 3, 4, 5};
        int[] arr5 = new int[]{7, 6, 4, 3, 1};
        int k = 2;
        //println(new Solution1().maxProfit(k, arr1));
        //println(new Solution1().maxProfit(k, arr2));
        //println(new Solution1().maxProfit(k, arr3));
        println(new Solution1().maxProfit(k, arr4));
        //println(new Solution1().maxProfit(k, arr5));
    }

}
