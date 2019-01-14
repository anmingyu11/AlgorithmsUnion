package dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import base.Base;

public class CombinationSum2 extends Base {

    static class Solution1 {

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new LinkedList<>();
            Arrays.sort(candidates);
            backtrack(res, new LinkedList<>(), candidates, target, 0);
            return res;
        }

        private void backtrack(List<List<Integer>> list, LinkedList<Integer> tempList, int[] nums, int remain, int start) {
            if (remain < 0) {
                return;
            } else if (remain == 0) {
                list.add((List<Integer>) tempList.clone());
            }
            for (int i = start; i < nums.length; ++i) {
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i + 1);
                tempList.removeLast();
            }
        }

    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target1 = 8;
        int[] nums2 = new int[]{2, 5, 2, 1, 2};
        int target2 = 5;
        println(new Solution1().combinationSum(nums1, target1));
        println(new Solution1().combinationSum(nums2, target2));
    }
}
