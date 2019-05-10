package _java;

import base.BaseLinkedList;
import base.util.ArraysUtil;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 */
public class _0092ReverseLinkedList2________ extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode<Integer> reverseBetween(ListNode<Integer> head, int m, int n);
    }

    // Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List II.
    // Memory Usage: 35.7 MB, less than 92.53% of Java online submissions for Reverse Linked List II.
    private static class Solution1 extends Solution {

        @Override
        public ListNode<Integer> reverseBetween(ListNode<Integer> head, int m, int n) {
            return null;
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> l1 = generateASingleListNode(ArraysUtil.generateFromTo(1, 5));
        int m1 = 2, n1 = 4;

        Solution s = new Solution1();

        //printSingleListNode(s.reverseBetween(l1, m1, n1));
        for (int i = 1; i <= 5; ++i) {
            for (int j = i; j <= 5; ++j) {
                print(" reverse: i : " + i + " j : " + j + " ---- ");
                printSingleListNode(s.reverseBetween(l1, i, j));
            }
        }
    }

}