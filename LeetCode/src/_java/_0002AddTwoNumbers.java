package _java;

import base.BaseLinkedList;

public class _0002AddTwoNumbers extends BaseLinkedList {

    private abstract static class Solution {
        abstract ListNode addTwoNumbers(ListNode l1, ListNode l2);
    }

    // Runtime: 22 ms, faster than 80.77% of Java online submissions for Add Two Numbers.
    // Memory Usage: 37.3 MB, less than 24.06% of Java online submissions for Add Two Numbers.
    private static class Solution1 extends Solution {

        @Override
        ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode fake = new ListNode(0);
            ListNode p = fake;

            int carry = 0;
            while (l1 != null || l2 != null) {
                int val = 0;
                int val1 = 0, val2 = 0;
                if (l1 != null) {
                    val1 = l1.val;
                    l1 = l1.next;
                }
                if (l2 != null) {
                    val2 = l2.val;
                    l2 = l2.next;
                }
                val = val1 + val2 + carry;
                if (val >= 10) {
                    carry = 1;
                    val = val % 10;
                } else {
                    carry = 0;
                }
                p.next = new ListNode(val);
                p = p.next;
            }

            if (carry == 1) {
                p.next = new ListNode(1);
            }

            return fake.next;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        ListNode n1 = new ListNode(2);
        n1.next = new ListNode(4);
        n1.next.next = new ListNode(3);
        ListNode n2 = new ListNode(5);
        n2.next = new ListNode(6);
        n2.next.next = new ListNode(4);

        ListNode n3 = new ListNode(1);
        n3.next = new ListNode(8);
        ListNode n4 = new ListNode(0);

        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(5);

        printSingleListNode(n1);
        printSingleListNode(n2);
        printSingleListNode(s.addTwoNumbers(n1, n2)); // 7 -> 0 -> 8
        printSingleListNode(n3);
        printSingleListNode(n4);
        printSingleListNode(s.addTwoNumbers(n3, n4)); // 1 -> 8
        printSingleListNode(n5);
        printSingleListNode(n6);
        printSingleListNode(s.addTwoNumbers(n5, n6)); // 1 -> 0
    }
}
