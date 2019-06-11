package _java;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given two binary trees, write a function to check if they are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 * <p>
 * Example 1:
 *
 * <pre>
 * Input:     1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * Output: true
 * </pre>
 * <p>
 * Example 2:
 * <pre>
 * Input:     1         1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 * Output: false
 * </pre>
 *
 * <p>
 * Example 3:
 * <pre>
 * Input:     1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * Output: false
 * </pre>
 */
public class _0100SameTree____MultiS extends BaseTree {

    private abstract static class Solution {
        public abstract boolean isSameTree(TreeNode p, TreeNode q);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Same Tree.
     * Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Same Tree.
     */
    private static class Solution1 extends Solution {

        @Override
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            if (p.val != q.val) {
                return false;
            }

            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();
        TreeNode p1 = TreeUtil.generateACompleteBT(new int[]{1, 2, 3}), q1 = TreeUtil.generateACompleteBT(new int[]{1, 2, 3});
        TreeNode p2 = TreeUtil.generateACompleteBT(new Integer[]{1, 2}), q2 = TreeUtil.generateACompleteBT(new Integer[]{1, null, 2});
        TreeNode p3 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 1}), q3 = TreeUtil.generateACompleteBT(new Integer[]{1, 1, 2});

        println(s.isSameTree(p1, q1)); // true
        println(s.isSameTree(p2, q2)); // false
        println(s.isSameTree(p3, q3)); // false

    }
}
