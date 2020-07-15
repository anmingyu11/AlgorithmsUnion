> 未经允许不得转载

相关题目:

> [141. Linked List Cycle]([https://leetcode.com/problems/linked-list-cycle/submissions/](https://leetcode.com/problems/linked-list-cycle/)
)
> [142. Linked List Cycle II]([https://leetcode.com/problems/linked-list-cycle-ii/](https://leetcode.com/problems/linked-list-cycle-ii/)
)

不想看描述和解法的可以直接跳过下面的部分。

####  描述

Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer `pos` which represents the position (0-indexed) in the linked list where tail connects to. If `pos` is `-1`, then there is no cycle in the linked list.

**Example 1:**

**Input:** head = [3,2,0,-4], pos = 1
**Output:** true
**Explanation:** There is a cycle in the linked list, where tail connects to the second node.

![image](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png) 

**Example 2:**

**Input:** head = [1,2], pos = 0
**Output:** true
**Explanation:** There is a cycle in the linked list, where tail connects to the first node.

![image](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png) 

**Example 3:**

**Input:** head = [1], pos = -1
**Output:** false
**Explanation:** There is no cycle in the linked list.

![image](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

--------------------------

#### 141 解:

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
            ListNode slow = head, fast = head;
            while (slow != null) {
                if (fast.next == null || fast.next.next == null) {
                    break;
                }
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    return true;
                }
            }
            return false;
    }
}
```
#### 142 解:

```java
public class Solution {
    private ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
}

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an entrance to
        // a cycle.
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // To find the entrance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }
}
```
--------------------------

这道题的具体描述和解题代码就不多做解释了，写关于解法的多如牛毛，下功夫都能明白这道题应该都能清楚是怎么做的。

顺便说一下，这种找重复的问题首先要想到的是要用`HashSet`去解决，`O(n)`的方法如果你没见过的话是很难想出来的。

**但是最关键问题在于，解法虽然简单，但是怎么证明你的解法是对的，除了leetcode的黑盒测试，给出一个数学上的一般化证明是十分必要。**

我看了网上的很多资料，绝大多数给出的证明都是错的，只是瞎猫碰死耗子碰对了，要么剩下的就极其繁琐文字众多并且一些地方非常模糊。

#### 1. 大致解题思路：

给两个指针:

- `slow` :  每次走一步
- `fast`：每次走两步

`slow`，`fast`指针从头开始扫描链表。指针 `slow` 每次走1步，指针 `fast` 每次走2步。如果存在环，则指针 `slow` 、`fast`会相遇。

如果环不存在，`fast`遇到`NULL`退出。

**使用这种方法，有一个定律**：

> `fast`和`slow`一定会环内相遇。

我们这篇文章的主要目的就在于给出这个定律的证明。

#### 2. 最广泛的错误证法：

> 如果两个指针的速度不一样，比如$p$，$q$，$(0<p<q)$二者满足什么样的关系，可以使得两者肯定交与一个节点？
>
>设$p$为指针`slow`每步移动距离，$q$为指针`fast`的每步移动距离。
设当`slow`指针到环的入口时，`fast`指针已经在环里走过了$k$
>1)
> $$Sp(i) = p*i$$ 
>2) 如果两个要相交于一个节点，则 
> $$Sq(i) = k + q*i$$
>3)
>$$
Sp(i) = Sq(i)
$$
>4)
>$$
(p*i) \bmod n = ( k+ q*i ) \bmod n
$$
>5)
>$$
[ (q - p) * i + k ]  \bmod n =0
$$
>6)
>$$
(q-p)i + k  = N*n [N 为自然数]
>$$
>7)
>$$
>i = (N*n -k) /(p-q)
>$$
$i$取自然数，则当 $p$，$q$满足上面等式 即存在一个自然数$N$，可以满足$N*n - k$ 是 $p - q$ 的倍数时，保证两者相交。

这个在互联网上最为广泛简洁的证明看似很有道理没什么问题，但是却有其致命的错误：

>**求模操作满足分配率吗?**

我不会证求模操作是否满足分配率，但是运行如下代码(python)，可以证明上面证明步骤 `4->5` 是错误的:

```python
ks = range(1,100,1)
ns = range(1,100,1)

total = len(ks) * len(ks) * len(ns)
i = 0
for n in ns:
    for k1 in ks:
        for k2 in ks:
            if (k1 + k2) % n != k1 % n + k2 % n:
                i += 1
                #print('k1 : {} k2 : {} n :{}'.format(k1,k2,n))
print('unsatisfied : {}'.format(i))
print('total : {}'.format(total))
```

输出结果：
```python
unsatisfied : 393883
total : 970299
```

**实际上求模操作是不满足像除法一样的分配率的**

当然上面的错误也归功于[百度百科](https://baike.baidu.com/item/%E5%8F%96%E6%A8%A1%E8%BF%90%E7%AE%97)给出的错误公式。
$$
(a+b) \mod p = ( a \mod p + b \mod p )
$$
实际上求模的分配率是这样的
$$
(a + b) \bmod n = [(a \bmod n) + (b \bmod n)] \bmod n
$$

同样给出一段代码:
```python
ks = range(1,100,1)
ns = range(1,100,1)

i = 0

for n in ns:
    for k1 in ks:
        for k2 in ks:
            if (k1 + k2) % n != (k1 % n + k2 % n) % n:
                i+=1

print('unsatisfied : {}'.format(i))
print('total : {}'.format(total))
```
输出结果：
```python
unsatisfied : 0
total : 970299
```

#### 3. 正确的证法：

设快慢指针`slow`，`fast`

设从开始走过的总步数为$s$

设非环部分的长度为$n_1$，环的长度为$n_2$

1. 假设当slow进入环，从开始走过s步相遇有：
$$
(s-n_1) \bmod n_2 = (2*s - n_1) \bmod n_2
$$

2. 即两者余数相同时等式成立，设余数为$c$，设`slow` 指针在环中走过了$k_1$圈，`fast`指针在环中走过了$k_2$圈，可得
3.
$$
s - n_1 = k_1 * n_2 + c
$$
4.
$$
2*s - n_1 =k_2 * n_2 + c
$$
5. 推出
$$
s = (k_2 - k_1) * n_2
$$

上面这个式子明显是成立的，因为 $k_2 - k_1 >=0$ 且$ k_2 >= k_1$
这里很明显$k_2 > k_1$

又因$s >= n_1$

所以当$s$为$N * n_2$，$N$为正整数时

5 式成立

当
$$k_1 =0$$
6.
$$
s = k_2 * n_2
$$
6 式成立，并能使$k_2$最小。

至于$k_2$是多少，具体要取决于链表中非环和环的长度之比，因为$s >= n_1$ 即$\exists k_2 \in N$使得$k_2 * n_2 >= n_1$时 6 式成立。

从而可以得出推论，
$$
k_2 >= \frac{n_1}{n_2}
$$

当 $n_1 <= n_2$ 时 $k_2 >=1$

当 $n_1 > n_2$ 时 $k_2 >=2$

根据以上的证明，关于单链表查环相关的问题都可以迎刃而解。

--------------------------

#### 4. 求环的长度

`slow`与`fast`相遇后，`slow`再次与`fast`相遇，计算`slow`走过的步数就是环的长度。

太过浅显请读者自证。

--------------------------

#### 5. 求环的入口

这里我们可以利用上面快慢指针相遇的结论，假设同上：

根据上面的结论，假设当`slow`和`fast`相遇时，相遇点为$h$，其中$h$是环内的点$0 <= h < n_2$

1. 当两个指针相遇时可以得到
$$
2 * distance(slow) = distance(fast)
$$
2. 
$$
2 * (n_1 + h) = n_1 + k_2 * n_2 + h
$$
3. 化简可得
$$
n_1  - k_2 * n_2 + h = 0
$$
4. 等式两边同时  mod $n_2$
$$
[n_1 \bmod n_2  + h \bmod n_2] \bmod n_2 = 0
$$
5. 为使得等式成立，设:
$$
n1 = k*n_2 + c 
$$
6. c为余数，可得
$$
c+h = t * n_2
$$
7. 可得
$$
c = t * n_2 - h
$$
8. 最终得到结论
$$
n_1 = (k + t-1) * n_2 + n_2 - h
$$

这样我们可以得到一个结论，我们设定一个头指针，从`head` 开始走，在`slow`和`fast`指针第一次相交的位置，`slow`指针开始走，当`head`指针走到环的入口，即slow指针在环里走了$n_2 - h $ 步，`head`指针与`slow`指针相交，找到环的入口。

--------------------------

最后只附上一个英文资料，是leetcode美国版上的，他的证明虽然对，但是有点太魔幻，不是很容易理解：
[https://leetcode.com/articles/linked-list-cycle-ii/](https://leetcode.com/articles/linked-list-cycle-ii/)

这个单链表查环问题的问题有个专有名称：Floyd's Tortoise and Hare或者[Floyd判圈法](https://zh.wikipedia.org/wiki/Floyd%E5%88%A4%E5%9C%88%E7%AE%97%E6%B3%95)

因为我看到的中文资料几乎全部有问题，当然也不排除我的做法本身也有问题，因为我的数学功底本身就很弱，如果是行家的话应该能从我的过程看的出来，这就麻烦一些精通数学证明的朋友们来帮忙指正批评了。
