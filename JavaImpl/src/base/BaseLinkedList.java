package base;

public class BaseLinkedList extends Base {

    public static class ListNode {

        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }

    }

    public static void printSingleListNode(ListNode h) {
        while (h != null && h.next != null) {
            print(h.val + " -> ");
            h = h.next;
        }

        if (h!= null) {
            print(h.val + " -> null\n");
        }else {
            print("null ");
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

    public static ListNode generateASingleListNode(int[] nums) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int n : nums) {
            cur.next = new ListNode(n);
            cur = cur.next;
        }

        return head.next;
    }

    public static void main(String[] args) {
    }

}