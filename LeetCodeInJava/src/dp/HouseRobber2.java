package dp;

import base.Base;

public class HouseRobber2 extends Base {

    // Mine
    static class Solution1 {

        public int rob(int[] nums) {
            final int n = nums.length;
            if (n == 0) {
                return 0;
            } else if (n == 1) {
                return nums[0];
            } else if (n == 2) {
                return Math.max(nums[0], nums[1]);
            }

            int[] dpC0 = new int[n];
            dpC0[0] = nums[0];
            dpC0[1] = Math.max(nums[0], nums[1]);
            int[] dpC1 = new int[n];
            dpC1[0] = 0;
            dpC1[1] = nums[1];

            for (int i = 2; i < n - 1; ++i) {
                dpC1[i] = Math.max(dpC1[i - 1], dpC1[i - 2] + nums[i]);
                dpC0[i] = Math.max(dpC0[i - 1], dpC0[i - 2] + nums[i]);
            }
            dpC0[n - 1] = dpC0[n - 2];
            dpC1[n - 1] = Math.max(dpC1[n - 2], dpC1[n - 3] + nums[n - 1]);

            return Math.max(dpC0[n - 1], dpC1[n - 1]);
        }

    }

    static class Solution2 {

        public int rob(int[] nums) {
            final int n = nums.length;
            if (n == 1) {
                return nums[0];
            }
            return Math.max(rob(nums, 0, n - 1), rob(nums, 1, n));
        }

        private int rob(int[] nums, int lo, int hi) {
            int prev = 0;
            int prev2 = 0;

            for (int i = lo; i < hi; ++i) {
                int tmp = prev;
                prev = Math.max(prev2 + nums[i], prev);
                prev2 = tmp;
            }

            return prev;
        }

    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 3, 2};
        int[] nums2 = new int[]{1, 2, 3, 1};
        int[] nums3 = new int[]{2, 7, 9, 3, 1};

        //println(new Solution2().rob(nums1));
        //println(new Solution2().rob(nums2));
        println(new Solution2().rob(nums3));
    }

}
