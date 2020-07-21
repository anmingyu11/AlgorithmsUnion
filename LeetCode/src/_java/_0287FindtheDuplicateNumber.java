package _java;

import java.util.HashSet;
import java.util.Set;

import base.Base;

public class _0287FindtheDuplicateNumber extends Base {

    private static abstract class Solution {
        public abstract int findDuplicate(int[] nums);
    }

    private static class Solution1 extends Solution {

        @Override
        public int findDuplicate(int[] nums) {
            Set<Integer> set = new HashSet<>();

            for (int v : nums) {
                if (!set.add(v)) {
                    return v;
                }
            }
            return -1;
        }

    }

    /**
     * 不但要判圈，还要找到入口！
     */
    private static class Solution2 extends Solution {

        @Override
        public int findDuplicate(int[] nums) {
            int slow = 0, fast = 0;
            do {
                slow = nums[slow];
                fast = nums[nums[fast]];
            } while (slow != fast);

            int slow2 = 0;
            do {
                slow = nums[slow];
                slow2 = nums[slow2];
            } while (slow != slow2);

            return slow;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution2();

        int find1 = s.findDuplicate(new int[]{1, 3, 2, 4, 2});
        int find2 = s.findDuplicate(new int[]{2, 5, 9, 6, 9, 3, 8, 9, 7, 1});
        println(find1);
        println(find2);
    }
}
