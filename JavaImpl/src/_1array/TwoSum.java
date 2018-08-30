package _1array;

import java.util.HashMap;

import _0base.Base;

public class TwoSum extends Base {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; ++i) {
            Integer v = map.get(target - nums[i]);
            if (v != null && v > i) {
                return new int[]{i + 1, v + 1};
            }

        }

        return null;
    }

    public static void main(String[] args) {

    }
}
