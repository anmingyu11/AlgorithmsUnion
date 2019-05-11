package _java;

import base.Base;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 */
public class _0012IntegerToRoman extends Base {

    private abstract static class Solution {
        public abstract String intToRoman(int num);
    }

    /**
     * Runtime: 3 ms, faster than 100.00% of Java online submissions for Integer to Roman.
     * Memory Usage: 35.4 MB, less than 100.00% of Java online submissions for Integer to Roman.
     */
    private static class Solution1 extends Solution {

        @Override
        public String intToRoman(int num) {
            int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < values.length && num > 0; ) {
                while (num - values[i] >= 0) {
                    num -= values[i];
                    sb.append(strs[i]);
                }
                ++i;
            }
            return sb.toString();
        }
    }

    /**
     * Runtime: 4 ms, faster than 83.55% of Java online submissions for Integer to Roman.
     * Memory Usage: 35 MB, less than 100.00% of Java online submissions for Integer to Roman.
     */
    private static class Solution2 extends Solution {

        @Override
        public String intToRoman(int num) {
            String M[] = {"", "M", "MM", "MMM"};
            String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
            String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
            String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
            return M[num / 1000] + C[num / 100 % 10] + X[num / 10 % 10] + I[num % 10];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution2();

        println(s.intToRoman(3)); // III
        println(s.intToRoman(4)); // IV
        println(s.intToRoman(9)); // IX
        println(s.intToRoman(58)); // LVIII
        println(s.intToRoman(1994)); // MCMXCIV
    }
}
