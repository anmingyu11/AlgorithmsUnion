package _java;

import java.util.Arrays;
import java.util.Comparator;

import base.Base;

public class _0253MeetingRooms2 extends Base {

    private static class Interval {
        private int start;
        private int end;

        private Interval() {
            start = 0;
            end = 0;
        }

        private Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    private abstract static class Solution {

        public abstract int minMeetingRooms(Interval[] intervals);

    }

    // Runtime: 6 ms, faster than 78.66% of Java online submissions for Meeting Rooms II.
    // Memory Usage: 39 MB, less than 28.74% of Java online submissions for Meeting Rooms II.
    // 优先队列
    private static class Solution1 extends Solution {

        private class MinPQ {
            private int[] pq;
            private int n = 0;

            public MinPQ(int size) {
                pq = new int[size];
                n = 0;
            }

            private void insert(int e) {
                pq[n++] = e;
                swim(n - 1);
            }

            private int deleteMin() {
                int min = pq[0];
                pq[0] = pq[--n];
                sink(0);
                return min;
            }

            private int min() {
                return pq[0];
            }

            private void sink(int k) {
                while (2 * k + 1 < n) {
                    int left = 2 * k + 1;
                    if (left + 1 < n) {
                        left = pq[left] < pq[left + 1] ? left : ++left;
                    }
                    if (pq[k] < pq[left]) {
                        break;
                    }
                    swap(left, k);
                    k = left;
                }
            }

            private void swim(int k) {
                while ((k - 1) / 2 >= 0) {
                    int p = (k - 1) / 2;
                    if (pq[p] > pq[k]) {
                        swap(p, k);
                        k = p;
                    } else {
                        break;
                    }
                }
            }

            private void swap(int i, int j) {
                int t = pq[i];
                pq[i] = pq[j];
                pq[j] = t;
            }

        }


        @Override
        public int minMeetingRooms(Interval[] intervals) {
            final int n = intervals.length;
            if (n == 0) {
                return 0;
            }

            Arrays.sort(intervals, new Comparator<Interval>() {
                @Override
                public int compare(Interval a, Interval b) {
                    return a.start - b.start;
                }
            });


            MinPQ pq = new MinPQ(n);
            pq.insert(intervals[0].end);
            for (int i = 1; i < n; ++i) {
                Interval interval = intervals[i];
                int min = pq.min();
                if (interval.start >= min) {
                    pq.deleteMin();
                }
                pq.insert(interval.end);
            }

            return pq.n;
        }

//        private void testSort(int[] A) {
//            final int n = A.length;
//            MinPQ pq = new MinPQ(n);
//            for (int e : A) {
//                pq.insert(e);
//            }
//            for (int i = 0; i < n; ++i) {
//                A[i] = pq.deleteMin();
//            }
//        }
    }

    // Runtime: 2 ms, faster than 100.00% of Java online submissions for Meeting Rooms II.
    // Memory Usage: 37.6 MB, less than 76.80% of Java online submissions for Meeting Rooms II.
    private static class Solution2 extends Solution {

        @Override
        public int minMeetingRooms(Interval[] intervals) {
            final int n = intervals.length;
            if (n == 0) {
                return 0;
            }
            int[] start = new int[n];
            int[] end = new int[n];
            for (int i = 0; i < n; ++i) {
                start[i] = intervals[i].start;
                end[i] = intervals[i].end;
            }

            Arrays.sort(start);
            Arrays.sort(end);

            int sPointer = 0, ePointer = 0;
            int rooms = 0;
            while (sPointer < n) {
                if (start[sPointer] >= end[ePointer]) {
                    --rooms;
                    ++ePointer;
                }

                ++sPointer;
                ++rooms;
            }
            return rooms;
        }

    }

    public static void main(String[] args) {
        Interval[] intervals1 = {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
        Interval[] intervals2 = {new Interval(7, 10), new Interval(2, 4)};

        Solution s = new Solution2();

        println(s.minMeetingRooms(intervals1)); // 2
        println(s.minMeetingRooms(intervals2)); // 1
    }

}