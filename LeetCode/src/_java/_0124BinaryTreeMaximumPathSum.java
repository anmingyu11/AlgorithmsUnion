package _java;

import base.BaseTree;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 * <p>
 * For this problem,
 * a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 * <p>
 * Example 1:
 * <pre>
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * </pre>
 * Example 2:
 * <pre>
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 * </pre>
 */
public class _0124BinaryTreeMaximumPathSum extends BaseTree {

    private abstract static class Solution {
        public abstract int maxPathSum(TreeNode root);
    }

    /**
     * Runtime: 1 ms, faster than 99.82% of Java online submissions for Binary Tree Maximum Path Sum.
     * Memory Usage: 39.9 MB, less than 99.54% of Java online submissions for Binary Tree Maximum Path Sum.
     */
    private static class Solution1 extends Solution {

        private int maxSum = Integer.MIN_VALUE;

        @Override
        public int maxPathSum(TreeNode root) {
            maxPath(root);
            return maxSum;
        }

        private int maxPath(TreeNode x) {
            if (x == null) {
                // termination condition
                return 0;
            }
            // single left path
            int l = Math.max(maxPath(x.left), 0);
            // single right path
            int r = Math.max(maxPath(x.right), 0);
            // Max path through x in subtree x, see if this is the max path
            maxSum = Math.max(maxSum, l + r + x.val);
            // if not the max, give the single path to
            return x.val + Math.max(l, r);
        }

    }

    public static void main(String[] args) {

    }
}
