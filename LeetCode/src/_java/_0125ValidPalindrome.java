package _java;

import base.Base;

public class _0125ValidPalindrome extends Base {

    private abstract static class Solution {
        public abstract boolean isPalindrome(String s);
    }

    // 9ms beats 63.29%
    private static class Solution1 extends Solution {

        @Override
        public boolean isPalindrome(String s) {
            if (s.isEmpty()) {
                return true;
            }

            s = s.toLowerCase();

            int i = 0, j = s.length() - 1;

            while (i < j) {
                char chI = s.charAt(i);
                while (i < j && !isValidCh(chI)) {
                    chI = s.charAt(++i);
                }
                char chJ = s.charAt(j);
                while (i < j && !isValidCh(chJ)) {
                    chJ = s.charAt(--j);
                }

                if (chI != chJ) {
                    return false;
                } else {
                    ++i;
                    --j;
                }
            }

            return true;
        }

        private boolean isValidCh(char ch) {
            return ('a' <= ch && ch <= 'z') || ('0' <= ch && ch <= '9');
        }
    }

    // 3ms beats 99.72%
    private static class Solution2 extends Solution {

        public boolean isPalindrome(String s) {
            if (s.isEmpty()) {
                return true;
            }

            int i = 0, j = s.length() - 1;

            final int gap = 32;
            while (i < j) {
                char chI = s.charAt(i);
                while (i < j) {
                    if (chI >= 'A' && chI <= 'Z') {
                        chI += gap;
                        break;
                    } else if ((chI >= 'a' && chI <= 'z') || (chI >= '0' && chI <= '9')) {
                        break;
                    } else {
                        chI = s.charAt(++i);
                    }
                }

                char chJ = s.charAt(j);
                while (i < j) {
                    if (chJ >= 'A' && chJ <= 'Z') {
                        chJ += gap;
                        break;
                    } else if ((chJ >= 'a' && chJ <= 'z') || (chJ >= '0' && chJ <= '9')) {
                        break;
                    } else {
                        chJ = s.charAt(--j);
                    }
                }

                if (i < j && chI != chJ) {
                    return false;
                } else {
                    ++i;
                    --j;
                }

            }

            return true;
        }

        private boolean isValidCh(char ch) {
            return ('A' <= ch && ch <= 'Z') || ('a' <= ch && ch <= 'z') || ('0' <= ch && ch <= '9');
        }
    }

    private static class Solution3 extends Solution {

        @Override
        public boolean isPalindrome(String s) {
            // 匹配任何非单词字符
            String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
            String rev = new StringBuffer(actual).reverse().toString();
            return actual.equals(rev);
        }
    }

    public static void main(String[] args) {
        String s1 = "";
        String s2 = "A man, a plan, a canal: Panama";
        String s3 = "race a car";
        String s4 = "0P";
        String s5 = "Euston saw I was not Sue.";

        Solution s = new Solution3();
        println(s.isPalindrome(s1));// T
        println(s.isPalindrome(s2));// T
        println(s.isPalindrome(s3));// F
        println(s.isPalindrome(s4));// F
        println(s.isPalindrome(s5));// T
    }
}
