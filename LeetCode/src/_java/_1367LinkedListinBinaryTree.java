package _java;

import base.BaseLinkedList;
import base.BaseTree;

/**
 * Given a binary tree root and a linked list with head as the first node.
 * Return True if all the elements in the linked list starting from the head correspond to
 * some downward path connected in the binary tree otherwise return False.
 * In this context downward path means a path that starts at some node and goes downwards.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * Output: true
 * Explanation: Nodes in blue form a subpath in the binary Tree.
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * Output: true
 * <p>
 * Example 3:
 * <p>
 * Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * Output: false
 * Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.
 * <p>
 * Constraints:
 * <p>
 * 1 <= node.val <= 100 for each node in the linked list and binary tree.
 * The given linked list will contain between 1 and 100 nodes.
 * The given binary tree will contain between 1 and 2500 nodes.
 */
public class _1367LinkedListinBinaryTree {

    private static abstract class Solution {
        public abstract boolean isSubPath(BaseLinkedList.ListNode<Integer> head, BaseTree.TreeNode root);
    }

    private static class Solution1 extends Solution {

        public boolean isSubPath(BaseLinkedList.ListNode<Integer> head, BaseTree.TreeNode root) {
            if (head == null) {
                return true;
            }
            if (root == null) {
                return false;
            }
            return hasPath(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
        }

        private boolean hasPath(BaseLinkedList.ListNode<Integer> head, BaseTree.TreeNode root) {
            if (head == null) {
                return true;
            }
            if (root == null) {
                return false;
            }
            return head.val == root.val && (hasPath(head.next, root.left) || hasPath(head.next, root.right));
        }
    }

    public static void main(String[] args) {
    }


}
