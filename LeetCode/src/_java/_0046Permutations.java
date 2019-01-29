package _java;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class _0046Permutations extends Base {

    private static abstract class Solution {
        abstract List<List<Integer>> permute(int[] nums);
    }

    private static class Solution1 extends Solution {

        @Override
        List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> list = new LinkedList<>();
            int[] invalid = new int[nums.length];
            backtrack(list, new LinkedList<>(), nums, invalid, 0);
            return list;
        }

        private void backtrack(List<List<Integer>> list, LinkedList<Integer> permute, int[] nums, int[] invalid, int pos) {
            if (pos == nums.length) {
                list.add((List<Integer>) permute.clone());
                return;
            }
            for (int i = 0; i < nums.length; ++i) {
                if (invalid[i] == 0) {
                    invalid[i] = -1;
                    permute.add(nums[i]);
                    backtrack(list, permute, nums, invalid, pos + 1);
                    permute.removeLast();
                    invalid[i] = 0;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{0};

        Solution s = new Solution1();

        println(s.permute(arr1));
        println(s.permute(arr2));
    }
}
