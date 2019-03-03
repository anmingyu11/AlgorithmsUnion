package _4Graph.sp;

/******************************************************************************
 *  Compilation:  javac CPM.java
 *  Execution:    java CPM < input.txt
 *  Dependencies: EdgeWeightedDigraph.java AcyclicDigraphLP.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/44sp/jobsPC.txt
 *
 *  Critical path method.
 *
 *  % java CPM < jobsPC.txt
 *   job   start  finish
 *  --------------------
 *     0     0.0    41.0
 *     1    41.0    92.0
 *     2   123.0   173.0
 *     3    91.0   127.0
 *     4    70.0   108.0
 *     5     0.0    45.0
 *     6    70.0    91.0
 *     7    41.0    73.0
 *     8    91.0   123.0
 *     9    41.0    70.0
 *  Finish time:   173.0
 *
 ******************************************************************************/

import base.stdlib.In;
import base.stdlib.StdOut;

/**
 * The {@code CPM} class provides a client that solves the
 * parallel precedence-constrained job scheduling problem
 * via the <em>critical path method</em>. It reduces the problem
 * to the longest-paths problem in edge-weighted DAGs.
 * It builds an edge-weighted digraph (which must be a DAG)
 * from the job-scheduling problem specification,
 * finds the longest-paths tree, and computes the longest-paths
 * lengths (which are precisely the start times for each job).
 * <p>
 * This implementation uses {@link AcyclicLP} to find a longest
 * path in a DAG.
 * The running time is proportional to <em>V</em> + <em>E</em>,
 * where <em>V</em> is the number of jobs and <em>E</em> is the
 * number of precedence constraints.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * 优先级限制下的并行任务调度问题的关键路径方法
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class CPM {

    // this class cannot be instantiated
    private CPM() {
    }

    /**
     * Reads the precedence constraints from standard input
     * and prints a feasible schedule to standard output.
     * <p>
     * 数据的输入格式见:/home/amy/github/AlgEssentialsSRC/AlgEdition4/resources/4graphs/sp/jobsPC.txt
     * 1. n代表任务的数目
     * 2. source代表起点任务,是倒数第二个顶点2*n,sink代表终点任务,是最后一个顶点,2*n+1 ,
     * 3. 顶点的总数是起点s,终点t,加上任务的数量n*2(每个任务对应两个顶点,权重为任务的消耗)
     * 4. 迭代所有任务,迭代开始:
     * 4-1,添加任务
     * - 首先输入的是任务的权重duration
     * - 将这个任务按自然数顺序标记,即我们按任务数标记,任务编号为{0,...i...n-1}
     * - 设当前任务为i
     * - 添加source->i,权重为0,这是起点到这个点的权重
     * - 添加i+n->sink,权重为0,i+n为任务的终止顶点,终止顶点到终点的权重为0
     * - 添加i->i+n,权重为这个任务本身的消耗,duration
     * 4-2,添加优先级限制
     * - 首先读取m,是优先级限制的数量
     * - 其次逐个读取优先级限制的任务,这个任务设为precedent,这个任务需要在当前任务i完成之前完成.
     * - i+n对应的是任务的终止结点,于是需要添加一条从n+i->precedent的边,权重为0
     * 5. 通过AcycliocLp算法计算最长路径,就是优先级限制下的并行任务调度问题的关键路径方法.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In("/home/amy/github/AlgEssentialsSRC/AlgEdition4/resources/4graphs/sp/jobsPC.txt");
        int n = in.readInt();
        int source = 2 * n;
        int sink = 2 * n + 1;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * n + 2);
        for (int i = 0; i < n; ++i) {
            double duration = in.readDouble();
            G.addEdge(new DirectedEdge(source, i, 0.0));
            G.addEdge(new DirectedEdge(i + n, sink, 0.0));
            G.addEdge(new DirectedEdge(i, i + n, duration));
            int m = in.readInt();
            for (int j = 0; j < m; j++) {
                int precedent = in.readInt();
                G.addEdge(new DirectedEdge(i + n, precedent, 0.0));
            }
        }
        AcyclicLP lp = new AcyclicLP(G, source);
        StdOut.println(" job   start  finish");
        StdOut.println("--------------------");
        for (int i = 0; i < n; i++) {
            StdOut.printf("%4d %7.1f %7.1f\n", i, lp.distTo(i), lp.distTo(i + n));
        }
        StdOut.printf("Finish time: %7.1f\n", lp.distTo(sink));
    }

}