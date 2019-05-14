package _java;

import base.BaseLinkedList;

/**
 * Given a linked list and a value x,
 * partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes
 * in each of the two partitions.
 */
public class _0086PartionList extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode<Integer> partition(ListNode<Integer> head, int x);
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Partition List.
     * Memory Usage: 36.9 MB, less than 22.92% of Java online submissions for Partition List.
     */
    private static class Solution1 extends Solution {

        @Override
        public ListNode<Integer> partition(ListNode<Integer> head, int x) {
            ListNode partion = new ListNode(-1);
            ListNode start = new ListNode(-1);
            ListNode<Integer> p = head;
            ListNode<Integer> p1 = start, p2 = partion;
            while (p != null) {
                if (p.val < x) {
                    p1.next = new ListNode(p.val);
                    p1 = p1.next;
                } else {
                    p2.next = new ListNode(p.val);
                    p2 = p2.next;
                }
                p = p.next;
            }
            p1.next = partion.next;

            return start.next;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution1();
        ListNode<Integer> l1 = generateASingleListNode(new int[]{1, 4, 3, 2, 5, 2});
        int x1 = 3;
        ListNode<Integer> l2 = generateASingleListNode(new int[]{1, 4, 3, 2, 5, 2});
        int x2 = 19;
        printSingleListNode(s.partition(l1, x1));
        printSingleListNode(s.partition(l2, x2));
    }
}
