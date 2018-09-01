package _1array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import _0base.Base;

public class FourSum extends Base {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        final int len = nums.length;
        if (len < 4) {
            return res;
        }

        Arrays.sort(nums);

        final int max = nums[len - 1];
        if (nums[0] * 4 > target || max * 4 < target) {//nums is too big or nums is too small
            return res;
        }

        for (int i = 0; i < len - 2; ++i) {
            int z = nums[i];
            if (i > 0 && nums[i - 1] == z) {
                continue;
            }
            if (z + 3 * max < target) {
                continue;
            }
            if (z * 4 > target) {// toobig
                break;
            }
            if (4 * z == target) {// z is the boundary
                if (i + 3 < len && z == nums[i + 3]) {
                    res.add(Arrays.asList(z, z, z, z));
                }
                break;
            }
            threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);
        }
        return res;
    }

    public static void threeSumForFourSum(int[] nums, int target, int low, int high, List<List<Integer>> res, int z1) {
        if (low + 1 >= high) {
            return;
        }

        final int max = nums[high];
        if (3 * nums[low] > target || 3 * max < target) {
            return;
        }

        for (int i = low; i < high - 1; ++i) {
            int z = nums[i];
            if (i > low && z == nums[i - 1]) {// avoid duplicate
                continue;
            }
            if (z + 2 * max < target) {//too small
                continue;
            }
            if (3 * z > target) {//too big
                break;
            }
            if (3 * z == target) {//z is the boundary
                if (i + 1 < high && nums[i + 2] == z) {
                    res.add(Arrays.asList(z1, z, z, z));
                }
                break;
            }
            twoSumForFourSum(nums, target - z, i + 1, high, res, z1, z);
        }
    }

    public static void twoSumForFourSum(int[] nums, int target, int lo, int hi, List<List<Integer>> res, int z1, int z) {
        if (lo >= hi) {
            return;
        }
        if (2 * nums[lo] > target || 2 * nums[hi] < target) {
            return;
        }

        int sum, x;
        while (lo < hi) {
            sum = nums[lo] + nums[hi];
            if (sum > target) {
                --hi;
            } else if (sum < target) {
                ++lo;
            } else {
                res.add(Arrays.asList(z1, z, nums[lo], nums[hi]));
                x = nums[lo];
                while (++lo < hi && x == nums[lo]) ; // avoid duplicate

                x = nums[hi];
                while (lo < --hi && x == nums[hi]) ; // avoid duplicate
            }
        }
        return;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        List<List<Integer>> list = fourSum(nums,0);
        println(list);
    }
}
