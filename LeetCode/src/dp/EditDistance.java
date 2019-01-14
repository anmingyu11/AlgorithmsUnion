package dp;

import base.Base;

public class EditDistance extends Base {

    static class Solution {

        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();

            if (m * n == 0) {
                return m + n;
            }

            int[][] d = new int[m + 1][n + 1];

            for (int i = 0; i < m + 1; ++i) {
                d[i][0] = i;
            }

            for (int j = 0; j < n + 1; ++j) {
                d[0][j] = j;
            }

            for (int i = 1; i < m + 1; ++i) {
                for (int j = 1; j < n + 1; ++j) {
                    int left = d[i][j - 1];
                    int down = d[i - 1][j];
                    int leftDown = d[i - 1][j - 1];
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        --leftDown;
                    }

                    d[i][j] = 1 + Math.min(leftDown, Math.min(left, down));
                }
            }

            return d[m][n];
        }

    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        String word1 = "horse", word2 = "ros";
        println(new Solution().minDistance(word1, word2));
    }

    private static void test2() {
        String word1 = "intention", word2 = "execution";
        println(new Solution().minDistance(word1, word2));
    }

}
