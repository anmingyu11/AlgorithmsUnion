package _java;

import base.BaseLinkedList;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 * <p>
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
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
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode(head.val - 1);
            dummy.next = head;
            ListNode p = head, prev = dummy;
            while (p != null) {
                ListNode next = p.next;
                if (p.val == prev.val) {
                    prev.next = p.next;
                } else {
                    prev = p;
                }
                p = next;
            }
            return dummy.next;
        }

    }

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted List.
    // Memory Usage: 37.9 MB, less than 18.84% of Java online submissions for Remove Duplicates from Sorted List.
    private static class Solution2 extends Solution {

        @Override
        public ListNode deleteDuplicates(ListNode<Integer> head) {
            ListNode p = head;
            while (p != null && p.next != null) {
                if (p.next.val == p.val) {
                    p.next = p.next.next;
                } else {
                    p = p.next;
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
