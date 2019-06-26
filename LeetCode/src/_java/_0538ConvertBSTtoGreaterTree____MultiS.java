package _java;

import java.util.LinkedList;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a Binary Search Tree (BST),
 * convert it to a Greater Tree
 * such that every key of the original BST
 * is changed to the original key plus sum of all keys greater than the original key in BST.
 * <p>
 * Example:
 * <pre>
 * Input: The root of a Binary Search Tree like this:
 *               5
 *             /   \
 *            2     13
 *
 * Output: The root of a Greater Tree like this:
 *              18
 *             /   \
 *           20     13
 * </pre>
 */
public class _0538ConvertBSTtoGreaterTree____MultiS extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode convertBST(TreeNode root);
    }

    /**
     * Runtime: 1 ms, faster than 56.83% of Java online submissions for Convert BST to Greater Tree.
     * Memory Usage: 40.5 MB, less than 62.03% of Java online submissions for Convert BST to Greater Tree.
     */
    private static class Solution1 extends Solution {

        private LinkedList<Integer> cumSum;

        @Override
        public TreeNode convertBST(TreeNode root) {
            cumSum = new LinkedList<>();
            cumSum.add(0);
            traversal1(root);
            cumSum.removeFirst();
            traversal2(root);
            return root;
        }

        private void traversal1(TreeNode t) {
            if (t == null) {
                return;
            }
            traversal1(t.left);
            cumSum.add(cumSum.getLast() + t.val);
            traversal1(t.right);
        }

        private void traversal2(TreeNode t) {
            if (t == null) {
                return;
            }
            traversal2(t.left);
            t.val += cumSum.getLast() - cumSum.removeFirst();
            traversal2(t.right);
        }

    }

    /**
     * 反中序遍历,太漂亮了,我是个弱智.
     * Runtime: 1 ms, faster than 56.83% of Java online submissions for Convert BST to Greater Tree.
     * Memory Usage: 38.9 MB, less than 85.07% of Java online submissions for Convert BST to Greater Tree.
     */
    private static class Solution2 extends Solution {

        private int sum = 0;

        @Override
        public TreeNode convertBST(TreeNode root) {
            inorderAux(root);
            return root;
        }

        private void inorderAux(TreeNode t) {
            if (t == null) {
                return;
            }
            inorderAux(t.right);
            sum += t.val;
            t.val = sum;
            inorderAux(t.left);
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new int[]{5, 2, 13});
        TreeNode t2 = TreeUtil.generateACompleteBT(new int[]{2, 0, 3, -4, 1});

        Solution s = new Solution1();

        println(s.convertBST(t1));
        println(s.convertBST(t2));
    }
}
