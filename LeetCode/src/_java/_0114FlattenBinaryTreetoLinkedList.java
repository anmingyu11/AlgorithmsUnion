package _java;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * 114. Flatten Binary Tree to Linked List
 * Medium
 * <p>
 * 1498
 * <p>
 * 192
 * <p>
 * Favorite
 * <p>
 * Share
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example, given the following tree:
 * <pre>
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * </pre>
 * The flattened tree should look like:
 * <pre>
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 * </pre>
 */
public class _0114FlattenBinaryTreetoLinkedList extends BaseTree {
    private abstract static class Solution {
        public abstract void flatten(TreeNode root);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Flatten Binary Tree to Linked List.
     * Memory Usage: 35.7 MB, less than 99.95% of Java online submissions for Flatten Binary Tree to Linked List.
     */
    private static class Solution1 extends Solution {

        private TreeNode prev;

        @Override
        public void flatten(TreeNode root) {
            prev = null;
            aux(root);
        }

        private void aux(TreeNode t) {
            if (t == null) {
                return;
            }
            aux(t.right);
            aux(t.left);
            t.right = prev;
            t.left = null;
            prev = t;
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 5, 3, 4, null, 6});

        Solution s = new Solution1();
        s.flatten(t1);
        println(t1);
    }
}
