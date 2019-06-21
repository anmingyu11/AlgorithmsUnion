package _java;

import java.util.LinkedList;
import java.util.List;

import base.BaseTree;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * Example:
 * <pre>
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * </pre>
 * Output: [1,3,2]
 */
public class _0094BinaryTreeInorderTraversal____MultiS extends BaseTree {

    private abstract static class Solution {
        public abstract List<Integer> inorderTraversal(TreeNode root);
    }

    private static class Solution1 extends Solution {

        @Override
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> l = new LinkedList<>();
            traverse(l, root);
            return l;
        }

        private void traverse(List<Integer> l, TreeNode t) {
            if (t == null) {
                return;
            }
            traverse(l, t.left);
            l.add(t.val);
            traverse(l, t.right);
        }
    }

    private static class Solution2 extends Solution {

        @Override
        public List<Integer> inorderTraversal(TreeNode root) {
            return null;
        }

    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.right = new TreeNode(2);
        p.right.left = new TreeNode(3);
        Solution s = new Solution1();

        println(s.inorderTraversal(p));
    }
}
