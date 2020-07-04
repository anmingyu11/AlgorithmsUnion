# 23 MergeKSortedLists

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6

## Solutions

### 优先队列

```java
class Solution {

        private class PQ {
            private ListNode[] heap;
            private int n = 0;

            private PQ(int total) {
                heap = new ListNode[total];
                n = 0;
            }

            private void insert(ListNode node) {
                heap[n++] = node;
                swim(n - 1);
            }

            private ListNode delMin() {
                ListNode min = heap[0];
                heap[0] = heap[n - 1];
                heap[n - 1] = null;
                --n;
                sink(0);
                return min;
            }

            private void sink(int k) {
                while (2 * k + 1 < n) {
                    int left = 2 * k + 1;
                    int right = 2 * k + 2;
                    int childIdx;
                    if (right < n) {
                        childIdx = less(heap[left], heap[right]) ? left : right;
                    } else {
                        childIdx = left;
                    }
                    if (less(heap[childIdx], heap[k])) {
                        swap(childIdx, k);
                        k = childIdx;
                    } else {
                        break;
                    }
                }
            }

            private void swim(int k) {
                int p = 0;
                while (true) {
                    p = (k - 1) / 2;
                    if (p < 0) {
                        return;
                    }
                    if (less(heap[k], heap[p])) {
                        swap(k, p);
                        k = p;
                    } else {
                        return;
                    }
                }
            }

            private boolean less(ListNode a, ListNode b) {
                return a.val < b.val;
            }

            private void swap(int a, int b) {
                ListNode temp = heap[b];
                heap[b] = heap[a];
                heap[a] = temp;
            }
        }

        public ListNode mergeKLists(ListNode[] lists) {
            ListNode dummy = new ListNode(-1), pD = dummy;

            int total = 0;
            for (ListNode node : lists) {
                ListNode p = node;
                while (p != null) {
                    p = p.next;
                    ++total;
                }
            }

            PQ pq = new PQ(total);
            for (ListNode node : lists) {
                ListNode p = node;
                while (p != null) {
                    pq.insert(p);
                    p = p.next;
                }
            }

            while (pq.n > 0) {
                ListNode min = pq.delMin();
                pD.next = min;
                pD = pD.next;
            }
            pD.next = null;

            return dummy.next;
        }

}
```

### 多路归并
```java
class Solution{
    
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
```
