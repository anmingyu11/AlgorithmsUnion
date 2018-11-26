package bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import base.Base;

public class WordLadder extends Base {
    static class Solution1 {

        public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
            boolean hasEnd = false;

            Set<String> wordDict = new HashSet<>();
            // Detect endWord has or not
            for (String word : wordList) {
                //Todo 其实这段是影响效率的。
                if (!hasEnd && word.equals(endWord)) {
                    hasEnd = true;
                }
                wordDict.add(word);
            }

            return hasEnd ? ladderLength(beginWord, endWord, wordDict) : 0;
        }

        private static int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
            int distance = 1;
            Set<String> reached = new HashSet<>();
            reached.add(beginWord);

            while (!reached.contains(endWord)) {
                Set<String> lay = new HashSet<>();

                // 遍历字典
                for (String each : reached) {
                    // 字典中的一个单词的某一个字符
                    for (int i = 0; i < each.length(); ++i) {
                        char[] eachC = each.toCharArray();
                        // 替换
                        for (char c = 'a'; c <= 'z'; ++c) {
                            eachC[i] = c;
                            String s = new String(eachC);
                            if (wordDict.contains(s)) {
                                //如果包含，添加到lay中
                                lay.add(s);
                                wordDict.remove(s);
                            }
                        }
                    }
                }

                if (reached.size() == 0) {
                    return 0;
                }
                reached = lay;
                ++distance;
            }

            return distance;
        }
    }

    public static void main(String[] args) {
        String[] str1s = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        String[] str2s = new String[]{"hot", "dot", "dog", "lot", "log"};
        List<String> wordList1 = Arrays.asList(str1s);
        List<String> wordList2 = Arrays.asList(str2s);

        println(Solution1.ladderLength("hot", "cog", wordList1));
        println(Solution1.ladderLength("hot", "cog", wordList2));
    }
}
