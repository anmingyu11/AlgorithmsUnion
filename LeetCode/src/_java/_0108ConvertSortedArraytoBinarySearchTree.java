package _java;

import base.BaseTree;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Example:
 * <p>
 * Given the sorted array: [-10,-3,0,5,9],
 * <p>
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 * <pre>
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 * </pre>
 */
public class _0108ConvertSortedArraytoBinarySearchTree extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode sortedArrayToBST(int[] nums);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
     * Memory Usage: 36.8 MB, less than 99.95% of Java online submissions for Convert Sorted Array to Binary Search Tree.
     * 二分查找, strong.
     */
    private static class Solution1 extends Solution {

        private int[] nums;

        public TreeNode sortedArrayToBST(int[] nums) {
            final int n = nums.length;
            if (n < 1) {
                return null;
            }
            this.nums = nums;
            int mid = n / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = aux2BST(0, mid - 1);
            root.right = aux2BST(mid + 1, n - 1);
            return root;
        }

        TreeNode aux2BST(int lo, int hi) {
            if (lo > hi) {
                return null;
            }
            final int mid = (lo + hi) / 2;
            TreeNode t = new TreeNode(nums[mid]);
            t.left = aux2BST(lo, mid - 1);
            t.right = aux2BST(mid + 1, hi);
            return t;
        }

    }

    public static void main(String[] args) {
        int[] t1 = {-10, -3, 0, 5, 9};

        Solution s = new Solution1();

        println(s.sortedArrayToBST(t1));
    }

}