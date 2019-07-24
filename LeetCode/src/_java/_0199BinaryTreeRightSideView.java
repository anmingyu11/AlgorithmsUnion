package _java;

import java.util.LinkedList;
import java.util.List;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 * <pre>
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * </pre>
 */
public class _0199BinaryTreeRightSideView extends BaseTree {

    private abstract static class Solution {
        public abstract List<Integer> rightSideView(TreeNode root);
    }

    /**
     * Runtime: 1 ms, faster than 94.33% of Java online submissions for Binary Tree Right Side View.
     * Memory Usage: 35.4 MB, less than 99.98% of Java online submissions for Binary Tree Right Side View.
     */
    private static class Solution1 extends Solution {

        private List<Integer> res;

        @Override
        public List<Integer> rightSideView(TreeNode root) {
            res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            LinkedList<TreeNode> level = new LinkedList<>();
            level.add(root);
            while (!level.isEmpty()) {
                res.add(level.getLast().val);
                final int len = level.size();
                for (int i = 0; i < len; ++i) {
                    TreeNode x = level.remove();
                    if (x.left != null) {
                        level.add(x.left);
                    }
                    if (x.right != null) {
                        level.add(x.right);
                    }
                }
            }
            return res;
        }

    }

    /**
     * Runtime: 1 ms, faster than 94.33% of Java online submissions for Binary Tree Right Side View.
     * Memory Usage: 36.1 MB, less than 99.98% of Java online submissions for Binary Tree Right Side View.
     */
    private static class Solution2 extends Solution {

        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new LinkedList<>();
            rightView(root, result, 0);
            return result;
        }

        public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
            if (curr == null) {
                return;
            }
            if (currDepth == result.size()) {
                result.add(curr.val);
            }
            rightView(curr.right, result, currDepth + 1);
            rightView(curr.left, result, currDepth + 1);
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 3, null, 5, null, 4});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, null, 3});
        TreeNode t3 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 4, 3});
        Solution s = new Solution1();
        println(s.rightSideView(t1));// 1,3,4
        println(s.rightSideView(t2));// 1,2,3
        println(s.rightSideView(t3));// 1,4,3
    }
}
