#  148 SortList

## Solution

```java

class Solution extends Solution {

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
    public ListNode sortList(ListNode<Integer> head) {
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
    ListNode merge(ListNode<Integer> l1, ListNode<Integer> l2) {
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
```


## Note

《算法4》里讲到归并排序常应用于链表排序

当然这里的merge方法中的技巧也很重要，之前一直傻乎乎的一个个的往dummy身上接，其实在归并的一条线断了之后就可以直接将第二条线加上来了。