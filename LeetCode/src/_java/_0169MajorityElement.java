package _java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import base.Base;

public class _0169MajorityElement extends Base {

    private abstract static class Solution {
        public abstract int majorityElement(int[] nums);
    }

    // 排序
    // 4ms 82.45%
    private static class Solution1 extends Solution {

        public int majorityElement(int[] nums) {
            final int n = nums.length;
            Arrays.sort(nums);
            return nums[n / 2];
        }

    }

    // HashMap
    // Runtime: 16 ms, faster than 39.18% of Java online submissions for Majority Element.
    // Memory Usage: 32.6 MB, less than 30.54% of Java online submissions for Majority Element.
    private static class Solution2 extends Solution {

        public int majorityElement(int[] nums) {
            final int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>(n / 2);
            int max = 0, cur = nums[0];
            for (int i = 0; i < n; ++i) {
                Integer time = map.get(nums[i]);
                if (time == null) {
                    time = 1;
                    map.put(nums[i], time);
                } else {
                    map.put(nums[i], ++time);
                }

                if (time > max) {
                    max = time;
                    cur = nums[i];
                }
                // Runtime: 14 ms, faster than 41.47% of Java online submissions for Majority Element.
                // Memory Usage: 32.9 MB, less than 22.03% of Java online submissions for Majority Element.
                if (max > n / 2) {
                    return cur;
                }

            }
            return cur;
        }

    }

    // Partition ,原地
    private static class Solution3 extends Solution {

        public int majorityElement(int[] nums) {
            mid(nums);
            return nums[nums.length / 2];
        }

        private void sort(int[] nums) {
            sort(nums, 0, nums.length - 1);
            printArr(nums);
        }

        private void sort(int[] nums, int lo, int hi) {
            if (lo >= hi) {
                return;
            }
            int p = partition2(nums, lo, hi);
            sort(nums, lo, p - 1);
            sort(nums, p + 1, hi);
        }

        private void mid(int[] nums) {
            final int n = nums.length;
            final int mid = n / 2;
            int lo = 0, hi = n - 1;
            int p = partition1(nums, lo, hi);
            while (p != mid) {
                if (p < mid) {
                    lo = p + 1;
                    p = partition1(nums, lo, hi);
                } else if (p > mid) {
                    hi = p - 1;
                    p = partition1(nums, lo, hi);
                } else {
                    return;
                }
            }
        }

        private int partition1(int[] nums, int lo, int hi) {
            final int p = lo;
            final int v = nums[lo];
            while (lo < hi) {
                while (lo < hi && nums[hi] >= v) {
                    --hi;
                }
                nums[lo] = nums[hi];
                while (lo < hi && nums[lo] <= v) {
                    ++lo;
                }
                nums[hi] = nums[lo];
            }
            nums[hi] = v;
            return hi;
        }

        // 貌似2要比1快3-4倍
        private int partition2(int[] nums, int lo, int hi) {
            final int p = lo;
            final int v = nums[lo];
            while (lo < hi) {
                while (nums[lo] <= v) {
                    ++lo;
                    if (lo == hi) {
                        break;
                    }
                }
                while (nums[hi] >= v) {
                    --hi;
                    if (hi < lo) {
                        break;
                    }
                }
                if (hi < lo) {
                    break;
                }
                swap(nums, lo, hi);
            }
            swap(nums, p, hi);
            return hi;
        }

        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }

    }

    // 摩尔投票法
    private static class Solution4 extends Solution {

        public int majorityElement(int[] nums) {
            int res = 0, count = 0;
            for (int n : nums) {
                if (count == 0) {
                    res = n;
                }
                if (res == n) {
                    ++count;
                } else {
                    --count;
                }
            }
            return res;
        }

    }

    // 分治法 O(nlgn)
    // Runtime: 3 ms, faster than 100.00% of Java online submissions for Majority Element.
    // Memory Usage: 39.6 MB, less than 93.04% of Java online submissions for Majority Element.
    /**
     * Here, we apply a classical divide & conquer approach that recurses on the left and right halves of an array until an answer can be trivially
     * achieved for a length-1 array.
     * Note that because actually passing copies of subarrays costs time and space,
     * we instead pass lo and hi indices that describe the relevant slice of the overall array.
     * In this case, the majority element for a length-1 slice is trivially its only element,
     * so the recursion stops there.
     * If the current slice is longer than length-1, we must combine the answers for the slice's left and right halves.
     * If they agree on the majority element, then the majority element for the overall slice is obviously the same1.
     * If they disagree, only one of them can be "right",
     * so we need to count the occurrences of the left and right majority elements to determine which subslice's answer is globally correct.
     * The overall answer for the array is thus the majority element between indices 0 and nn.
     */
    private static class Solution5 extends Solution {

        /**
         * 计算num在lo和hi之间出现的频率
         *
         * @param nums
         * @param num
         * @param lo
         * @param hi
         * @return
         */
        private int countInRange(int[] nums, int num, int lo, int hi) {
            int count = 0;
            for (int i = lo; i <= hi; i++) {
                if (nums[i] == num) {
                    ++count;
                }
            }
            return count;
        }

        /**
         * 当传入数组区间是1,直接返回nums[lo],因为这个是最主要的元素
         * 折半在左右数组里查找,分别计算left和right的majority element
         * 如果两边结果一样,直接返回.
         * 如果不一样,从这两个数组中数一遍谁大.
         *
         * @param nums
         * @param lo
         * @param hi
         * @return
         */
        private int majorityElementRec(int[] nums, int lo, int hi) {
            if (lo == hi) {
                return nums[lo];
            }
            int mid = (hi - lo) / 2 + lo;
            int left = majorityElementRec(nums, lo, mid);
            int right = majorityElementRec(nums, mid + 1, hi);
            if (left == right) {
                return left;
            }
            int leftCount = countInRange(nums, left, lo, hi);
            int rightCount = countInRange(nums, right, lo, hi);
            return leftCount > rightCount ? left : right;
        }

        public int majorityElement(int[] nums) {
            return majorityElementRec(nums, 0, nums.length - 1);
        }

    }

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 3};
        int[] arr2 = {2, 2, 1, 1, 1, 2, 2};
        int[] arr3 = {3, 3, 3, 3, 2, 2, 1};
        int[] arr4 = {1, 2, 2, 3, 4, 4, 4, 4};


        Solution s = new Solution4();

        println(s.majorityElement(arr3)); // 3
        println(s.majorityElement(arr1)); // 3
        println(s.majorityElement(arr2)); // 2
        println(s.majorityElement(arr4)); // 4
    }

    private static void testQuickSort() {
        int[] arr1 = new int[]{3, 2, 1, 5, 6, 4};
        int[] arr2 = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        int[] arr3 = new int[]{99, 99};
        int[] arr4 = new int[]{1};
        int[] arr5 = new int[]{7, 6, 5, 4, 3, 2, 1};
        int[] arr6 = new int[]{-1, 2, 0};
        int[] arr7 = new int[]{1, 2, 3, 4, 5};
        int[] arr8 = new int[]{1, 2, 5, 4, 3};

        Solution3 s = new Solution3();

        s.sort(arr1);
        s.sort(arr2);
        s.sort(arr3);
        s.sort(arr4);
        s.sort(arr5);
        s.sort(arr6);
        s.sort(arr7);
        s.sort(arr8);
    }

}