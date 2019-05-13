package _java;

import base.Base;

public class _0013RomanToInteger extends Base {

    private abstract static class Solution {
        public abstract int romanToInt(String s);
    }

    /**
     * Runtime: 3 ms, faster than 100.00% of Java online submissions for Roman to Integer.
     * Memory Usage: 35.2 MB, less than 100.00% of Java online submissions for Roman to Integer.
     */
    private static class Solution1 extends Solution {

        @Override
        public int romanToInt(String s) {
            char[] romans = new char[256];
            romans['I'] = 1;
            romans['V'] = 5;
            romans['X'] = 10;
            romans['L'] = 50;
            romans['C'] = 100;
            romans['D'] = 500;
            romans['M'] = 1000;
            int[] nums = new int[s.length()];
            for (int i = 0; i < s.length(); ++i) {
                nums[i] = romans[s.charAt(i)];
            }
            int sum = 0;
            for (int i = 0; i < s.length() - 1; ++i) {
                if (nums[i] < nums[i + 1]) {
                    sum -= nums[i];
                } else {
                    sum += nums[i];
                }
            }
            return sum + nums[nums.length - 1];
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        println(s.romanToInt("III"));//3
        println(s.romanToInt("IV"));//4
        println(s.romanToInt("IX"));//9
        println(s.romanToInt("LVIII"));//58
        println(s.romanToInt("MCMXCIV"));//1994

    }

}
