package _java;

import java.util.LinkedList;
import java.util.List;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Input:
 * <pre>
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * </pre>
 * Output: ["1->2->5", "1->3"]
 * <p>
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class _0257BinaryTreePaths extends BaseTree {

    private abstract static class Solution {
        public abstract List<String> binaryTreePaths(TreeNode root);
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Binary Tree Paths.
     * Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Binary Tree Paths.
     */
    private static class Solution1 extends Solution {

        List<String> res = null;

        public List<String> binaryTreePaths(TreeNode root) {
            res = new LinkedList<>();
            traverseAux(root, "");
            return res;
        }

        private void traverseAux(TreeNode x, String path) {
            if (x == null) {
                return;
            }
            path += x.val;
            if (x.left == null && x.right == null) {
                res.add(path);
                return;
            }
            path += "->";
            traverseAux(x.left, path);
            traverseAux(x.right, path);
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 3, null, 5});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{1, 2, 3, 4, 5});

        Solution s = new Solution1();
        println(s.binaryTreePaths(t1));
        println(s.binaryTreePaths(t2));
    }
}
