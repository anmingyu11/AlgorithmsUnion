package _java;

import java.util.HashMap;
import java.util.Map;

import base.Base;

public class _0167TwoSum2 extends Base {

    private abstract static class Solution {
        public abstract int[] twoSum(int[] nums, int target);
    }

    // HashMap
    private static class Solution1 extends Solution {

        Map<Integer, Integer> map = new HashMap<>();

        @Override
        public int[] twoSum(int[] nums, int target) {
            final int len = nums.length;
            if (len < 2) {
                return new int[0];
            }
            for (int i = 0; i < len; ++i) {
                map.put(nums[i], i);
            }
            for (int i = 0; i < len; ++i) {
                Integer index = map.get(target - nums[i]);
                if (index != null) {
                    return new int[]{i + 1, index + 1};
                }
            }
            return new int[0];
        }

    }

    // 二分
    private static class Solution2 extends Solution {

        private int binarySearch(int[] nums, int lo, int hi, int target) {
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] < target) {
                    lo = mid + 1;
                } else if (nums[mid] > target) {
                    hi = mid - 1;
                } else {
                    return mid;
                }
            }
            return -lo;
        }

        public int[] twoSum(int[] nums, int target) {
            final int len = nums.length;

            for (int i = 0; i < len - 1; ++i) {
                int b = binarySearch(nums, i + 1, len - 1, target - nums[i]);
                if (b > 0) {
                    return new int[]{i + 1, b + 1};
                }
            }

            return new int[0];
        }
    }

    // 双指针夹逼
    private static class Solution3 extends Solution {

        @Override
        public int[] twoSum(int[] nums, int target) {
            final int len = nums.length;

            int i = 0, j = len - 1;
            while (i <= j) {
                if (nums[i] + nums[j] > target) {
                    --j;
                } else if (nums[i] + nums[j] < target) {
                    ++i;
                } else {
                    return new int[]{i + 1, j + 1};
                }
            }

            return new int[0];
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 7, 11, 15};
        int target1 = 9;

        int[] arr2 = new int[]{-1, 0};
        int target2 = -1;

        Solution s = new Solution3();

        //printArr(s.twoSum(arr1, target1));
        printArr(s.twoSum(arr2, target2));
    }
}
