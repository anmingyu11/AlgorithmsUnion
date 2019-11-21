package _java;

import base.Base;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern
 * on a given number of rows like this:
 * (you may want to display this pattern in a fixed
 * font for better legibility)
 * <pre>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * </pre>
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make
 * this conversion given a number of rows:
 * <p>
 * string convert(string s, int numRows);
 * Example 1:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * <pre>
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * </pre>
 */
public class _0006ZigZagConversion extends Base {

    private static abstract class Solution {
        public abstract String convert(String s, int numRows);
    }

    /**
     * Runtime: 14 ms, faster than 32.75% of Java online submissions for ZigZag Conversion.
     * Memory Usage: 37.5 MB, less than 98.94% of Java online submissions for ZigZag Conversion.
     */
    private static class Solution1 extends Solution {

        public String convert(String s, int numRows) {
            final int n = s.length();
            if (n <= numRows || numRows == 1) {
                return s;
            }
            final int cols = ((numRows - 1) * n / (2 * numRows - 2)) + 1;
            char[][] zigzag = new char[numRows][cols];
            int direct = -1; // -1 down, 0 diag
            int r = 0, c = 0;
            for (int i = 0; i < n; ++i) {
                if (direct < 0) {
                    // down
                    zigzag[r][c] = s.charAt(i);
                    if (r < numRows - 1) {
                        ++r;
                    } else {
                        --r;
                        ++c;
                        direct = 0;
                    }
                } else {
                    // diag
                    zigzag[r][c] = s.charAt(i);
                    if (r > 0) {
                        --r;
                        ++c;
                    } else {
                        direct = -1;
                        ++r;
                    }
                }
                //print2DArr(zigzag);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numRows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    if (zigzag[i][j] != 0) {
                        sb.append(zigzag[i][j]);
                    }
                }
            }
            return sb.toString();
        }
    }

    /**
     * Runtime: 5 ms, faster than 75.57% of Java online submissions for ZigZag Conversion.
     * Memory Usage: 39.4 MB, less than 68.09% of Java online submissions for ZigZag Conversion.
     * Amazing sort by row.
     * 稀疏矩阵
     */
    private static class Solution2 extends Solution {
        @Override
        public String convert(String s, int numRows) {
            final int n = s.length();
            if (n <= numRows || numRows == 1) {
                return s;
            }
            StringBuilder[] rows = new StringBuilder[numRows];
            int r = 0;
            boolean down = true;
            for (int i = 0; i < n; ++i) {
                if (rows[r] == null) {
                    rows[r] = new StringBuilder();
                }
                rows[r].append(s.charAt(i));
                r += down ? 1 : -1;
                if (r == numRows - 1) {
                    down = false;
                } else if (r == 0) {
                    down = true;
                }
            }

            StringBuilder res = new StringBuilder();
            for (StringBuilder sb : rows) {
                res.append(sb);
            }
            return res.toString();
        }
    }

    private static class Solution3 extends Solution {

        public String convert(String s, int numRows) {
            return null;
        }

    }

    public static void main(String[] args) {
        String s1 = "PAYPALISHIRING";
        int nr1 = 3;
        String s2 = "PAYPALISHIRING";
        int nr2 = 4;
        String s3 = "";
        int nr3 = 1;
        String s4 = "ABC";
        int nr4 = 2;
        String s5 = "ABCD";
        int nr5 = 2;

        Solution s = new Solution2();

        int n = s1.length();
        println(s.convert(s1, nr1)); // PAHNAPLSIIGYIR
        println(s.convert(s2, nr2)); // PINALSIGYAHRPI
        println(s.convert(s3, nr3)); //
        println(s.convert(s4, nr4)); // ACB
        println(s.convert(s5, nr5)); // ACBD
    }
}
