package _java;

import base.Base;

public class _0191Numberof1Bits extends Base {
    private abstract static class Solution {
        public abstract int hammingWeight(int n);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of 1 Bits.
     * Memory Usage: 33.2 MB, less than 5.39% of Java online submissions for Number of 1 Bits.
     */
    private static class Solution1 extends Solution {

        @Override
        public int hammingWeight(int n) {
            int bits = 0;
            while (n != 0) {
                n &= n - 1;
                ++bits;
            }
            return bits;
        }

    }

    public static void main(String[] args) {
    }

}
