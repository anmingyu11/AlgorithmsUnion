package _java;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import base.Base;
import base.util.ArraysUtil;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 */
public class _0140WordBreak2___TLE extends Base {

    private abstract static class Solution {
        public abstract List<String> wordBreak(String s, List<String> wordDict);
    }

    /**
     * Runtime: 4 ms, faster than 96.12% of Java online submissions for Word Break II.
     * Memory Usage: 35 MB, less than 100.00% of Java online submissions for Word Break II.
     * 备忘录递归
     */
    private static class Solution1 extends Solution {

        HashMap<Integer, List<String>> map;

        @Override
        public List<String> wordBreak(String s, List<String> wordDict) {
            if (s == null || s.length() == 0) {
                return null;
            }
            map = new HashMap<>();
            return wordBreakAux(s, wordDict, 0);
        }

        private List<String> wordBreakAux(String s, List<String> wordDict, int start) {
            if (map.containsKey(start)) {
                return map.get(start);
            }
            LinkedList<String> res = new LinkedList<>();
            if (start == s.length()) {
                // 专门用来处理最后一个字符串的.
                res.add("");
                return res;
            }
            for (String word : wordDict) {
                if (s.startsWith(word, start)) {
                    List<String> sub = wordBreakAux(s, wordDict, start + word.length());
                    for (String subWord : sub) {
                        res.add(word + (subWord.equals("") ? subWord : " " + subWord));
                    }
                }
            }
            map.put(start, res);
            return res;
        }

    }

    /**
     * B-U DP
     * LTE??
     */
    private static class Solution2 extends Solution {

        @Override
        public List<String> wordBreak(String s, List<String> wordDict) {
            if (s == null || s.length() == 0) {
                return null;
            }
            final int n = s.length();
            wordDict.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length();
                }
            });
            LinkedList<String>[] dp = new LinkedList[n];
            for (int i = n - 1; i >= 0; --i) {
                LinkedList<String> subI = new LinkedList<>();
                for (String word : wordDict) {
                    int j = i + word.length();
                    if (j > n) {
                        break;
                    }
                    if (s.startsWith(word, i)) {
                        if (j == n) {
                            subI.add(word);
                        } else {
                            for (String subJ : dp[j]) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(word).append(" ").append(subJ);
                                subI.add(sb.toString());
                            }
                        }
                    }
                }
                dp[i] = subI;
            }
            return dp[0];
        }


    }

    public static void main(String[] args) {
        Solution s = new Solution2();

        String s1 = "catsanddog";
        List<String> l1 = ArraysUtil.string2List("cat", "cats", "and", "sand", "dog");
        String s2 = "pineapplepenapple";
        List<String> l2 = ArraysUtil.string2List("apple", "pen", "applepen", "pine", "pineapple");
        String s3 = "catsandog";
        List<String> l3 = ArraysUtil.string2List("cats", "dog", "sand", "and", "cat");

        println(s.wordBreak(s1, l1));
        println(s.wordBreak(s2, l2));
        println(s.wordBreak(s3, l3));
    }
}
