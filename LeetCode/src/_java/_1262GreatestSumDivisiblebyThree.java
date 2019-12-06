package _java;

import java.util.Arrays;

import base.Base;

/**
 * Given an array nums of integers,
 * we need to find the maximum possible sum of elements
 * of the array such that it is divisible by three.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,6,5,1,8]
 * Output: 18
 * Explanation: Pick numbers 3, 6, 1 and 8
 * their sum is 18 (maximum sum divisible by 3).
 * Example 2:
 * <p>
 * Input: nums = [4]
 * Output: 0
 * Explanation: Since 4 is not divisible by 3,
 * do not pick any number.
 * Example 3:
 * <p>
 * Input: nums = [1,2,3,4,4]
 * Output: 12
 * Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12
 * (maximum sum divisible by 3).
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 */
public class _1262GreatestSumDivisiblebyThree extends Base {

    private static abstract class Solution {
        public abstract int maxSumDivThree(int[] nums);
    }

    /**
     * Runtime: 10 ms, faster than 52.74% of Java online submissions for Greatest Sum Divisible by Three.
     * Memory Usage: 42.5 MB, less than 100.00% of Java online submissions for Greatest Sum Divisible by Three.
     * ####################################
     * DP solution max Sum Div by Three
     * Expansion to k problem.
     * DP新世界.
     */
    private static class Solution1 extends Solution {

        public int maxSumDivThree(int[] nums) {
            return maxSumDivByK(nums, 3);
        }

        private int maxSumDivByK(int[] nums, int k) {
            int[] dp = new int[k];
            for (int e : nums) {
                int[] tmp = Arrays.copyOf(dp, k);
                for (int i = 0; i < k; ++i) {
                    dp[(tmp[i] + e) % k]
                            = Math.max(
                            dp[(tmp[i] + e) % k] // 当前余数(tmp[i]+e)%k最大的
                            , tmp[i] + e // 当前之和
                    );
                }
            }
            return dp[0];
        }
    }

    /**
     * Runtime: 5 ms, faster than 88.83% of Java online submissions for Greatest Sum Divisible by Three.
     * Memory Usage: 43.4 MB, less than 100.00% of Java online submissions for Greatest Sum Divisible by Three.
     * ###############################
     * math & greedy
     */
    private static class Solution2 extends Solution {

        public int maxSumDivThree(int[] nums) {
            // (res - n) % k == 0
            // res % k - n % k ==0
            // res % k == n % k
            int sum = 0;
            //int one = Integer.MAX_VALUE, two = Integer.MAX_VALUE;
            // 此处不能选最大值，会溢出,选择题干给出的最大值
            int one = 10001, two = 10001;
            for (int num : nums) {
                sum += num;
                if (num % 3 == 1) {
                    // ((a + b) % 3) == 2
                    //  (a % 3 + b % 3)
                    //  (a % 3 == 1) && (b % 3 == 1)
                    // 这个顺序非常重要！！！！
                    // 如果one和two调换顺序，则在第一次初始化one时，two = 2 * one
                    two = Math.min(two, num + one);
                    one = Math.min(one, num);
                    // continue;
                }
                if (num % 3 == 2) {
                    // (a + b) % 3 == 2
                    // a % 3 == 2 && b % 3 == 2
                    // (a + b) % 3 == 1
                    // 这个顺序非常重要！！！！
                    // 同理上面
                    one = Math.min(two + num, one);
                    two = Math.min(two, num);
                    // continue;
                }
            }
            if (sum % 3 == 0) {
                return sum;
            } else if (sum % 3 == 1) {
                return sum - one;
            } else {
                return sum - two;
            }
        }

    }

    public static void main(String[] args) {
        //  余数的性质 n % k = [0,k)
        Solution s = new Solution2();
        println(s.maxSumDivThree(new int[]{3, 6, 5, 1, 8})); // 18
        println(s.maxSumDivThree(new int[]{4})); // 0
        println(s.maxSumDivThree(new int[]{1, 2, 3, 4, 4})); // 12
        println(s.maxSumDivThree(new int[]{2, 6, 2, 2, 7})); // 15
    }
}
