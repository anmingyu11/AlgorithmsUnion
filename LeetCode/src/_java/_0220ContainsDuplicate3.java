package _java;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import base.Base;

/**
 * Given an array of integers,
 * find out whether there are two distinct indices i and j in the array
 * such that the absolute difference between nums[i] and nums[j] is at most t
 * and the absolute difference between i and j is at most k.
 */
public class _0220ContainsDuplicate3 extends Base {

    private abstract static class Solution {
        public abstract boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t);
    }

    // Runtime: 19 ms, faster than 73.92% of Java online submissions for Contains Duplicate III.
    // Memory Usage: 34.7 MB, less than 99.07% of Java online submissions for Contains Duplicate III.
    // 用TreeSet做窗口,brilliant
    private static class Solution1 extends Solution {

        @Override
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            final int n = nums.length;
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < n; ++i) {
                int v = nums[i];
                // Returns the least element in this set greater than or equal to the given element,
                // or null if there is no such element.
                // 大于等于v的最小元素
                Integer s = set.ceiling(v);
                if (s != null && v + t >= s) {
                    return true;
                }
                // Returns the greatest element in this set less than or equal to the given element,
                // or null if there is no such element.
                // 小于等于v的最大元素.
                Integer g = set.floor(v);
                if (g != null && g + t >= v) {
                    return true;
                }
                set.add(v);
                if (set.size() > k) {
                    set.remove(nums[i - k]);
                }
            }
            return false;
        }

    }

    // 以桶做窗口.
    // Runtime: 10 ms, faster than 92.47% of Java online submissions for Contains Duplicate III.
    // Memory Usage: 39.2 MB, less than 29.77% of Java online submissions for Contains Duplicate III.
    // 将Long 改成 long之后效率提高, 省去了自动装箱?
    // Runtime: 9 ms, faster than 95.77% of Java online submissions for Contains Duplicate III.
    // Memory Usage: 40.4 MB, less than 5.58% of Java online submissions for Contains Duplicate III.
    private static class Solution2 extends Solution {

        private long getId(long x, long w) {
            return x < 0 ? x / w - 1 : x / w;
        }

        // t is between nums[i] and nums[j]
        // use long is reasonable because if a = Integer.MaxValue b = Integer.MinValue
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (t < 0 || k < 0) {
                return false;
            }
            Map<Long, Long> bucket = new HashMap<>();
            final int n = nums.length;
            // window
            long w = (long) t + 1;
            for (int i = 0; i < n; ++i) {
                long v = nums[i];
                long subBucket = getId(v, w);
                if (bucket.containsKey(subBucket)) {
                    return true;
                }
                if (bucket.containsKey(subBucket - 1) && Math.abs(nums[i] - bucket.get(subBucket - 1)) < w) {
                    return true;
                }
                if (bucket.containsKey(subBucket + 1) && Math.abs(nums[i] - bucket.get(subBucket + 1)) < w) {
                    return true;
                }

                bucket.put(subBucket, v);
                if (i >= k) {
                    bucket.remove(getId((long) nums[i - k], w));
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 1};
        int k1 = 3;
        int t1 = 0;
        int[] arr2 = {1, 0, 1, 1};
        int k2 = 1;
        int t2 = 2;
        int[] arr3 = {1, 5, 9, 1, 5, 9};
        int k3 = 2;
        int t3 = 3;
        int[] arr4 = {-1, -1};
        int k4 = 1;
        int t4 = -1;

        Solution s = new Solution2();

        println(s.containsNearbyAlmostDuplicate(arr1, k1, t1));// true
        println(s.containsNearbyAlmostDuplicate(arr2, k2, t2));// true
        println(s.containsNearbyAlmostDuplicate(arr3, k3, t3));// false
        println(s.containsNearbyAlmostDuplicate(arr3, k4, t4));// false
    }
}
