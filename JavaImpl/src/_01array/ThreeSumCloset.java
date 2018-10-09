package _01array;

import java.util.Arrays;

import _00base.Base;

public class ThreeSumCloset extends Base {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int result = 0;
        int minGap = Integer.MAX_VALUE;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; ++i) {
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int gap = Math.abs(sum - target);
                if (gap < minGap) {
                    result = sum;
                    minGap = gap;
                }
                if (sum < target) {
                    ++j;
                } else if (sum > target) {
                    --k;
                } else {
                    return result;
                }
            }
        }

        return result;
    }
}
