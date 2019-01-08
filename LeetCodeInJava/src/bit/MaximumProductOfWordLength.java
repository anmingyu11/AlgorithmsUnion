package bit;

import base.Base;

public class MaximumProductOfWordLength extends Base {

    abstract static class Solution {
        public abstract int maxProduct(String[] words);
    }

    static class Solution1 extends Solution {

        private static final int ALPHABET_SIZE = 26;

        @Override
        public int maxProduct(String[] words) {
            final int wordsLen = words.length;
            boolean[][] set = new boolean[wordsLen][ALPHABET_SIZE];
            for (int i = 0; i < wordsLen; ++i) {
                String word = words[i];
                for (int j = 0; j < word.length(); ++j) {
                    int pos = word.charAt(j) - 'a';
                    set[i][pos] = true;
                }
            }

            int max = 0;
            for (int i = 0; i < wordsLen; ++i) {
                String word1 = words[i];
                for (int j = i + 1; j < wordsLen; ++j) {
                    String word2 = words[j];
                    boolean common = false;
                    for (int k = 0; k < ALPHABET_SIZE; ++k) {
                        if (set[i][k] && set[j][k]) {
                            common = true;
                            break;
                        }
                    }
                    if (!common) {
                        max = Math.max(max, word1.length() * word2.length());
                    }
                }
            }

            return max;
        }
    }

    static class Solution2 extends Solution {

        @Override
        public int maxProduct(String[] words) {
            final int wordsLen = words.length;
            final int[] set = new int[wordsLen];

            for (int i = 0; i < wordsLen; ++i) {
                String word = words[i];
                for (int j = 0; j < word.length(); ++j) {
                    int pos = word.charAt(j) - 'a';
                    set[i] |= 1 << pos;
                }
            }

            int max = 0;
            for (int i = 0; i < wordsLen; ++i) {
                int word1 = set[i];
                for (int j = i + 1; j < wordsLen; ++j) {
                    int word2 = set[j];
                    if ((word1 & word2) == 0) {
                        max = Math.max(max,words[i].length() * words[j].length());
                    }
                }
            }

            return max;
        }
    }

    public static void main(String[] args) {
        String[] ws1 = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        String[] ws2 = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        String[] ws3 = {"a", "aa", "aaa", "aaaa"};

        println(new Solution2().maxProduct(ws1));
        println(new Solution2().maxProduct(ws2));
        println(new Solution2().maxProduct(ws3));
    }

}
