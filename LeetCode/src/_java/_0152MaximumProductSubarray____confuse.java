package _java;

import base.Base;

/**
 * Given an integer array nums,
 * find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class _0152MaximumProductSubarray____confuse extends Base {

    private abstract static class Solution {
        public abstract int maxProduct(int[] nums);
    }

    private static class Solution1 extends Solution {

        public int maxProduct(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            int res = nums[0];
            int max = 1;
            int min = 1;

            for (int i = 0; i < nums.length; i++) {
                // 1. 到0归1,这个简单
                // 2. max,min相当于两条并行的蛇,max,min二者总有一个存的是当前乘积绝对值最大的值.
                // 3. max与min符号有时会相同,所以需要加与当前元素x的判断.
                int x = nums[i];
                if (x > 0) {
                    max = Math.max(max * x, x);
                    min = min * x;
                } else if (x < 0) {
                    int t = min;
                    min = Math.min(max * x, x);
                    max = t * x;
                } else {
                    min = 1;
                    max = 1;
                    res = Math.max(res, 0);
                    continue;
                }
                res = Math.max(res, Math.max(min, max));
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] a1 = {2, 3, -2, 4};
        int[] a2 = {-2, 0, -1};
        int[] a3 = {3, -1, 4};

        Solution s = new Solution1();

        //println(s.maxProduct(a1)); // 6
        //println(s.maxProduct(a2)); // 0
        println(s.maxProduct(a3)); // 4
    }
}
