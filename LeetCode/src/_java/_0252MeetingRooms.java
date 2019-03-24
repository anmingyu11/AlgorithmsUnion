package _java;

import java.util.Arrays;
import java.util.Comparator;

import base.Base;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 */
public class _0252MeetingRooms extends Base {

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

        @Override
        public String toString() {
            return "start : " + start + " end: " + end;
        }
    }

    private abstract static class Solution {
        public abstract boolean canAttendMeetings(Interval[] intervals);
    }

    // Runtime: 5 ms, faster than 89.07% of Java online submissions for Meeting Rooms.
    // Memory Usage: 39 MB, less than 18.33% of Java online submissions for Meeting Rooms.
    // The idea here is to sort the meetings by starting time.
    // Then, go through the meetings one by one and make sure that each meeting ends before the next one starts.
    private static class Solution1 extends Solution {

        @Override
        public boolean canAttendMeetings(Interval[] intervals) {
            Arrays.sort(intervals, new Comparator<Interval>() {
                @Override
                public int compare(Interval a, Interval b) {
                    if (a.start < b.start) {
                        return -1;
                    } else if (a.start > b.start) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            // 想一下,排序后,设两个interval:a,b. ai<bi
            // a.start < b.start
            // a.end >= a.start
            // a和b重合的话,那么一定无法安排会议
            for (int i = 1; i < intervals.length; ++i) {
                if (overlap(intervals[i - 1], intervals[i])) {
                    return false;
                }
            }
            return true;
        }

        private boolean overlap(Interval a, Interval b) {
            return !(a.end <= b.start || a.start >= b.end);
        }
    }

    public static void main(String[] args) {
        Interval[] intervals1 = {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
        Interval[] intervals2 = {new Interval(7, 10), new Interval(2, 4)};

        Solution s = new Solution1();

        println(s.canAttendMeetings(intervals1)); // false
        println(s.canAttendMeetings(intervals2)); // true
    }

}
