## 栈和队列

栈实现的是一种后进先出(LIFO)策略.
队列实现的是一种先进先出(FIFO)策略.

### 栈
```
STACK-EMPTY(S)
if S.top == 0
  return true
else
  return false
```

```
PUSH(S,x)
S.top = S.top + 1
S[S.top]  = x
```

```
POP(S)
if STACK-EMPTY(S)
  error "underflow"
else if S.top = S.top - 1
  return S[S.top + 1]
```

### 队列

队列上的INSERT操作称为入队(ENQUEUE),DELETE操作称为出队(DEQUEUE);

该队列有一个属性Q.head指向队头元素.

而属性Q.tail则指向下一个新元素将要插入的位置.

```
ENQUEUE(Q,x)
Q[Q.tail] = x
if Q.tail == Q.length
  Q.tail = 1
else
  Q.tail = Q.tail + 1
```

```
DEQUEUE(Q)
x = Q[Q.head]

if Q.head == Q.length
  Q.head = 1
else
  Q.head = Q.head + 1

return x
```
