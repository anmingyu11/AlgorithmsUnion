package _java;

import base.BaseLinkedList;
import base.interfaces.ISort;
import base.util.TestUtil;

/**
 * Sort a linked list using insertion sort.
 * <p>
 * A graphical example of insertion sort.
 * The partial sorted list (black) initially contains only the first element in the list.
 * With each iteration one element (red) is removed
 * from the input data and inserted in-place into the sorted list
 * <p>
 * Algorithm of Insertion Sort:
 * <p>
 * Insertion sort iterates, consuming one input element each repetition,
 * and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data,
 * finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 * <p>
 * Example 1:
 * <p>
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 * <p>
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class _0147InsertionSortList extends BaseLinkedList {

    private static abstract class Solution {
        public abstract ListNode insertionSortList(ListNode head);
    }

    private static class Solution1 extends Solution {

        @Override
        public ListNode insertionSortList(ListNode head) {
            ListNode res = new ListNode(-1);
            ListNode<Integer> cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = null;
                // prev , cur
                ListNode<Integer> prevRes = res, curRes = res.next;
                while (true) {
                    if (curRes == null || cur.val <= curRes.val) {
                        prevRes.next = cur;
                        cur.next = curRes;
                        break;
                    }
                    prevRes = curRes;
                    curRes = curRes.next;
                }
                cur = next;
            }
            return res.next;
        }

    }

    private static class InsertionSort implements ISort {

        @Override
        public void sort(int[] A) {
            final int n = A.length;
            for (int i = 1; i < n; ++i) {
                int v = A[i];
                int j = i - 1;
                for (; j >= 0 && v < A[j]; --j) {
                    A[j + 1] = A[j];
                }
                A[j + 1] = v;
            }
        }
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(-1);
        Solution s = new Solution1();
        TestUtil.testSort(new InsertionSort());
    }
}
