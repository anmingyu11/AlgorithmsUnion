# 19 Remove Nth Node From End of List

## Description

## Solution

#### Solution1

```java
class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head, dummy = new ListNode(-1), prev = dummy;
        dummy.next = head;
        int len = 0;
        for (; p != null; p = p.next, ++len) ;
        int i = len;
        for (p = head; p != null; prev = p, p = p.next, --i) {
            if (i == n) {
                prev.next = p.next;
            }
        }
        return dummy.next;
    }

}
```
#### Solution2
```java
class Solution2 {

    private int total;
    private int n;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        this.total = -1;
        this.n = n;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        aux(dummy, head, 0);
        return dummy.next;
    }

    private void aux(ListNode prev, ListNode cur, int i) {
        if (cur == null) {
            total = i;
            return;
        }
        aux(cur, cur.next, i + 1);
        if (total > 0 && total - i == n) {
            prev.next = cur.next;
        }
    }

}
```

#### Solution3

```java
class Solution3 {

    @Override
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1), prev = dummy, slow = head, fast = head;
        dummy.next = head;
        for (int i = 0; i < n; ++i, fast = fast.next) ;
        while (fast != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = slow.next;
        return dummy.next;
    }
}
```

## Reports

1. 递归方式删除节点。
2. 快慢指针的巧妙应用。
3. 对于快慢针需要好好思考思考.
