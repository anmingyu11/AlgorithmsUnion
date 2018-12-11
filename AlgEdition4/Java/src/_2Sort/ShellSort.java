package _2Sort;

import base.BaseSort;

public class ShellSort extends BaseSort {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            println("------");
            //println("h : " + h);
            for (int i = h; i < N; ++i) {
                for (int j = i; j >= h; j -= h) {
                    print("j : " + j + " j - h : " + (j - h) +"\t");
                    if (less(a[j], a[j - h])) {
                        exch(a, j, j - h);
                    } else {
                        break;
                    }
                }
                println("\ni : " + i);
            }
            h = h / 3;
        }

    }

    public static void main(String[] args) {
        ShellSort s = new ShellSort();
        s.testSorted(generateArrRandom(17, 0, 20));
    }

}
