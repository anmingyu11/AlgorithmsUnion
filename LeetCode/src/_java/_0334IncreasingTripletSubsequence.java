package _java;

import base.Base;

public class _0334IncreasingTripletSubsequence extends Base {
    // Return true if there exists i, j, k
    // such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
    private abstract static class Solution {
        public abstract boolean increasingTriplet(int[] nums);
    }

    // Runtime: 2 ms, faster than 100.00% of Java online submissions for Increasing Triplet Subsequence.
    // Memory Usage: 40.1 MB, less than 5.30% of Java online submissions for Increasing Triplet Subsequence.
    private static class Solution1 extends Solution {

        @Override
        public boolean increasingTriplet(int[] nums) {
            // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
            int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
            for (int n : nums) {
                if (n <= small) {
                    small = n;
                } // update small if n is smaller than both
                else if (n <= big) {
                    big = n;
                } // update big only if greater than small but smaller than big
                else {
                    return true;
                } // return if you find a number bigger than both
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {5, 4, 3, 2, 1};
        int[] arr3 = {9, 1, 9, 2, 3};
        int[] arr4 = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
        int[] arr5 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, 3};

        Solution s = new Solution1();

        println(s.increasingTriplet(arr1));
        println(s.increasingTriplet(arr2));
        println(s.increasingTriplet(arr3));
        println(s.increasingTriplet(arr4));
        println(s.increasingTriplet(arr5));
    }

}