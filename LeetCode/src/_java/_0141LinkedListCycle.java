package _java;

import java.util.HashSet;

import base.BaseLinkedList;

/**
 * Given a linked list, determine if it has a cycle in it.
 * <p>
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 * <p>
 * Follow up:
 * <p>
 * Can you solve it using O(1) (i.e. constant) memory?
 */
public class _0141LinkedListCycle extends BaseLinkedList {
    private abstract static class Solution {
        public abstract boolean hasCycle(ListNode head);
    }

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle.
    // Memory Usage: 38.7 MB, less than 25.10% of Java online submissions for Linked List Cycle.
    // 自己写的版本.
    private static class Solution1 extends Solution {

        @Override
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }
            ListNode p1 = head, p2 = head.next;
            while (p1 != null && p2 != null) {
                p1 = p1.next;
                if (p2.next != null) {
                    p2 = p2.next.next;
                } else {
                    return false;
                }
                if (p1 == p2) {
                    return true;
                }
            }

            return false;
        }

    }

    // 同样是双指针,人家的为什么这么优雅
    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle.
    // Memory Usage: 39 MB, less than 5.15% of Java online submissions for Linked List Cycle.
    private static class Solution2 extends Solution {

        @Override
        public boolean hasCycle(ListNode head) {
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
    }

    // Runtime: 6 ms, faster than 7.45% of Java online submissions for Linked List Cycle.
    // Memory Usage: 37.5 MB, less than 96.97% of Java online submissions for Linked List Cycle.
    // HashSet
    private static class Solution3 extends Solution {

        @Override
        public boolean hasCycle(ListNode head) {
            HashSet<ListNode> set = new HashSet<>();

            ListNode p = head;
            while (p != null) {
                if (!set.add(p)) {
                    return true;
                }
                p = p.next;
            }
            return false;
        }

    }

    public static void main(String[] args) {
        ListNode l1 = generateASingleListNode(3, 2, 0, -4);
        ListNode p = l1, prev = null;
        while (p != null) {
            prev = p;
            p = p.next;
        }
        prev.next = l1;
        ListNode l2 = generateASingleListNode(1);
        Solution s = new Solution3();

        println(s.hasCycle(l1));// true
        println(s.hasCycle(l2));// false
    }
}
