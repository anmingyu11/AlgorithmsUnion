package dp;

import base.Base;

public class InterleavingString extends Base {
    //
    static class Solution1 {

        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }

            // 必须是+1 否则无法解决三个空串的问题。
            boolean[][] m = new boolean[s1.length() + 1][s2.length() + 1];
            m[0][0] = true;

            // i从1开始，因为要留出空白 这一空白位代表j并没有用，也就是s1走，s2没走，s1走一步，
            // 需要取s1的i - 1 就是s1的第一位，比如i = 1 则取 i - 1 去获得s1的第一位
            // 同理s3没有用到，也就是说 相当于  i - 1 + j , 因为j 是 0  所以 简化为 i - 1
            for (int i = 1; i <= s1.length(); i++) {
                m[i][0] = m[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            }

            for (int j = 1; j <= s2.length(); j++) {
                m[0][j] = m[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
            }

            for (int i = 1; i <= s1.length(); i++) {
                for (int j = 1; j <= s2.length(); j++) {
                    // i = 1 , j = 1 代表 j 和 i 都走了一步，这需要考察两种情况，
                    // 第一种情况就是s1[i - 1]是否等于s3[j + i - 1], 如果是，则考察m[i-1][j] 也就是 s1的前一步，但是s2已经走到该步，
                    // 这种情况下的s1 和 s2是否能构成s3的 i + j - 1的长度
                    // 第二种情况同理
                    // 举例，1,1的情况， 先考察 0,1 这就相当于第一个用的是j，如果第一个是j的情况下，合理，则考察当前i对应的情况
                    // 再考察 1, 0 这就相当于第一个用的是i，如果第一个是j 合理。
                    m[i][j] = m[i - 1][j] && s1.charAt(i - 1) == s3.charAt(j + i - 1)
                            || m[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j + i - 1);
                }
            }

            return m[s1.length()][s2.length()];
        }

    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    private static void test1() {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        println(new Solution1().isInterleave(s1, s2, s3));
    }

    private static void test2() {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";
        println(new Solution1().isInterleave(s1, s2, s3));
    }

    private static void test3() {
        String s1 = "", s2 = "", s3 = "a";
        println(new Solution1().isInterleave(s1, s2, s3));
    }

    private static void test4() {
        String s1 = "", s2 = "", s3 = "";
        println(new Solution1().isInterleave(s1, s2, s3));
    }

    private static void test5() {
        String s1 = "a", s2 = "", s3 = "c";
        println(new Solution1().isInterleave(s1, s2, s3));
    }

    private static void test6() {
        String s1 = "aa", s2 = "ab", s3 = "aaba";
        println(new Solution1().isInterleave(s1, s2, s3));
    }
}
