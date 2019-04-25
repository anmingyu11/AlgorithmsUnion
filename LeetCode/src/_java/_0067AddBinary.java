package _java;

import base.Base;

/**
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * The input strings are both non-empty and contains only characters 1 or 0.
 * <p>
 * Example 1:
 * <p>
 * Input: a = "11", b = "1"
 * Output: "100"
 * <p>
 * Example 2:
 * <p>
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class _0067AddBinary extends Base {


    private abstract static class Solution {
        public abstract String addBinary(String a, String b);
    }

    /**
     * Runtime: 1 ms, faster than 99.94% of Java online submissions for Add Binary.
     * Memory Usage: 37 MB, less than 51.75% of Java online submissions for Add Binary.
     */
    private static class Solution1 extends Solution {

        @Override
        public String addBinary(String a, String b) {
            char[] aStr = a.toCharArray();
            char[] bStr = b.toCharArray();
            int lenA = aStr.length;
            int lenB = bStr.length;
            int carry = 0;
            int i = 0;

            StringBuilder sb = new StringBuilder();
            while (i < lenA || i < lenB) {
                int aBit = lenA - 1 - i >= 0 ? aStr[lenA - 1 - i] - '0' : 0;
                int bBit = lenB - 1 - i >= 0 ? bStr[lenB - 1 - i] - '0' : 0;
                int r = aBit + bBit + carry;
                if (r > 1) {
                    r %= 2;
                    carry = 1;
                } else {
                    carry = 0;
                }
                sb.append(r);
                ++i;
            }
            if (carry > 0) {
                sb.append(1);
            }
            return sb.reverse().toString();
        }

    }

    /**
     * Runtime: 1 ms, faster than 99.94% of Java online submissions for Add Binary.
     * Memory Usage: 37 MB, less than 57.74% of Java online submissions for Add Binary.
     */
    private static class Solution2 extends Solution {

        @Override
        public String addBinary(String a, String b) {
            StringBuilder sb = new StringBuilder();
            int i = a.length() - 1, j = b.length() -1, carry = 0;
            while (i >= 0 || j >= 0) {
                int sum = carry;
                if (j >= 0) sum += b.charAt(j--) - '0';
                if (i >= 0) sum += a.charAt(i--) - '0';
                sb.append(sum % 2);
                carry = sum / 2;
            }
            if (carry != 0) sb.append(carry);
            return sb.reverse().toString();
        }
    }

    public static void main(String[] args) {
        String a1 = "11", b1 = "1";
        String a2 = "1010", b2 = "1011";

        Solution s = new Solution1();

        println(s.addBinary(a1, b1));// 100
        println(s.addBinary(a2, b2));// 10101

    }
}
