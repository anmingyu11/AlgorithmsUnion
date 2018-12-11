package bf;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import base.Base;

public class Subset2 extends Base {

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(nums);
        backTrack(list, new LinkedList<>(), nums, 0);
        return list;
    }

    private static void backTrack(List<List<Integer>> list, LinkedList<Integer> curList, int[] nums, int start) {
        List<Integer> newList = (List<Integer>) curList.clone();
        list.add(newList);
        for (int i = start; i < nums.length; ++i) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            curList.add(nums[i]);
            backTrack(list, curList, nums, i + 1);
            curList.remove(curList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 2};
        int[] nums2 = new int[]{4, 4, 4, 1, 4};
        println(subsetsWithDup(nums2));
    }
}
