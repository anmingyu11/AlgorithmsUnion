package _java;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree, count the number of uni-value subtrees.
 * <p>
 * A Uni-value subtree means all nodes of the subtree have the same value.
 * <p>
 * Example :
 * <pre>
 * Input:  root = [5,1,5,5,5,null,5]
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 * </pre>
 * Output: 4
 */
public class _0250CountUnivalueSubtrees extends BaseTree {

    private abstract static class Solution {
        public abstract int countUnivalSubtrees(TreeNode root);
    }

    /**
     * Runtime: 1 ms, faster than 36.63% of Java online submissions for Count Univalue Subtrees.
     * Memory Usage: 37.8 MB, less than 79.69% of Java online submissions for Count Univalue Subtrees.
     */
    private static class Solution1 extends Solution {

        @Override
        public int countUnivalSubtrees(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return (uniV(root, root.val) ? 1 : 0)
                    + countUnivalSubtrees(root.left)
                    + countUnivalSubtrees(root.right);
        }

        private boolean uniV(TreeNode t, int last) {
            if (t == null) {
                return true;
            }
            return t.val == last
                    && uniV(t.left, t.val)
                    && uniV(t.right, t.val);
        }

    }

    /**
     * Runtime: 1 ms, faster than 37.62% of Java online submissions for Count Univalue Subtrees.
     * Memory Usage: 38.4 MB, less than 71.18% of Java online submissions for Count Univalue Subtrees.
     */
    private static class Solution2 extends Solution {

        private int cnt;

        @Override
        public int countUnivalSubtrees(TreeNode root) {
            if (root == null) {
                return 0;
            }
            cnt = 0;
            isUni(root);
            return cnt;
        }

        private boolean isUni(TreeNode t) {
            if (t.left == null && t.right == null) {
                ++cnt;
                return true;
            }
            boolean cur = true;

            if (t.left != null) {
                cur = isUni(t.left) && cur && t.left.val == t.val;
            }
            if (t.right != null) {
                cur = isUni(t.right) && cur && t.right.val == t.val;
            }
            if (!cur) {
                return false;
            }
            ++cnt;
            return true;
        }
    }

    private static class Solution3 extends Solution {

        private int cnt;

        public int countUnivalSubtrees(TreeNode root) {
            if (root == null) {
                return 0;
            }
            cnt = 0;
            traversalAux(root, 0);
            return cnt;
        }

        private boolean traversalAux(TreeNode t, int last) {
            if (t == null) {
                return true;
            }
            // 不短路的或
            if (!traversalAux(t.left, t.val) | !traversalAux(t.right, t.val)) {
                return false;
            }
            // left right 都等,后者都空.
            ++cnt;
            return t.val == last;
        }

    }

    /**
     * Runtime: 1 ms, faster than 37.62% of Java online submissions for Count Univalue Subtrees.
     * Memory Usage: 39.5 MB, less than 41.98% of Java online submissions for Count Univalue Subtrees.
     */
    private static class Solution4 extends Solution {

        public int countUnivalSubtrees(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return (uniV(root, root.val) ? 1 : 0)
                    + countUnivalSubtrees(root.left)
                    + countUnivalSubtrees(root.right);
        }

        private boolean uniV(TreeNode t, int last) {
            if (t == null) {
                return true;
            }
            return uniV(t.left, t.val)
                    && uniV(t.right, t.val)
                    && t.val == last;
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{5, 1, 5, 5, 5, null, 5});

        Solution s = new Solution4();

        println(s.countUnivalSubtrees(t1));//4
    }

}
