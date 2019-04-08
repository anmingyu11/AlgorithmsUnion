## 分治策略

当子问题足够大,需要递归求解时,我们称之为**递归情况**.当子问题变得足够小,不需要再递归时,我们说递归已经"触底",进入了**基本情况**. 


#### 递归式

本章介绍三种求解递归式的方法,即得出算法的"Θ"或"O"渐进界的方法:

(1) **代入法**
我们猜测一个界,然后用数学归纳法证明这个界是正确的.
(2) **递归树法**
将递归式转换为一棵树,其结点表示不同层次的递归调用产生的代价.然后采用边界和技术来求解递归式
(3) **主方法**
可求解形如下面公式的递归式的界:
```
T(n) = aT(n/b) + f(n)
```
其中a>=1,b>1,f(n)是一个给定的函数.这种形式的递归式很常见,它刻画了这样一个分治算法:生成a个子问题,每个问题的规模是原问题规模的1/b,分解和合并步骤总共花费时间为f(n)


### 最大子数组问题

### 矩阵乘法的Strassen算法.

实际上,我们可以不必复制元素就能完成矩阵分解,其中的诀窍使用下标计算.

### 用代入法求解递归式

代入法求解递归式分两步:

1.猜测解的形式.
2.用数学归纳法求出解中的常数,并证明解是正确的.


因为子问题的规模每一步减少为上一步的1/4,所以最终必然会达到边界条件.那么根结点与距离为1的子问题举例多远呢?深度为i的结点对应规模为n/(4^i)的子问题.因此,当n/(4^i)=1或等价地i=log(4)(n)时,子问题规模变为1.因此,递归树右log(4)(n) +1层.(深度为0,1,2,...,log(4)(n)).

### 用主方法求递归式

主方法为如下形式的递归式提供了一种"菜谱"式的求解方法

```
T(n) = aT(n/b) + f(n)
```

主定理

### 证明主定理

#### 对b的幂证明主定理

#### 向下取整和向上取整.