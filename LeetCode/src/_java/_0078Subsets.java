package _java;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class _0078Subsets extends Base {

    private abstract static class Solution {
        public abstract List<List<Integer>> subsets(int[] nums);
    }

    // Runtime: 1 ms, faster than 100.00% of Java online submissions for Subsets.
    // Memory Usage: 37.1 MB, less than 81.24% of Java online submissions for Subsets.
    private static class Solution1 extends Solution {

        @Override
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> ans = new LinkedList<>();
            backTrack(ans, new LinkedList<>(), nums, 0);
            return ans;
        }

        private void backTrack(List<List<Integer>> ans, LinkedList<Integer> cur, int[] nums, int pos) {
            final int n = nums.length;
            ans.add((List<Integer>) cur.clone());

            for (int i = pos; i < n; ++i) {
                cur.add(nums[i]);
                backTrack(ans, cur, nums, i + 1);
                cur.removeLast();
            }
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{};

        Solution s = new Solution1();

        println(s.subsets(arr1));
        println(s.subsets(arr2));
    }
}
