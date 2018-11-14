package greedy;

import java.util.Arrays;

import base.Base;

public class BestTimeToBuyAndSellStock2 extends Base {

    //bruteForce
    static class Solution1 {

        public static int maxProfit(int[] prices) {
            return calculate(prices, 0);
        }

        public static int calculate(int prices[], int s) {
            final int len = prices.length;
            if (s >= len) {
                return 0;
            }

            int max = 0;
            for (int start = s; start < len; ++start) {
                int maxProfit = 0;
                for (int i = start + 1; i < len; ++i) {
                    if (prices[start] < prices[i]) {
                        maxProfit = Math.max(calculate(prices, i + 1) + prices[i] - prices[start], maxProfit);
                    }
                }
                max = Math.max(max, maxProfit);
            }

            return max;
        }

    }

    //Peak Valley Approach
    static class Solution2 {
        public static int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            final int len = prices.length - 1;
            int i = 0;
            int peak = prices[0];
            int valley = prices[0];
            int maxProfit = 0;
            while (i < len) {
                while (i < len && prices[i + 1] <= prices[i]) {
                    ++i;
                }
                valley = prices[i];
                while (i < len && prices[i + 1] >= prices[i]) {
                    ++i;
                }
                peak = prices[i];
                maxProfit += peak - valley;
            }

            return maxProfit;
        }
    }

    //Greedy
    static class Solution3 {
        public static int maxProfit(int[] prices) {
            final int len = prices.length;
            int maxProfit = 0;
            for (int i = 0; i < len - 1; ++i) {
                if (prices[i] < prices[i + 1]) {
                    maxProfit += prices[i + 1] - prices[i];
                }
            }
            return maxProfit;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {7, 1, 5, 3, 6, 4};
        //println(Arrays.toString(nums1) + " correct is : 7 " + " res : " + Solution1.maxProfit(nums1));
        //println(Arrays.toString(nums1) + " correct is : 7 " + " res : " + Solution2.maxProfit(nums1));
        println(Arrays.toString(nums1) + " correct is : 7 " + " res : " + Solution3.maxProfit(nums1));
        int[] nums2 = {1, 2, 3, 4, 5};
        //println(Arrays.toString(nums2) + " correct is : 4 " + " res : " + Solution1.maxProfit(nums2));
        //println(Arrays.toString(nums2) + " correct is : 4 " + " res : " + Solution2.maxProfit(nums2));
        println(Arrays.toString(nums2) + " correct is : 4 " + " res : " + Solution3.maxProfit(nums2));
        int[] nums3 = {7, 6, 4, 3, 1};
        //println(Arrays.toString(nums3) + " correct is : 0 " + " res : " + Solution1.maxProfit(nums3));
        //println(Arrays.toString(nums3) + " correct is : 0 " + " res : " + Solution2.maxProfit(nums3));
        println(Arrays.toString(nums3) + " correct is : 0 " + " res : " + Solution3.maxProfit(nums3));
    }
}
