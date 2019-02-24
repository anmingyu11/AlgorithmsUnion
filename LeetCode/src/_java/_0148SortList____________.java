package _java;

import java.util.ArrayList;

import base.BaseLinkedList;

public class _0148SortList____________ extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode sortList(ListNode head);
    }

    // 自底向上的归并排序
    private static class Solution1 extends Solution {

        @Override
        public ListNode sortList(ListNode list) {
            return null;
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
