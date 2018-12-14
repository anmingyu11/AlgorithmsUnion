package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import base.Base;

public class WordBreak extends Base {

    //backtrack Time limit exceed
    static class Solution1 {

        public boolean wordBreak(String s, List<String> wordDict) {
            if (s.length() == 0) {
                return true;
            }
            for (String start : wordDict) {
                if (s.startsWith(start)) {
                    String sub = s.substring(start.length());
                    if (wordBreak(sub, wordDict)) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    static class Solution2 {

        private boolean[] dp;

        public boolean wordBreak(String s, List<String> wordDict) {
            dp = new boolean[s.length() + 1];
            dp[0] = true;
            Set<String> dict = new HashSet<>(wordDict);
            return wordBreakAux(s, dict);
        }

        public boolean wordBreakAux(String s, Set<String> dict) {
            for (int i = 1; i <= s.length(); ++i) {
                for (String str : dict) {
                    int strLen = str.length();
                    if (i >= strLen) {
                        if (dp[i - strLen] && s.substring(i - strLen, i).equals(str)) {
                            dp[i] = true;
                            //只要对应的i的结果
                            break;
                        }
                    }
                }
            }

            return dp[s.length()];
        }
    }

    //Dp2 省去了遍历过程 n!
    static class Solution3 {

        private boolean[] dp;

        public boolean wordBreak(String s, List<String> wordDict) {
            dp = new boolean[s.length() + 1];
            dp[0] = true;
            Set<String> dict = new HashSet<>(wordDict);
            return wordBreakAux(s, dict);
        }

        private boolean wordBreakAux(String s, Set<String> dict) {
            for (int i = 1; i <= s.length(); ++i) {
                for (int j = 0; j < i; ++j) {
                    String sub = s.substring(j, i);
                    if (dp[j] && dict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }

    }

    public static void main(String[] args) {
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        Solution3 solu = new Solution3();
        println(solu.wordBreak(s1, wordDict1));
        println(solu.wordBreak(s2, wordDict2));
        println(solu.wordBreak(s3, wordDict3));
    }

}
