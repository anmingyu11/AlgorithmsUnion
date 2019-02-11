package _java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import base.Base;

public class _0217ContainsDuplicate extends Base {

    private abstract static class Solution {
        public abstract boolean containsDuplicate(int[] nums);
    }

    // HashSet 可以折半添加
    // Runtime: 6 ms, faster than 89.28% of Java online submissions for Contains Duplicate.
    // Memory Usage: 30.9 MB, less than 76.32% of Java online submissions for Contains Duplicate.
    private static class Solution1 extends Solution {

        @Override
        public boolean containsDuplicate(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int n : nums) {
                if (!set.add(n)) {
                    return true;
                }
            }
            return false;
        }
    }

    // 排序
    // Runtime: 3 ms, faster than 99.33% of Java online submissions for Contains Duplicate.
    // Memory Usage: 32.7 MB, less than 42.95% of Java online submissions for Contains Duplicate.
    private static class Solution2 extends Solution {

        @Override
        public boolean containsDuplicate(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; ++i) {
                if (nums[i] == nums[i - 1]) {
                    return true;
                }
            }
            return false;
        }

    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 1};
        int[] a2 = {1, 2, 3, 4};
        int[] a3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};

        Solution s = new Solution2();

        println(s.containsDuplicate(a1)); // T
        println(s.containsDuplicate(a2)); // F
        println(s.containsDuplicate(a3)); // T
    }

}
