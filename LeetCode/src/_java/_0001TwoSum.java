package _java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import base.Base;

public class _0001TwoSum extends Base {

    private abstract static class Solution {
        public abstract int[] twoSum(int[] nums, int target);
    }

    /**
     * 测试用例里有负值，所以不能用桶。
     */
    private static class Solution1 extends Solution {

        @Override
        public int[] twoSum(int[] nums, int target) {
            if (target < 0) {
                throw new NullPointerException("loha");
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; ++i) {
                int n = nums[i];
                int remain = target - n;
                Integer j = map.get(remain);
                if (j != null && j != i) {
                    return new int[]{i, j};
                }
                map.put(n, i);
            }
            return new int[0];
        }

    }

    /**
     * 这个是错的，只有排序数组才能这么干。
     */
    private static class Solution2 extends Solution {

        private int binarySearch(int[] nums, int target) {
            int lo = 0, hi = nums.length;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] < target) {
                    lo = mid + 1;
                } else if (nums[mid] > target) {
                    hi = mid - 1;
                } else {
                    return mid;
                }
            }

            return -1;
        }

        @Override
        public int[] twoSum(int[] nums, int target) {
            if (nums.length <= 1) {
                return new int[0];
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; ++i) {
                int n = nums[i];
                int j = binarySearch(nums, target - n);
                if (j >= 0 && j != i) {
                    return new int[]{i, j};
                }
            }
            return new int[0];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 7, 11, 15};
        int nums1Target1 = 9;
        int nums1Target2 = 14;
        int[] nums2 = new int[]{3, 2, 4};
        int nums2Target1 = 6;
        Solution s = new Solution2();
        //printArr(s.twoSum(nums1, nums1Target1));
        //printArr(s.twoSum(nums1, nums1Target2));
        printArr(s.twoSum(nums2, nums2Target1));
    }
}
