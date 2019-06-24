package _java;

import java.util.LinkedList;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,1,4,null,2], k = 1
 * <pre>
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * </pre>
 * Output: 1
 * Example 2:
 * <p>
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * <pre>
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * </pre>
 * Output: 3
 * Follow up:
 * What if the BST is modified (insert/delete operations)
 * often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 */
public class _0230KthSmallestElementinaBST____MultiS extends BaseTree {

    private abstract static class Solution {
        public abstract int kthSmallest(TreeNode root, int k);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Kth Smallest Element in a BST.
     * Memory Usage: 38.2 MB, less than 95.69% of Java online submissions for Kth Smallest Element in a BST.
     */
    private static class Solution1 extends Solution {

        private LinkedList<Integer> res;
        private int k;

        @Override
        public int kthSmallest(TreeNode root, int k) {
            this.k = k;
            this.res = new LinkedList<>();
            traversal(root);
            println(res);
            return res.removeLast();
        }

        private void traversal(TreeNode t) {
            if (t == null) {
                return;
            }
            traversal(t.left);
            if (res.size() == k) {
                return;
            }
            res.add(t.val);
            traversal(t.right);
        }

    }

    /**
     * Very subtle
     *
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Kth Smallest Element in a BST.
     * Memory Usage: 37.4 MB, less than 99.98% of Java online submissions for Kth Smallest Element in a BST.
     */
    private static class Solution2 extends Solution {

        private int count;
        private int res;

        @Override
        public int kthSmallest(TreeNode root, int k) {
            count = k;
            traversalAux(root);
            return res;
        }

        private void traversalAux(TreeNode t) {
            if (t == null) {
                return;
            }
            traversalAux(t.left);
            --count;
            if (count == 0) {
                res = t.val;
                return;
            }
            traversalAux(t.right);
        }

    }

    /**
     * Todo : 结点计数
     */
    private static class Solution3 {

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{5, 3, 6, 2, 4, 7});
        println(s.kthSmallest(t1, 2)); //3;
    }
}
