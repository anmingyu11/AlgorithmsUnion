package _java;

import java.util.LinkedList;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <pre>
 * Example 1:
 *
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 * </pre>
 * <pre>
 * Example 2:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 *
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * </pre>
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class _0098ValidateBinarySearchTree extends BaseTree {

    private abstract static class Solution {
        public abstract boolean isValidBST(TreeNode root);
    }

    /**
     * Runtime: 1 ms, faster than 36.93% of Java online submissions for Validate Binary Search Tree.
     * Memory Usage: 39.2 MB, less than 76.09% of Java online submissions for Validate Binary Search Tree.
     */
    private static class Solution1 extends Solution {

        private LinkedList<Integer> queue;

        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            queue = new LinkedList<>();
            inorderAux(root);
            int last = queue.removeFirst();
            while (!queue.isEmpty()) {
                int next = queue.removeFirst();
                if (next <= last) {
                    return false;
                }
                last = next;
            }
            return true;
        }

        private void inorderAux(TreeNode t) {
            if (t == null) {
                return;
            }
            inorderAux(t.left);
            queue.add(t.val);
            inorderAux(t.right);
        }

    }

    /**
     * optimized of S1
     * <p>
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Validate Binary Search Tree.
     * Memory Usage: 38.6 MB, less than 79.15% of Java online submissions for Validate Binary Search Tree.
     */
    private static class Solution2 extends Solution {

        private Integer last;

        public boolean isValidBST(TreeNode root) {
            last = null;
            return inorderAux(root);
        }

        private boolean inorderAux(TreeNode t) {
            if (t == null) {
                return true;
            }
//            if (!inorderAux(t.left)) {
//                return false;
//            }
//            if (last != null && t.val <= last) {
//                return false;
//            }
//            last = t.val;
//            if (!inorderAux(t.right)) {
//                return false;
//            }
            if (!inorderAux(t.left) || (last != null && t.val <= last)) {
                return false;
            }
            last = t.val;
            return inorderAux(t.right);
        }
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Validate Binary Search Tree.
     * Memory Usage: 38.8 MB, less than 77.84% of Java online submissions for Validate Binary Search Tree.
     */
    private static class Solution3 extends Solution {

        public boolean isValidBST(TreeNode root) {
            return isBST(root, null, null);
        }

        private boolean isBST(TreeNode t, Integer minLim, Integer maxLim) {
            if (t == null) {
                return true;
            }
            int v = t.val;
            if (maxLim != null && v >= maxLim || minLim != null && v <= minLim) {
                return false;
            }
            return isBST(t.left, minLim, t.val) && isBST(t.right, t.val, maxLim);
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{2, 1, 3});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{5, 1, 4, null, null, 3, 6});
        TreeNode t3 = TreeUtil.generateACompleteBT(new Integer[]{10, 5, 15, null, null, 6, 20});
        TreeNode t4 = TreeUtil.generateACompleteBT(new Integer[]{1, 1});
        TreeNode t5 = TreeUtil.generateACompleteBT(new Integer[]{Integer.MIN_VALUE});

        Solution s = new Solution3();

        println(s.isValidBST(t1));//t
        println(s.isValidBST(t2));//f
        println(s.isValidBST(t3));//f
        println(s.isValidBST(t4));//f
        println(s.isValidBST(t5));//t
    }
}
