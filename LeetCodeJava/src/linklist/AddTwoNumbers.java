package linklist;

import base.BaseLinkedList;

public class AddTwoNumbers extends BaseLinkedList {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        int carry = 0;
        while (l1 != null || l2 != null) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummy.next;
    }

    public static void main(String[] args) {

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
        printSingleListNode(n5);
        printSingleListNode(n6);
        printSingleListNode(addTwoNumbers(n5, n6));
    }
}
