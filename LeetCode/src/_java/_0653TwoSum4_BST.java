package _java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.
 * <p>
 * Example 1:
 * <pre>
 * Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * </pre>
 * Target = 9
 * <p>
 * Output: True
 * <p>
 * Example 2:
 * <pre>
 * Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * </pre>
 * Target = 28
 * <p>
 * Output: False
 */
public class _0653TwoSum4_BST extends BaseTree {

    private abstract static class Solution {
        public abstract boolean findTarget(TreeNode root, int k);
    }

    /**
     * Runtime: 3 ms, faster than 85.36% of Java online submissions for Two Sum IV - Input is a BST.
     * Memory Usage: 42 MB, less than 71.76% of Java online submissions for Two Sum IV - Input is a BST.
     */
    private static class Solution1 extends Solution {

        private LinkedList<Integer> que;

        public boolean findTarget(TreeNode root, int k) {
            que = new LinkedList<>();
            inorderAux(root);

            while (!(que.size() < 2)) {
                int lo = que.getFirst();
                int hi = que.getLast();
                if (lo + hi == k) {
                    return true;
                } else if (lo + hi < k) {
                    que.removeFirst();
                } else {
                    que.removeLast();
                }
            }
            return false;
        }

        private void inorderAux(TreeNode t) {
            if (t == null) {
                return;
            }
            inorderAux(t.left);
            que.add(t.val);
            inorderAux(t.right);
        }

    }

    /**
     * Runtime: 4 ms, faster than 71.11% of Java online submissions for Two Sum IV - Input is a BST.
     * Memory Usage: 42.1 MB, less than 69.44% of Java online submissions for Two Sum IV - Input is a BST.
     * BST + HashSet
     */
    private static class Solution2 extends Solution {

        private Set<Integer> map;
        private int k;

        public boolean findTarget(TreeNode root, int k) {
            this.k = k;
            map = new HashSet<>();
            return preorderAux(root);
        }

        private boolean preorderAux(TreeNode t) {
            if (t == null) {
                return false;
            }
            int v = t.val;
            if (map.contains(k - v)) {
                return true;
            }
            map.add(t.val);
            return preorderAux(t.left) || preorderAux(t.right);
        }

    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new Integer[]{5, 3, 6, 2, 4, null, 7});
        TreeNode t2 = TreeUtil.generateACompleteBT(new Integer[]{1});
        TreeNode t3 = TreeUtil.generateACompleteBT(new Integer[]{2, null, 3});

        Solution s = new Solution2();

        println(s.findTarget(t1, 9));
        println(s.findTarget(t1, 28));
        println(s.findTarget(t2, 2));
        println(s.findTarget(t2, 1));
        println(s.findTarget(t3, 6));
    }
}
