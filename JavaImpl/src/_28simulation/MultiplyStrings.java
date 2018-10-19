package _28simulation;

import _00base.Base;

public class MultiplyStrings extends Base {

    public static String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] result = new int[m + n];
        char zero = '0';

        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                int product = (num1.charAt(i) - zero) * (num2.charAt(j) - zero);

                int carry = i + j, k = i + j + 1;
                int sum = product + result[k];

                result[carry] += sum / 10;
                result[k] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            if (!(sb.length() == 0 && digit == 0)) {
                sb.append(digit);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        String n1 = "16";
        String n2 = "16";
        String res = multiply(n1, n2);
        println(res);
    }
}
