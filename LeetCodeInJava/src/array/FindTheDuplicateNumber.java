package array;

import java.util.Arrays;

import base.Base;

public class FindTheDuplicateNumber extends Base {

    abstract static class Solution {
        abstract public int findDuplicate(int[] nums);
    }

    // Hash set is On
    static class Solution1 extends Solution {

        @Override
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; ++i) {
                if (nums[i] == nums[i - 1]) {
                    return nums[i];
                }
            }
            return 0;
        }
    }

    static class Solution2 extends Solution {

        @Override
        public int findDuplicate(int[] nums) {
            int tortoise = nums[0];
            int hare = nums[0];

            do {
                tortoise = nums[tortoise];
                hare = nums[nums[hare]];
            } while (tortoise != hare);

            // Find the "entrance" to the cycle.
            int ptr1 = nums[0];
            int ptr2 = tortoise;
            while (ptr1 != ptr2) {
                ptr1 = nums[ptr1];
                ptr2 = nums[ptr2];
            }

            return ptr1;
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 2, 2};
        int[] nums2 = {3, 1, 3, 4, 2};

        Solution s = new Solution2();
        println(s.findDuplicate(nums1));
        println(s.findDuplicate(nums2));
    }
}
