# 138 Copy List With Random Pointer

## Solution

### Solution1

```java
class Solution {
        public Node copyRandomList(Node head) {
            Node dummy = new Node(-1), pD = dummy;
            Map<Node, Node> map = new HashMap<>();
            for (Node p = head; p != null; p = p.next, pD = pD.next) {
                Node newNode = new Node(p.val);
                pD.next = newNode;
                map.put(p, newNode);
            }

            for (Node p = head; p != null; p = p.next) {
                Node r = p.random;
                map.get(p).random = map.get(r);
            }

            return dummy.next;
        }
}
```

#### Solution2

```java
class Solution {
        Map<Node, Node> map = new HashMap<>();

        public Node copyRandomList(Node head) {
            if (head == null) {
                return head;
            }

            for (Node p = head; p != null; p = p.next) {
                Node node = getCloneNode(p);
                node.random = getCloneNode(p.random);
                node.next= getCloneNode(p.next);

            }

            return map.get(head);
        }

        private Node getCloneNode(Node oldNode) {
            if(oldNode == null){
                return null;
            }
            Node newNode = map.get(oldNode);
            if (newNode != null) {
                return newNode;
            } else {
                newNode = new Node(oldNode.val, null, null);
                map.put(oldNode, newNode);
                return newNode;
            }
        }
}
```

#### Solution3
```java
class Solution {
        Map<Node, Node> map = new HashMap<>();

        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            Node node = map.get(head);

            if (node == null) {
                node = new Node(head.val,null,null);
            } else {
                return node;
            }

            map.put(head, node);

            node.random = copyRandomList(head.random);
            node.next = copyRandomList(head.next);

            return node;
        }
}
```

## Reports

1. Map + 图遍历，其实Map<Node,Node>本身已经是一张邻接表了，只是是用map实现的，可以快速读取
2. 这个问题是披着链表外衣的图.