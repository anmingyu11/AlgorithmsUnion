package bit;

import java.util.HashSet;
import java.util.Set;

import base.Base;

public class SingleNumber2 extends Base {

    static class Solution1 {

        public int singleNumber(int[] nums) {
            Set<Long> set = new HashSet<>();

            long sum3 = 0;
            long sum = 0;
            for (int n : nums) {
                if (set.add((long) n)) {
                    sum3 += n;
                }
                sum += n;
            }

            return (int) ((3 * sum3 - sum) / 2);
        }

    }


    static class Solution2 {

        public int singleNumber(int[] nums) {
            int W = Integer.SIZE;
            int[] count = new int[W];
            for (int i = 0; i < nums.length; ++i) {
                for (int j = 0; j < W; ++j) {
                    // from least significant to most significant
                    count[j] += (nums[i] >> j) & 1;
                    // if there is more than three put it to 0
                    count[j] %= 3;
                }
            }

            int res = 0;
            for (int i = 0; i < W; ++i) {
                res |= count[i] << i;
            }

            return res;
        }

    }

    static class Solution3 {

        public int singleNumber(int[] nums) {
            int one = 0, two = 0, three = 0;
            // 位运算满足结合律,这点非常重要
            for (int i : nums) {
                // two to one
                two |= one & i;
                // one xor i
                one ^= i;
                three = ~(one & two);

                //if one or two three will be -1
                one &= three;
                two &= three;
            }

            return one;
        }
    }

    static class Solution4 {

        public int singleNumber(int[] nums) {
            // b0 and b1 represent bit 0 and bit 1 respectively
            int b0 = 0, b1 = 0;

            for (int i : nums) {
                // if b1 is 0 , b0  to i, if b1 is i b0 to 0.
                b0 = (b0 ^ i) & (~b1);
                b1 = (b1 ^ i) & (~b0);
            }

            return b0;
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 2, 2, 3};
        int[] arr2 = new int[]{0, 1, 0, 1, 0, 1, 99};

        println(new Solution4().singleNumber(arr1));
        println(new Solution4().singleNumber(arr2));

    }
}
