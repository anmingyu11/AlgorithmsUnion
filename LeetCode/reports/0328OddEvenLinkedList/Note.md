# 328. Odd Even Linked List

## Solution

#### Solution1
思维定式

```java
class Solution {

    public ListNode<Integer> oddEvenList(ListNode<Integer> head) {
        ListNode<Integer> fake = new ListNode<>(-1);

        ListNode<Integer> odd = new ListNode<>(-1);
        ListNode<Integer> even = new ListNode<>(-1);
        ListNode p = head;
        ListNode po = odd;
        ListNode pe = even;
        int i = 0;
        while (p != null) {
            ListNode node = p;
            p = p.next;
            node.next = null;
            ++i;
            if ((i & 1) == 1) {
                po.next = node;
                po = po.next;
            } else {
                pe.next = node;
                pe = pe.next;
            }
        }
        fake.next = odd.next;
        po.next = even.next;
        return fake.next;
    }
}
```

#### Solution2
优雅

```java
class Solution {

    public ListNode<Integer> oddEvenList(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head, even = head.next,evenHead=head.next;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

}
```

## Reports

从这题开始体会了算法工程师的思维。

理解和体会一个算法的最好方式就是用手实现一遍。
