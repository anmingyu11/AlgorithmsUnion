package _java;

import base.BaseLinkedList;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Example:
 * <p>
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class _0024SwapNodesinPairs extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode swapPairs(ListNode head);
    }

    /**
     * Details
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Swap Nodes in Pairs.
     * Memory Usage: 35.6 MB, less than 91.13% of Java online submissions for Swap Nodes in Pairs.
     */
    private static class Solution1 extends Solution {

        @Override
        public ListNode swapPairs(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode prev = dummy, cur = head, next = head.next;

            while (cur != null && next != null) {
                cur.next = next.next;
                next.next = cur;
                prev.next = next;
                prev = cur;

                cur = cur.next;
                next = cur != null ? cur.next : null;
            }

            return dummy.next;
        }
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Swap Nodes in Pairs.
     * Memory Usage: 35.7 MB, less than 91.13% of Java online submissions for Swap Nodes in Pairs.
     */
    private static class Solution2 extends Solution {

        @Override
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode n = head.next;
            head.next = swapPairs(n.next);
            n.next = head;
            return n;
        }

    }

    public static void main(String[] args) {
        ListNode l1 = generateASingleListNode(1, 2, 3, 4);
        ListNode l2 = generateASingleListNode(1, 2, 3, 4, 5);
        ListNode l3 = generateASingleListNode(1);
        ListNode l4 = generateASingleListNode(1, 2);

        Solution s = new Solution2();

        printSingleListNode(s.swapPairs(l1)); // 2->1>4->3
        printSingleListNode(s.swapPairs(l2));
        printSingleListNode(s.swapPairs(l3));
        printSingleListNode(s.swapPairs(l4));
    }
}
