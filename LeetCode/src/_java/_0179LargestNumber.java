package _java;

import java.util.Arrays;
import java.util.Comparator;

import base.Base;

public class _0179LargestNumber extends Base {

    private abstract static class Solution {
        public abstract String largestNumber(int[] nums);
    }

    // 自定义顺序,没想到还能这么写Comparator,折腾了老半天没写出来
    // Runtime: 10 ms, faster than 99.38% of Java online submissions for Largest Number.
    // Memory Usage: 38.5 MB, less than 20.51% of Java online submissions for Largest Number.
    private static class Solution1 extends Solution {

        private class LargerNumberComparator implements Comparator<String> {
            @Override
            public int compare(String a, String b) {
                StringBuilder order1 = new StringBuilder(a).append(b);
                StringBuilder order2 = new StringBuilder(b).append(a);
                return order2.toString().compareTo(order1.toString());
            }
        }

        public String largestNumber(int[] nums) {
            String[] asStrs = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                asStrs[i] = String.valueOf(nums[i]);
            }
            Arrays.sort(asStrs, new LargerNumberComparator());
            if (asStrs[0].equals("0")) {
                return "0";
            }
            StringBuilder largestNumberStr = new StringBuilder();
            for (String numAsStr : asStrs) {
                largestNumberStr.append(numAsStr);
            }

            return largestNumberStr.toString();
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {10, 2};
        int[] nums2 = {3, 30, 34, 5, 9};
        int[] nums3 = {0, 0, 9, 97, 99, 5, 89, 83, 12, 13, 14, 15, 19, 10, 1, 54};
        int[] nums4 = {392, 2};
        int[] nums5 = {2, 392};
        int[] nums6 = {128, 12};
        int[] nums7 = {12, 121};
        int[] nums8 = {0, 0};

        Solution s = new Solution1();

        println(s.largestNumber(nums1)); // 210
        println(s.largestNumber(nums2)); // 9534330
        println(s.largestNumber(nums3)); //
        println(s.largestNumber(nums4)); // 3922
        println(s.largestNumber(nums5)); // 3922
        println(s.largestNumber(nums6)); // 12812
        println(s.largestNumber(nums7)); // 12121
        println(s.largestNumber(nums8)); // 0
    }
}
