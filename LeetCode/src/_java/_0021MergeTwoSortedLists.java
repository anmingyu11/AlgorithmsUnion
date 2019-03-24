package _java;

import base.BaseLinkedList;

public class _0021MergeTwoSortedLists extends BaseLinkedList {

    private abstract static class Solution {
        abstract ListNode mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2);
    }

    // 归并排序 99.03%
    private static class Solution1 extends Solution {

        ListNode mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
            ListNode fake = new ListNode(Integer.MIN_VALUE);
            ListNode p = fake;
            while (l1 != null || l2 != null) {
                if (l1 == null) {
                    p.next = l2;
                    p = p.next;
                    l2 = l2.next;
                    continue;
                }
                if (l2 == null) {
                    p.next = l1;
                    p = p.next;
                    l1 = l1.next;
                    continue;
                }
                if (l1.val < l2.val) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            }
            return fake.next;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();
        test1(s);
        test2(s);
    }

    private static void test1(Solution s) {
        ListNode l1 = new ListNode(1);
        ListNode p1 = l1;
        l1.next = new ListNode(2);
        l1 = l1.next;
        l1.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        ListNode p2 = l2;
        l2.next = new ListNode(3);
        l2 = l2.next;
        l2.next = new ListNode(4);

        ListNode r = s.mergeTwoLists(p1, p2);

        printSingleListNode(r);
    }

    private static void test2(Solution s) {
        ListNode l1 = new ListNode(1);
        ListNode p1 = l1;
        l1.next = new ListNode(2);
        l1 = l1.next;
        l1.next = new ListNode(4);
        ListNode p2 = null;

        ListNode r = s.mergeTwoLists(p1, p2);
        printSingleListNode(r);
    }
}
