package _java;

import base.BaseLinkedList;

/**
 * Remove all elements from a linked list of integers that have value val.
 * <p>
 * Example:
 * <p>
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class _0203RemoveLinkedListElements extends BaseLinkedList {

    private static abstract class Solution {
        public abstract ListNode removeElements(ListNode head, int val);
    }

    private static class Solution1 extends Solution {

        @Override
        public ListNode removeElements(ListNode head, int val) {
            ListNode<Integer> dummy = new ListNode(-1), prev = dummy, p = head;
            dummy.next = head;

            while (p != null) {
                if (p.val == val) {
                    prev.next = p.next;
                } else {
                    prev = p;
                }
                p = p.next;
            }
            return dummy.next;
        }

    }

    public static void main(String[] args) {
        ListNode l1 = generateASingleListNode(new int[]{1, 2, 6, 3, 4, 5, 6});

        Solution s = new Solution1();

        printSingleListNode(s.removeElements(l1, 6));
    }
}
