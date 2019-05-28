package _java;

import java.util.HashSet;
import java.util.Set;

import base.Base;

public class _0136SingleNumber extends Base {

    private abstract static class Solution {
        public abstract int singleNumber(int[] nums);
    }

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Single Number.
    // Memory Usage: 38.8 MB, less than 64.57% of Java online submissions for Single Number.
    private static class Solution1 extends Solution {

        @Override
        public int singleNumber(int[] nums) {
            int single = 0;
            for (int e : nums) {
                single ^= e;
            }
            return single;
        }

    }

    // Runtime: 5 ms, faster than 37.78% of Java online submissions for Single Number.
    // Memory Usage: 38.4 MB, less than 70.12% of Java online submissions for Single Number.
    private static class Solution2 extends Solution {

        @Override
        public int singleNumber(int[] nums) {
            Set<Integer> set = new HashSet<>();
            int sum = 0;
            for (int e : nums) {
                sum += e;
                set.add(e);
            }
            int sum2 = 0;
            for (int e2 : set) {
                sum2 += e2;
            }
            return sum2 * 2 - sum;
        }

    }

    public static void main(String[] args) {
        int[] a1 = {2, 2, 1};
        int[] a2 = {4, 1, 2, 1, 2};
        Solution s = new Solution2();

        println(s.singleNumber(a1)); // 1
        println(s.singleNumber(a2)); // 4
    }

}
