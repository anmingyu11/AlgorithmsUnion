package _1Fundamentals.uf;

import base.stdlib.In;

public class UFUtils {
    static final String TINY_UF = "https://algs4.cs.princeton.edu/15uf/tinyUF.txt";
    static final String MEDIUM_UF = "https://algs4.cs.princeton.edu/15uf/mediumUF.txt";
    static final String LARGE_UF = "https://algs4.cs.princeton.edu/15uf/largeUF.txt";

    static class UFDataHolder {
        int N = 0;
        int[] ints = null;

        public UFDataHolder(int n, int[] ints) {
            N = n;
            this.ints = ints;
        }
    }

    static UFDataHolder getUFList(int type) {
        switch (type) {
            case 0: {
                In in = new In(UFUtils.TINY_UF);
                int N = in.readInt();
                int[] ints = in.readAllInts();
                return new UFDataHolder(N, ints);
            }
            case 1: {
                In in = new In(UFUtils.MEDIUM_UF);
                int N = in.readInt();
                int[] ints = in.readAllInts();
                return new UFDataHolder(N, ints);
            }
            case 2: {
                In in = new In(UFUtils.LARGE_UF);
                int N = in.readInt();
                int[] ints = in.readAllInts();
                return new UFDataHolder(N, ints);
            }
            default: {
                throw new RuntimeException("Are u idoit or something?");
            }
        }
    }
}
