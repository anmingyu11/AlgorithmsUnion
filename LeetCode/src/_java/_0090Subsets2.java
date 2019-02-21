package _java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import base.Base;

public class _0090Subsets2 extends Base {

    private abstract static class Solution {
        public abstract List<List<Integer>> subsetsWithDup(int[] nums);
    }

    // 去重问题可以排序.
    // Runtime: 2 ms, faster than 97.51% of Java online submissions for Subsets II.
    // Memory Usage: 38 MB, less than 56.90% of Java online submissions for Subsets II.
    private static class Solution1 extends Solution {

        @Override
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> ans = new LinkedList<>();
            //单纯回溯无法通过,需要排个序
            Arrays.sort(nums);
            backTrack(ans, new LinkedList<>(), nums, 0);
            return ans;
        }

        private void backTrack(List<List<Integer>> ans, LinkedList<Integer> cur, int[] nums, int pos) {
            final int n = nums.length;
            ans.add((List<Integer>) cur.clone());
            for (int i = pos; i < n; ++i) {
                if (i > pos && nums[i] == nums[i - 1]) {
                    continue;
                }
                cur.add(nums[i]);
                backTrack(ans, cur, nums, i + 1);
                cur.removeLast();
            }
        }

    }

    /**
     * 用Set是不行的,对于幂集,{1,4}和{4,1}其实是一个. 所以还是要排序
     */
    private static class Solution2 extends Solution {

        @Override
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            final int n = nums.length;
            Set<List<Integer>> set = new HashSet<>(n * n);
            backTrack(set, new LinkedList<>(), nums, 0);
            return new LinkedList<>(set);
        }

        private void backTrack(Set<List<Integer>> ans, LinkedList<Integer> cur, int[] nums, int pos) {
            final int n = nums.length;
            ans.add((List<Integer>) cur.clone());
            for (int i = pos; i < n; ++i) {
                if (i > pos && nums[i] == nums[i - 1]) {
                    continue;
                }
                cur.add(nums[i]);
                backTrack(ans, cur, nums, i + 1);
                cur.removeLast();
            }
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 2};
        int[] arr2 = new int[]{};
        int[] arr3 = new int[]{4, 4, 4, 1, 4};

        Solution s = new Solution2();
        println(s.subsetsWithDup(arr1));
        println(s.subsetsWithDup(arr2));
        println(s.subsetsWithDup(arr3));
    }
}
