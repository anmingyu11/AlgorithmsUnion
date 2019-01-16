package _java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import base.Base;

public class _0128LongestConsecutiveSequence extends Base {

    /**
     * 来好好把Uf复习一下。
     */
    private abstract static class Solution {
        public abstract int longestConsecutive(int[] nums);
    }

    // 排序可以,但是不是要求的 O(n)
    private static class Solution1 extends Solution {

        public int longestConsecutive(int[] nums) {
            if (nums.length == 1) {
                return 1;
            }
            Arrays.sort(nums);
            int len = 1;
            int max = 0;
            for (int i = 1; i < nums.length; ++i) {
                if (nums[i] - 1 == nums[i - 1]) {
                    len++;
                } else if (nums[i] == nums[i - 1]) ;
                else {
                    len = 1;
                }
                max = Math.max(max, len);
            }
            return max;
        }
    }

    // 类似Uf
    private static class Solution2 extends Solution {

        public int longestConsecutive(int[] nums) {
            if (nums.length <= 1) {
                return nums.length;
            }

            Set<Integer> set = new HashSet<>();
            for (int n : nums) {
                set.add(n);
            }

            int max = 1;

            int curMax = 1;
            for (int n : nums) {
                //这个剪枝很重要。
                if (set.contains(n + 1)) {
                    continue;
                }
                int cur = n - 1;
                while (set.contains(cur)) {
                    cur = cur - 1;
                    ++curMax;
                }
                max = Math.max(max, curMax);
                curMax = 1;
            }

            return max;
        }

    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{100, 4, 200, 1, 3, 2};
        int[] nums2 = new int[]{1, 2, 0, 1};

        Solution s = new Solution2();
        println(s.longestConsecutive(nums1));
        println(s.longestConsecutive(nums2));
    }
}
