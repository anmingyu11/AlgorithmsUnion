package bit;

import base.Base;

public class SingleNumber3 extends Base {

    // 楞想 是想不出来的, 太牛逼了太牛逼了。
    static class Solution1 {
        public int[] singleNumber(int[] nums) {
            int diff = 0;
            for (int i : nums) {
                diff ^= i;
            }

            //右面第一位是1的
            diff &= -diff;

            int[] res = new int[]{0, 0};
            for (int i : nums) {
                if ((diff & i) == 0) {
                    res[0] ^= i;
                } else {
                    res[1] ^= i;
                }
            }

            return res;
        }
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 1, 3, 2, 5};

        printArr(new Solution1().singleNumber(nums1));
    }

}
