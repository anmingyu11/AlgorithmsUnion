package _java;

import java.util.LinkedList;
import java.util.List;

import base.BaseTree;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class _0102BinaryTreeLevelOrderTraversal extends BaseTree {

    private abstract static class Solution {
        public abstract List<List<Integer>> levelOrder(TreeNode root);
    }

    /**
     * Runtime: 1 ms, faster than 80.07% of Java online submissions for Binary Tree Level Order Traversal.
     * Memory Usage: 36.2 MB, less than 99.99% of Java online submissions for Binary Tree Level Order Traversal.
     */
    private static class Solution1 extends Solution {

        @Override
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            LinkedList<TreeNode> curLevel = new LinkedList<>();
            curLevel.add(root);
            while (!curLevel.isEmpty()) {
                LinkedList<TreeNode> nextLevel = new LinkedList<>();
                LinkedList<Integer> levelRes = new LinkedList<>();
                while (!curLevel.isEmpty()) {
                    TreeNode cur = curLevel.remove();
                    levelRes.add(cur.val);
                    if (cur.left != null) {
                        nextLevel.add(cur.left);
                    }
                    if (cur.right != null) {
                        nextLevel.add(cur.right);
                    }
                }
                curLevel = nextLevel;
                res.add(levelRes);
            }
            return res;
        }

    }

    /**
     * 怎样不需要第二个队列?
     * Runtime: 1 ms, faster than 80.07% of Java online submissions for Binary Tree Level Order Traversal.
     * Memory Usage: 36.4 MB, less than 99.99% of Java online submissions for Binary Tree Level Order Traversal.
     */
    private static class Solution2 extends Solution {

        @Override
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            LinkedList<TreeNode> level = new LinkedList<>();
            level.add(root);
            while (!level.isEmpty()) {
                LinkedList<Integer> subRes = new LinkedList<>();
                int levelSize = level.size();
                for (int i = 0; i < levelSize; ++i) {
                    TreeNode cur = level.removeFirst();
                    subRes.add(cur.val);
                    if (cur.left != null) {
                        level.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        level.addLast(cur.right);
                    }
                }
                res.add(subRes);
            }
            return res;
        }

    }

    public static void main(String[] args) {

    }

}