package bit;

import java.util.HashSet;
import java.util.Set;

import base.Base;

public class SingleNumber extends Base {

    //My solution
    static class Solution1 {
        public static int singleNumber(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (!set.add(num)) {
                    set.remove(num);
                }
            }

            return set.iterator().next();
        }
    }

    // 位运算 异或
    static class Solution2 {
        public static int singleNumber(int[] nums) {
            int ans = 0;
            int len = nums.length;
            for (int num : nums) {
                ans ^= num;
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 1, 2, 1, 2};
        println(Solution1.singleNumber(nums1));
        println(Solution2.singleNumber(nums1));

    }
}
