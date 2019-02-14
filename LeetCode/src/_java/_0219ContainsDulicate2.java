package _java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import base.Base;

public class _0219ContainsDulicate2 extends Base {

    private abstract static class Solution {
        public abstract boolean containsNearbyDuplicate(int[] nums, int k);
    }

    // HashMap
    // Runtime: 8 ms, faster than 90.94% of Java online submissions for Contains Duplicate II.
    // Memory Usage: 31 MB, less than 70.24% of Java online submissions for Contains Duplicate II.
    private static class Solution1 extends Solution {

        @Override
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            final int len = nums.length;
            if (len < 2) {
                return false;
            }
            Map<Integer, Integer> map = new HashMap<>(len);
            map.put(nums[0], 0);
            for (int i = 1; i < len; ++i) {
                int v = nums[i];
                Integer j = map.get(v);
                if (j != null) {
                    if (i - j <= k) {
                        return true;
                    }
                }
                map.put(v, i);
            }
            return false;
        }
    }

    // Runtime: 7 ms, faster than 96.61% of Java online submissions for Contains Duplicate II.
    // Memory Usage: 33.7 MB, less than 32.05% of Java online submissions for Contains Duplicate II.
    private static class Solution2 extends Solution {

        @Override
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            final int len = nums.length;
            if (len < 2) {
                return false;
            }
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < len; ++i) {
                if (!set.add(nums[i])) {
                    return true;
                }
                if (set.size() > k) {
                    set.remove(nums[i - k]);
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 1};
        int k1 = 3;
        int[] arr2 = {1, 0, 1, 1};
        int k2 = 1;
        int[] arr3 = {1, 2, 3, 1, 2, 3};
        int k3 = 2;

        Solution s = new Solution2();

        println(s.containsNearbyDuplicate(arr1, k1));
        println(s.containsNearbyDuplicate(arr2, k2));
        println(s.containsNearbyDuplicate(arr3, k3));
    }

}
