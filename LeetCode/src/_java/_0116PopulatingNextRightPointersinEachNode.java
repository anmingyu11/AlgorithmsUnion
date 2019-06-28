package _java;

import java.util.LinkedList;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The binary tree has the following definition:
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 */
public class _0116PopulatingNextRightPointersinEachNode extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode2 connect(TreeNode2 root);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Populating Next Right Pointers in Each Node.
     * Memory Usage: 36.4 MB, less than 25.92% of Java online submissions for Populating Next Right Pointers in Each Node.
     */
    private static class Solution1 extends Solution {

        @Override
        public TreeNode2 connect(TreeNode2 root) {
            LinkedList<TreeNode2> queue = new LinkedList<>();
            if (root == null) {
                return root;
            }
            queue.add(root);
            while (!queue.isEmpty()) {
                int len = queue.size();
                TreeNode2 last = null;
                for (int i = 0; i < len; ++i) {
                    TreeNode2 x = queue.remove();
                    if (x.right != null) {
                        queue.add(x.right);
                    }
                    if (x.left != null) {
                        queue.add(x.left);
                    }
                    x.next = last;
                    last = x;
                }
            }
            return root;
        }

    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Populating Next Right Pointers in Each Node.
     * Memory Usage: 36.3 MB, less than 29.79% of Java online submissions for Populating Next Right Pointers in Each Node.
     */
    private static class Solution2 extends Solution {

        @Override
        public TreeNode2 connect(TreeNode2 root) {
            TreeNode2 level = root;
            while (level != null) {
                TreeNode2 cur = level;
                while (cur != null) {
                    if (cur.left != null) {
                        cur.left.next = cur.right;
                    }
                    if (cur.right != null && cur.next != null) {
                        cur.right.next = cur.next.left;
                    }
                    cur = cur.next;
                }
                level = level.left;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeUtil.generateACompleteBT(new int[]{1, 2, 3, 4, 5, 6, 7});

        Solution s = new Solution1();

    }
}
