输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。

 

示例：

给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    private int n = -1;
    private int k = 0;

    public ListNode getKthFromEnd(ListNode head, int k) {
        this.k = k;
        return aux(head, 0);
    }

    private ListNode aux(ListNode x, int idx) {
        if(x == null) {
            n = idx;
            return null;
        }
        ListNode res = aux(x.next, idx + 1);
        if (res != null) {
            return res;
        }
        if (n - idx == k) {
            return x;
        }
        return null;
    }
}
```


```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p = head;
        for(;p != null && k > 0; p = p.next) {
            --k;
        }
        ListNode q = head;
        for(;p != null;) {
            q = q.next;
            p = p.next;
        }
        return q;
    }

}
```
