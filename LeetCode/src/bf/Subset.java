package bf;

import java.util.LinkedList;
import java.util.List;

import base.Base;

public class Subset extends Base {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        backTrack(list, nums, new LinkedList<>(), 0);
        return list;
    }

    private static void backTrack(List<List<Integer>> list, int[] nums, LinkedList<Integer> curList, int start) {
        List<Integer> newList = (List<Integer>) curList.clone();
        list.add(newList);
        for (int i = start; i < nums.length; ++i) {
            curList.add(nums[i]);
            backTrack(list, nums, curList, i + 1);
            //会默认删除第一个 如果选择remove nums[i]
            curList.remove(curList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        println(subsets(nums));
    }
}
