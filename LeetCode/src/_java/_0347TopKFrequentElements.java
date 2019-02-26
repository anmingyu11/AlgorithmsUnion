package _java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import base.Base;
import base.TestCases;

public class _0347TopKFrequentElements extends Base {
    private abstract static class Solution {
        public abstract List<Integer> topKFrequent(int[] nums, int k);
    }

    // 出现次数
    // Runtime: 10 ms, faster than 99.25% of Java online submissions for Top K Frequent Elements.
    // Memory Usage: 42 MB, less than 35.44% of Java online submissions for Top K Frequent Elements.
    private static class Solution1 extends Solution {
        private class CounterMap extends HashMap<Integer, Integer> {
            void put(Integer num) {
                Integer time = get(num);
                if (time == null) {
                    put(num, 1);
                } else {
                    put(num, ++time);
                }
            }
        }

        private class Heap {
            private int[] pq; // 排序主元
            private int[] qp; // 附属部分
            private int n; // 有多少个元素

            public Heap(int cap) {
                pq = new int[cap];
                qp = new int[cap];
            }

            private void insert(int i, int v) {
                pq[n] = v;
                qp[n] = i;
                swim(n++);
            }

            private int deleteMax() {
                int v = qp[0];
                swap(0, n - 1);
                --n;
                sink(0);
                return v;
            }

            private void sink(int k) {
                while (2 * k + 1 < n) {
                    int child = 2 * k + 1;
                    if (child + 1 < n && greater(child + 1, child)) {
                        ++child;
                    }
                    if (greater(k, child)) {
                        break;
                    } else {
                        swap(k, child);
                        k = child;
                    }
                }
            }

            private void swim(int k) {
                while (k > 0) {
                    int parent = (k - 1) / 2;
                    if (greater(k, parent)) {
                        swap(parent, k);
                        k = parent;
                    } else {
                        break;
                    }
                }
            }

            private boolean greater(int i, int j) {
                return pq[i] > pq[j];
            }

            private void swap(int i, int j) {
                int t1 = pq[i];
                int t2 = qp[i];
                pq[i] = pq[j];
                qp[i] = qp[j];
                pq[j] = t1;
                qp[j] = t2;
            }

        }

        @Override
        public List<Integer> topKFrequent(int[] nums, int k) {
            List<Integer> res = new LinkedList<>();

            // init map
            CounterMap map = new CounterMap();
            for (int n : nums) {
                map.put(n);
            }
            Heap heap = new Heap(map.size());
            for (int key : map.keySet()) {
                heap.insert(key, map.get(key));
            }
            for (int i = 0; i < k; ++i) {
                res.add(heap.deleteMax());
            }
            return res;
        }

        private void heapSort(int[] a) {
            final int n = a.length;
            Heap pq = new Heap(n);
            for (int i = 0; i < n; ++i) {
                pq.insert(i, a[i]);
            }
            for (int i = 0; i < n; ++i) {
                print(a[pq.deleteMax()] + " , ");
            }
            println("");
        }
    }

    public static void main(String[] args) {
        int[] a1 = {1, 1, 1, 2, 2, 3};
        int[] a2 = {1};
        int[] a3 = {3, 0, 1, 0};

        Solution s = new Solution1();

        println(s.topKFrequent(a1, 2));
        println(s.topKFrequent(a2, 1));
        println(s.topKFrequent(a3, 1));
    }

    private static void testSort() {
        Solution1 s = new Solution1();

        List<int[]> lists = TestCases.Sort.getTestcases();
        for (int[] a : lists) {
            s.heapSort(a);
            println("correct : " + TestCases.Sort.checkSort(true, a));
        }
    }
}
