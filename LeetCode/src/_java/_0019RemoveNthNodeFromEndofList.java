package _java;

import base.BaseLinkedList;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * <p>
 * Example:
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
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

    private static class Solution1 extends Solution {

        @Override
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode p = head, dummy = new ListNode(-1), prev = dummy;
            dummy.next = head;
            int len = 0;
            for (; p != null; p = p.next, ++len) ;
            int i = len;
            for (p = head; p != null; prev = p, p = p.next, --i) {
                if (i == n) {
                    prev.next = p.next;
                }
            }
            return dummy.next;
        }

    }

    private static class Solution2 extends Solution {

        private int total;
        private int n;

        public ListNode removeNthFromEnd(ListNode head, int n) {
            this.total = -1;
            this.n = n;
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            dummy.next = aux(dummy, head, 0);
            return dummy.next;
        }

        private ListNode aux(ListNode prev, ListNode cur, int i) {
            if (cur == null) {
                total = i;
                return null;
            }
            ListNode next = aux(cur, cur.next, i + 1);
            if (total > 0 && total - i == n) {
                return next;
            }
            cur.next = next;
            return cur;
        }

    }

    private static class Solution3 extends Solution {

        @Override
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(-1), prev = dummy, slow = head, fast = head;
            dummy.next = head;
            for (int i = 0; i < n; ++i, fast = fast.next) ;
            while (fast != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next;
            }
            prev.next = slow.next;
            return dummy.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = generateASingleListNode(1, 2, 3, 4, 5);
        int n1 = 2;
        ListNode l2 = generateASingleListNode(1);
        int n2 = 1;

        Solution s = new Solution2();

        printSingleListNode(s.removeNthFromEnd(l1, n1));
        printSingleListNode(s.removeNthFromEnd(l2, n2));
    }

}
