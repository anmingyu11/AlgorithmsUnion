package _java;

import base.BaseLinkedList;
import base.util.ArraysUtil;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place.
 * The program should run in O(1) space complexity and O(nodes) time complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 * <p>
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * <p>
 * Constraints:
 * <p>
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 * The length of the linked list is between [0, 10^4].
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
            if (head == null || head.next == null) {
                return head;
            }
            ListNode odd = head, even = head.next,evenHead=head.next;
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
