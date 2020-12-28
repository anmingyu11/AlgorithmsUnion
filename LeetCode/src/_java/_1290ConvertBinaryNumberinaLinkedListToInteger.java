package _java;

import base.BaseLinkedList;

public class _1290ConvertBinaryNumberinaLinkedListToInteger extends BaseLinkedList {

    private static abstract class Solution {
        public abstract int getDecimalValue(ListNode head);
    }

    private static class Solution1 extends Solution {
        private int bits = 0;

        public int getDecimalValue(ListNode head) {
            return aux(0, head);
        }

        private int aux(int bit, ListNode<Integer> x) {
            if (x.next == null) {
                bits = bit;
                return x.val;
            }
            int res = aux(bit + 1, x.next);

            return res + (x.val << (bits - bit));
        }
    }

    private static class Solution2 extends Solution {

        public int getDecimalValue(ListNode head) {
            int sum = 0;
            for (ListNode<Integer> p = head; p != null; p = p.next) {
                sum = 2 * sum + p.val;
            }
            return sum;
        }
    }

    private static class Solution3 extends Solution {
        @Override
        public int getDecimalValue(ListNode head) {
            int sum = 0;
            for (ListNode<Integer> p = head; p != null; p = p.next) {
                sum = (sum << 1) + p.val;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution1();

    }
}
