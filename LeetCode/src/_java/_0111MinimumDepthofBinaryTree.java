package _java;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
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
public class _0111MinimumDepthofBinaryTree extends BaseTree {

    private abstract static class Solution {
        public abstract int minDepth(TreeNode root);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Depth of Binary Tree.
     * Memory Usage: 39.3 MB, less than 97.45% of Java online submissions for Minimum Depth of Binary Tree.
     */
    private static class Solution1 extends Solution {

        @Override
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return depth(root);
        }

        private int depth(TreeNode t) {
            if (t.left == null && t.right == null) {
                return 1;
            }
            int l = t.left != null ? depth(t.left) : Integer.MAX_VALUE;
            int r = t.right != null ? depth(t.right) : Integer.MAX_VALUE;
            return 1 + Math.min(l, r);
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{3, 9, 20, null, null, 15, 7});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{1, 2});
        TreeNode t3 = TreeUtil.generateACompleteBT(new Integer[]{1, null, 2});

        Solution s = new Solution1();
        println(s.minDepth(t1)); // 2
        println(s.minDepth(t2)); // 2
        println(s.minDepth(t3)); // 2
    }
}
