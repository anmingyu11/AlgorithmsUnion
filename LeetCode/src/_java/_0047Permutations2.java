package _java;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import base.Base;

public class _0047Permutations2 extends Base {

    private abstract static class Solution {
        public abstract List<List<Integer>> permuteUnique(int[] nums);
    }

    // Runtime: 3 ms, faster than 98.15% of Java online submissions for Permutations II.
    // Memory Usage: 41.5 MB, less than 18.33% of Java online submissions for Permutations II.
    private static class Solution1 extends Solution {

        @Override
        public List<List<Integer>> permuteUnique(int[] nums) {
            final int n = nums.length;
            Arrays.sort(nums);
            List<List<Integer>> ans = new LinkedList<>();
            LinkedList<Integer> cur = new LinkedList<>();
            boolean[] used = new boolean[n];
            // 选所有单独的一个数
            for (int i = 0; i < n; ++i) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                cur.add(nums[i]);
                used[i] = true;
                backTrack(ans, cur, nums, used);
                used[i] = false;
                cur.removeLast();
            }
            return ans;
        }

        private void backTrack(List<List<Integer>> ans, LinkedList<Integer> cur, int[] nums, boolean[] used) {
            final int n = nums.length;
            if (cur.size() == n) {
                ans.add((List<Integer>) cur.clone());
                return;
            }
            for (int i = 0; i < n; ++i) {
                if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                    continue;
                }
                cur.add(nums[i]);
                used[i] = true;
                backTrack(ans, cur, nums, used);
                used[i] = false;
                cur.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2};
        int[] arr2 = {};

        Solution s = new Solution1();

        println(s.permuteUnique(arr1));
        println(s.permuteUnique(arr2));
    }

}
