package _java;

import java.util.LinkedList;
import java.util.Stack;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * Example:
 *
 * Das ist ein bild(German)
 * <p>
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // return 3
 * iterator.next();    // return 7
 * iterator.hasNext(); // return true
 * iterator.next();    // return 9
 * iterator.hasNext(); // return true
 * iterator.next();    // return 15
 * iterator.hasNext(); // return true
 * iterator.next();    // return 20
 * iterator.hasNext(); // return false
 * <p>
 * Note:
 * <p>
 * next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
 */
public class _0173BinarySearchTreeIterator extends BaseTree {

    private static abstract class BSTIterator {

        public BSTIterator(TreeNode root) {
        }

        /**
         * @return the next smallest number
         */
        public abstract int next();

        /**
         * @return whether we have a next smallest number
         */
        public abstract boolean hasNext();
    }

    /**
     * Runtime: 57 ms, faster than 99.63% of Java online submissions for Binary Search Tree Iterator.
     * Memory Usage: 50.7 MB, less than 93.54% of Java online submissions for Binary Search Tree Iterator.
     */
    private static class Solution1 extends BSTIterator {

        private final LinkedList<Integer> mem;

        public Solution1(TreeNode root) {
            super(root);
            mem = new LinkedList<>();
            traverseAux(root);
            println(mem);
        }

        private void traverseAux(TreeNode root) {
            if (root == null) {
                return;
            }
            traverseAux(root.left);
            mem.addLast(root.val);
            traverseAux(root.right);
        }

        @Override
        public int next() {
            return mem.removeFirst();
        }

        @Override
        public boolean hasNext() {
            return !mem.isEmpty();
        }
    }

    /**
     * Runtime: 57 ms, faster than 99.63% of Java online submissions for Binary Search Tree Iterator.
     * Memory Usage: 50.7 MB, less than 93.57% of Java online submissions for Binary Search Tree Iterator.
     */
    private static class Solution2 extends BSTIterator {

        private TreeNode root;

        public Solution2(TreeNode root) {
            super(root);
            this.root = root;
        }

        private int min(TreeNode root) {
            if (root.left == null) {
                return root.val;
            } else {
                return min(root.left);
            }
        }

        private void delMin() {
            root = delMin(root);
        }

        private TreeNode delMin(TreeNode node) {
            if (node.left == null) {
                return node.right;
            }
            node.left = delMin(node.left);
            return node;
        }

        @Override
        public int next() {
            int min = min(root);
            delMin();
            return min;
        }

        @Override
        public boolean hasNext() {
            return root != null;
        }
    }

    /**
     * Runtime: 82 ms, faster than 5.86% of Java online submissions for Binary Search Tree Iterator.
     * Memory Usage: 50.6 MB, less than 93.67% of Java online submissions for Binary Search Tree Iterator
     */
    private static class Solution3 extends BSTIterator {

        private Stack<TreeNode> stack;

        public Solution3(TreeNode root) {
            super(root);
            stack = new Stack<>();
            leftMost(root);
        }

        private void leftMost(TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public int next() {
            TreeNode top = stack.pop();

            if (top.right != null) {
                leftMost(top.right);
            }
            return top.val;
        }

        @Override
        public boolean hasNext() {
            return this.stack.size() > 0;
        }
    }

    /**
     * Runtime: 61 ms, faster than 58.06% of Java online submissions for Binary Search Tree Iterator.
     * Memory Usage: 50 MB, less than 95.34% of Java online submissions for Binary Search Tree Iterator.
     * 比上面的略快一些.
     */
    private static class Solution4 extends BSTIterator {
        private final Stack<TreeNode> stack = new Stack<>();

        public Solution4(TreeNode root) {
            super(root);
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            final TreeNode node = stack.pop();
            if (node.right != null) {
                TreeNode p = node.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
            }

            return node.val;
        }
    }

    public static void main(String[] args) {
        TreeNode t = TreeUtil.generateACompleteBT(new Integer[]{7, 3, 15, null, null, 9, 20});
        //println(t);
        BSTIterator iter = new Solution1(t);
    }

}
