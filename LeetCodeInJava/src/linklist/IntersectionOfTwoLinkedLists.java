package linklist;

import base.BaseLinkedList;

public class IntersectionOfTwoLinkedLists extends BaseLinkedList {

    abstract static class Solution {
        public abstract ListNode getIntersectionNode(ListNode headA, ListNode headB);
    }

    static class Solution2 extends Solution {

        @Override
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) return null;

            ListNode a = headA;
            ListNode b = headB;

            while (a != b) {
                a = a == null ? headB : a.next;
                b = b == null ? headA : b.next;
            }


            return a;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution2();
        //test1(s);
        //test2(s);
        test3(s);
    }

    private static void test1(Solution s) {
        ListNode p1, node1 = new ListNode(4);
        ListNode p2, node2 = new ListNode(5);
        p1 = node1;
        p2 = node2;
        ListNode joint = new ListNode(8);
        node1.next = new ListNode(1);
        node1 = node1.next;
        node2.next = new ListNode(0);
        node2 = node2.next;

        node1.next = joint;
        node2.next = new ListNode(1);
        node2.next.next = joint;
        joint.next = new ListNode(4);
        joint = joint.next;
        joint.next = new ListNode(5);

        printSingleListNode(p1);
        printSingleListNode(p2);
        ListNode res = s.getIntersectionNode(p1, p2);
        printSingleListNode(res);
    }

    private static void test2(Solution s) {
        ListNode p1, node1 = new ListNode(0);
        ListNode p2, node2 = new ListNode(3);
        p1 = node1;
        p2 = node2;

        ListNode joint = new ListNode(2);
        joint.next = new ListNode(4);
        node1.next = new ListNode(9);
        node1 = node1.next;
        node1.next = new ListNode(1);
        node1 = node1.next;
        node1.next = joint;
        node2.next = joint;
        printSingleListNode(p1);
        printSingleListNode(p2);

        printSingleListNode(s.getIntersectionNode(p1, p2));
    }

    private static void test3(Solution s) {
        ListNode p1, node1 = new ListNode(2);
        ListNode p2, node2 = new ListNode(1);
        p1 = node1;
        p2 = node2;
        node1.next = new ListNode(6);
        node1 = node1.next;
        node1.next = new ListNode(4);
        node2.next = new ListNode(5);
        //printSingleListNode(p1);
        //printSingleListNode(p2);
        ListNode r = s.getIntersectionNode(p1, p2);
        printSingleListNode(r);
    }
}
