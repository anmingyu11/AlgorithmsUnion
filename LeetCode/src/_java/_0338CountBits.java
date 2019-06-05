package _java;

import base.Base;

/**
 * Given a non negative integer number num.
 * For every numbers i in the range 0 ≤ i ≤ num
 * calculate the number of 1's in their binary representation and return them as an array.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: [0,1,1]
 * Example 2:
 * <p>
 * Input: 5
 * Output: [0,1,1,2,1,2]
 * Follow up:
 * <p>
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)).
 * But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */
public class _0338CountBits extends Base {

    private abstract static class Solution {
        public abstract int[] countBits(int num);
    }

    /**
     * Runtime: 2 ms, faster than 27.41% of Java online submissions for Counting Bits.
     * Memory Usage: 37.5 MB, less than 40.81% of Java online submissions for Counting Bits.
     */
    private static class Solution1 extends Solution {

        @Override
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 0; i <= num; ++i) {
                ans[i] = popCount(i);
            }
            return ans;
        }

        /**
         * 位运算技巧
         *
         * @param x
         * @return
         */
        private int popCount(int x) {
            int count;
            for (count = 0; x != 0; ++count) {
                x &= x - 1; //zeroing out the least significant nonzero bit
            }
            return count;
        }

    }

    /**
     * Runtime: 1 ms, faster than 99.78% of Java online submissions for Counting Bits.
     * Memory Usage: 37.6 MB, less than 40.56% of Java online submissions for Counting Bits.
     */
    private static class Solution2 extends Solution {

        @Override
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            int i = 0, b = 1;
            // [0,b) is calculated
            while (b <= num) {
                while (i < b && i + b <= num) {
                    ans[i + b] = ans[i] + 1;
                    ++i;
                }
                i = 0;
                b <<= 1;
            }
            return ans;
        }

    }

    /**
     * 一个数右移一位,相当于移动了最低位,如果最低位为1,则为1的位数+1
     * Runtime: 1 ms, faster than 99.78% of Java online submissions for Counting Bits.
     * Memory Usage: 37.6 MB, less than 40.56% of Java online submissions for Counting Bits.
     */
    private static class Solution3 extends Solution {

        @Override
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 1; i <= num; ++i) {
                ans[i] = ans[i >>> 1] + (i & 1);
            }
            return ans;
        }
    }

    /**
     * 根据 popCount的写法,动态规划.
     * Runtime: 1 ms, faster than 99.78% of Java online submissions for Counting Bits.
     * Memory Usage: 37.6 MB, less than 40.51% of Java online submissions for Counting Bits.
     */
    private static class Solution4 extends Solution {

        @Override
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 1; i <= num; ++i) {
                ans[i] = ans[i & (i - 1)] + 1;
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution2();

        printArr(s.countBits(2)); // 0,1,1
        printArr(s.countBits(5)); // 0,1,1,2,1,2
    }

}
