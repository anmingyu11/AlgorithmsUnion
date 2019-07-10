package _java;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree and a sum,
 * determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given the below binary tree and sum = 22,
 * <pre>
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * </pre>
 */
public class _0112PathSum extends BaseTree {

    private abstract static class Solution {
        public abstract boolean hasPathSum(TreeNode root, int sum);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Path Sum.
     * Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Path Sum.
     */
    private static class Solution1 extends Solution {

        private int target;
        private boolean has;

        @Override
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            has = false;
            target = sum;
            hasAux(root, 0);
            return has;
        }

        private void hasAux(TreeNode t, int sum) {
            if (t == null) {
                return;
            }
            if (has) {
                return;
            }
            int cur = sum + t.val;
            if (t.left == null && t.right == null && cur == target) {
                has = true;
                return;
            }
            hasAux(t.left, cur);
            hasAux(t.right, cur);
        }
    }

    /**
     * This is Elegant.
     */
    private static class Solution2 extends Solution {

        @Override
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            sum -= root.val;
            if (root.left == null && root.right == null) {
                return sum == 0;
            }
            return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{1, 2});

        Solution s = new Solution1();

        println(s.hasPathSum(t1, 22));
        println(s.hasPathSum(t2, 1));
    }
}
