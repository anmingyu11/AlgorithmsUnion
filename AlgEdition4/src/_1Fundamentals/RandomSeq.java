package _1Fundamentals;

/******************************************************************************
 *  Compilation:  javac RandomSeq.java
 *  Execution:    java RandomSeq n lo hi
 *  Dependencies: StdOut.java
 *
 *  Prints N numbers between lo and hi.
 *
 *  % java RandomSeq 5 100.0 200.0
 *  123.43
 *  153.13
 *  144.38
 *  155.18
 *  104.02
 *
 ******************************************************************************/

import base.stdlib.StdOut;
import base.stdlib.StdRandom;

/**
 * The {@code RandomSeq} class is a client that prints out a pseudorandom
 * sequence of real numbers in a given range.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/11model">Section 1.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class RandomSeq {

    // this class should not be instantiated
    private RandomSeq() {
    }

    /**
     * Reads in two command-line arguments lo and hi and prints n uniformly
     * random real numbers in [lo, hi) to standard output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        //generateNRam(10);
        generateNBetween(0, 10, 20);
    }

    private static void generateNRam(int n) {
        // generate and print n numbers between 0.0 and 1.0
        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniform();
            StdOut.println(x);
        }
    }

    private static void generateNBetween(double lo, double hi, int n) {

        // generate and print n numbers between lo and hi
        for (int i = 0; i < n; i++) {
            double x = StdRandom.uniform(lo, hi);
            StdOut.printf("%.2f\n", x);
        }

    }

}