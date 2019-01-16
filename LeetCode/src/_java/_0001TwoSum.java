package _java;

import java.util.HashMap;
import java.util.Map;

import base.Base;

public class _0001TwoSum extends Base {

    private abstract static class Solution {
        public abstract int[] twoSum(int[] nums, int target);
    }

    /**
     * 测试用例里有负值，所以不能用桶。
     */
    private static class Solution1 extends Solution {

        @Override
        public int[] twoSum(int[] nums, int target) {
            if (target < 0 ){
                throw new NullPointerException("loha");
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; ++i) {
                int n = nums[i];
                int remain = target - n;
                Integer j = map.get(remain);
                if (j != null && j != i) {
                    return new int[]{i, j};
                }
                map.put(n, i);
            }
            return new int[0];
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public int[] twoSum(int[] nums, int target) {
            return new int[0];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 7, 11, 15};
        printArr(new Solution1().twoSum(nums1, 9));
        printArr(new Solution1().twoSum(nums1, 14));
    }
}
