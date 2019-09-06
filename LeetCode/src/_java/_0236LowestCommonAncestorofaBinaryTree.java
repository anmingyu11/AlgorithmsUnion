package _java;

import base.BaseTree;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * <p>
 * Note:
 * <p>
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the binary tree.
 */
public class _0236LowestCommonAncestorofaBinaryTree extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q);
    }

    /**
     * Runtime: 6 ms, faster than 51.61% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     * Memory Usage: 35.6 MB, less than 5.04% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     * 非常有技巧,很值得学习.
     * 应该算是准状态机??
     */
    private static class Solution1 extends Solution {

        private TreeNode ans;

        @Override
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            ans = null;
            lcaAux(root, p, q);
            return ans;
        }

        private boolean lcaAux(TreeNode x, TreeNode p, TreeNode q) {
            if (x == null) {
                return false;
            }
            int l = lcaAux(x.left, p, q) ? 1 : 0;
            int r = lcaAux(x.right, p, q) ? 1 : 0;
            int mid = (x == p) || (x == q) ? 1 : 0;

            if (l + r + mid == 2) {
                ans = x;
            }

            return (l + r + mid) > 0;
        }

    }

    public static void main(String[] args) {

    }
}
