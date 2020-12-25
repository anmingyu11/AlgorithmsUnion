package _java;

import java.util.LinkedList;

import base.BaseLinkedList;

public class _1019NextGreaterNodeInLinkedList extends BaseLinkedList {

    private static abstract class Solution {
        public abstract int[] nextLargerNodes(ListNode head);
    }

    /**
     * Runtime: 4 ms, faster than 99.70% of Java online submissions for Next Greater Node In Linked List.
     * Memory Usage: 42.9 MB, less than 75.38% of Java online submissions for Next Greater Node In Linked List.
     */
    private static class Solution1 extends Solution {

        public int[] nextLargerNodes(ListNode head) {
            int[] A, R;
            A = toArray(head);
            R = new int[A.length];

            for (int i = 1; i < A.length; ++i) {
                int v = A[i];
                for (int j = i - 1; j >= 0 && v > A[j]; --j) {
                    if (R[j] == 0) {
                        R[j] = v;
                    }
                }
            }
            return R;
        }

        private int[] toArray(ListNode<Integer> head) {
            int i = 0, n = 0;
            int[] A;
            for (ListNode p = head; p != null; p = p.next) {
                ++n;
            }
            A = new int[n];
            for (ListNode<Integer> p = head; p != null; p = p.next) {
                A[i++] = p.val;
            }
            return A;
        }
    }

    private static class Solution2 extends Solution {

        public int[] nextLargerNodes(ListNode head) {
            int[] A = toArray(head);
            LinkedList<Integer> stack = new LinkedList<>();
            int[] result = new int[A.length];
            for (int i = 0; i < A.length; i++) {
                while (!stack.isEmpty() && A[i] > A[stack.getLast()]) {
                    result[stack.removeLast()] = A[i];
                }
                stack.addLast(i);
            }
            return result;
        }

        private int[] toArray(ListNode head) {
            int i = 0, n = 0;
            int[] A;
            for (ListNode p = head; p != null; p = p.next) {
                ++n;
            }
            A = new int[n];
            for (ListNode<Integer> p = head; p != null; p = p.next) {
                A[i++] = p.val;
            }
            return A;
        }

    }

    public static void main(String[] args) {
        ListNode l1 = generateASingleListNode(2, 1, 5);
        ListNode l2 = generateASingleListNode(2, 7, 4, 3, 5);
        ListNode l3 = generateASingleListNode(1, 7, 5, 1, 9, 2, 5, 1);

        Solution s = new Solution1();

        printArr(s.nextLargerNodes(l1)); // [5,5,0]
        printArr(s.nextLargerNodes(l2)); // [7,0,5,5,0]
        printArr(s.nextLargerNodes(l3)); // [7,9,9,9,0,5,0,0]
    }
}
