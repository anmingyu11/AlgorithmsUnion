package dp;

import java.util.Arrays;

import base.Base;

public class LongestIncreasingSubsequence extends Base {
    static class Solution1 {
        public static int lengthOfLIS(int[] nums) {
            final int len = nums.length;
            if (len == 0) {
                return 0;
            }
            int maxLen = 1;
            int[] dp = new int[len];
            dp[0] = 1;
            for (int i = 1; i < len; ++i) {
                int maxVal = 0;
                for (int j = 0; j < i; ++j) {
                    if (nums[j] < nums[i]) {
                        maxVal = Math.max(dp[j], maxVal);
                    }
                }
                dp[i] = maxVal + 1;
                maxLen = Math.max(maxLen, dp[i]);
            }

            return maxLen;
        }
    }

    static class Solution2 {

        public static int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int len = 0;

            for (int num : nums) {
                // 确定元素应在的正确位置，当返回的查找是负数，说明有一个新的大于所有dp数组内的元素的元素，
                // 此时可以将最大元素插入并扩大len,也就是最长子序列的长度,更加高级聪明的dp，非常精妙。
                int i = Arrays.binarySearch(dp, 0, len, num);
                if (i < 0) {
                    // 处理二分查找返回的插入点
                    i = -(i + 1);
                }

                dp[i] = num;
                if (i == len) {
                    ++len;
                }
            }

            return len;
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        println(Solution1.lengthOfLIS(nums));
        println(Solution2.lengthOfLIS(nums));
    }
}