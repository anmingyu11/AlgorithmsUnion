package _java;

import base.Base;

/**
 * The Hamming distance between two integers is the number of positions at which
 * the corresponding bits are different.
 * <p>
 * Given two integers x and y, calculate the Hamming distance.
 * <p>
 * Note:
 * 0 ≤ x, y < 231.
 * <p>
 * Example:
 * <p>
 * Input: x = 1, y = 4
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 * <p>
 * The above arrows point to positions where the corresponding bits are different.
 */
public class _0461HammingDistance extends Base {
    /**
     * 在信息理论中，两个相等长度的串之间的汉明距离是相应符号不同的位置的数量。
     * 换句话说，它测量将一个字符串更改为另一个字符串所需的最小替换次数，或者可能将一个字符串转换为另一个字符串的最小错误数。
     * 在更一般的上下文中，汉明距离是用于测量两个序列之间的编辑距离的若干字符串度量之一。它以美国数学家理查德汉明命名。
     */

    private abstract static class Solution {
        public abstract int hammingDistance(int x, int y);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Hamming Distance.
     * Memory Usage: 33.3 MB, less than 5.09% of Java online submissions for Hamming Distance.
     */
    private static class Solution1 extends Solution {

        @Override
        public int hammingDistance(int x, int y) {
            int distance = 0;
            for (int i = 0; i < 32; ++i) {
                if ((x & 1) != (y & 1)) {
                    ++distance;
                }
                x >>= 1;
                y >>= 1;
            }
            return distance;
        }

    }

    // 异或1
    private static class Solution2 extends Solution {

        @Override
        public int hammingDistance(int x, int y) {
            return Integer.bitCount(x ^ y);
        }
    }

    private static class Solution3 extends Solution {

        @Override
        public int hammingDistance(int x, int y) {
            int xor = x ^ y, count = 0;
            while (xor != 0) {
                xor &= xor - 1;
                ++count;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        println(s.hammingDistance(1, 4));
    }

}
