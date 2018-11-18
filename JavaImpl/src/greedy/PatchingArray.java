package greedy;

import base.Base;

public class PatchingArray extends Base {

    //非常精妙，每一个
    static class Solution1 {
        public static int minPatches(int[] nums, int n) {
            long miss = 1;
            int len = nums.length, i = 0, res = 0;
            while (miss <= n) {
                if (i <= len - 1 && nums[i] <= miss) {
                    miss += nums[i++];
                } else {
                    miss += miss;//这段程序的灵魂
                    ++res;
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 11, 30};
        int[] nums2 = {1, 3};
        //Solution1
        //println(Solution1.minPatches(nums, 50));
        //println(Solution1.minPatches(nums2, 3));
    }
}
