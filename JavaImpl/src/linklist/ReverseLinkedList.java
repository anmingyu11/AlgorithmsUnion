package linklist;

import base.BaseLinkedList;

public class ReverseLinkedList extends BaseLinkedList {
    static class SolutionIterate {
        public static ListNode reverseList(ListNode head) {
            ListNode pre = null, cur = head;

            while (cur != null) {
                ListNode temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }

            return pre;
        }
    }

    static class SolutionRecursive {
        public static ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode p = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return p;
        }
    }

    public static void main(String[] args) {
        printSingleListNode(SolutionIterate.reverseList(generateASingleListNodeFrom0To(5)));
        printSingleListNode(SolutionRecursive.reverseList(generateASingleListNodeFrom0To(5)));
    }

}