package dp;

import java.util.Arrays;

import base.Base;

public class BestTimeBuySellStock3 extends Base {

    /**
     * dp[k][i] = max(dp[k][i-1],dp[k-1][j-1] + price[i] -prices[j]);
     */

    abstract static class Solution {

        public abstract int maxProfit(int[] p);

    }

    static class Solution1 extends Solution {

        @Override
        public int maxProfit(int[] p) {
            final int pLen = p.length;
            if (pLen == 0) {
                return 0;
            }

            int[][] dp = new int[3][pLen];

            // dp[k][i] = max(dp[k][i-1], dp[k - 1][j - 1] + p[i] - p[j]);
            for (int k = 1; k < 3; ++k) {
                for (int i = 1; i < pLen; ++i) {
                    int max = 0;
                    for (int j = 0; j <= i; ++j) {
                        max = Math.max(dp[k - 1][j] + p[i] - p[j], max);
                    }
                    dp[k][i] = Math.max(dp[k][i - 1], max);
                }
            }

            return dp[2][pLen - 1];
        }

    }

    static class Solution2 extends Solution {

        @Override
        public int maxProfit(int[] p) {
            final int pLen = p.length;
            if (pLen == 0) {
                return 0;
            }

            int[][] dp = new int[3][pLen];

            for (int k = 1; k < 3; ++k) {
                for (int i = 1; i < pLen; ++i) {
                    int min = p[0];
                    for (int j = 1; j <= i; ++j) {
                        min = Math.min(min, p[j] - dp[k - 1][j]);
                    }
                    dp[k][i] = Math.max(dp[k][i - 1], p[i] - min);
                }
            }

            return dp[2][pLen - 1];
        }
    }

    static class Solution3 extends Solution {

        @Override
        public int maxProfit(int[] p) {
            final int pLen = p.length;
            if (pLen == 0) {
                return 0;
            }

            int[][] dp = new int[3][pLen];

            for (int k = 1; k < 3; ++k) {
                int min = p[0];
                for (int i = 1; i < pLen; ++i) {
                    min = Math.min(min, p[i] - dp[k - 1][i - 1]);
                    dp[k][i] = Math.max(dp[k][i - 1], p[i] - min);
                }
            }

            return dp[2][pLen - 1];
        }
    }

    static class Solution4 extends Solution {

        @Override
        public int maxProfit(int[] p) {
            final int pLen = p.length;
            if (pLen == 0) {
                return 0;
            }

            int[][] dp = new int[3][pLen];
            int[] min = new int[3];

            Arrays.fill(min, p[0]);
            for (int i = 1; i < pLen; ++i) {
                for (int k = 1; k < 3; ++k) {
                    min[k] = Math.min(p[i] - dp[k - 1][i - 1], min[k]);
                    dp[k][i] = Math.max(dp[k][i - 1], p[i] - min[k]);
                }
            }

            return dp[2][pLen - 1];
        }

    }

    static class Solution5 extends Solution {

        @Override
        public int maxProfit(int[] p) {
            final int pLen = p.length;
            if (pLen == 0) {
                return 0;
            }

            int[] dp = new int[3];
            int[] min = new int[3];

            Arrays.fill(min, p[0]);

            for (int i = 1; i < pLen; ++i) {
                for (int k = 1; k < 3; ++k) {
                    min[k] = Math.min(min[k], p[i] - dp[k - 1]);
                    dp[k] = Math.max(dp[k], p[i] - min[k]);
                }
            }

            return dp[2];
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int[] arr2 = new int[]{1, 2, 3, 4, 5};
        int[] arr3 = new int[]{7, 6, 4, 3, 1};

        println(new Solution5().maxProfit(arr1));
        println(new Solution5().maxProfit(arr2));
        println(new Solution5().maxProfit(arr3));
    }

}
