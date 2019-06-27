package _java;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given the root node of a binary search tree,
 * return the sum of values of all nodes with value between L and R (inclusive).
 * <p>
 * The binary search tree is guaranteed to have unique values.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * Output: 32
 * Example 2:
 * <p>
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * Output: 23
 * <p>
 * Note:
 * <p>
 * The number of nodes in the tree is at most 10000.
 * The final answer is guaranteed to be less than 2^31.
 */
public class _0938RangeSumofBST extends BaseTree {

    private abstract static class Solution {
        public abstract int rangeSumBST(TreeNode root, int L, int R);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Range Sum of BST.
     * Memory Usage: 44.7 MB, less than 97.67% of Java online submissions for Range Sum of BST.
     */
    private static class Solution1 extends Solution {

        private int sum;

        public int rangeSumBST(TreeNode root, int L, int R) {
            sum = 0;
            sumAux(root, L, R);
            return sum;
        }

        private void sumAux(TreeNode t, int L, int R) {
            if (t == null) {
                return;
            }
            int v = t.val;
            if (v < L) {
                sumAux(t.right, L, R);
                return;
            }
            if (v > R) {
                sumAux(t.left, L, R);
                return;
            }
            sum += v;
            sumAux(t.left, L, R);
            sumAux(t.right, L, R);
        }

    }

    private static class Solution2 extends Solution {

        private int sum = 0;

        public int rangeSumBST(TreeNode root, int L, int R) {
            sum = 0;
            sumAux(root, L, R);
            return sum;
        }

        private void sumAux(TreeNode t, int L, int R) {
            if (t == null) {
                return;
            }
            int v = t.val;
            if (v >= L && v <= R) {
                sum += v;
            }
            if (v > L) {
                sumAux(t.left, L, R);
            }
            if (v < R) {
                sumAux(t.right, L, R);
            }
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{10, 5, 15, 3, 7, null, 18});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{10, 5, 15, 3, 7, 13, 18, 1, null, 6});

        Solution s = new Solution2();

        println(s.rangeSumBST(t1, 7, 15)); // 32
        println(s.rangeSumBST(t2, 6, 10)); // 23

    }
}
