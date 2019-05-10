package _java;

import java.util.LinkedList;
import java.util.List;

import base.BaseTree;

public class _0144BinaryTreePreorderTraversal_________ extends BaseTree {

    private abstract static class Solution {
        public abstract List<Integer> preorderTraversal(BaseTree.TreeNode root);
    }

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Preorder Traversal.
    // Memory Usage: 36.2 MB, less than 22.21% of Java online submissions for Binary Tree Preorder Traversal.
    // 递归 简单的不能再简单
    private static class Solution1 extends Solution {

        public List<Integer> preorderTraversal(TreeNode root) {

            List<Integer> l = new LinkedList<>();
            traversal(root, l);
            return l;
        }

        private void traversal(TreeNode t, List<Integer> l) {
            if (t == null) {
                return;
            }
            l.add(t.val);
            traversal(t.left, l);
            traversal(t.right, l);
        }

    }

    // 迭代版
    // Let's start from the root and then at each iteration pop the current node out of the stack and push its child nodes.
    // In the implemented strategy we push nodes into output list following the order Top->Bottom and Left->Right,
    // that naturally reproduces preorder traversal.
    private static class Solution2 extends Solution {

        @Override
        public List<Integer> preorderTraversal(TreeNode root) {
            return null;
        }
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.right = new TreeNode(2);
        t.right.left = new TreeNode(3);
        Solution s = new Solution2();

        println(s.preorderTraversal(t));
    }
}
