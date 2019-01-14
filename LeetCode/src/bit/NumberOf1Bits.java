package bit;

import base.Base;

public class NumberOf1Bits extends Base {

    static class Solution1 {
        public int hammingWeight(int n) {
            int mask = 1, bits = 0;
            for (int i = 0; i < 32; ++i) {
                if ((n & mask) != 0) {
                    ++bits;
                }
                mask <<= 1;
            }

            return bits;
        }
    }

    static class Solution2 {

        public int hammingWeight(int n) {
            int bits = 0;

            while (n != 0) {
                bits++;
                n &= (n - 1);
            }
            return bits;
        }

    }

    public static void main(String[] args) {
    }
}
