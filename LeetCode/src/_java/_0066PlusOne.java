package _java;

import base.Base;

public class _0066PlusOne extends Base {

    private abstract static class Solution {

        public abstract int[] plusOne(int[] digits);

    }

    // 0ms 100% 没啥说的
    private static class Solution1 extends Solution {

        public int[] plusOne(int[] digits) {
            int carry = 0;

            int num = digits[digits.length - 1] + 1;
            if (num > 9) {
                digits[digits.length - 1] = 0;
                carry = 1;
            } else {
                digits[digits.length - 1] = num;
            }

            for (int i = digits.length - 2; carry > 0 && i >= 0; --i) {
                int n = digits[i] + carry;
                if (n > 9) {
                    digits[i] = 0;
                    carry = 1;
                } else {
                    digits[i] = n;
                    carry = 0;
                }
            }

            if (digits[0] == 0) {
                int[] rs = new int[digits.length + 1];
                rs[0] = 1;
                return rs;
            }

            return digits;
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{4, 3, 2, 1};
        int[] arr3 = new int[]{1, 2, 9, 9};
        int[] arr4 = new int[]{1, 2, 4, 9};
        int[] arr5 = new int[]{9};

        Solution s = new Solution1();

        printArr(s.plusOne(arr1));
        printArr(s.plusOne(arr2));
        printArr(s.plusOne(arr3));
        printArr(s.plusOne(arr4));
        printArr(s.plusOne(arr5));
    }
}
