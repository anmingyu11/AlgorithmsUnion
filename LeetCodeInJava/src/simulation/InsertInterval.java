package simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import base.Base;

public class InsertInterval extends Base {

    public static class Interval {
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
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        int i = 0;
        int n = intervals.size();

        while (i < n && newInterval.start > intervals.get(i).end) {
            result.add(intervals.get(i++));
        }

        while (i < n && newInterval.end >= intervals.get(i).start) {
            newInterval = new Interval(
                    Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(i).end)
            );
            ++i;
        }

        result.add(newInterval);

        while (i < n){
            result.add(intervals.get(i++));
        }

        return result;

    }

    public static void main(String[] args) {
        List<Interval> intervals1 = getIntervals1();
        Interval newInterval1 = new Interval(4, 5);
        insert(intervals1, newInterval1);
        println(intervals1);
        List<Interval> intervals2 = getIntervals2();
        Interval newInterval2 = new Interval(4, 8);
        insert(intervals2, newInterval2);
        println(intervals2);
    }

    private static List<Interval> getIntervals1() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 2));
        intervals.add(new Interval(10, 12));
        return intervals;
    }

    private static List<Interval> getIntervals2() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 2));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(12, 16));
        return intervals;
    }

}
