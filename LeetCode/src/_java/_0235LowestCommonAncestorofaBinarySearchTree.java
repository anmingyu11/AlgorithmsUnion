package _java;

import base.BaseTree;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * Example 2:
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 */
public class _0235LowestCommonAncestorofaBinarySearchTree extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q);
    }

    /**
     * Runtime: 4 ms, faster than 100.00% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
     * Memory Usage: 36.5 MB, less than 5.04% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
     * 利用二叉树的性质.
     */
    private static class Solution1 extends Solution {

        @Override
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            int rV = root.val;
            int pV = p.val;
            int qV = q.val;
            if (pV > rV && qV > rV) {
                return lowestCommonAncestor(root.right, p, q);
            }
            if (pV < rV && qV < rV) {
                return lowestCommonAncestor(root.left, p, q);
            }

            return root;
        }

    }

    public static void main(String[] args) {
    }

}
