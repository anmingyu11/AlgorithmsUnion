package _java;

import base.BaseLinkedList;

/**
 * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
 * <p>
 * If there are two middle nodes, return the second middle node.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,4,5]
 * Output: Node 3 from this list (Serialization: [3,4,5])
 * The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
 * Note that we returned a ListNode object ans, such that:
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
 * Example 2:
 * <p>
 * Input: [1,2,3,4,5,6]
 * Output: Node 4 from this list (Serialization: [4,5,6])
 * Since the list has two middle nodes with values 3 and 4, we return the second one.
 * <p>
 * Note:
 * <p>
 * The number of nodes in the given list will be between 1 and 100.
 */
public class _0876MiddleOfTheLinkedList extends BaseLinkedList {
    private static abstract class Solution {
        public abstract ListNode middleNode(ListNode head);
    }

    private static class Solution1 extends Solution {

        public ListNode middleNode(ListNode head) {
            ListNode slow = head, fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            if (fast.next == null) {
                return slow;
            } else {
                return slow.next;
            }
        }

    }

    private static class Solution2 extends Solution {

        public ListNode middleNode(ListNode head) {
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();
    }
}
