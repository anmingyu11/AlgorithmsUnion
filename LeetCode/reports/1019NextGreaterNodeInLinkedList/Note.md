# 1019 Next Greater Node In Linked List

## Description

We are given a linked list with head as the first node.
Let's number the nodes in the list: node_1, node_2, node_3, ... etc.

Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val 
such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  
If such a j does not exist, the next larger value is 0.

Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).

Note that in the example inputs (not outputs) below, 
arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, 
second node value of 1, and third node value of 5.

Example 1:

Input: [2,1,5]
Output: [5,5,0]

Example 2:

Input: [2,7,4,3,5]
Output: [7,0,5,5,0]

Example 3:

Input: [1,7,5,1,9,2,5,1]
Output: [7,9,9,9,0,5,0,0]

Note:

1 <= node.val <= 10^9 for each node in the linked list.
The given list has length in the range [0, 10000].

## Solution

#### Solution1

```java
class Solution1 {

    public int[] nextLargerNodes(ListNode head) {
        int[] A, R;
        A = toArray(head);
        R = new int[A.length];

        for (int i = 1; i < A.length; ++i) {
            int v = A[i];
            for (int j = i - 1; j >= 0 && v > A[j]; --j) {
                if(R[j] ==0){
                    R[j] = v;
                }
            }
        }
        return R;
    }

    private int[] toArray(ListNode head) {
        int i = 0, n = 0;
        int[] A;
        for (ListNode p = head; p != null; p = p.next) {
            ++n;
        }
        A = new int[n];
        for (ListNode p = head; p != null; p = p.next) {
            A[i++] = p.val;
        }
        return A;
    }

}
```

#### Solution2

```java
class Solution2 {

    public int[] nextLargerNodes(ListNode head) {
        int[] A = toArray(head);
        LinkedList<Integer> stack = new LinkedList<>();
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            while (!stack.isEmpty() && A[i] > A[stack.getLast()]) {
                result[stack.removeLast()] = A[i];
            }
            stack.addLast(i);
        }
        return result;
    }

    private int[] toArray(ListNode head) {
        int i = 0, n = 0;
        int[] A;
        for (ListNode p = head; p != null; p = p.next) {
            ++n;
        }
        A = new int[n];
        for (ListNode<Integer> p = head; p != null; p = p.next) {
            A[i++] = p.val;
        }
        return A;
    }

}
```

## Reports

栈的解法有些过于精妙。

栈能够记录一个数组，通过外层遍历，能够记录一个数组的片段。

太精妙了无以言表，艺术一样的代码:
```
for(int i = 0; i < n;++i) {
    int v = A[i];
    while(!stack.isEmpty() && v > A[stack.getLast()]) {
        R[stack.removeLast] = v;
    }
    stack.addLast(i);
}
```
