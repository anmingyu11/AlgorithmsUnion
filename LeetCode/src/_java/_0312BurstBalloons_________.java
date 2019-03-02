package _java;

import base.Base;

/**
 * Given n balloons, indexed from 0 to n-1.
 * Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons.
 * <p>
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * <p>
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 * <p>
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class _0312BurstBalloons_________ extends Base {

    private abstract static class Solution {
        public abstract int maxCoins(int[] nums);
    }

    /**
     * 0 <= n <= 500
     * 0 <= nums[i] <=100
     * 一些细节没有想清楚,这里给出一个discuss
     * <p>
     * 1. Balloons 0, 1, ..., n - 1
     * 2. What is the max value if we burst all of them [0, n - 1]?
     * 上面这两句看不懂还需要翻译请自杀.
     * 3. Let's first consider bursting [start, end]
     * 3. 首先我们考虑扎 [start,end] 这个区间
     * 4. Imagine we burst index i at the end
     * 4. 想象我们最后扎i(这个自底向上扎的思想是整个这个解法的核心,否则我们需要考虑先扎左面的区间还是先扎右面区间并且需要分别计算,问题会变得极为复杂)
     * 5. [start, ... i - 1, (i), i + 1 ... end]
     * 5. 给个扎爆i的分治递归区间
     * 6. Before the end, we already bursted [start, i - 1] and [i + 1, end]
     * 6. 在结算之前,我们已经扎爆了[start,i-1]和[i+1,end]这两个区间.
     * 7. Before the end, boundaries start - 1, i, end + 1 are always there
     * 7. 在结算之前,也就是说,现在这个分治递归区间里,只存在三个元素即start-1,i,end+1,因为在[start-1,i-1],[i+1,end+1]这两个区间已经提前被扎爆了
     * 8. This helps us calculate coins without worrying about details inside [start, i - 1] and [i + 1, end]
     * 8. 这个就是整个这个解法的最关键的部分,我们不需要考虑扎爆的先后顺序,不然如果普通的分治法,这个问题会变得极为复杂而且难以证明正确性(亲自体验)
     * 9. So the range can be divided into
     * 9. 所以说整个区间可以被分解成如下部分
     * 10. start - 1, maxCoin(start, i - 1), i, maxCoins(i + 1, end), end + 1
     * 最后结语,66666,How to do this??
     */
    // Runtime: 5 ms, faster than 99.05% of Java online submissions for Burst Balloons.
    // Memory Usage: 34.3 MB, less than 90.70% of Java online submissions for Burst Balloons.
    private static class Solution1 extends Solution {
        public int maxCoins(int[] nums) {
            final int n = nums.length;
            int[][] dp = new int[n][n];
            return maxCoins(nums, 0, n - 1, dp);
        }

        public int maxCoins(int[] nums, int lo, int hi, int[][] dp) {
            if (lo > hi) {
                return 0;
            }
            if (dp[lo][hi] != 0) {
                return dp[lo][hi];
            }
            int max = nums[lo];
            for (int i = lo; i <= hi; i++) {
                int left = maxCoins(nums, lo, i - 1, dp);
                int right = maxCoins(nums, i + 1, hi, dp);
                int res = left + get(nums, i) * get(nums, lo - 1) * get(nums, hi + 1) + right;
                max = Math.max(max, res);
            }
            dp[lo][hi] = max;
            return max;
        }

        public int get(int[] nums, int i) {
            if (i == -1 || i == nums.length) {
                return 1;
            }
            return nums[i];
        }

    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 8};

        Solution s = new Solution1();

        println(s.maxCoins(arr));
    }
}
