package _java;

import base.BaseLinkedList;

public class _0023MergeKSortedLists extends BaseLinkedList {

    private abstract static class Solution {
        public abstract ListNode mergeKLists(ListNode<Integer>[] lists);
    }

    // 归并,竟然一次编写一次通过了,奇迹
    // Runtime: 282 ms, faster than 10.47% of Java online submissions for Merge k Sorted Lists.
    // Memory Usage: 40.1 MB, less than 100.00% of Java online submissions for Merge k Sorted Lists.
    private static class Solution1 extends Solution {
        @Override
        public ListNode mergeKLists(ListNode<Integer>[] lists) {
            final int n = lists.length;
            ListNode h = new ListNode(-1);
            ListNode p = h;

            boolean hasNext = true;
            while (hasNext) {
                int min = Integer.MAX_VALUE;
                int minK = 0;
                int nullNum = 0;
                for (int i = 0; i < n; ++i) {
                    if (lists[i] != null) {
                        int v = lists[i].val;
                        if (v < min) {
                            min = v;
                            minK = i;
                        }
                    } else {
                        ++nullNum;
                    }
                }
                if (nullNum == n) {
                    hasNext = false;
                } else {
                    ListNode node = lists[minK];
                    lists[minK] = lists[minK].next;
                    p.next = node;
                    p = p.next;
                }
            }
            return h.next;
        }
    }

    // 优先队列 , 又是一次编写,一次通过,我太牛逼了
    // Runtime: 12 ms, faster than 60.79% of Java online submissions for Merge k Sorted Lists.
    // Memory Usage: 39.2 MB, less than 100.00% of Java online submissions for Merge k Sorted Lists.
    private static class Solution2 extends Solution {

        //非典型PQ
        private static class PQ {

            private int[] heap;
            private int[] index;
            private int size = 0;

            public PQ(int n) {
                heap = new int[n];
                index = new int[n];
            }

            public PQ(int[] a) {
                final int n = a.length;
                heap = new int[n];
                index = new int[n];
                for (int i = 0; i < n; ++i) {
                    insert(i, a[i]);
                }
            }

            private void insert(int i, int e) {
                heap[size] = e;
                index[size] = i;
                swim(size);
                ++size;
            }

            private int delete() {
                int v = index[0];
                heap[0] = heap[size - 1];
                index[0] = index[size - 1];
                sink(0);
                --size;
                return v;
            }

            private void swim(int k) {
                while (k > 0) {
                    int parent = (k - 1) / 2;
                    if (heap[parent] > heap[k]) {
                        swap(k, parent);
                    } else {
                        break;
                    }
                    k = parent;
                }
            }

            private void sink(int k) {
                // 2 * k + 1是左子节点 2* k + 2是右子节点
                // 还有父结点
                while (2 * k + 1 < size) {
                    int child = 2 * k + 1;
                    if (child + 1 < size && heap[child + 1] < heap[child]) {
                        ++child;
                    }
                    if (heap[child] < heap[k]) {
                        swap(child, k);
                        k = child;
                    } else {
                        break;
                    }
                }
            }

            private void swap(int i, int j) {
                int t1 = heap[i];
                int t2 = index[i];
                heap[i] = heap[j];
                index[i] = index[j];
                heap[j] = t1;
                index[j] = t2;
            }

        }

        public ListNode mergeKLists(ListNode<Integer>[] lists) {
            final int n = lists.length;
            PQ pq = new PQ(n);
            ListNode head = new ListNode(-1);
            ListNode p = head;

            // 先行初始化,避免空指针.
            int nullCount = 0;
            for (int i = 0; i < n; ++i) {
                if (lists[i] != null) {
                    pq.insert(i, lists[i].val);
                } else {
                    ++nullCount;
                }
            }

            boolean hasNext = nullCount != n;
            while (hasNext) {
                // 取出最小元素
                int index = pq.delete();
                // 插入
                p.next = lists[index];
                // 移除
                lists[index] = lists[index].next;
                p = p.next;
                if (lists[index] != null) {
                    pq.insert(index, lists[index].val);
                } else {
                    ++nullCount;
                }
                if (nullCount == n) {
                    hasNext = false;
                }
            }
            return head.next;
        }

        private void heapSort(int[] a) {
            final int n = a.length;
            PQ pq = new PQ(a);
            for (int i = 0; i < n; ++i) {
                int index = pq.delete();
                print(a[index] + " , ");
            }
            println("");
        }

    }

    // 分治法
    // 多路归并
    // NlogN
    // Runtime: 7 ms, faster than 90.60% of Java online submissions for Merge k Sorted Lists.
    // Memory Usage: 39.6 MB, less than 100.00% of Java online submissions for Merge k Sorted Lists.
    private static class Solution3 extends Solution {
        @Override
        public ListNode mergeKLists(ListNode[] lists) {
            int n = lists.length;
            return auxMerge(lists, 0, n - 1);
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

        private ListNode merge(ListNode<Integer> l1, ListNode<Integer> l2) {
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
        Solution s = new Solution3();

        printSingleListNode(s.mergeKLists(lists1()));
        printSingleListNode(s.mergeKLists(lists2()));

        //testSort();
    }

    private static void testSort() {
    }

    private static ListNode[] lists2() {
        return new ListNode[0];
    }

    private static ListNode[] lists1() {
        int[] arr1 = {1, 4, 5};
        int[] arr2 = {1, 3, 4};
        int[] arr3 = {2, 6};
        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = generateASingleListNode(arr1);
        listNodes[1] = generateASingleListNode(arr2);
        listNodes[2] = generateASingleListNode(arr3);
        return listNodes;
    }

}