package _java;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * <p>
 * Example:
 * Given a binary tree
 * <pre>
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * </pre>
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class _0543DiameterofBinaryTree extends BaseTree {

    private abstract static class Solution {
        public abstract int diameterOfBinaryTree(TreeNode root);
    }

    /**
     * Runtime: 1 ms, faster than 36.04% of Java online submissions for Diameter of Binary Tree.
     * Memory Usage: 38.8 MB, less than 38.39% of Java online submissions for Diameter of Binary Tree.
     */
    private static class Solution1 extends Solution {

        private int max;

        @Override
        public int diameterOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            max = Integer.MIN_VALUE;
            tAux(root);
            diameter(root);
            return max;
        }

        private int diameter(TreeNode t) {
            if (t == null) {
                return 0;
            }
            int l = diameter(t.left);
            int r = diameter(t.right);
            max = Math.max(max, l + r);
            return t.val;
        }

        private int tAux(TreeNode t) {
            if (t == null) {
                return 0;
            }
            int l = tAux(t.left);
            int r = tAux(t.right);
            t.val = 1 + Math.max(l, r);
            return t.val;
        }

    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Diameter of Binary Tree.
     * Memory Usage: 38.3 MB, less than 67.64% of Java online submissions for Diameter of Binary Tree.
     */
    private static class Solution2 extends Solution {

        private int max;

        @Override
        public int diameterOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            max = Integer.MIN_VALUE;
            tAux(root);
            return max;
        }

        private int tAux(TreeNode t) {
            if (t == null) {
                return 0;
            }
            int l = tAux(t.left);
            int r = tAux(t.right);
            t.val = 1 + Math.max(l, r);
            max = Math.max(l + r, max);
            return t.val;
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 3, 4, 5});
        TreeNode t2 = TreeUtil.generateACompleteBT(new int[]{1, 2});

        Solution s = new Solution1();

        println(s.diameterOfBinaryTree(t1)); // 3
        println(s.diameterOfBinaryTree(t2)); // 1
    }
}
