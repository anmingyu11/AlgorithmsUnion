package dp;

import base.Base;

public class MaximumProductSubarray extends Base {

    public static int maxProduct(int[] nums) {
        int maxP = nums[0], len = nums.length;

        for (int i = 1, iMax = maxP, iMin = maxP; i < len; ++i) {

            if (nums[i] < 0) {
                int temp = iMax;
                iMax = iMin;
                iMin = temp;
            }

            iMax = Math.max(nums[i], nums[i] * iMax);
            iMin = Math.min(nums[i], nums[i] * iMin);

            maxP = Math.max(maxP, iMax);
        }

        return maxP;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, -2, -4, 5};
        println(maxProduct(nums));
    }
}
