package _java;

import java.util.LinkedList;

import base.BaseTree;

/**
 * Given a binary tree
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Example:
 * <pre>
 * Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 * Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"6"},"val":1}
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.
 * </pre>
 * <p>
 * Note:
 * <p>
 * You may only use constant extra space.
 * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 */
public class _0117PopulatingNextRightPointersinEachNode2 extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode2 connect(TreeNode2 root);
    }

    private static class Solution1 extends Solution {

        @Override
        public TreeNode2 connect(TreeNode2 root) {
            LinkedList<TreeNode2> queue = new LinkedList<>();
            if (root == null) {
                return root;
            }
            queue.add(root);
            while (!queue.isEmpty()) {
                int len = queue.size();
                TreeNode2 last = null;
                for (int i = 0; i < len; ++i) {
                    TreeNode2 x = queue.remove();
                    if (x.right != null) {
                        queue.add(x.right);
                    }
                    if (x.left != null) {
                        queue.add(x.left);
                    }
                    x.next = last;
                    last = x;
                }
            }
            return root;
        }

    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Populating Next Right Pointers in Each Node II.
     * Memory Usage: 50.4 MB, less than 96.86% of Java online submissions for Populating Next Right Pointers in Each Node II.
     *
     * Very clever skill.
     */
    private static class Solution2 extends Solution {

        @Override
        public TreeNode2 connect(TreeNode2 root) {
            connectAux(root);
            return root;
        }

        private void connectAux(TreeNode2 t) {
            while (t != null) {
                TreeNode2 dummy = new TreeNode2();
                TreeNode2 p = dummy;
                while (t != null) {
                    if (t.left != null) {
                        p.next = t.left;
                        p = p.next;
                    }
                    if (t.right != null) {
                        p.next = t.right;
                        p = p.next;
                    }
                    t = t.next;
                }
                t = dummy.next;
            }
        }

    }

    public static void main(String[] args) {

    }
}
