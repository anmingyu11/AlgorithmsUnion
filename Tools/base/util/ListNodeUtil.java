package base.util;

import java.util.NoSuchElementException;

import base.BaseLinkedList.ListNode;

import static base.util.PrintUtil.print;

public class ListNodeUtil {

    public static void printSingleListNode(ListNode h) {
        while (h != null && h.next != null) {
            print(h.val + " -> ");
            h = h.next;
        }

        if (h != null) {
            print(h.val + " -> null\n");
        } else {
            print("null \n");
        }
    }

    public static ListNode generateASingleListNodeFrom0To(int end) {
        return generateASingleListNodeRange(0, end);
    }

    public static ListNode generateASingleListNodeRange(int start, int end) {
        if (start == end) {
            return new ListNode(start);
        }
        ListNode head = new ListNode(0);
        ListNode h = head;

        int sign = start < end ? 1 : -1;
        while (start != end) {
            h.next = new ListNode(start);
            start += sign;
            h = h.next;
        }

        return head.next;
    }

    public static ListNode<Integer> generateASingleListNode(int... nums) {
        ListNode<Integer> head = new ListNode(0);
        ListNode<Integer> cur = head;
        for (int n : nums) {
            cur.next = new ListNode(n);
            cur = cur.next;
        }

        return head.next;
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head, fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }

    public static ListNode getLast(ListNode l) {
        if (hasCycle(l)) {
            throw new NoSuchElementException(" linkedList has cycle");
        }
        ListNode p = l, prev = null;
        while (p != null) {
            prev = p;
            p = p.next;
        }
        return prev;
    }

}