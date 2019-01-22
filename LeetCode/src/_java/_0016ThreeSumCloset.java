package _java;

import java.util.Arrays;

import base.Base;

public class _0016ThreeSumCloset extends Base {

    private abstract static class Solution {

        int binarySearch(int[] nums, int lo, int hi, int target) {
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

        abstract int threeSumClosest(int[] nums, int target);
    }

    // 把二分查找写错了，草，撞了五次墙。36ms beats 9.68%
    private static class Solution1 extends Solution {

        int threeSumClosest(int[] nums, int target) {
            final int len = nums.length;
            if (len < 3) {
                int sum = 0;
                for (int i = 0; i < len; ++i) {
                    sum += nums[i];
                }
                return sum;
            }

            Arrays.sort(nums);

            int closet = nums[0] + nums[1] + nums[2];
            for (int i = 0; i < len - 2; ++i) {
                for (int j = i + 1; j < len - 1; ++j) {
                    int sum = nums[i] + nums[j];
                    int b = binarySearch(nums, j + 1, len - 1, target - sum);
                    if (b > 0) {
                        return target;
                    } else {
                        b = -b;
                        b = b == len ? b - 1 : b;
                        int sum1 = sum + nums[b];
                        if (Math.abs(target - sum1) < Math.abs(target - closet)) {
                            closet = sum1;
                        }
                        if (b + 1 < len) {
                            int sum2 = sum + nums[b + 1];
                            if (Math.abs(target - sum2) < Math.abs(target - closet)) {
                                closet = sum2;
                            }
                        }
                        if (b - 1 > j) {
                            int sum3 = sum + nums[b - 1];
                            if (Math.abs(target - sum3) < Math.abs(target - closet)) {
                                closet = sum3;
                            }
                        }
                    }
                }
            }

            return closet;
        }

    }

    private static class Solution2 extends Solution {

        int threeSumClosest(int[] nums, int target) {
            final int len = nums.length;
            if (len < 3) {
                int sum = 0;
                for (int i = 0; i < len; ++i) {
                    sum += nums[i];
                }
                return sum;
            }

            Arrays.sort(nums);
            int closet = nums[0] + nums[1] + nums[2];
            for (int i = 0; i < len - 2; ++i) {
                int j = i + 1, k = len - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(closet - target) > Math.abs(sum - target)) {
                        closet = sum;
                    }
                    if (sum < target) {
                        ++j;
                    } else if (sum > target) {
                        --k;
                    } else {
                        return target;
                    }
                }
            }

            return closet;
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{-1, 2, 1, -4};
        int target1 = -5;
        int[] arr2 = new int[]{-1, -3, 3, -5};
        int target2 = 50;
        int[] arr3 = new int[]{1, 2, 5, 10, 11};
        int target3 = 12;
        int[] arr4 = new int[]{1, 6, 9, 14, 16, 70};
        int target4 = 81;

        Solution s = new Solution2();
        println(s.threeSumClosest(arr1, target1)); // -4
        println(s.threeSumClosest(arr2, target2)); // -1
        println(s.threeSumClosest(arr3, target3)); // 13
        println(s.threeSumClosest(arr4, target4)); // 80
    }

}
