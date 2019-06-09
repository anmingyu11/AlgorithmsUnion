package _java;

import java.util.Stack;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given two binary trees and imagine that when you put one of them to cover the other,
 * some nodes of the two trees are overlapped while the others are not.
 * <p>
 * You need to merge them into a new binary tree.
 * The merge rule is that if two nodes overlap,
 * then sum node values up as the new value of the merged node.
 * Otherwise, the NOT null node will be
 * used as the node of new tree.
 * <p>
 * Example 1:
 *
 * <pre>
 * Input:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * Output:
 * Merged tree:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 * </pre>
 */
public class _0617MergeTwoBinaryTrees extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode mergeTrees(TreeNode t1, TreeNode t2);
    }

    /**
     * Runtime: 1 ms, faster than 50.92% of Java online submissions for Merge Two Binary Trees.
     * Memory Usage: 42.1 MB, less than 22.67% of Java online submissions for Merge Two Binary Trees.
     */
    private static class Solution1 extends Solution {

        @Override
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            return mergeAux(t1, t2);
        }


        private TreeNode mergeAux(TreeNode t1, TreeNode t2) {
            if (t1 == null && t2 == null) {
                return null;
            }
            int t1val = t1 == null ? 0 : t1.val;
            int t2val = t2 == null ? 0 : t2.val;
            TreeNode newNode = new TreeNode(t1val + t2val);
            newNode.left = mergeAux(t1 != null ? t1.left : null, t2 != null ? t2.left : null);
            newNode.right = mergeAux(t1 != null ? t1.right : null, t2 != null ? t2.right : null);
            return newNode;
        }
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Binary Trees.
     * Memory Usage: 44.3 MB, less than 11.39% of Java online submissions for Merge Two Binary Trees.
     * 智力失去,根链表归并一样
     */
    private static class Solution2 extends Solution {

        @Override
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null) {
                return t2;
            }
            if (t2 == null) {
                return t1;
            }
            t1.val += t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
            return t1;
        }

    }

    /**
     * 树遍历的递归实现,其实就是用栈来模拟函数调用.
     * Runtime: 2 ms, faster than 34.18% of Java online submissions for Merge Two Binary Trees.
     * Memory Usage: 40.1 MB, less than 99.89% of Java online submissions for Merge Two Binary Trees.
     */
    private static class Solution3 extends Solution {

        @Override
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null) {
                return t2;
            }
            if (t2 == null) {
                return t1;
            }
            Stack<TreeNode[]> stack = new Stack<>();
            stack.push(new TreeNode[]{t1, t2});
            while (!stack.isEmpty()) {
                TreeNode[] p = stack.pop();
                if (p[0] == null || p[1] == null) {
                    continue;
                }
                p[0].val += p[1].val;
                if (p[0].left == null) {
                    p[0].left = p[1].left;
                } else {
                    stack.push(new TreeNode[]{p[0].left, p[1].left});
                }
                if (p[0].right == null) {
                    p[0].right = p[1].right;
                } else {
                    stack.push(new TreeNode[]{p[0].right, p[1].right});
                }
            }

            return t1;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{1, 3, 2, 5});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{2, 1, 3, null, 4, null, 7});

        println(s.mergeTrees(t1, t2));
    }
}
