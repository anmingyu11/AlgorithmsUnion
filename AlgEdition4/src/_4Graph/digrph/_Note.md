## 有向图

一幅有方向性的图(或有向图)是由一组顶点和一组有方向的边组成的而每条有方向的边都连接着有序的一对顶点.

在一幅有向图中,有向路径由一系列顶点组成,对于其中的每个顶点都存在一条有向边从它指向序列中的下一个顶点.

有向环为至少含有一条边且起点和终点相同的有向路径.

简单有向环是一条(除了起点和终点必须相同之外)不含有重复的顶点和边的环.路径或环的长度即为其中所包含的边数.

### 环和有向无环图

有向无环图(DAG)就是一幅不含有向环的有向图.

#### 拓扑排序

给定一幅有向图,将所有顶点排序,使得所有有向边均从前面的元素指向在后面的元素(或者说明办不到).

#### 命题E:当且仅当一副有向图是无环图时它才能进行拓扑排序.

证明:如果一幅有向图含有一个环,它就不可能是拓扑有序的.与此相反,拓扑排序能够计算任意有向无环图的拓扑顺序
> 这个证明太随意了.


#### DFS的前序,中序,后序

前序就是dfs()调用的顺序
后序就是顶点遍历完成的顺序.
逆后序就是顶点遍历完成的倒序.

#### 命题F: 一幅有向无环图的拓扑顺序即为所有顶点的逆后序排列.

证明:对于任一边v->w,在调用dfs(v)时,下面三种情况必有一成立.

1.dfs(w)已经被调用过且已经返回了(w已经被标记).
2.dfs(w)还没有被调用(w还没有被标记),因此v->w会直接或间接调用并返回dfs(w),且dfs(w)会在dfs(v)返回前返回.
3.dfs(w)已经被调用但未返回.证明的关键在于,在有向无环图中这种情况是不可能出现的,这是由于递归调用链意味着存在从w到v的路径,但存在v->w则表示存在一个环.

在两种可能的情况中,dfs(w)都会在dfs(v)之前完成,因此在后序排列中w排在v之前而在逆后序中,w排在v之后.
因此任意一条边v->w都如我们所愿地从排名较前顶点指向排名较后的顶点.

> 后序是dfs()完成的序列,最先完成的肯定是在一个树中的叶子结点,最后完成的肯定是根结点,那么逆后序就可以解释的通了.

#### 命题G: 使用深度优先搜索对有向无环图进行拓扑排序所需的时间和V+E成正比

证明:由代码可知,第一遍深度优先搜索保证了不存在有向环,第二遍深度优先搜索产生了顶点的逆后序排列.两次搜索都访问了所有的顶点和所有的边,因此它所需的时间和V+E成正比.

检测有向环是拓扑排序的前提.

### 有向图中的强连通性

如果两个顶点v和w是互相可达的,则称它们为强连通的.也就是说,既存在一条从v到w的有向路径,也存在一条从w到v的有向路径.如果一幅有向图的任意两个顶点都是强连通的,则称这幅有向图也是强连通的.

强连通分量的定义是基于顶点的,而非边.

#### Kosaraju算法

为了找到所有强连通分量,它会在反向图中进行深度优先搜索来将顶点排序(搜索顺序的逆后序),在给定有向图中用这个顺序再进行一次深度优先搜索.

#### 命题H: 使用深度优先搜索查找给定有向图G的反向图GR,根据由此得到的所有顶点的逆后序再用深度优先搜索处理有向图G,其构造函数中的每一次递归调用所标记的顶点都在同一个强连通分量中.

证明: 首先要用反证法证明:"每个和s强连通的顶点v都会在构造函数调用的dfs(G,s)中被访问到".
假设有一个和s强连通的顶点v不会在构造函数调用的dfs(G,s)中被访问到.因为存在从s到v的路径,所以v肯定在之前就已经标记过了.但是,因为也存在从v到s的路径,在dfs(G,v)调用中s肯定会被标记,因此构造函数应该是不会调用dfs(G,s)的.矛盾.

其次要证明:"构造函数调用的dfs(G,s)所到达的任意顶点v都必然是和s强连通的".

设v为dfs(G,s)到达的某个顶点.那么G中必然存在一条从s到v的路径,因此只需要证明G中还存在一条从v到s的路径即可.这也等价于Gr中存在一条从s到v的路径,因此只需要证明在Gr中存在一条从s到v的路径即可.

证明的核心在于,按照逆后序进行的深度优先搜索意味着,在Gr中进行的深度优先搜索中,dfs(G,v)必然在dfs(G,s)之前就已经结束了,这样dfs(G,v)的调用就只会出现两种情况.

> G的逆后序是拓扑顺序,Gr的逆后序就是拓扑顺序的反序,也就是说,对于G,生成的Gr的逆后序就是G的反拓扑顺序,即s->v   gr的逆后序就是相当于G图中的v->s

下面来说这两种情况:

1. 调用dfs(G,v)在dfs(G,s)的调用之前&&dfs(G,s)的调用之前结束;
2. 调用dfs(G,v)在dfs(G,s)的调用之后&&在dfs(G,s)的结束之前结束;

第一种情况是不可能出现的,因为在Gr中存在一条从v到s的路径;而第二种情况则说明Gr中存在一条从s到v的路径,完毕.

假如在G中,有s->v,那么在Gr的逆序列会生成v->s,按照这个序列进行遍历,那么就可以判定v->s是强连通的.

#### 命题I: Kosaraju算法的预处理所需的时间和空间与V+E成正比且支持常数时间的有向图连通性的查询

证明: 该算法会处理有向图的反向图进行两次深度优先搜索.这三步所需的时间都与V+E成正比.反向复制一副有向图的空间与V+E成正比.

### 传递闭包

有向图G的传递闭包是有相同的一组顶点组成的另一幅有向图,在传递闭包中存在一条从v指向w的边当且仅当在G中w是从v可达的.