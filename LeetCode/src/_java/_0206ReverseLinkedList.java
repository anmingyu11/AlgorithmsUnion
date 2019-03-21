package _java;

import base.BaseLinkedList;

public class _0206ReverseLinkedList extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode reverseList(ListNode head);
    }

    // 迭代,头插法
    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
    // Memory Usage: 37.9 MB, less than 12.90% of Java online submissions for Reverse Linked List.
    private static class Solution1 extends Solution {

        @Override
        public ListNode reverseList(ListNode head) {
            ListNode fake = new ListNode(-1);
            while (head != null) {
                // 获取head,然后将head指向head的下一个
                ListNode node = head;
                head = head.next;
                node.next = null;
                // 头插法
                if (fake.next != null) {
                    ListNode p = fake.next;
                    fake.next = node;
                    fake.next.next = p;
                } else {
                    fake.next = node;
                }
            }
            return fake.next;
        }

    }

    // 优雅的迭代
    private static class Solution2 extends Solution {

        @Override
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }

    }

    // 优雅的递归
    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
    // Memory Usage: 37.9 MB, less than 10.16% of Java online submissions for Reverse Linked List.
    private static class Solution3 extends Solution {

        @Override
        public ListNode reverseList(ListNode head) {
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
        ListNode l1 = generateASingleListNode(new int[]{1, 2, 3, 4, 5});
        ListNode l2 = new ListNode(1);
        ListNode l3 = null;

        Solution s = new Solution3();

        printSingleListNode(s.reverseList(l1));
        printSingleListNode(s.reverseList(l2));
        printSingleListNode(s.reverseList(l3));
    }
}
