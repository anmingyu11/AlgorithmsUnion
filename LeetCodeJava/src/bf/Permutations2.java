package bf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import base.Base;

public class Permutations2 extends Base {

    static class Solution {
        private boolean[] used;

        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            used = new boolean[nums.length];
            List<List<Integer>> list = new LinkedList<>();
            backTrack(list, new ArrayList<>(nums.length), nums);
            return list;
        }

        private void backTrack(List<List<Integer>> res, ArrayList<Integer> curList, int[] nums) {
            if (curList.size() == nums.length) {
                res.add((List<Integer>) curList.clone());
            } else {
                for (int i = 0; i < nums.length; ++i) {
                    if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                        continue;
                    } else {
                        curList.add(nums[i]);
                        used[i] = true;
                        backTrack(res, curList, nums);
                        curList.remove(curList.size() - 1);
                        used[i] = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 1, 2};

        for (List<Integer> ns : new Solution().permuteUnique(nums1)) {
            println(ns);
        }
    }

}
