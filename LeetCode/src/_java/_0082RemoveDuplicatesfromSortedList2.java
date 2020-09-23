package _java;

import base.BaseLinkedList;

/**
 * Given a sorted linked list,
 * delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * <p>
 * Return the linked list sorted as well.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * <p>
 * Example 2:
 * <p>
 * Input: 1->1->1->2->3
 * Output: 2->3
 */
public class _0082RemoveDuplicatesfromSortedList2 extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode deleteDuplicates(ListNode head);
    }

    // Runtime: 1 ms, faster than 41.67% of Java online submissions for Remove Duplicates from Sorted List II.
    // Memory Usage: 37.6 MB, less than 28.11% of Java online submissions for Remove Duplicates from Sorted List II.
    private static class Solution1 extends Solution {

        @Override
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(-1);
            ListNode cur = head, p = dummy;

            while (cur != null) {
                ListNode next = cur.next;
                boolean add = true;
                while (next != null && cur.val == next.val) {
                    next = next.next;
                    add = false;
                }
                if (add) {
                    p.next = new ListNode(cur.val);
                    p = p.next;
                }
                cur = next;
            }

            return dummy.next;
        }
    }

    private static class Solution2 extends Solution {

        @Override
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode cur = head, prev = dummy;
            while (cur != null) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                if (prev.next == cur) {
                    prev = prev.next;
                } else {
                    prev.next = cur.next;
                }
                cur = cur.next;
            }

            return dummy.next;
        }

    }

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted List II.
    // Memory Usage: 37.7 MB, less than 18.53% of Java online submissions for Remove Duplicates from Sorted List II.
    // 非常有价值.
    private static class Solution3 extends Solution {

        @Override
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }
            if (head.next != null && head.next.val == head.val) {
                while (head.next != null && head.next.val == head.val) {
                    head = head.next;
                }
                return deleteDuplicates(head.next);
            } else {
                head.next = deleteDuplicates(head.next);
            }
            return head;
        }

    }

    public static void main(String[] args) {
        ListNode<Integer> l1 = generateASingleListNode(1, 2, 3, 3, 4, 4, 5);
        ListNode<Integer> l2 = generateASingleListNode(1, 1, 1, 2, 3);

        Solution s = new Solution3();

        printSingleListNode(s.deleteDuplicates(l1)); // 1->2->5
        printSingleListNode(s.deleteDuplicates(l2)); // 2->3
    }

}
