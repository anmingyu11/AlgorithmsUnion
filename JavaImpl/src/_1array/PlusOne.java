package _1array;

import _0base.Base;

public class PlusOne extends Base {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] < 9) {
                // <9 正常加 并返回
                ++digits[i];
                return digits;
            } else {
                // ==9 进位
                digits[i] = 0;
            }
        }

        int[] newDigits = new int[n + 1];
        newDigits[0] = 1;

        return newDigits;
    }

    public int[] plusOneCarry(int[] digits) {
        int n = digits.length;
        //进位为1
        int carry = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (carry == 0) {
                return digits;
            } else {
                int sum = carry + digits[i];
                digits[i] = sum % 10;
                carry = sum / 10;
            }
        }

        int[] newDigits = new int[n + 1];
        newDigits[0] = 1;

        // 一位数处理 还是方法1精妙一些 但此方法更通用
        return carry == 0 ? digits : newDigits;
    }


}
