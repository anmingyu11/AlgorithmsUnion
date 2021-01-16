# 817 Linked LIst Components

## Description

We are given head, the head node of a linked list containing unique integer values.

We are also given the list G, a subset of the values in the linked list.

Return the number of connected components in G, 
where two values are connected if they appear consecutively in the linked list.

Example 1:

Input: 
head: 0->1->2->3
G = [0, 1, 3]
Output: 2
Explanation: 
0 and 1 are connected, so [0, 1] and [3] are the two connected components.
Example 2:

Input: 
head: 0->1->2->3->4
G = [0, 3, 1, 4]
Output: 2
Explanation: 
0 and 1 are connected,
3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
Note:

If N is the length of the linked list given by head, 1 <= N <= 10000.
The value of each node in the linked list will be in the range [0, N - 1].
1 <= G.length <= 10000.
G is a subset of all values in the linked list.

## Solution

#### Solution1
```java
class Solution1 {

    public int numComponents(ListNode head, int[] G) {
        int n = 0;
        Set<Integer> set = new HashSet<>(G.length);

        for (int v : G) {
            set.add(v);
        }

        for (ListNode p = head; p != null; p = p.next) {
            if (set.contains(p.val)) {
                ++n;
            }
            while (p != null && set.contains(p.val)) {
                p = p.next;
            }
            if (p == null) {
                break;
            }
        }

        return n;
    }

}

```

#### Solution2
```java
class Solution2 {

    public int numComponents(ListNode head, int[] G) {
        int N = 10001;
        int components = 0;
        int[] search = new int[N];
        for (int v : G) {
            search[v] = 1;
        }

        for (ListNode p = head; p != null; p = p.next){
            if (search[p.val] > 0) {
                ++components;
            }
            while(p!=null && search[p.val] > 0) {
                p = p.next;
            }
            if (p == null){
                break;
            }
        }
        return components;
    }

}

```

## Reports

1. 使用set进行查询
2. 用空间换时间，条件中给出了 1<=N<=10000, 0<=v<N
