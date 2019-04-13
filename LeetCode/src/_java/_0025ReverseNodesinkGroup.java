package _java;

import base.BaseLinkedList;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out
 * nodes in the end should remain as it is.
 * <p>
 * Example:
 * <p>
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Note:
 * <p>
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes,
 * only nodes itself may be changed.
 */
public class _0025ReverseNodesinkGroup extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode reverseKGroup(ListNode head, int k);
    }


    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Nodes in k-Group.
    // Memory Usage: 37.9 MB, less than 81.90% of Java online submissions for Reverse Nodes in k-Group.
    // 自己的
    private static class Solution1 extends Solution {

        @Override
        public ListNode reverseKGroup(ListNode head, int k) {
            if (k <= 1 || head == null) {
                return head;
            }
            int i = 0;
            ListNode p = head;
            ListNode prev = null;

            while (p != null) {
                ++i;
                prev = p;
                p = p.next;
                if (i == k) {
                    break;
                }
            }
            if (i != k) {
                return head;
            }
            // prev指向第k个结点
            // p指向k+1个结点.
            prev.next = null;
            ListNode newHead = reverse(head);
            head.next = reverseKGroup(p, k);
            return newHead;
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode p = head;

            while (p != null) {
                ListNode next = p.next;
                p.next = prev;
                prev = p;
                p = next;
            }

            return prev;
        }

    }

    // 此法最为简洁和优雅,一行不多,一行不少,改一句都不行.
    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Nodes in k-Group.
    // Memory Usage: 37.9 MB, less than 82.13% of Java online submissions for Reverse Nodes in k-Group.
    private static class Solution2 extends Solution {

        @Override
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k <= 1) {
                return head;
            }
            ListNode p = head;
            int i = 0;
            // 一个细节,这里在循环条件里面用i++和在循环内部用++i,i的最终结果,用i++要比用++i大1;
            while (p != null && i != k) {
                ++i;
                p = p.next;
            }

            if (i == k) {
                p = reverseKGroup(p, k);
                while (i-- > 0) {
                    ListNode next = head.next;
                    head.next = p;
                    p = head;
                    head = next;
                }
                head = p;
            }
            return head;
        }

    }

    public static void main(String[] args) {

        Solution s = new Solution2();
        for (int k = 0; k <= 5; ++k) {
            println("---------------");
            println("k = " + k);
            ListNode l = generateASingleListNode(1, 2, 3, 4, 5);
            ListNode l2 = s.reverseKGroup(l, k);
            printSingleListNode(l2);
        }

    }

}
