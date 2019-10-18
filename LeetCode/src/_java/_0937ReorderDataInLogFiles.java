package _java;

import java.util.Arrays;
import java.util.Comparator;

import base.Base;

/**
 * You have an array of logs.  Each log is a space delimited string of words.
 * <p>
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * <p>
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * <p>
 * We will call these two varieties of logs letter-logs and digit-logs.
 * It is guaranteed that each log has at least one word after its identifier.
 * <p>
 * Reorder the logs so that all of the letter-logs come before any digit-log.
 * The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
 * The digit-logs should be put in their original order.
 * <p>
 * Return the final order of the logs.
 * <p>
 * Example 1:
 * <p>
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * <p>
 * Constraints:
 * <p>
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class _0937ReorderDataInLogFiles extends Base {

    private static abstract class Solution {
        public abstract String[] reorderLogFiles(String[] logs);
    }

    /**
     * Runtime: 8 ms, faster than 57.24% of Java online submissions for Reorder Data in Log Files.
     * Memory Usage: 37.1 MB, less than 97.06% of Java online submissions for Reorder Data in Log Files.
     * 提干很难理解
     */
    private static class Solution1 extends Solution {

        public String[] reorderLogFiles(String[] logs) {
            Arrays.sort(logs, new Comparator<String>() {
                public int compare(String log1, String log2) {
                    String[] split1 = log1.split(" ", 2);
                    String[] split2 = log2.split(" ", 2);

                    boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
                    boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

                    if (!isDigit1 && !isDigit2) {
                        int cmp = split1[1].compareTo(split2[1]);
                        if (cmp == 0) {
                            return split1[0].compareTo(split2[0]);
                        }
                        return cmp;
                    }

                    return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
                }
            });
            return logs;
        }

    }

    /**
     * Runtime: 4 ms, faster than 73.36% of Java online submissions for Reorder Data in Log Files.
     * Memory Usage: 38.3 MB, less than 85.29% of Java online submissions for Reorder Data in Log Files.
     */
    private static class Solution2 extends Solution {

        public String[] reorderLogFiles(String[] logs) {
            final int n = logs.length;
            String[] res = new String[n];
            int pLet = 0;
            int pDig = n - 1;
            for (int i = n - 1; i >= 0; --i) {
                String log = logs[i];
                char first = log.charAt(log.indexOf(" ") + 1);
                if (first >= '0' && first <= '9') {
                    res[pDig--] = log;
                } else {
                    res[pLet++] = log;
                }
            }

            Arrays.sort(res, 0, pLet, new Comparator<String>() {
                public int compare(String log1, String log2) {
                    // 正则表达式太慢。
                    String[] split1 = log1.split(" ", 2);
                    String[] split2 = log2.split(" ", 2);
                    int cmp = split1[1].compareTo(split2[1]);
                    return cmp != 0 ? cmp : split1[0].compareTo(split2[0]);
                }
            });

            return res;
        }

    }

    /**
     * Runtime: 2 ms, faster than 99.39% of Java online submissions for Reorder Data in Log Files.
     * Memory Usage: 38.6 MB, less than 73.53% of Java online submissions for Reorder Data in Log Files.
     * 改进版
     */
    private static class Solution3 extends Solution {

        public String[] reorderLogFiles(String[] logs) {

            final int n = logs.length;
            String[] res = new String[n];
            int pLet = 0;
            int pDig = n - 1;
            for (int i = n - 1; i >= 0; --i) {
                String log = logs[i];
                char first = log.charAt(log.indexOf(" ") + 1);
                if (first >= '0' && first <= '9') {
                    res[pDig--] = log;
                } else {
                    res[pLet++] = log;
                }
            }

            Arrays.sort(res, 0, pLet, new Comparator<String>() {
                public int compare(String log1, String log2) {
                    int index1 = log1.indexOf(" ");
                    int index2 = log2.indexOf(" ");
                    return log1.substring(index1).compareTo(log2.substring(index2));
                }
            });

            return res;
        }
    }

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};

        Solution s = new Solution3();

        printArr(s.reorderLogFiles(logs));
    }

}