package _java;

import java.util.HashMap;
import java.util.Map;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called the "root."
 * Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that
 * "all houses in this place forms a binary tree".
 * It will automatically contact the police if
 * two directly-linked houses were broken into on the same night.
 * <p>
 * Determine the maximum amount of money the thief can rob tonight
 * without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * <p>
 * Input: [3,4,5,1,3,null,1]
 * <p>
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * <p>
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class _0337HouseRobber3 extends BaseTree {

    private abstract static class Solution {
        public abstract int rob(TreeNode root);
    }

    private static class Solution1 extends Solution {

        public int rob(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int val = 0;

            if (root.left != null) {
                val += rob(root.left.left) + rob(root.left.right);
            }

            if (root.right != null) {
                val += rob(root.right.left) + rob(root.right.right);
            }

            return Math.max(val + root.val, rob(root.left) + rob(root.right));
        }

    }

    private static class Solution2 extends Solution {
        public int rob(TreeNode root) {
            return robSub(root, new HashMap<>());
        }

        private int robSub(TreeNode root, Map<TreeNode, Integer> map) {
            if (root == null) return 0;
            if (map.containsKey(root)) return map.get(root);

            int val = 0;

            if (root.left != null) {
                val += robSub(root.left.left, map) + robSub(root.left.right, map);
            }

            if (root.right != null) {
                val += robSub(root.right.left, map) + robSub(root.right.right, map);
            }

            val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
            map.put(root, val);

            return val;
        }

    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber III.
     * Memory Usage: 36.2 MB, less than 99.89% of Java online submissions for House Robber III.
     */
    private static class Solution3 extends Solution {

        @Override
        public int rob(TreeNode root) {
            int[] res = robSub(root);
            return Math.max(res[0], res[1]);
        }

        public int[] robSub(TreeNode root) {
            if (root == null) {
                return new int[2];
            }

            int[] left = robSub(root.left);
            int[] right = robSub(root.right);
            int[] res = new int[2];

            res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            res[1] = root.val + left[0] + right[0];

            return res;
        }
    }

    public static void main(String[] args) {
        TreeNode bst1 = TreeUtil.generateACompleteBT(new Integer[]{3, 2, 3, null, 3, null, 1});
        TreeNode bst2 = TreeUtil.generateACompleteBT(new Integer[]{3, 4, 5, 1, 3, null, 1});

        Solution s = new Solution1();
        println(s.rob(bst1)); // 7
        println(s.rob(bst2)); // 9
    }
}
