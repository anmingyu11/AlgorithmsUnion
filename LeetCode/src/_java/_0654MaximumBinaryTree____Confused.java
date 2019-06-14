package _java;

import java.util.LinkedList;

import base.BaseTree;

/**
 * Given an integer array with no duplicates.
 * A maximum tree building on this array is defined as follow:
 * <p>
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 * <p>
 * Example 1:
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 * <pre>
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 * </pre>
 * Note:
 * The size of the given array will be in the range [1,1000].
 */
public class _0654MaximumBinaryTree____Confused extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode constructMaximumBinaryTree(int[] nums);
    }


    /**
     * 这个例子只是一个巧合,并不是所有中序遍历都可以.
     * Runtime: 2 ms, faster than 99.98% of Java online submissions for Maximum Binary Tree.
     * Memory Usage: 38 MB, less than 99.66% of Java online submissions for Maximum Binary Tree.
     */
    private static class Solution1 extends Solution {

        @Override
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return build(nums, 0, nums.length - 1);
        }

        private TreeNode build(int[] nums, int lo, int hi) {
            if (lo > hi) {
                return null;
            }
            int maxIndex = max(nums, lo, hi);

            TreeNode t = new TreeNode(nums[maxIndex]);
            t.left = build(nums, lo, maxIndex - 1);
            t.right = build(nums, maxIndex + 1, hi);
            return t;
        }

        private int max(int[] nums, int lo, int hi) {
            int max = Integer.MIN_VALUE;
            int index = 0;
            for (int i = lo; i <= hi; ++i) {
                if (nums[i] > max) {
                    max = nums[i];
                    index = i;
                }
            }
            return index;
        }

    }

    /**
     * Runtime: 6 ms, faster than 23.44% of Java online submissions for Maximum Binary Tree.
     * Memory Usage: 39.6 MB, less than 81.35% of Java online submissions for Maximum Binary Tree.
     */
    private static class Solution2 extends Solution {

        @Override
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            LinkedList<TreeNode> stack = new LinkedList<>();

            for (int i = 0; i < nums.length; i++) {
                TreeNode curr = new TreeNode(nums[i]);
                while (!stack.isEmpty() && stack.peek().val < nums[i]) {
                    curr.left = stack.pop();
                }
                if (!stack.isEmpty()) {
                    stack.peek().right = curr;
                }
                stack.push(curr);
            }

            return stack.removeLast();
        }

    }

    public static void main(String[] args) {
        int[] a1 = {3, 2, 1, 6, 0, 5};

        Solution s = new Solution2();

        s.constructMaximumBinaryTree(a1);
    }

}
