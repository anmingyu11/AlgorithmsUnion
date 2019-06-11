package _java;

import base.BaseTree;

/**
 * Invert a binary tree.
 *
 * <pre>
 * Example:
 *
 * Input:
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * Output:
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * </pre>
 * Trivia:
 * This problem was inspired by this original tweet by Max Howell:
 * <p>
 * Google: 90% of our engineers use the software you wrote (Homebrew),
 * but you can’t invert a binary tree on a whiteboard so f*** off.
 */
public class _0226InvertBinaryTree____MultiS extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode invertTree(TreeNode root);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
     * Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Invert Binary Tree.
     */
    private static class Solution1 extends Solution {

        @Override
        public TreeNode invertTree(TreeNode root) {
            return invertTreeAux(root);
        }

        private TreeNode invertTreeAux(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode right = invertTree(root.left);
            TreeNode left = invertTree(root.right);
            root.right = right;
            root.left = left;
            return root;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();
    }

}
