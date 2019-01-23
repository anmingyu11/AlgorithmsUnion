package _java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import base.Base;

public class _0018FourSum extends Base {

    private abstract static class Solution {
        abstract List<List<Integer>> fourSum(int[] nums, int target);
    }

    // 3Sum和2Sum的混合体
    private static class Solution1 extends Solution {

        Map<Integer, Integer> map;

        List<List<Integer>> fourSum(int[] nums, int target) {
            final int len = nums.length;
            List<List<Integer>> res = new LinkedList<>();
            if (len < 4) {
                return res;
            }

            Arrays.sort(nums);
            map = new HashMap<>();
            for (int i = 0; i < len; ++i) {
                map.put(nums[i], i);
            }
            for (int i = 0; i <= len - 4; ++i) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int v = nums[i];
                List<int[]> threeR = threeSum(nums, i + 1, len - 1, target - v);
                for (int[] arr : threeR) {
                    res.add(Arrays.asList(arr[0], arr[1], arr[2], v));
                }
            }

            return res;
        }

        private List<int[]> threeSum(int[] nums, int lo, int hi, int target) {
            List<int[]> res = new LinkedList<>();

            for (int i = lo; i <= hi - 2; ++i) {
                if (i > lo && nums[i] == nums[i - 1]) {
                    continue;
                }
                int v = nums[i];
                List<int[]> twoR = twoSum(nums, i + 1, hi, target - v);
                for (int[] arr : twoR) {
                    res.add(new int[]{arr[0], arr[1], v});
                }
            }

            return res;
        }

        private List<int[]> twoSum(int[] nums, int lo, int hi, int target) {
            List<int[]> res = new LinkedList<>();

            for (int i = lo; i <= hi - 1; ++i) {
                if (i > lo && nums[i] == nums[i - 1]) {
                    continue;
                }
                Integer index = map.get(target - nums[i]);
                if (index != null && index > i && index <= hi) {
                    res.add(new int[]{nums[i], nums[index]});
                }
            }

            return res;
        }
    }

    // 魔改版
    private static class Solution2 extends Solution {

        List<List<Integer>> fourSum(int[] nums, int target) {
            final int len = nums.length;
            List<List<Integer>> res = new LinkedList<>();
            if (len < 4) {
                return res;
            }

            Arrays.sort(nums);
            // 不用map了 去掉一个常数的n
            // 剪枝 1
            if (4 * nums[0] > target || 4 * nums[len - 1] < target) {
                return res;
            }
            for (int i = 0; i <= len - 4; ++i) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int v = nums[i];
                // 剪枝2
                if (4 * v > target) {
                    break;
                }
                // 剪枝3
                if (4 * v == target) {
                    if (i + 3 < len && nums[i + 3] == v) {
                        res.add(Arrays.asList(v, v, v, v));
                    }
                    break;
                }
                List<int[]> threeR = threeSum(nums, i + 1, len - 1, target - v);
                for (int[] arr : threeR) {
                    res.add(Arrays.asList(arr[0], arr[1], arr[2], v));
                }
            }

            return res;
        }

        private List<int[]> threeSum(int[] nums, int lo, int hi, int target) {
            List<int[]> res = new LinkedList<>();

            if (3 * nums[lo] > target || 3 * nums[hi] < target) {
                return res;
            }

            for (int i = lo; i <= hi - 2; ++i) {
                if (i > lo && nums[i] == nums[i - 1]) {
                    continue;
                }
                int v = nums[i];

                if (v + 2 * nums[hi] < target) {
                    continue;
                }
                if (3 * v > target) {
                    break;
                }
                //这里应该再剪一下，多个参数，懒得整了。
                List<int[]> twoR = twoSum(nums, i + 1, hi, target - v);
                for (int[] arr : twoR) {
                    res.add(new int[]{arr[0], arr[1], v});
                }
            }

            return res;
        }

        //既然数组已经排序了，那么就直接夹逼就完事
        private List<int[]> twoSum(int[] nums, int lo, int hi, int target) {
            List<int[]> res = new LinkedList<>();

            if (2 * nums[lo] > target || 2 * nums[hi] < target) {
                return res;
            }
            int i = lo, j = hi;
            while (i < j) {
                int v = nums[i] + nums[j];
                if (v < target) {
                    ++i;
                } else if (v > target) {
                    --j;
                } else {
                    res.add(new int[]{nums[i++], nums[j--]});
                }
                while (j < hi && nums[j] == nums[j + 1]) {
                    --j;
                    if (j < lo) {
                        break;
                    }
                }
                while (i > lo && nums[i] == nums[i - 1]) {
                    ++i;
                    if (i > hi) {
                        break;
                    }
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{-2, -1, 0, 0, 1, 2};
        int target1 = 0;
        int[] arr2 = new int[]{-3, -2, -1, 0, 0, 1, 2, 3};
        int target2 = 0;
        int[] arr3 = new int[]{-1, -5, -5, -3, 2, 5, 0, 4};
        int target3 = -7;
        int[] arr4 = new int[]{-1, 0, -5, -2, -2, -4, 0, 1, -2};
        int target4 = -9;
        int[] arr5 = new int[]{0, 1, 5, 0, 1, 5, 5, -4};
        int target5 = 11;

        Solution s = new Solution2();
        println(s.fourSum(arr1, target1)); // [[-1,0,0,1],[-2,-1,1,2],[-2,0,0,2]]
        println(s.fourSum(arr2, target2)); // [[-3,-2,2,3],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],[-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        println(s.fourSum(arr3, target3)); // [[-5,-5,-1,4],[-5,-3,-1,2]]
        println(s.fourSum(arr4, target4)); // [[-5,-4,-1,1],[-5,-4,0,0],[-5,-2,-2,0],[-4,-2,-2,-1]]
        println(s.fourSum(arr5, target5)); // [[-5,-4,-1,1],[-5,-4,0,0],[-5,-2,-2,0],[-4,-2,-2,-1]]
    }

}
