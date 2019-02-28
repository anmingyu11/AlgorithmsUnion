package _java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import base.Base;

public class _0056MergeIntervals extends Base {

    private static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start + " , " + end + "]";
        }
    }

    private abstract static class Solution {
        public abstract List<Interval> merge(List<Interval> intervals);
    }

    // Runtime: 11 ms, faster than 95.09% of Java online submissions for Merge Intervals.
    // Memory Usage: 40.9 MB, less than 88.97% of Java online submissions for Merge Intervals.
    private static class Solution1 extends Solution {

        @Override
        public List<Interval> merge(List<Interval> intervals) {
            final int n = intervals.size();
            if (n < 2) {
                return intervals;
            }
            intervals.sort(new Comparator<Interval>() {
                @Override
                public int compare(Interval o1, Interval o2) {
                    if (o1.start < o2.start) {
                        return -1;
                    } else if (o1.start > o2.start) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            LinkedList<Interval> ans = new LinkedList<>();
            ans.add(intervals.get(0));
            for (int i = 1; i < n; ++i) {
                Interval i1 = ans.getLast();
                Interval i2 = intervals.get(i);
                if (intersect1(i1, i2)) {
                    // 去掉最后一个并且归并这两个
                    Interval r = merge(i1, i2);
                    ans.removeLast();
                    ans.add(r);
                } else {
                    // 如果不是继续归并
                    ans.add(i2);
                }
            }
            return ans;
        }

        private boolean intersect1(Interval i1, Interval i2) {
            final int s1 = i1.start, s2 = i2.start, e1 = i1.end, e2 = i2.end;
            if (e1 < s2 || e2 < s1) {
                return false;
            }
            return true;
        }

        private Interval merge(Interval i1, Interval i2) {
            return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
        }
    }

    // 这个求强连通分量的做法很有启发性 复制粘贴的
    // 无法AC, Memory Exceed
    private static class Solution2 extends Solution {
        private Map<Interval, List<Interval>> graph;
        private Map<Integer, List<Interval>> nodesInComp;
        private Set<Interval> visited;

        /**
         * 基础功能:判断overlap
         * 这个判断overlap的方法简单而又优雅.
         *
         * @param a
         * @param b
         * @return
         */
        private boolean overlap(Interval a, Interval b) {
            return a.start <= b.end && b.start <= a.end;
        }

        /**
         * 构造无向图,每个interval都对应所有其他重合的结点
         * 图的数据结构是简单的邻接表
         * 其实这个步骤本身就是在构建连通分量
         *
         * @param intervals
         */
        private void buildGraph(List<Interval> intervals) {
            graph = new HashMap<>();
            for (Interval interval : intervals) {
                graph.put(interval, new LinkedList<>());
            }
            for (Interval interval1 : intervals) {
                for (Interval interval2 : intervals) {
                    if (overlap(interval1, interval2)) {
                        // 如果重合,加入到邻接表中 ,使 interval1 和interval2重合
                        graph.get(interval1).add(interval2);
                        graph.get(interval2).add(interval1);
                    }
                }
            }
        }

        /**
         * 构造连通分量,为每个连通分量标记编号,如果是连通分量.
         *
         * @param intervals
         */
        private void buildComponents(List<Interval> intervals) {
            nodesInComp = new HashMap<>();
            visited = new HashSet<>();
            int compNumber = 0;
            for (Interval interval : intervals) {
                if (!visited.contains(interval)) {
                    dfs(interval, compNumber);
                    ++compNumber;
                }
            }
        }

        /**
         * 非递归版的dfs
         * @param start
         * @param compNumber
         */
        private void dfs(Interval start, int compNumber) {
            // 函数调用栈
            Stack<Interval> stack = new Stack<>();
            stack.push(start);
            while (!stack.isEmpty()) {
                Interval node = stack.pop();
                // 未访问
                if (!visited.contains(node)) {
                    // 访问 标记
                    visited.add(node);
                    // 连通分量结点
                    if (nodesInComp.get(compNumber) == null) {
                        nodesInComp.put(compNumber, new LinkedList<>());
                    }
                    nodesInComp.get(compNumber).add(node);
                    // 相当一dfs()
                    for (Interval child : graph.get(node)) {
                        stack.push(child);
                    }
                }
            }
        }

        /**
         * merge同一个连通分量中的所有顶点
         *
         * @param nodes
         * @return
         */
        private Interval mergeNodes(List<Interval> nodes) {
            int minStart = nodes.get(0).start;
            for (Interval node : nodes) {
                minStart = Math.min(minStart, node.start);
            }
            int maxEnd = nodes.get(0).end;
            for (Interval node : nodes) {
                maxEnd = Math.max(maxEnd, node.end);
            }
            return new Interval(minStart, maxEnd);
        }

        public List<Interval> merge(List<Interval> intervals) {
            // 构建无向图,将所有overlap的部分连接到一起
            buildGraph(intervals);
            // 遍历图,并将所有连通分量集中到一个顶点上,最后将所有的连通分量结点压缩归并成一个Interval
            buildComponents(intervals);
            // 输出结果
            List<Interval> merged = new LinkedList<>();
            for (int comp = 0; comp < nodesInComp.size(); comp++) {
                merged.add(mergeNodes(nodesInComp.get(comp)));
            }

            return merged;
        }
    }

    public static void main(String[] args) {
        List<Interval> intervals1 = Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18));
        List<Interval> intervals2 = Arrays.asList(new Interval(1, 4), new Interval(4, 5));
        List<Interval> intervals3 = Arrays.asList(new Interval(2, 3), new Interval(4, 5), new Interval(6, 7), new Interval(8, 9), new Interval(1, 10));

        Solution s = new Solution2();
        println(s.merge(intervals1));//[1,6],[8,10],[15,18]
        println(s.merge(intervals2));//[1,5]
        println(s.merge(intervals3));//[1,10]
    }
}
