package base;

import java.util.Iterator;

import base.util.ListNodeUtil;

public class BaseLinkedList extends Base {

    public static class ListNode<T> implements Iterable<ListNode> {

        public T val;
        public ListNode<T> next;

        public ListNode(T x) {
            val = x;
        }

        @Override
        public Iterator<ListNode> iterator() {
            return new ListNodeIterator(this);
        }

        private static class ListNodeIterator implements Iterator<ListNode> {
            private ListNode head;

            private ListNodeIterator(ListNode head) {
                this.head = head;
            }

            @Override
            public boolean hasNext() {
                return head != null;
            }

            @Override
            public ListNode next() {
                ListNode v = head;
                head = head.next;
                return v;
            }
        }
    }

    public static void printSingleListNode(ListNode h) {
        ListNodeUtil.printSingleListNode(h);
    }

    public static ListNode generateASingleListNodeFrom0To(int end) {
        return ListNodeUtil.generateASingleListNodeRange(0, end);
    }

    public static ListNode generateASingleListNodeRange(int start, int end) {
        return ListNodeUtil.generateASingleListNodeRange(start, end);
    }

    public static ListNode<Integer> generateASingleListNode(int... nums) {
        return ListNodeUtil.generateASingleListNode(nums);
    }

    public static boolean hasCycle(ListNode h) {
        return ListNodeUtil.hasCycle(h);
    }

    public static ListNode getLast(ListNode l) {
        return ListNodeUtil.getLast(l);
    }

    public static void main(String[] args) {
    }

}