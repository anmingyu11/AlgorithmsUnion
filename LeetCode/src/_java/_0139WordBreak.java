package _java;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import base.Base;
import base.util.ArraysUtil;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class _0139WordBreak extends Base {

    private abstract static class Solution {
        public abstract boolean wordBreak(String s, List<String> wordDict);
    }

    /**
     * 经过朴素递归改进后的...
     * Runtime: 1 ms, faster than 99.95% of Java online submissions for Word Break.
     * Memory Usage: 33.4 MB, less than 99.98% of Java online submissions for Word Break.
     */
    private static class Solution1 extends Solution {

        @Override
        public boolean wordBreak(String s, List<String> wordDict) {
            final int n = s.length();
            Boolean[] dp = new Boolean[n];

            for (String word : wordDict) {
                if (s.startsWith(word) && wordBreakAux(s, word.length(), wordDict, dp)) {
                    return true;
                }
            }
            return false;
        }

        private boolean wordBreakAux(String s, int start, List<String> wordDict, Boolean[] dp) {
            if (start == s.length()) {
                return true;
            }
            if (dp[start] != null) {
                return dp[start];
            }
            for (String word : wordDict) {
                if (s.startsWith(word, start) && wordBreakAux(s, start + word.length(), wordDict, dp)) {
                    return dp[start] = true;
                }
            }
            return dp[start] = false;
        }
    }

    /**
     * memorized
     * Runtime: 5 ms, faster than 67.95% of Java online submissions for Word Break.
     * Memory Usage: 35.9 MB, less than 95.38% of Java online submissions for Word Break.
     */
    private static class Solution2 extends Solution {

        public boolean wordBreak(String s, List<String> wordDict) {
            return wordBreakAux(s, 0, new HashSet<>(wordDict), new Boolean[s.length()]);
        }

        private boolean wordBreakAux(String s, int start, Set<String> dict, Boolean[] dp) {
            if (start == s.length()) {
                return true;
            }
            if (dp[start] != null) {
                return dp[start];
            }
            for (int end = start + 1; end <= s.length(); ++end) {
                if (dict.contains(s.substring(start, end)) && wordBreakAux(s, end, dict, dp)) {
                    return dp[start] = true;
                }
            }
            return dp[start] = false;
        }
    }

    /**
     * DP B-U
     * Runtime: 3 ms, faster than 90.54% of Java online submissions for Word Break.
     * Memory Usage: 33.2 MB, less than 99.99% of Java online submissions for Word Break.
     */
    private static class Solution3 extends Solution {

        @Override
        public boolean wordBreak(String s, List<String> wordDict) {
            final int n = s.length();
            boolean[] dp = new boolean[n + 1];
            dp[n] = true;
            for (int i = n - 1; i >= 0; --i) {
                for (String word : wordDict) {
                    if (i + word.length() > n) {
                    } else if (dp[i + word.length()] && s.startsWith(word, i)) {
                        dp[i] = true;
                    }
                }
            }
            return dp[0];
        }

    }

    private static class Solution4 extends Solution {

        @Override
        public boolean wordBreak(String s, List<String> wordDict) {
            return false;
        }

    }

    public static void main(String[] args) {
        String s1 = "leetcode";
        List<String> wordDict1 = ArraysUtil.string2List("leet", "code");
        String s2 = "applepenapple";
        List<String> wordDict2 = ArraysUtil.string2List("apple", "pen");
        String s3 = "catsandog";
        List<String> wordDict3 = ArraysUtil.string2List("cats", "dog", "sand", "and", "cat");

        Solution s = new Solution3();

        println(s.wordBreak(s1, wordDict1)); //T
        println(s.wordBreak(s2, wordDict2)); //T
        println(s.wordBreak(s3, wordDict3)); //F
    }
}