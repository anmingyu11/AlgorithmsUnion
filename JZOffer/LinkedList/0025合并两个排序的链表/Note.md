输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

示例1：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
限制：

0 <= 链表长度 <= 1000


## Solution1

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode fake = new ListNode(-1);
        ListNode p = fake;
        while(p1 != null || p2 != null) {
            if (p1 != null && p2 != null) {
                ListNode cur = null;
                if(p1.val < p2.val) {
                    cur = p1;
                    p1 = p1.next;
                    cur.next = null;
                    p.next = cur;
                } else {
                    cur = p2;
                    p2 = p2.next;
                    cur.next = null;
                    p.next = cur;
                }
            } else if(p1 == null) {
                p.next = p2;
                break;
            } else if(p2 == null) {
                p.next = p1;
                break;
            }
            p = p.next;
        }
        return fake.next;
    }
}
```

## Solution2
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode fake = new ListNode(-1);
        ListNode p = fake;
        while(p1 != null && p2 != null) {
            if (p1 != null && p2 != null) {
                if(p1.val < p2.val) {
                    p.next = p1;
                    p1 = p1.next;
                } else {
                    p.next = p2;
                    p2 = p2.next;
                }
            }
            p = p.next;
        }
        if(p1 == null) {
            p.next = p2;
        } else if(p2 == null) {
            p.next = p1;
        }
        return fake.next;
    }
}
```
