package _java;

import base.Base;

public class _0295FindMedianfromDataStream____________ extends Base {

    private abstract static class MedianFinder {
        public MedianFinder() {
        }

        public abstract void addNum(int num);

        public abstract double findMedian();
    }

    private static class Solution1 extends MedianFinder {

        @Override
        public void addNum(int num) {

        }

        @Override
        public double findMedian() {
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
