package _java;

import base.Base;

public class _1003CheckIfWordIsValidAfterSubstitutions__________ extends Base {
    private abstract static class Solution {
        public abstract boolean isValid(String S);
    }

    private static class Solution1 extends Solution {

        @Override
        public boolean isValid(String S) {
            final int n = S.length();
            if (n % 3 != 0) {
                return false;
            }
            StringBuilder sb = new StringBuilder(S);
            char[] v = "abc".toCharArray();
            int cur;
            while (sb.length() > 0) {
                if (sb.length() == 3) {
                    if (sb.toString().equals("abc")) {
                        return true;
                    } else {
                        return false;
                    }
                }
                cur = 0;
                for (int i = 0; i < 3; ++i) {
                    for (int j = cur; j < sb.length(); ++j) {
                        if (v[i] == sb.charAt(j)) {
                            sb.deleteCharAt(j);
                            cur = j;
                            break;
                        }
                    }
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        String s1 = "aabcbc";
        String s2 = "abcabcababcc";
        String s3 = "abccba";
        String s4 = "cababc";

        Solution s = new Solution1();

        println(s.isValid(s1)); // true
        println(s.isValid(s2)); // true
        println(s.isValid(s3)); // false
        println(s.isValid(s4)); // false
    }

}
