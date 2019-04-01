package _java;

import base.BaseLinkedList;
import base.util.ArraysUtil;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 */
public class _0328OddEvenLinkedList extends BaseLinkedList {

    // 解决任何链表问题的最佳方法是在脑海中或纸上形象化。
    private abstract static class Solution {
        public abstract ListNode<Integer> oddEvenList(ListNode<Integer> head);
    }

    // Runtime: 2 ms, faster than 100.00% of Java online submissions for Odd Even Linked List.
    // Memory Usage: 37.8 MB, less than 25.35% of Java online submissions for Odd Even Linked List.
    private static class Solution1 extends Solution {

        @Override
        public ListNode<Integer> oddEvenList(ListNode<Integer> head) {
            ListNode<Integer> fake = new ListNode<>(-1);

            ListNode<Integer> odd = new ListNode<>(-1);
            ListNode<Integer> even = new ListNode<>(-1);
            ListNode p = head;
            ListNode po = odd;
            ListNode pe = even;
            int i = 0;
            while (p != null) {
                ListNode node = p;
                p = p.next;
                node.next = null;
                ++i;
                if ((i & 1) == 1) {
                    po.next = node;
                    po = po.next;
                } else {
                    pe.next = node;
                    pe = pe.next;
                }
            }
            fake.next = odd.next;
            po.next = even.next;
            return fake.next;
        }

    }

    // 非常优雅,值得学习
    // Runtime: 2 ms, faster than 100.00% of Java online submissions for Odd Even Linked List.
    // Memory Usage: 37.8 MB, less than 24.65% of Java online submissions for Odd Even Linked List.
    private static class Solution2 extends Solution {

        @Override
        public ListNode<Integer> oddEvenList(ListNode<Integer> head) {
            if (head == null) {
                return null;
            }
            ListNode<Integer> odd = head, even = head.next, evenHead = even;
            // even在后,odd在前,
            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
            return head;
        }

    }

    public static void main(String[] args) {
        ListNode<Integer> l1 = generateASingleListNode(ArraysUtil.generateFromTo(1, 8));
        ListNode<Integer> l2 = new ListNode<>(1);
        ListNode<Integer> l3 = null;
        ListNode<Integer> l4 = generateASingleListNode(ArraysUtil.generateFromTo(1, 3));

        Solution s = new Solution2();

        printSingleListNode(s.oddEvenList(l1));
        printSingleListNode(s.oddEvenList(l2));
        printSingleListNode(s.oddEvenList(l3));
        printSingleListNode(s.oddEvenList(l4));

    }
}
