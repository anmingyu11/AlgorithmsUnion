package _java;

import java.util.LinkedList;
import java.util.List;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
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
 * return its zigzag level order traversal as:
 * <pre>
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * </pre>
 */
public class _0103BinaryTreeZigzagLevelOrderTraversal____MultiS extends BaseTree {

    private abstract static class Solution {
        public abstract List<List<Integer>> zigzagLevelOrder(TreeNode root);
    }

    /**
     * Runtime: 1 ms, faster than 82.94% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
     * Memory Usage: 36.1 MB, less than 99.96% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
     */
    private static class Solution1 extends Solution {

        @Override
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            LinkedList<List<Integer>> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            int zig = 1;
            LinkedList<TreeNode> level = new LinkedList<>();
            level.add(root);
            while (!level.isEmpty()) {
                LinkedList<Integer> subRes = new LinkedList<>();
                int len = level.size();
                for (int i = 0; i < len; ++i) {
                    TreeNode t = level.remove();
                    if (zig > 0) {
                        subRes.addLast(t.val);
                    } else {
                        subRes.addFirst(t.val);
                    }
                    if (t.left != null) {
                        level.add(t.left);
                    }
                    if (t.right != null) {
                        level.add(t.right);
                    }
                }
                res.add(subRes);
                zig = -zig;
            }
            return res;
        }

    }

    /**
     * Todo : DFS
     */
    private static class Solution2 extends Solution {

        @Override
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            return null;
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{3, 9, 20, null, null, 15, 7});

        Solution s = new Solution1();

        println(s.zigzagLevelOrder(t1));
    }
}
