package _java;

import base.BaseLinkedList;

public class _0083RemoveDuplicateFromSortedList extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode deleteDuplicates(ListNode<Integer> head);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted List.
     * Memory Usage: 37.8 MB, less than 23.14% of Java online submissions for Remove Duplicates from Sorted List.
     */
    private static class Solution1 extends Solution {

        @Override
        public ListNode deleteDuplicates(ListNode<Integer> head) {
            ListNode dummy = new ListNode(-1);
            ListNode p = dummy;
            ListNode cur = head, prev = cur;
            p.next = head;
            p = p.next;

            while (cur != null) {
                if (cur.val != prev.val) {
                    p.next = cur;
                    p = p.next;
                    prev = cur;
                }
                cur = cur.next;
            }
            if (p != null) {
                p.next = null;
            }

            return dummy.next;
        }
    }

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted List.
    // Memory Usage: 37.9 MB, less than 18.84% of Java online submissions for Remove Duplicates from Sorted List.
    private static class Solution2 extends Solution {

        @Override
        public ListNode deleteDuplicates(ListNode<Integer> head) {
            ListNode cur = head;
            while (cur != null && cur.next != null) {
                if (cur.next.val == cur.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> l1 = generateASingleListNode(new int[]{1, 1, 2});
        ListNode<Integer> l2 = generateASingleListNode(new int[]{1, 1, 2, 3, 3});
        ListNode<Integer> l3 = null;

        Solution s = new Solution2();

        printSingleListNode(s.deleteDuplicates(l1));
        printSingleListNode(s.deleteDuplicates(l2));
        printSingleListNode(s.deleteDuplicates(l3));
    }
}
