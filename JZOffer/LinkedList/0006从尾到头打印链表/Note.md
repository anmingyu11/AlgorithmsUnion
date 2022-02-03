输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

 

示例 1：

输入：head = [1,3,2]
输出：[2,3,1]
 

限制：

0 <= 链表长度 <= 10000

# Solution1

反序问题首先要想到用栈。

```Java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        for(ListNode p = head; p != null; p=p.next) {
            ll.addLast(p.val);
        }
        int n = ll.size();
        int[] res = new int[n];
        for(int i = 0; i < n; ++i) {
            res[i] = ll.removeLast();
        }
        return res;
    }

}
```

# Solution2


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

    private int total;
    private int[] res;

    public int[] reversePrint(ListNode head) {
        aux(head, 0);
        return res;
    }

    private void aux(ListNode p, int c) {
        if (p == null) {
            total = c;
            res = new int[total];
            return;
        }
        aux(p.next, c + 1);
        res[total - 1 - c] = p.val;
    }

}
```
