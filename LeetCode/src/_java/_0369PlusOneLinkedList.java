package _java;

import base.BaseLinkedList;

/**
 * Given a non-negative integer represented as a linked list of digits,
 * plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list.
 * <p>
 * Example 1:
 * Input: head = [1,2,3]
 * Output: [1,2,4]
 * <p>
 * Example 2:
 * Input: head = [0]
 * Output: [1]
 * <p>
 * Constraints:
 * The number of nodes in the linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * The number represented by the linked list does not contain
 * leading zeros except for the zero itself.
 */
public class _0369PlusOneLinkedList extends BaseLinkedList {

    private static abstract class Solution {
        public abstract ListNode plusOne(ListNode head);
    }

    private static class Solution1 extends Solution {

        @Override
        public ListNode plusOne(ListNode head) {
            int res = aux(head);
            ListNode fake = new ListNode(-1);
            fake.next = head;
            if (res > 0) {
                fake.val = res;
                return fake;
            } else {
                return fake.next;
            }
        }

        private int aux(ListNode<Integer> x) {
            if (x == null) {
                return 1;
            }
            int res = aux(x.next);
            if (res > 0) {
                x.val += res;
                int carry = x.val / 10;
                x.val %= 10;
                return carry;
            }
            return 0;
        }

    }

    public static void main(String[] args) {

    }
}
