package linklist;

import base.BaseLinkedList;

public class OddEvenList extends BaseLinkedList {
    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode odd = head, even = head.next, evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        ListNode node = generateASingleListNodeFrom0To(10);
        printSingleListNode(node);
        oddEvenList(node);
        printSingleListNode(node);
    }
}
