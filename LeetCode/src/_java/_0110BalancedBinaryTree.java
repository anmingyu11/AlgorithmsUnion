package _java;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree, determine if it is isBalancedAux-balanced.
 * <p>
 * For this problem, a isBalancedAux-balanced binary tree is defined as:
 * <p>
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Example 1:
 * <pre>
 * Given the following tree [3,9,20,null,null,15,7]:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Return true.
 * </pre>
 * <p>
 * Example 2:
 * <pre>
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * Return false.
 * </pre>
 */
public class _0110BalancedBinaryTree extends BaseTree {

    private abstract static class Solution {
        public abstract boolean isBalanced(TreeNode root);
    }

    /**
     * Runtime: 1 ms, faster than 82.50% of Java online submissions for Balanced Binary Tree.
     * Memory Usage: 37.3 MB, less than 99.27% of Java online submissions for Balanced Binary Tree.
     * 我能想到的最好
     * 1. 思想基于求树的高度.
     * 2. 利用树结点的附加信息,存储对应结点的高度.
     * 3. 后序遍历求解.
     * 4. 利用短路剪枝加快速度,一个树结点的两个结点其中一个不是平衡的,那么整棵树都不是平衡的.
     */
    private static class Solution1 extends Solution {

        @Override
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            int l = 0, r = 0;
            boolean lb = true, lr = true;
            if (root.left != null) {
                lb = isBalanced(root.left);
                l = root.left.val;
                if (!lb) {
                    return lb;
                }
            }
            if (root.right != null) {
                lr = isBalanced(root.right);
                r = root.right.val;
                if (!lr) {
                    return lr;
                }
            }
            root.val = 1 + Math.max(l, r);
            return Math.abs(l - r) <= 1;
        }

    }

    /**
     *
     */
    private static class Solution2 extends Solution {

        @Override
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            return height(root) != -1;
        }

        private int height(TreeNode x) {
            if (x == null) {
                return 0;
            }
            int l = height(x.left);
            if (l == -1) {
                return -1;
            }
            int r = height(x.right);
            if (r == -1) {
                return -1;
            }
            if (Math.abs(l - r) > 1) {
                return -1;
            }
            return 1 + Math.max(l, r);
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{3, 9, 20, null, null, 15, 7});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4});
        TreeNode t3 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 2, 3, null, null, 3, 4, null, null, null, null, null, null, 4});

        Solution s = new Solution1();

        println(s.isBalanced(t1)); // T
        println(t1);
        println(s.isBalanced(t2)); // F
        println(t2);
        println(s.isBalanced(t3)); // F
        println(t3);
    }
}
