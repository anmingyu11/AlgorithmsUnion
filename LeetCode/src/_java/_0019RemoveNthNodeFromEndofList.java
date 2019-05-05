package _java;

import base.BaseLinkedList;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * <p>
 * Example:
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * 这道题让我们移除链表倒数第N个节点
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * <p>
 * Given n will always be valid.
 * <p>
 * Follow up:
 * <p>
 * Could you do this in one pass?
 */
public class _0019RemoveNthNodeFromEndofList extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode removeNthFromEnd(ListNode head, int n);
    }

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth Node From End of List.
    // Memory Usage: 36.8 MB, less than 92.46% of Java online submissions for Remove Nth Node From End of List.
    private static class Solution1 extends Solution {

        @Override
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode p1 = dummy, p2 = dummy;
            int i = 0;
            while (i < n) {
                p2 = p2.next;
                ++i;
            }
            ListNode prev = null;
            while (p2 != null) {
                prev = p1;
                p1 = p1.next;
                p2 = p2.next;
            }
            prev.next = prev.next.next;
            return dummy.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = generateASingleListNode(1, 2, 3, 4, 5);
        int n1 = 2;
        ListNode l2 = generateASingleListNode(1);
        int n2 = 1;

        Solution s = new Solution1();

        printSingleListNode(s.removeNthFromEnd(l1, n1));
        printSingleListNode(s.removeNthFromEnd(l2, n2));
    }

}
