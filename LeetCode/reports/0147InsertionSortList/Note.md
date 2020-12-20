# 147 Insertion Sort List

## Description
Sort a linked list using insertion sort.

![](https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif)

A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 

Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5


## Solution

```java
class Solution {
    public ListNode insertionSortList(ListNode head) {
            ListNode res = new ListNode(-1);
            ListNode cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = null;
                // prev , cur
                ListNode prevRes = res, curRes = res.next;
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
```

## Description

从一个链表里实现插入排序是很难的，链表在查询上其实本质也是插入排序的特点。

插入排序与链表的插入是相似的，插入排序是局部链表的排序


```java
class Sort { 
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
```


