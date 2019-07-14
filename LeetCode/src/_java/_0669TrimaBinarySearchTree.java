package _java;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary search tree and the lowest and highest boundaries as L and R,
 * trim the tree so that all its elements lies in [L, R] (R >= L).
 * You might need to change the root of the tree,
 * so the result should return the new root of the trimmed binary search tree.
 * <p>
 * Example 1:
 * <pre>
 * Input:
 *     1
 *    / \
 *   0   2
 *
 *   L = 1
 *   R = 2
 *
 * Output:
 *     1
 *       \
 *        2
 * </pre>
 * Example 2:
 * <pre>
 * Input:
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *
 *   L = 1
 *   R = 3
 *
 * Output:
 *       3
 *      /
 *    2
 *   /
 *  1
 * </pre>
 */
public class _0669TrimaBinarySearchTree extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode trimBST(TreeNode root, int L, int R);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Trim a Binary Search Tree.
     * Memory Usage: 37.9 MB, less than 97.64% of Java online submissions for Trim a Binary Search Tree.
     * 我想的太麻烦了,二分查找树的性质啊性质啊,加上前序遍历.
     */
    private static class Solution1 extends Solution {

        @Override
        public TreeNode trimBST(TreeNode root, int L, int R) {
            if (root == null) {
                return null;
            }
            if (root.val < L) {
                return trimBST(root.right, L, R);
            }
            if (root.val > R) {
                return trimBST(root.left, L, R);
            }
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{1, 0, 2});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{3, 0, 4, null, 2, null, null, null, null, 1});
    }
}
