package _java;

import base.Base;

/**
 * Given n, how many structurally unique BST's (binary search trees)
 * that store values 1 ... n?
 * <p>
 * Example:
 * <p>
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 */
public class _0096UniqueBinarySearchTrees extends Base {

    private abstract static class Solution {
        public abstract int numTrees(int n);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Binary Search Trees.
     * Memory Usage: 32 MB, less than 35.90% of Java online submissions for Unique Binary Search Trees.
     * Brilliant
     */
    private static class Solution1 extends Solution {

        @Override
        public int numTrees(int n) {
            int[] G = new int[n + 1];
            G[0] = 1;
            G[1] = 1;

            for (int i = 2; i <= n; ++i) {
                for (int j = 1; j <= i; ++j) {
                    G[i] += G[j - 1] * G[i - j];
                }
            }
            return G[n];
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        println(s.numTrees(3)); // 5
    }
}
