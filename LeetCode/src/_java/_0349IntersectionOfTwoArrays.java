package _java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import base.Base;

/**
 * Given two arrays, write a function to compute their intersection.
 * Each element in the result must be unique.
 * The result can be in any order.
 */
public class _0349IntersectionOfTwoArrays extends Base {

    private abstract static class Solution {
        public abstract int[] intersection(int[] a, int[] b);
    }

    // Runtime: 3 ms, faster than 49.65% of Java online submissions for Intersection of Two Arrays.
    // Memory Usage: 37.5 MB, less than 33.16% of Java online submissions for Intersection of Two Arrays.
    private static class Solution1 extends Solution {

        @Override
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            for (int e : nums1) {
                set1.add(e);
            }
            for (int e : nums2) {
                set2.add(e);
            }
            final int len1 = set1.size();
            final int len2 = set2.size();
            if (len1 > len2) {
                return setIntersection(set2, set1);
            } else {
                return setIntersection(set1, set2);
            }
        }

        /**
         * set1 size is smaller than set2 size
         *
         * @param set1
         * @param set2
         * @return
         */
        private int[] setIntersection(Set<Integer> set1, Set<Integer> set2) {
            int[] res = new int[set1.size()];
            int i = 0;
            for (int e : set1) {
                if (set2.contains(e)) {
                    res[i++] = e;
                }
            }
            return Arrays.copyOf(res, i);
        }
    }

    // Runtime: 3 ms, faster than 49.65% of Java online submissions for Intersection of Two Arrays.
    // Memory Usage: 35.5 MB, less than 60.53% of Java online submissions for Intersection of Two Arrays.
    private static class Solution2 extends Solution {

        @Override
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            for (int e : nums1) {
                set1.add(e);
            }
            for (int e : nums2) {
                set2.add(e);
            }
            set1.retainAll(set2);
            int[] res = new int[set1.size()];
            int i = 0;
            for (int e : set1) {
                res[i++] = e;
            }
            return res;
        }

    }

    // 双指针,很经典
    // Runtime: 2 ms, faster than 97.77% of Java online submissions for Intersection of Two Arrays.
    // Memory Usage: 37.3 MB, less than 54.21% of Java online submissions for Intersection of Two Arrays.
    private static class Solution3 extends Solution {

        @Override
        public int[] intersection(int[] a, int[] b) {
            Set<Integer> set = new HashSet<>();
            Arrays.sort(a);
            Arrays.sort(b);
            int i = 0, j = 0;
            while (i < a.length && j < b.length) {
                if (a[i] < b[j]) {
                    ++i;
                } else if (a[i] > b[j]) {
                    ++j;
                } else {
                    set.add(a[i]);
                    ++i;
                    ++j;
                }
            }
            int[] res = new int[set.size()];
            int k = 0;
            for (int e : set) {
                res[k++] = e;
            }

            return res;
        }

    }

    // Runtime: 2 ms, faster than 97.77% of Java online submissions for Intersection of Two Arrays.
    // Memory Usage: 37.6 MB, less than 18.25% of Java online submissions for Intersection of Two Arrays.
    private static class Solution4 extends Solution {

        @Override
        public int[] intersection(int[] a, int[] b) {
            if (a.length > b.length) {
                return intersection(b, a);
            }
            Arrays.sort(b);
            Set<Integer> set = new HashSet<>();
            for (int e : a) {
                if (binarySearch(b, 0, b.length - 1, e) >= 0) {
                    set.add(e);
                }
            }
            int[] res = new int[a.length];
            int idx = 0;
            for (int e : set) {
                res[idx++] = e;
            }
            return Arrays.copyOf(res, idx);
        }

        private int binarySearch(int[] A, int lo, int hi, int target) {
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                if (target < A[mid]) {
                    hi = mid - 1;
                } else if (target > A[mid]) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            }
            return -(lo + 1);
        }

    }

    public static void main(String[] args) {
        int[] arr1_1 = {1, 2, 2, 1};
        int[] arr1_2 = {2, 2};
        int[] arr2_1 = {4, 9, 5};
        int[] arr2_2 = {9, 4, 9, 8, 4};

        Solution s = new Solution4();

        printArr(s.intersection(arr1_1, arr1_2));// [ 2 ]
        printArr(s.intersection(arr2_1, arr2_2));// [ 9,4 ]
    }

}