package _java;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * <pre>
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * </pre>
 * <p>
 * But the following [1,2,2,null,3,null,3] is not:
 * <pre>
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * </pre>
 * <p>
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */
public class _0101SymmetricTree extends BaseTree {

    private abstract static class Solution {
        public abstract boolean isSymmetric(TreeNode root);
    }

    private static class Solution1 extends Solution {

        @Override
        public boolean isSymmetric(TreeNode root) {
            return isMirror(root, root);
        }

        private boolean isMirror(TreeNode t1, TreeNode t2) {
            if (t1 == null && t2 == null) {
                return true;
            }
            if (t1 == null || t2 == null) {
                return false;
            }
            return (t1.val == t2.val)
                    && isMirror(t1.left, t2.right)
                    && isMirror(t1.right, t2.left);
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 2, 3, 4, 4, 3});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 2, null, 3, null, 3});
        TreeNode t3 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 2, 2, null, 2});
        Solution s = new Solution1();

        println(s.isSymmetric(t1));
        println(s.isSymmetric(t2));
        println(s.isSymmetric(t3));
    }

}
