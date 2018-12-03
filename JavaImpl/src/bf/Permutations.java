package bf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import base.Base;

public class Permutations extends Base {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        backTrack(list, new ArrayList<>(nums.length), nums);
        return list;
    }

    private static void backTrack(List<List<Integer>> res, ArrayList<Integer> curList, int[] nums) {
        if (curList.size() == nums.length) {
            res.add((List<Integer>) curList.clone());
        } else {
            for (int i = 0; i < nums.length; ++i) {
                if (curList.contains(nums[i])) {
                    continue;
                } else {
                    curList.add(nums[i]);
                    backTrack(res, curList, nums);
                    curList.remove(curList.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3};

        for (List<Integer> ns : permute(nums1)) {
            println(ns);
        }
    }
}
