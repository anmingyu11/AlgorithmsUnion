package _java;

/**
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 * <p>
 * You can think of the left and right pointers as synonymous to the predecessor
 * and successor pointers in a doubly-linked list. For a circular doubly linked list,
 * the predecessor of the first element is the last element,
 * and the successor of the last element is the first element.
 * <p>
 * We want to do the transformation in place.
 * After the transformation,
 * the left pointer of the tree node should point to its predecessor,
 * and the right pointer should point to its successor.
 * You should return the pointer to the smallest element of the linked list.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [4,2,5,1,3]
 * Output: [1,2,3,4,5]
 * Explanation: The figure below shows the transformed BST.
 * The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
 * <p>
 * Example 2:
 * <p>
 * Input: root = [2,1,3]
 * Output: [1,2,3]
 * <p>
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * Explanation: Input is an empty tree. Output is also an empty Linked List.
 * <p>
 * Example 4:
 * <p>
 * Input: root = [1]
 * Output: [1]
 * <p>
 * Constraints:
 * <p>
 * -1000 <= Node.val <= 1000
 * Node.left.val < Node.val < Node.right.val
 * All values of Node.val are unique.
 * 0 <= Number of Nodes <= 2000
 */
public class _0426ConvertBinarySearchTreetoSortedDoublyLinkedList {

    private static abstract class Solution {
        class Node {
            public int val;
            public Node left;
            public Node right;

            public Node() {
            }

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, Node _left, Node _right) {
                val = _val;
                left = _left;
                right = _right;
            }
        }

        public abstract Node treeToDoublyList(Node root);
    }

    private static class Solution1 extends Solution {

        public Node treeToDoublyList(Node root) {
            if (root == null) {
                return null;
            }
            Node fake = new Node(-1);
            Node tail = traverse(fake, root);
            Node head = fake.right;
            tail.right = head;
            head.left = tail;
            return head;
        }

        private Node traverse(Node dll, Node x) {
            if (x == null) {
                return dll;
            }
            dll = traverse(dll, x.left);
            Node last = dll;
            dll.right = x;
            dll = dll.right;
            dll.left = last;
            dll = traverse(dll, x.right);
            return dll;
        }

    }

}
