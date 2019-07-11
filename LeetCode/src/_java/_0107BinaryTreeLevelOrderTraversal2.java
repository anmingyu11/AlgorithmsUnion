package _java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import base.BaseTree;

/**
 * Given a binary tree,
 * return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
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
 * return its bottom-up level order traversal as:
 * <pre>
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 * </pre>
 */
public class _0107BinaryTreeLevelOrderTraversal2 extends BaseTree {

    private abstract static class Solution {

        public abstract List<List<Integer>> levelOrderBottom(TreeNode root);
    }

    /**
     * Runtime: 1 ms, faster than 93.09% of Java online submissions for Binary Tree Level Order Traversal II.
     * Memory Usage: 36.2 MB, less than 99.94% of Java online submissions for Binary Tree Level Order Traversal II.
     */
    private static class Solution1 extends Solution {

        @Override
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            LinkedList<List<Integer>> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                LinkedList<Integer> level = new LinkedList<>();
                int len = queue.size();
                for (int i = 0; i < len; ++i) {
                    TreeNode t = queue.remove();
                    level.add(t.val);
                    if (t.left != null) {
                        queue.add(t.left);
                    }
                    if (t.right != null) {
                        queue.add(t.right);
                    }
                }
                res.addFirst(level);
            }
            return res;
        }
    }

    /**
     * DFS
     */
    private static class Solution2 extends Solution {

        ArrayList<List<Integer>> ans;

        @Override
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            ans = new ArrayList<>();
            traversalAux(root, 0);
            return ans;
        }

        private void traversalAux(TreeNode t, int level) {
            if (t == null) {
                return;
            }
            if (level >= ans.size()) {
                ans.add(0, new LinkedList<>());
            }
            traversalAux(t.left, level + 1);
            traversalAux(t.right, level + 1);
            ans.get(ans.size() - level - 1).add(t.val);
        }

    }

    public static void main(String[] args) {

    }
}
