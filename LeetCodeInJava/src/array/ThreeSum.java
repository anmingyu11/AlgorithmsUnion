package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base.Base;

public class ThreeSum extends Base {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        final int target = 0;
        println(Arrays.toString(nums));
        for (int i = 0; i < nums.length - 2; ++i) {
            if (nums[i] > target) {//cut the nums[i] > 0
                return result;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;//remove the duplicate
            }

            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] > target) {
                    break;
                } else if (nums[j] + nums[k] < target) {
                    ++j;
                    while (j < k && nums[j] == nums[j - 1]) ++j;
                } else if (nums[i] + nums[j] + nums[k] < target) {
                    ++j;
                    while (j < k && nums[j] == nums[j - 1]) ++j;
                } else if (nums[i] + nums[j] + nums[k] > target) {
                    --k;
                    while (j < k && nums[k] == nums[k + 1]) --k;
                } else if (nums[i] + nums[j] + nums[k] == target) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    ++j;
                    --k;
                    while (j < k && nums[j] == nums[j - 1]) ++j;
                    while (j < k && nums[k] == nums[k + 1]) --k;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{0, -4, -1, -4, -2, -3, 2};
        int[] nums2 = new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
        List<List<Integer>> r = threeSum(nums2);
        println(r);
    }
}
