package dp;

import base.Base;

public class BestTimeBuySellStockWithCoolDown extends Base {

    static class Solution1 {

        public int maxProfit(int[] p) {
            final int n = p.length;
            if (n <= 1) {
                return 0;
            }
            int[] m = new int[n];

            m[0] = 0;

            for (int i = 0; i < 2; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    m[j] = Math.max(Math.max(p[j] - p[i], m[j - 1]), m[j]);
                }
            }

            for (int i = 2; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    m[j] = Math.max(Math.max(p[j] - p[i] + m[i - 2], m[j]), m[j - 1]);
                }
            }

            return m[n - 1];
        }

    }

    static class Solution2 {

        // for each of them we make an array, buy[n], sell[n] and rest[n].
        // buy[i] means before day i what is the maxProfit for any sequence end with buy.
        // sell[i] means before day i what is the maxProfit for any sequence end with sell.
        // rest[i] means before day i what is the maxProfit for any sequence end with rest.
        public int maxProfit(int[] p) {

            final int n = p.length;
            if (n < 2) {
                return 0;
            }

            int[] buy = new int[n];
            int[] sell = new int[n];
            buy[0] = -p[0];
            buy[1] = Math.max(buy[0], -p[1]);
            sell[0] = 0;
            sell[1] = Math.max(p[1] + buy[0], 0);
            for (int i = 2; i < n; ++i) {
                // 在第i天买，就减去买的价格， 和前一天买的价格相比谁更大。
                buy[i] = Math.max(sell[i - 2] - p[i], buy[i - 1]);
                // 在第i天卖，取在第i-1天买的最大值加上p[i]
                sell[i] = Math.max(buy[i - 1] + p[i], sell[i - 1]);
            }

            return sell[n - 1];
        }

    }

    static class Solution3 {

        public int maxProfit(int[] p) {

            final int n = p.length;
            if (n < 2) {
                return 0;
            }


            int buyPrev = Math.max(-p[1], -p[0]);
            int sellPrev2 = 0, sellPrev = Math.max(p[1] - p[0], sellPrev2);
            for (int i = 2; i < n; ++i) {
                buyPrev = Math.max(sellPrev2 - p[i], buyPrev);
                int sell = Math.max(p[i] + buyPrev, sellPrev);
                sellPrev2 = sellPrev;
                sellPrev = sell;
            }

            return sellPrev;
        }

    }

    public static void main(String[] args) {
        int[] prices1 = new int[]{1, 2, 3, 0, 2};
        int[] prices2 = new int[]{1, 2, 4};
        int[] prices3 = new int[]{2, 1, 4};
        int[] prices4 = new int[]{2, 1, 4, 5, 2, 9, 7};
        println(new Solution3().maxProfit(prices1));
        println(new Solution3().maxProfit(prices2));
        println(new Solution3().maxProfit(prices3));
        println(new Solution3().maxProfit(prices4));
    }
}
