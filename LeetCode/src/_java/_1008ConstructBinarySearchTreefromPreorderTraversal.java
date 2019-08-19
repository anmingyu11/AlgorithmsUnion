package _java;

import base.BaseTree;

/**
 * Return the root node of a binary search tree that matches the given preorder traversal.
 * <p>
 * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.
 * left has a value < node.val, and any descendant of node.right has a value > node.val.
 * Also recall that a preorder traversal displays the value of the node first,
 * then traverses node.left, then traverses node.right.)
 * <p>
 * Example 1:
 * <pre>
 * Input: [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 * </pre>
 */
public class _1008ConstructBinarySearchTreefromPreorderTraversal extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode bstFromPreorder(int[] preorder);
    }

    /**
     * Very Subtle
     * Runtime: 1 ms, faster than 67.87% of Java online submissions for Construct Binary Search Tree from Preorder Traversal.
     * Memory Usage: 35.7 MB, less than 99.70% of Java online submissions for Construct Binary Search Tree from Preorder Traversal.
     */
    private static class Solution1 extends Solution {

        private int idx;
        private int[] pre;

        public TreeNode bstFromPreorder(int[] preorder) {
            idx = 0;
            pre = preorder;
            return tAux(null, null);
        }

        private TreeNode tAux(Integer lo, Integer hi) {
            if (idx >= pre.length) {
                return null;
            }
            int v = pre[idx];
            if (lo != null && v < lo || hi != null && v > hi) {
                return null;
            }
            TreeNode t = new TreeNode(v);
            ++idx;
            t.left = tAux(lo, v);
            t.right = tAux(v, hi);
            return t;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution1();
        println(s.bstFromPreorder(new int[]{8, 5, 1, 7, 10, 12}));
    }

}
