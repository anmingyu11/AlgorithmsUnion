package _java;

import base.Base;
import base.util.ArraysUtil;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 */
public class _0014LongestCommonPrefix extends Base {

    private abstract static class Solution {
        public abstract String longestCommonPrefix(String[] strs);
    }

    /**
     * Horizontal Scanning
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Longest Common Prefix.
     * Memory Usage: 36.2 MB, less than 95.50% of Java online submissions for Longest Common Prefix.
     */
    private static class Solution1 extends Solution {

        @Override
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) {
                return "";
            }
            String prefix = strs[0];
            for (int i = 0; i < strs.length; ++i) {
                while (strs[i].indexOf(prefix) != 0) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                }
                if (prefix.isEmpty()) {
                    return "";
                }
            }

            return prefix;
        }

    }

    /**
     * Vertical scanning
     * <p>
     * Runtime: 1 ms, faster than 89.01% of Java online submissions for Longest Common Prefix.
     * Memory Usage: 35 MB, less than 98.40% of Java online submissions for Longest Common Prefix.
     */
    private static class Solution2 extends Solution {

        @Override
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) {
                return "";
            }

            for (int i = 0; i < strs[0].length(); ++i) {
                for (int j = 1; j < strs.length; ++j) {
                    if (strs[j].length() == i || strs[0].charAt(i) != strs[j].charAt(i)) {
                        return strs[0].substring(0, i);
                    }
                }
            }

            return strs[0];
        }

    }

    /**
     * Divide and conquer
     * Runtime: 1 ms, faster than 89.01% of Java online submissions for Longest Common Prefix.
     * Memory Usage: 36.5 MB, less than 95.06% of Java online submissions for Longest Common Prefix.
     */
    private static class Solution3 extends Solution {

        @Override
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) {
                return "";
            }
            return longestCommonPrefixAux(strs, 0, strs.length - 1);
        }

        private String longestCommonPrefixAux(String[] strs, int l, int r) {
            if (l == r) {
                return strs[l];
            }
            int mid = (l + r) / 2;
            String lcpLeft = longestCommonPrefixAux(strs, l, mid);
            String lcpRight = longestCommonPrefixAux(strs, mid + 1, r);
            return commonPrefix(lcpLeft, lcpRight);
        }

        private String commonPrefix(String left, String right) {
            int min = left.length() < right.length() ? left.length() : right.length();
            for (int i = 0; i < min; ++i) {
                if (left.charAt(i) != right.charAt(i)) {
                    return left.substring(0, i);
                }
            }
            return left.substring(0, min);
        }

    }

    /**
     * 二分查找
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Longest Common Prefix.
     * Memory Usage: 36.8 MB, less than 94.77% of Java online submissions for Longest Common Prefix.
     */
    private static class Solution4 extends Solution {

        @Override
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) {
                return "";
            }
            int minLen = Integer.MAX_VALUE;
            for (String str : strs) {
                minLen = Math.min(str.length(), minLen);
            }
            int lo = 1;
            int hi = minLen;

            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                String prefix = strs[0].substring(0,mid);
                if (isCommonPrefix(strs, prefix)) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            return strs[0].substring(0, (lo + hi) >>> 1);
        }

        private boolean isCommonPrefix(String[] strs, String prefix) {
            for (int i = 1; i < strs.length; ++i) {
                if (!strs[i].startsWith(prefix)) {
                    return false;
                }
            }
            return true;
        }

    }

    public static void main(String[] args) {
        String[] l1 = ArraysUtil.string2Arr("flower", "flow", "flight");
        String[] l2 = ArraysUtil.string2Arr("dog", "racecar", "car");

        Solution s = new Solution4();

        println("\"" + s.longestCommonPrefix(l1) + "\"");// "fl"
        println("\"" + s.longestCommonPrefix(l2) + "\"");// ""

    }
}
