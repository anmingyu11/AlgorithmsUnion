package _java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import base.Base;

public class _0015ThreeSum extends Base {

    private abstract static class Solution {
        public abstract List<List<Integer>> threeSum(int[] nums);

        public int binarySearch(int[] nums, int target, int lo, int hi) {
            // 细节1
            // 细节2 hi == nums.length - 1
            while (lo <= hi) {
                final int mid = (lo + hi) / 2;
                final int val = nums[mid];
                if (val < target) {
                    lo = mid + 1;
                } else if (val > target) {
                    hi = mid - 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }

        public int binarySearch2(int[] nums, int target, int fromIndex, int toIndex) {
            int mid = 0;
            //int lo = fromIndex, hi = toIndex - 1;
            int lo = fromIndex, hi = toIndex;
            while (lo <= hi) {
                // 无符号右移  相当与除2,因为lo和hi不可能为负数
                mid = (lo + hi) >>> 1;
                if (nums[mid] < target) {
                    // 重要的细节，如果找不到的时候，最后一个必定会产生如下情况
                    // 例如 lo+1 == hi 也就是 mid =(lo + 1 + lo) / 2; 因为整数截断，所以mid = lo 因为nums[lo] <  target 所以最后会落到lo 上
                    // 即 lo = mid + 1;
                    lo = mid + 1;
                } else if (nums[mid] > target) {
                    hi = mid - 1;
                } else {
                    //println("lo : " + lo + " hi : " + hi + " mid : " + mid);
                    return mid;
                }
            }

            //println("lo : " + lo + " hi : " + hi + " mid : " + mid);
            return -(lo);
        }
    }

    // 737 ms 常规做法
    private static class Solution1 extends Solution {

        @Override
        public List<List<Integer>> threeSum(int[] nums) {
            if (nums.length < 3) {
                return new LinkedList<>();
            }
            Arrays.sort(nums);
            Set<List<Integer>> res = new HashSet<>();
            for (int i = 0; i <= nums.length - 3; ++i) {
                for (int j = i + 1; j <= nums.length - 2; ++j) {
                    int val1 = nums[i], val2 = nums[j];
                    int remain = -(val1 + val2);
                    int binI = binarySearch(nums, remain, j + 1, nums.length - 1);
                    if (binI >= 0) {
                        res.add(Arrays.asList(val1, val2, nums[binI]));
                    }
                }
            }
            return new ArrayList<>(res);
        }
    }

    // 516ms 上下界剪枝
    private static class Solution2 extends Solution {

        public List<List<Integer>> threeSum(int[] nums) {
            if (nums.length < 3) {
                return new LinkedList<>();
            }
            Arrays.sort(nums);
            Set<List<Integer>> res = new HashSet<>();
            for (int i = 0; i <= nums.length - 3; ++i) {
                for (int j = i + 1; j <= nums.length - 2; ++j) {
                    final int valI = nums[i], valJ = nums[j], remain = -(nums[i] + nums[j]);
                    int lo = j + 1, hi = nums.length - 1;
                    //两端剪枝
                    if (nums[lo] > remain) {
                        continue;
                    } else if (nums[hi] < remain) {
                        continue;
                    }
                    int b = binarySearch(nums, remain, lo, hi);
                    if (b >= 0) {
                        res.add(Arrays.asList(valI, valJ, nums[b]));
                    }
                }
            }
            return new LinkedList<>(res);
        }
    }

    // 166ms 去重 + 剪枝。
    // 二分 O(n*n*logN)
    private static class Solution3 extends Solution {

        @Override
        public List<List<Integer>> threeSum(int[] nums) {
            final int len = nums.length;
            if (len < 3) {
                return new LinkedList<>();
            }
            List<List<Integer>> ans = new LinkedList<>();
            Arrays.sort(nums);

            for (int i = 0; i <= len - 3; ++i) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                for (int j = i + 1; j <= len - 2; ++j) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }
                    final int valI = nums[i], valJ = nums[j], remain = -(valI + valJ);
                    int lo = j + 1, hi = len - 1;
                    if (nums[lo] > remain) {
                        continue;
                    } else if (nums[hi] < remain) {
                        continue;
                    }
                    int b = binarySearch(nums, remain, lo, hi);
                    if (b >= 0) {
                        ans.add(Arrays.asList(valI, valJ, nums[b]));
                    }
                }
            }

            return ans;
        }
    }

    //O(n n) 夹逼法
    private static class Solution4 extends Solution {

        public List<List<Integer>> threeSum(int[] nums) {
            final int len = nums.length;
            final List<List<Integer>> res = new LinkedList<>();
            if (len < 3) {
                return res;
            }

            Arrays.sort(nums);
            for (int i = 0; i <= len - 3; ++i) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int lo = i + 1, hi = len - 1, sum = -nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] < sum) {
                        ++lo;
                    } else if (nums[lo] + nums[hi] > sum) {
                        --hi;
                    } else {
                        res.add(Arrays.asList(nums[lo], nums[hi], nums[i]));
                        //Remove duplicate
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            ++lo;
                        }
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            --hi;
                        }
                        ++lo;
                        --hi;
                    }
                }
            }

            return res;
        }

    }

    /**
     * 好尼玛复杂，弃坑了。
     */
    private static class Solution5 extends Solution {

        @Override
        public List<List<Integer>> threeSum(int[] nums) {
            final int len = nums.length;
            final List<List<Integer>> res = new LinkedList<>();
            if (len < 3) {
                return res;
            }

            Arrays.sort(nums);
            for (int i = 0; i <= len - 3; ++i) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int lo = i + 1, hi = len - 1, sum = -nums[i];
                while (lo < hi) {
                    int b = binarySearch2(nums, sum + nums[lo], lo + 1, hi);
                    if (b >= 0) {
                        res.add(Arrays.asList(nums[lo], nums[b], nums[i]));
                        hi = b;
                        //RemoveDuplicate
                        //先去掉hi的重复部分
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            --hi;
                        }
                        //去掉lo的重复部分
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            ++lo;
                        }
                        ++lo;
                        --hi;
                    } else {
                        //没找到,-b刚好在大于目标的值;
                        hi = -b;
                        if (hi == len) {
                            --hi;
                        }
                        if (nums[lo] + nums[hi] < sum) {
                            ++lo;
                            while (lo < hi && nums[lo] == nums[lo - 1]) {
                                ++lo;
                            }
                        } else {
                            --hi;
                            while (lo < hi && nums[hi] == nums[hi + 1]) {
                                --hi;
                            }
                        }
                    }
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{-1, 0, 1, 2, -1, -4};
        int[] arr2 = new int[]{0, 0, 0, 0};
        int[] arr3 = new int[]{3, -2, 1, 0};

        Solution s = new Solution5();

        println(s.threeSum(arr1));
        println(s.threeSum(arr2));
        println(s.threeSum(arr3));

        testBinSearch();
    }

    private static void testBinSearch() {
        int[] arr = new int[]{-4, -2, 0, 1, 1, 1, 1, 2, 2, 2, 2, 4, 6};
        //first 1 is 3 first 2 is 7
        Solution s = new Solution() {
            @Override
            public List<List<Integer>> threeSum(int[] nums) {
                return null;
            }
        };
        final int len = arr.length;
        //int index1 = s.binarySearch2(arr, -4, 0, len);
        //int index2 = s.binarySearch2(arr, 6, 0, len);
        //int index3 = s.binarySearch2(arr, 0, 0, len);
        //int index4 = s.binarySearch2(arr, 5, 0, len);
        //s.binarySearch2(arr, -3, 0, len);
        //s.binarySearch2(arr, -1, 0, len);
        //s.binarySearch2(arr, 3, 0, len);
        //s.binarySearch2(arr, 5, 0, len);
        //println("index1 : " + "index : " + index1 + " val: " + arr[index1]);
        //println("index2 : " + "index : " + index2 + " val: " + arr[index2]);
        //println("index3 : " + "index : " + index3 + " val: " + arr[index3]);
        //println("index4 : " + "index : " + index4 + " val: " + (index4 < 0 ? index4 : arr[index4]));
        println(s.binarySearch2(arr, -1, 0, len));
    }
}
