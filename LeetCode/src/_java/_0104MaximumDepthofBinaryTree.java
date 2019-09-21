package _java;

import base.BaseTree;

/**
 * Given a binary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from
 * the root node down to the farthest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given binary tree [3,9,20,null,null,15,7],
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 */
public class _0104MaximumDepthofBinaryTree extends BaseTree {

    private abstract static class Solution {
        public abstract int maxDepth(TreeNode root);
    }

    private static class Solution1 extends Solution {

        @Override
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }

    }

    public static void main(String[] args) {

    }
}
