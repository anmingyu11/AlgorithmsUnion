package dfs;

import java.util.ArrayList;
import java.util.List;

import base.Base;

public class PalindromePartitioning extends Base {

    // DP
    static class Solution1 {

        public static List<List<String>> partition(String s) {
            final int n = s.length();
            boolean[][] pair = new boolean[n][n];
            List<List<String>>[] result = new List[n + 1];
            result[0] = new ArrayList<>();
            result[0].add(new ArrayList<>());

            for (int i = 0; i < n; ++i) {
                result[i + 1] = new ArrayList<>();
                for (int j = 0; j <= i; ++j) {
                    if (s.charAt(i) == s.charAt(j)
                            && (j + 1 > i - 1 || pair[i - 1][j + 1])) {
                        pair[i][j] = true;
                        String str = s.substring(j, i + 1);
                        // 每次获得的最大回文子字符串从i到j
                        // result j 每次添加的顺序依次是0-j的回文串集合.
                        for (List<String> r : result[j]) {
                            List<String> ri = new ArrayList<>(r);
                            ri.add(str);
                            result[i + 1].add(ri);
                        }
                    }
                }
            }

            return result[n];
        }

    }

    // Dp + DFS
    static class Solution2 {

        public static List<List<String>> partition(String s) {

            final int n = s.length();
            boolean[][] pair = new boolean[n][n];
            List<List<String>> res = new ArrayList<>();

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (s.charAt(i) == s.charAt(j)
                            && (j + 1 > i - 1 || pair[j + 1][i - 1])) {
                        pair[j][i] = true;
                    }
                }
            }

            dfs(res, new ArrayList<>(), pair, s, 0);

            return res;
        }

        private static void dfs(List<List<String>> res, List<String> path, boolean[][] pair, String s, int pos) {
            final int n = s.length();
            if (pos == n) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = pos; i < n; ++i) {
                if (pair[pos][i]) {
                    path.add(s.substring(pos, i + 1));
                    dfs(res, path, pair, s, i + 1);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    //Back tracking
    static class Solution3 {

        static List<List<String>> resultList;
        static ArrayList<String> currList;

        public static List<List<String>> partition(String s) {
            resultList = new ArrayList<>();
            currList = new ArrayList<>();
            backTrack(s, 0);
            return resultList;
        }

        private static void backTrack(String s, int start) {
            final int n = s.length();
            if (start >= n) {
                resultList.add((List<String>) currList.clone());
                return;
            }

            for (int i = start; i < n; ++i) {
                if (isPalindrome(s, start, i)) {
                    currList.add(s.substring(start, i + 1));
                    backTrack(s, i + 1);
                    currList.remove(currList.size() - 1);
                }
            }
        }

        private static boolean isPalindrome(String str, int l, int r) {
            if (l == r) return true;
            while (l < r) {
                if (str.charAt(l) != str.charAt(r)) return false;
                l++;
                r--;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        String p1 = "aab";
        String p2 = "aabccbac";
        println(Solution3.partition(p1));
    }
}
