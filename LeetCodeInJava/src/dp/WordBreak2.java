package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import base.Base;

public class WordBreak2 extends Base {

    //backtrack  LTE
    static class Solution1 {

        public List<String> wordBreak(String s, List<String> wordDict) {
            // avoid LTE
            Set<String> set = new HashSet<>(wordDict);
            List<String> res = new LinkedList<>();
            wordBreakAux(s, new StringBuilder(), res, set);
            return res;
        }

        private void wordBreakAux(String s, StringBuilder res, List<String> resList, Set<String> set) {
            if (s.length() == 0) {
                resList.add(res.substring(0, res.length() - 1));
                return;
            }

            for (int i = 0; i <= s.length(); ++i) {
                String sub = s.substring(0, i);
                if (set.contains(sub)) {
                    res.append(sub).append(" ");
                    wordBreakAux(s.substring(i, s.length()), res, resList, set);
                    res.delete(res.length() - sub.length() - 1, res.length());
                }
            }
        }

    }

    //Todo 未成功的自底向上法
    static class Solution2 {
        StringBuilder[] dp;

        public List<String> wordBreak(String s, List<String> wordDict) {
            dp = new StringBuilder[s.length() + 1];
            Set<String> set = new HashSet<>(wordDict);
            wordBreakAux(s, null, set);
            return generateRes();
        }

        private void wordBreakAux(String s, List<String> res, Set<String> wordDict) {
            for (int i = 0; i < s.length(); ++i) {
                for (String word : wordDict) {
                    final int wordLen = i + word.length();
                    if (wordLen > s.length()) {
                        continue;
                    }
                    String sub = s.substring(i, wordLen);
                    if (sub.equals(word)) {
                        if (dp[wordLen] == null) {
                            dp[wordLen] = new StringBuilder(sub + " ");
                        } else {
                            dp[wordLen].append(sub + " ");
                        }
                    }
                }
            }

        }

        private List<String> generateRes() {
            final int n = dp.length - 1;
            List<String> res = new LinkedList<>();
            if (dp[n] == null) {
                return res;
            }

            generateResAux(res, new StringBuilder(), 0);

            return res;
        }

        private void generateResAux(List<String> res, StringBuilder sb, int cur) {
            final int n = dp.length - 1;
            if (cur == n) {
                res.add(sb.toString());
                return;
            }
            for (int i = cur; i < n; ++i) {
                if (dp[i] != null) {
                    for (String word : dp[i].toString().split(" ")) {
                        sb.append(word);
                        generateResAux(res, sb, cur + word.length());
                        sb.delete(sb.length() - word.length(), sb.length());
                    }
                }
            }
        }

    }

    static class Solution3 {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Set<String> set = new HashSet<>(wordDict);
            return wordBreak(s, set);
        }

        public List<String> wordBreak(String s, Set<String> wordDict) {
            return dfs(s, wordDict, new HashMap<>());
        }

        // dfs function returns an array including all substrings derived from s.
        List<String> dfs(String s, Set<String> wordDict, HashMap<String, LinkedList<String>> map) {

            if (map.containsKey(s)) {
                return map.get(s);
            }

            LinkedList<String> res = new LinkedList<>();
            if (s.length() == 0) {
                //very subtle
                res.add("");
                return res;
            }

            for (String word : wordDict) {
                if (s.startsWith(word)) {
                    List<String> subList = dfs(s.substring(word.length()), wordDict, map);
                    for (String subWord : subList) {
                        //Use String builder make beats 38% to 50%
                        StringBuilder subRes = new StringBuilder(word);
                        String str = subRes.append(subWord.isEmpty() ? "" : " ").append(subWord).toString();
                        res.add(str);
                    }
                }
            }

            map.put(s, res);
            return res;
        }
    }

    public static void main(String[] args) {
        String s1 = "catsanddog";
        List<String> dictList1 = Arrays.asList("cat", "cats", "and", "sand", "dog");

        String s2 = "pineapplepenapple";
        List<String> dictList2 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");

        String s3 = "catsandog";
        List<String> dictList3 = Arrays.asList("cats", "dog", "sand", "and", "cat");

        println(new Solution3().wordBreak(s1, dictList1));
        println(new Solution3().wordBreak(s2, dictList2));
        println(new Solution3().wordBreak(s3, dictList3));
    }
}
