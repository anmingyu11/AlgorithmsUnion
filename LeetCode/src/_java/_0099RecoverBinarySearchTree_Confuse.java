package _java;

import base.BaseTree;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * <p>
 * Recover the tree without changing its structure.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,null,null,2]
 * <pre>
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * </pre>
 * Example 2:
 *
 * <pre>
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * </pre>
 * Follow up:
 * <p>
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */
public class _0099RecoverBinarySearchTree_Confuse extends BaseTree {

    private abstract static class Solution {
        public abstract void recoverTree(TreeNode root);
    }

    /**
     * 有两个结点的位置反了,把它们换过来.
     * Runtime: 2 ms, faster than 93.77% of Java online submissions for Recover Binary Search Tree.
     * Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Recover Binary Search Tree.
     */
    private static class Solution1 extends Solution {

        private TreeNode pre, first, second;

        public void recoverTree(TreeNode root) {
            pre = new TreeNode(Integer.MIN_VALUE);
            first = null;
            second = null;
            // 中序遍历的特点就是 L<-Root->R
            // 比如说[1,3,null,null,2]
            // 如果我们中序输出的话,就是 ((null,3,2,1,null),
            // 原题是二叉查找树有两个结点 are swapped by mistake, 只有两个结点被换了位置.
            traverse(root);

            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }

        private void traverse(TreeNode t) {
            if (t == null) {
                return;
            }

            traverse(t.left);
            // 第一个结点不在,左结点比右结点大,这是第一个被错误交换了的结点.
            // pre代表的是比当前结点t要小的结点,pre > t,pre是第一个错误结点,first = pre
            if (first == null && pre.val > t.val) {
                first = pre;
            }
            // 第二个结点, 这里比较精妙,因为这道题的前提是两个结点互相调换了位置,那么一定是一大一小调换了位置,
            // 那么之前的pre则是找到了大的结点被换到的小的结点,现在的t则是小的结点被换到的大的结点.
            // 因为 pre < t,再次违反规则,这里pre > t,再次违反规则,说明t是较小的那个结点,second = t
            // second < first.
            // Really, what we are comparing is the current node and its previous node in the "in order traversal".
            if (first != null && pre.val > t.val) {
                second = t;
            }
            pre = t;
            traverse(t.right);
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

    }

}
