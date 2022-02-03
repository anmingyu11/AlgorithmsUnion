请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

 

示例 1：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png)

输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
示例 2：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png)

输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
示例 3：

![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e3.png)

输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
示例 4：

输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。
 

提示：

-10000 <= Node.val <= 10000
Node.random 为空（null）或指向链表中的节点。
节点数目不超过 1000 。


# solution1
```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    private Map<Node,Node> map;
    public Node copyRandomList(Node head) {
        map = new HashMap<Node,Node>();
        Node p = head;
        while(p != null) {
            Node x = new Node(p.val);
            map.put(p, x);
            p = p.next;
        }
        p = head;
        while(p != null) {
            Node x = map.get(p);
            x.next = map.get(p.next);
            x.random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }
}
```

## solution2
```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    private Map<Node,Node> map = new HashMap<Node,Node>();
    public Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }
        Node p = head, pc = map.get(p);
        if(pc == null) {
            pc = new Node(p.val);
            map.put(p, pc);
            pc.next = copyRandomList(p.next);
            pc.random = copyRandomList(p.random);
        }
        return map.get(head);
    }
}
```
