package _java;

import java.util.LinkedList;
import java.util.List;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree and a sum,
 * find all root-to-leaf paths where each path's sum equals the given sum.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given the below binary tree and sum = 22,
 * <pre>
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 * </pre>
 */
public class _0113PathSum2 extends BaseTree {

    private abstract static class Solution {
        public abstract List<List<Integer>> pathSum(TreeNode root, int sum);
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Path Sum II.
     * Memory Usage: 37.4 MB, less than 99.97% of Java online submissions for Path Sum II.
     */
    private static class Solution1 extends Solution {

        private LinkedList<List<Integer>> res;
        private int target;

        @Override
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            res = new LinkedList<>();
            target = sum;
            tAux(root, new LinkedList<>(), 0);
            return res;
        }

        private void tAux(TreeNode t, LinkedList<Integer> sub,
                          int cur) {
            if (t == null) {
                return;
            }
            int v = t.val;
            sub.add(v);
            cur += v;
            if (t.left == null && t.right == null && cur == target) {
                res.add((List<Integer>) sub.clone());
                sub.removeLast();
                return;
            }

            tAux(t.left, sub, cur);

            tAux(t.right, sub, cur);
            sub.removeLast();
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, null, 5, 1});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{});

        Solution s = new Solution1();

        println(s.pathSum(t1, 22));
        println(s.pathSum(t2, 11));
    }
}
