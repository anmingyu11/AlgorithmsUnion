package _java;

import java.util.HashMap;
import java.util.Map;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * You are given a binary tree in which each node contains an integer value.
 * <p>
 * Find the number of paths that sum to a given value.
 * <p>
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (traveling only from parent nodes to child nodes).
 * <p>
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * <p>
 * Example:
 * <p>
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <pre>
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 * </pre>
 * Return 3. The paths that sum to 8 are:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 */
public class _0437PathSum3 extends BaseTree {

    private abstract static class Solution {
        public abstract int pathSum(TreeNode root, int sum);
    }

    /**
     * Runtime: 10 ms, faster than 73.08% of Java online submissions for Path Sum III.
     * Memory Usage: 40.7 MB, less than 59.82% of Java online submissions for Path Sum III.
     */
    private static class Solution1 extends Solution {

        @Override
        public int pathSum(TreeNode root, int sum) {
            if (root == null) {
                return 0;
            }
            return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        }

        private int findPath(TreeNode t, int sum) {
            if (t == null) {
                return 0;
            }
            int cnt = 0;
            if (sum == t.val) {
                ++cnt;
            }
            cnt += findPath(t.left, sum - t.val);
            cnt += findPath(t.right, sum - t.val);
            return cnt;
        }

    }

    private static class Solution2 extends Solution {

        private Map<Integer, Integer> map;

        @Override
        public int pathSum(TreeNode root, int sum) {
            map = new HashMap<>();
            map.put(0, 1);
            return backtrack(root, 0, sum);
        }

        private int backtrack(TreeNode t, int curSum, int target) {
            if (t == null) {
                return 0;
            }
            curSum += t.val;
            // currSum - target == 0 的话,说明这条链管用
            // curSum 如果比target大, 如果 之前的子链条Sum有 curSum - target
            // 那么存在  curSum - (curSum - target) 该结点存在一个链条可用,cnt += 1.
            // [1,2,3,4] target = 7 , sum(1,2,3,4) - sum(1,2) = 7
            int cnt = map.getOrDefault(curSum - target, 0);
            Integer curSumCnt = map.get(curSum);
            map.put(curSum, curSumCnt != null ? curSumCnt + 1 : 1);
            cnt += backtrack(t.left, curSum, target) + backtrack(t.right, curSum, target);
            map.put(curSum, map.get(curSum) - 1);
            return cnt;
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1});

        Solution s = new Solution2();

        println(s.pathSum(t1, 8)); // 3
    }
}
