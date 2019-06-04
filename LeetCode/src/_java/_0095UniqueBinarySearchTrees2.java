package _java;

import java.util.LinkedList;
import java.util.List;

import base.BaseTree;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 * <p>
 * Example:
 * <p>
 * Input: 3
 * Output:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 */
public class _0095UniqueBinarySearchTrees2 extends BaseTree {

    private abstract static class Solution {
        public abstract List<TreeNode> generateTrees(int n);
    }

    /**
     * Runtime: 2 ms, faster than 65.68% of Java online submissions for Unique Binary Search Trees II.
     * Memory Usage: 37.4 MB, less than 90.74% of Java online submissions for Unique Binary Search Trees II.
     */
    private static class Solution1 extends Solution {

        @Override
        public List<TreeNode> generateTrees(int n) {
            if (n == 0) {
                return new LinkedList<>();
            }
            return generateTrees(1, n);
        }

        public List<TreeNode> generateTrees(int start, int end) {
            LinkedList<TreeNode> trees = new LinkedList<>();
            if (start > end) {
                trees.add(null);
                return trees;
            }
            for (int i = start; i <= end; ++i) {
                List<TreeNode> lTrees = generateTrees(start, i - 1);
                List<TreeNode> rTrees = generateTrees(i + 1, end);
                for (TreeNode lt : lTrees) {
                    for (TreeNode rt : rTrees) {
                        TreeNode cur = new TreeNode(i);
                        cur.left = lt;
                        cur.right = rt;
                        trees.add(cur);
                    }
                }
            }
            return trees;
        }

    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Unique Binary Search Trees II.
     * Memory Usage: 36.5 MB, less than 94.93% of Java online submissions for Unique Binary Search Trees II.
     */
    private static class Solution2 extends Solution {

        private List<TreeNode>[][] dp;

        public List<TreeNode> generateTrees(int n) {
            if (n == 0) {
                return new LinkedList<>();
            }
            dp = new LinkedList[n + 1][n + 1];
            return generateTrees(1, n);
        }

        public List<TreeNode> generateTrees(int start, int end) {
            LinkedList<TreeNode> trees = new LinkedList<>();
            if (start > end) {
                trees.add(null);
                return trees;
            } else if (dp[start][end] != null) {
                return dp[start][end];
            }
            for (int i = start; i <= end; ++i) {
                List<TreeNode> lTrees = generateTrees(start, i - 1);
                List<TreeNode> rTrees = generateTrees(i + 1, end);
                for (TreeNode lt : lTrees) {
                    for (TreeNode rt : rTrees) {
                        TreeNode cur = new TreeNode(i);
                        cur.left = lt;
                        cur.right = rt;
                        trees.add(cur);
                    }
                }
            }
            return dp[start][end] = trees;
        }
    }

    public static void main(String[] args) {
        int n = 3;

        Solution s = new Solution1();
        println(s.generateTrees(n));
    }
}
