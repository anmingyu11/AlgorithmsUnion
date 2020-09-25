package _java;

import base.BaseLinkedList;

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 * <p>
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class _0061RotateList extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode rotateRight(ListNode head, int k);
    }

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate List.
    // Memory Usage: 40 MB, less than 5.18% of Java online submissions for Rotate List.
    private static class Solution1 extends Solution {

        @Override
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            // Get the len and the last node.
            int n = 1;
            ListNode p, last, prev;
            for (p = head; p.next != null; p = p.next, ++n) ;
            last = p;
            k %= n;
            if (k == 0) {
                return head;
            }
            k = n - k;
            for (p = head, prev = dummy; k > 0; --k, prev = p, p = p.next) ;
            prev.next = null;
            dummy.next = p;
            last.next = head;
            return dummy.next;
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> l1 = generateASingleListNode(1, 2, 3, 4, 5);
        int k1 = 2;
        ListNode<Integer> l2 = generateASingleListNode(0, 1, 2);
        int k2 = 4;
        ListNode<Integer> l3 = generateASingleListNode(1);
        int k3 = 0;
        ListNode<Integer> l4 = generateASingleListNode(1, 2);
        int k4 = 1;
        Solution s = new Solution1();

        printSingleListNode(s.rotateRight(l1, k1)); //Output: 4->5->1->2->3->NULL
        printSingleListNode(s.rotateRight(l2, k2)); //Output: 2->0->1->NULL
        printSingleListNode(s.rotateRight(l3, k3)); // 1->NULL
        printSingleListNode(s.rotateRight(l4, k4)); // 2->1->NULL

    }

}
