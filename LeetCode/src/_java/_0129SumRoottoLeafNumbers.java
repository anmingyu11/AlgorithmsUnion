package _java;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree containing digits from 0-9 only,
 * each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3]
 * <pre>
 *     1
 *    / \
 *   2   3
 * </pre>
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * Example 2:
 * <p>
 * Input: [4,9,0,5,1]
 * <pre>
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * </pre>
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
public class _0129SumRoottoLeafNumbers extends BaseTree {

    private abstract static class Solution {
        public abstract int sumNumbers(TreeNode root);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Sum Root to Leaf Numbers.
     * Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Sum Root to Leaf Numbers.
     */
    private static class Solution1 extends Solution {

        @Override
        public int sumNumbers(TreeNode root) {
            return sumNumbers(root, 0);
        }

        private int sumNumbers(TreeNode t, int sum) {
            if (t == null) {
                return 0;
            }
            sum = 10 * sum + t.val;
            if (t.left == null && t.right == null) {
                return sum;
            }
            int l = sumNumbers(t.left, sum);
            int r = sumNumbers(t.right, sum);
            return l + r;
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new int[]{1, 2, 3});
        TreeNode t2 = TreeUtil.generateACompleteBT(new int[]{4, 9, 0, 5, 1});

        Solution s = new Solution1();

        println(s.sumNumbers(t1)); // 25
        println(s.sumNumbers(t2)); // 1026
    }

}
