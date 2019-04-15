package _java;

import java.util.LinkedList;

import base.BaseLinkedList;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Example 1:
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class _0143ReorderList extends BaseLinkedList {


    private abstract static class Solution {
        public abstract void reorderList(ListNode head);
    }

    // Runtime: 2 ms, faster than 80.49% of Java online submissions for Reorder List.
    // Memory Usage: 41.1 MB, less than 5.16% of Java online submissions for Reorder List.
    // 连蒙带猜
    private static class Solution1 extends Solution {

        @Override
        public void reorderList(ListNode head) {
            LinkedList<ListNode> stack = new LinkedList<>();
            ListNode p = head;
            while (p != null) {
                stack.push(p);
                p = p.next;
            }
            int stackN = stack.size() / 2;
            p = head;
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            while (p != null && stack.size() != stackN) {
                ListNode next = stack.pop();
                next.next = null;
                cur.next = p;
                p = p.next;
                cur.next.next = next;
                cur = next;
            }
            cur.next = null;
        }

    }

    /**
     * 人家的,用栈,更巧妙灵活,相比之下我的更让人费解.
     */
    private static class Solution2 extends Solution {

        @Override
        public void reorderList(ListNode head) {
            if (head == null || head.next == null) {
                return;
            }
            LinkedList<ListNode> stack = new LinkedList<>();
            ListNode p = head;
            while (p != null) {
                stack.push(p);
                p = p.next;
            }

            p = head;
            int cnt = (stack.size() - 1) / 2;
            while (cnt-- > 0) {
                ListNode next = stack.pop();
                ListNode tmp = p.next;
                p.next = next;
                next.next = tmp;
                p = tmp;
            }
            stack.pop().next = null;
        }
    }

    /**
     * 反转后半部分,然后归并.
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Reorder List.
     * Memory Usage: 40.6 MB, less than 11.54% of Java online submissions for Reorder List.
     */
    private static class Solution3 extends Solution {

        @Override
        public void reorderList(ListNode head) {
            if (head == null || head.next == null) {
                return;
            }
            ListNode mid = findMid(head);
            ListNode reverseMid = reverse(mid);
            head = merge(head, reverseMid);
        }

        private ListNode findMid(ListNode head) {
            ListNode slow = head, fast = head, prev = null;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null;
            return slow;
        }

        private ListNode reverse(ListNode head) {
            ListNode p = head, prev = null;
            while (p != null) {
                ListNode next = p.next;
                p.next = prev;
                prev = p;
                p = next;
            }
            return prev;
        }

        private ListNode merge(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            ListNode p = dummy;
            while (l1 != null && l2 != null) {
                p.next = l1;
                l1 = l1.next;
                p.next.next = l2;
                l2 = l2.next;
                p = p.next.next;
            }

            if (l1 != null) {
                p.next = l1;
            }
            if (l2 != null) {
                p.next = l2;
            }
            return dummy.next;
        }
    }

    public static void main(String[] args) {

        ListNode head1 = generateASingleListNode(1, 2, 3, 4, 5);
        ListNode head2 = generateASingleListNode(1, 2, 3, 4);
        Solution s = new Solution3();
        s.reorderList(head1);
        s.reorderList(head2);

        //printSingleListNode(new Solution3().findMid(head1));
        //printSingleListNode(new Solution3().findMid(head2));
        printSingleListNode(head1); // 1->5->2->4->3
        printSingleListNode(head2); // 1->4->2->3

    }

}
