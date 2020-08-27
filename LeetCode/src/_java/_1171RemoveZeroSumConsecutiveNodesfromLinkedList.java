package _java;

import java.util.HashMap;
import java.util.Map;

import base.BaseLinkedList;

/**
 * Given the head of a linked list,
 * we repeatedly delete consecutive sequences of nodes that sum to 0
 * until there are no such sequences.
 * <p>
 * After doing so, return the head of the final linked list.  You may return any such answer.
 * <p>
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,-3,3,1]
 * Output: [3,1]
 * Note: The answer [1,2,1] would also be accepted.
 * Example 2:
 * <p>
 * Input: head = [1,2,3,-3,4]
 * Output: [1,2,4]
 * Example 3:
 * <p>
 * Input: head = [1,2,3,-3,-2]
 * Output: [1]
 * <p>
 * Constraints:
 * <p>
 * The given linked list will contain between 1 and 1000 nodes.
 * Each node in the linked list has -1000 <= node.val <= 1000.
 */
public class _1171RemoveZeroSumConsecutiveNodesfromLinkedList extends BaseLinkedList {

    private static abstract class Solution {
        public abstract ListNode removeZeroSumSublists(ListNode head);
    }

    private static class Solution1 extends Solution {

        @Override
        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode<Integer> dummy = new ListNode<>(-1), p;
            dummy.next = head;
            boolean removed;
            do {
                int sum = 0;
                int i = 0;
                removed = false;
                Map<Integer, Integer> map = new HashMap<>();
                map.put(sum, -1);
                for (p = dummy.next; p != null; p = p.next, ++i) {
                    sum += p.val;
                    if (map.containsKey(sum)) {
                        int lo = map.get(sum);
                        int hi = i + 1;
                        dummy.next = remove(dummy.next, lo, hi);
                        removed = true;
                        break;
                    } else {
                        map.put(sum, i);
                    }
                }
            } while (removed);
            return dummy.next;
        }

        private ListNode remove(ListNode head, int lo, int hi) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode p, slow = dummy, fast = null;
            int i;
            for (i = -1, p = dummy; i <= hi && p != null; p = p.next, ++i) {
                if (i == lo) {
                    slow = p;
                }
                if (i == hi) {
                    fast = p;
                }
            }
            slow.next = fast;
            return dummy.next;
        }

    }

    private static class Solution2 extends Solution {

        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode<Integer> dummy = new ListNode(0), p;
            dummy.next = head;
            int sum = 0;
            Map<Integer, ListNode> map = new HashMap<>();
            for (p = dummy; p != null; p = p.next) {
                sum += p.val;
                map.put(sum, p);
            }
            sum =0;
            for (p = dummy; p != null; p = p.next) {
                sum += p.val;
                p.next = map.get(sum).next;
            }
            return dummy.next;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution2();

        ListNode l1 = generateASingleListNode(new int[]{1, 2, -3, 3, 1});
        ListNode l2 = generateASingleListNode(new int[]{1, 2, 3, -3, 4});
        ListNode l3 = generateASingleListNode(new int[]{1, 2, -3, 3, -2});
        ListNode l4 = generateASingleListNode(new int[]{1, -1});
        ListNode l5 = generateASingleListNode(new int[]{1, 3, 2, -3, -2, 5, 5, -5, 1});

        ListNode res1 = s.removeZeroSumSublists(l1);
        ListNode res2 = s.removeZeroSumSublists(l2);
        ListNode res3 = s.removeZeroSumSublists(l3);
        ListNode res4 = s.removeZeroSumSublists(l4);
        ListNode res5 = s.removeZeroSumSublists(l5);

        printSingleListNode(res1);
        printSingleListNode(res2);
        printSingleListNode(res3);
        printSingleListNode(res4);
        printSingleListNode(res5);
    }
}
