package _java;

import java.util.ArrayList;

import base.BaseLinkedList;

public class _0148SortList extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode sortList(ListNode head);
    }

    // 归并排序
    // Runtime: 4 ms, faster than 93.04% of Java online submissions for Sort List.
    // Memory Usage: 41.4 MB, less than 32.05% of Java online submissions for Sort List.
    private static class Solution1 extends Solution {

        /**
         * 技巧1:快慢指针对链表分两段:
         * 1. 4个指针,head代表原链表的头部,slow,fast对应快慢指针,fast的遍历速度 fast = 2*slow,prev = slow
         * 2. 当fast到末尾,用prev将原链表断开成两部分,1:head->...->slow,2:slow.next-> 链表的末尾
         * 技巧2:merge两个list的技巧,对于数组的merge,需要一直遍历到尾部,
         * 对于链表则不需要,当一个归并的一个链表到达末尾时,就可以直接将下一个还未结束的链表接上来.
         *
         * @param head
         * @return
         */
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // step 1. cut the list to two halves
            ListNode prev = null, slow = head, fast = head;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null;
            // step 2. sort each half
            ListNode l1 = sortList(head);
            ListNode l2 = sortList(slow);
            // step 3. merge l1 and l2
            return merge(l1, l2);
        }

        /**
         * 我是弱智
         * 原来还可以特么的这么写.
         *
         * @param l1
         * @param l2
         * @return
         */
        ListNode merge(ListNode l1, ListNode l2) {
            ListNode l = new ListNode(0), p = l;

            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            }

            if (l1 != null) {
                p.next = l1;
            }

            if (l2 != null) {
                p.next = l2;
            }

            return l.next;
        }
    }

    // 自创的优化归并
    // Runtime: 5 ms, faster than 61.85% of Java online submissions for Sort List.
    // Memory Usage: 41.4 MB, less than 100.00% of Java online submissions for Sort List.
    private static class Solution2 extends Solution {

        @Override
        public ListNode sortList(ListNode head) {
            return auxiliary(head);
        }

        // 不计算长度
        // 转化成合并k链表的问题.
        private ListNode auxiliary(ListNode h) {
            ArrayList<ListNode> lists = new ArrayList<>();

            // 先将一条链表 转换成 k个合并的子数组
            ListNode p = h;
            ListNode l1 = new ListNode(-1), l2 = new ListNode(-1);
            ListNode l1p = l1, l2p = l2;

            // 分割原链表 2n
            while (p != null) {
                // 分割链表成l1,l2
                while (p != null && (l1p != null || l2p != null)) {
                    if (p.next != null) {
                        if (p.val <= p.next.val) {
                            // 顺序部分
                            if (l1p != null) {
                                // append to l1
                                l1p.next = p;
                                l1p = l1p.next;
                                p = p.next;
                            } else {
                                // append to l2
                                l2p.next = p;
                                l2p = l2p.next;
                                p = p.next;
                            }
                        } else {
                            if (l1p != null) {
                                // l1 end
                                l1p.next = p;
                                l1p = l1p.next;
                                p = p.next;
                                l1p.next = null;
                                l1p = l1p.next;
                            } else {
                                // l2 end
                                l2p.next = p;
                                l2p = l2p.next;
                                p = p.next;
                                l2p.next = null;
                                l2p = l2p.next;
                            }
                        }
                    } else {
                        if (l1p != null) {
                            l1p.next = p;
                            p = p.next;
                            l1p = l1p.next;
                        } else if (l2 != null) {
                            l2p.next = p;
                            p = p.next;
                            l2p = l2p.next;
                        }
                    }
                }
                // 分割完毕,开始归并
                ListNode merge = merge(l1.next, l2.next);
                lists.add(merge);
                l1.next = null;
                l2.next = null;
                l1p = l1;
                l2p = l2;
            }

            // 分治法 merge 所有的链表 nlogn
            return mergeKLists(lists);
        }

        public ListNode mergeKLists(ArrayList<ListNode> lists) {
            int n = lists.size();
            ListNode[] arr = new ListNode[n];
            lists.toArray(arr);
            return auxMerge(arr, 0, n - 1);
        }

        public ListNode auxMerge(ListNode[] lists, int lo, int hi) {
            if (lo >= hi) {
                if (lists.length < 1) {
                    return null;
                }
                return lists[lo];
            }

            int mid = (lo + hi) / 2;
            ListNode l1 = auxMerge(lists, lo, mid);
            ListNode l2 = auxMerge(lists, mid + 1, hi);
            ListNode head = merge(l1, l2);

            return head;
        }

        private ListNode merge(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(-1);
            ListNode p = head;
            boolean hasNext = true;
            while (hasNext) {
                if (l1 == null) {
                    // l1 == null, move l2
                    if (l2 != null) {
                        p.next = l2;
                        l2 = l2.next;
                    } else {
                        hasNext = false;
                    }
                } else if (l2 == null) {
                    p.next = l1;
                    l1 = l1.next;
                } else if (l1.val < l2.val) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            }

            return head.next;
        }

    }

    public static void main(String[] args) {
        ListNode l1 = generateASingleListNode(new int[]{4, 2, 1, 3});
        ListNode l2 = generateASingleListNode(new int[]{-1, 5, 3, 4, 0});
        ListNode l3 = generateASingleListNode(new int[]{1, 2, 3, 4, 5});
        ListNode l4 = generateASingleListNode(new int[]{5, 4, 3, 2, 1});

        Solution s = new Solution2();

        printSingleListNode(s.sortList(l1));
        printSingleListNode(s.sortList(l2));
        printSingleListNode(s.sortList(l3));
        printSingleListNode(s.sortList(l4));
    }
}
