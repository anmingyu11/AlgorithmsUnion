package dfs;

import base.Base;

public class AdditiveNumber extends Base {

    public static boolean isAdditiveNumber(String num) {
        final int l = num.length();
        for (int i = 1; i <= l / 2; ++i) {
            if(num.charAt(0) == '0' && i>=2) break; //previous code: continue;
            for (int j = i + 1;
                 (l - j >= j - i) && (l - j >= i);
                 ++j) {
                if(num.charAt(i) == '0' && j-i>=2) break; // previous: continue;

                String num1 = num.substring(0, i);
                String num2 = num.substring(i, j);

                if (isAdditive(num.substring(j), Long.valueOf(num1), Long.valueOf(num2))) {
                    return true;
                }
            }
        }
        return false;
    }

    // Recursively checks if a string is additive
    private static boolean isAdditive(String str, long num1, long num2) {
        if (str.equals("")) {
            return true;
        }
        long sum = num1 + num2;
        String sumS = String.valueOf(sum);
        if (!str.startsWith(sumS)) {
            return false;
        }
        return isAdditive(str.substring(sumS.length()), num2, sum);
    }

    public static void main(String[] args) {
        String s1 = "112358";
        String s2 = "199100199";
        String s3 = "0";
        String s4 ="123";
        //println(isAdditiveNumber(s1));
        //println(isAdditiveNumber(s2));
        //println(isAdditiveNumber(s3));
        println(isAdditiveNumber(s4));
    }
}
