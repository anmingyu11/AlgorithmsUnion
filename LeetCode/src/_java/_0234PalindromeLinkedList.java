package _java;

import java.util.LinkedList;

import base.BaseLinkedList;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2
 * Output: false
 * Example 2:
 * <p>
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class _0234PalindromeLinkedList extends BaseLinkedList {

    /**
     * 一段关于时间复杂度的解释.
     * <p>
     * It is a common misunderstanding that the space complexity of a program is
     * just how much the size of additional memory space being used besides input.
     * An important prerequisite is neglected the above definition: the input has to be read-only.
     * By definition, changing the input and change it back is not allowed (or the input size should be counted
     * when doing so).
     * Another way of determining the space complexity of a program is to simply look at how much space
     * it has written to.
     * Reversing a singly linked list requires writing to O(n) memory space,
     * thus the space complexities for all "reverse-the-list"-based approaches are O(n), not O(1).
     * <p>
     * Solving this problem in O(1) space is theoretically impossible due to two simple facts:
     * (1) a program using O(1) space is computationally equivalent to a finite automata,
     * or a regular expression checker;
     * (2) the pumping lemma states that the set of palindrome strings does not form a regular set.
     * <p>
     * Please change the incorrect problem statement.
     */

    private abstract static class Solution {
        public abstract boolean isPalindrome(ListNode head);
    }

    /**
     * Runtime: 2 ms, faster than 36.32% of Java online submissions for Palindrome Linked List.
     * Memory Usage: 41 MB, less than 96.20% of Java online submissions for Palindrome Linked List.
     */
    private static class Solution1 extends Solution {

        @Override
        public boolean isPalindrome(ListNode head) {
            LinkedList<ListNode> stack = new LinkedList<>();
            ListNode p = head;
            while (p != null) {
                stack.push(p);
                p = p.next;
            }

            p = head;
            while (p != null) {
                if (p.val != stack.pop().val) {
                    return false;
                }
                p = p.next;
            }
            return true;
        }

    }

    /**
     * Runtime: 1 ms, faster than 94.30% of Java online submissions for Palindrome Linked List.
     * Memory Usage: 41.2 MB, less than 95.80% of Java online submissions for Palindrome Linked List.*
     * <p>
     * 对于奇数结点个数的链表的处理.
     */
    private static class Solution2 extends Solution {

        @Override
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {
                return true;
            }
            ListNode p = head;
            while (p != null) {
                p = p.next;
            }
            ListNode mid = findMid(head);
            ListNode reverseMid = reverse(mid);

            p = head;
            while (p != null) {
                if (reverseMid.val != p.val) {
                    return false;
                }
                reverseMid = reverseMid.next;
                p = p.next;
            }

            return true;
        }

        private ListNode reverse(ListNode head) {
            ListNode p = head, prev = null;
            while (p != null) {
                ListNode next = p.next;
                p.next = prev;
                prev = p;
                p = next;
            }
            return prev;
        }

        private ListNode findMid(ListNode head) {
            ListNode slow = head, fast = head, prev = null;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null;
            if (fast != null) {// 奇数的话, slow往后挪一位.
                slow = slow.next;
            }
            return slow;
        }

    }

    public static void main(String[] args) {
        ListNode head1 = generateASingleListNode(1, 2);
        ListNode head2 = generateASingleListNode(1, 2, 2, 1);
        ListNode head3 = generateASingleListNode(1, 2, 3, 2, 1);
        ListNode head4 = generateASingleListNode(1);
        Solution s = new Solution1();

        println(s.isPalindrome(head1)); // F
        println(s.isPalindrome(head2)); // T
        println(s.isPalindrome(head3)); // T
        println(s.isPalindrome(head4)); // T
    }
}
