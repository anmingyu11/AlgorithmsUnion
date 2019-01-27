package _3Searching;

/******************************************************************************
 *  Compilation:  javac LookupIndex.java
 *  Execution:    java LookupIndex movies.txt "/"
 *  Dependencies: ST.java Queue.java In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/35applications/aminoI.csv
 *                https://algs4.cs.princeton.edu/35applications/movies.txt
 *
 *  % java LookupIndex aminoI.csv ","
 *  Serine
 *    TCT
 *    TCA
 *    TCG
 *    AGT
 *    AGC
 *  TCG
 *    Serine
 *
 *  % java LookupIndex movies.txt "/"
 *  Bacon, Kevin
 *    Animal House (1978)
 *    Apollo 13 (1995)
 *    Beauty Shop (2005)
 *    Diner (1982)
 *    Few Good Men, A (1992)
 *    Flatliners (1990)
 *    Footloose (1984)
 *    Friday the 13th (1980)
 *    ...
 *  Tin Men (1987)
 *    DeBoy, David
 *    Blumenfeld, Alan
 *    ...
 *
 ******************************************************************************/

import _1Fundamentals.queue.Queue;
import base.stdlib.In;
import base.stdlib.StdIn;
import base.stdlib.StdOut;

/**
 * The {@code LookupIndex} class provides a data-driven client for reading in a
 * key-value pairs from a file; then, printing the values corresponding to the
 * keys found on standard input. Keys are strings; values are lists of strings.
 * The separating delimiter is taken as a command-line argument. This client
 * is sometimes known as an <em>inverted index</em>.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class LookupIndex {

    // Do not instantiate.
    private LookupIndex() {
    }

    public static void main(String[] args) {
        In in = new In("/home/amy/github/AlgEssentialsSRC/AlgEdition4/resources/csv/ip.csv");

        ST<String, Queue<String>> st = new ST<>();
        ST<String, Queue<String>> ts = new ST<>();

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(",");
            String key = fields[0];
            for (int i = 1; i < fields.length; i++) {
                String val = fields[i];
                if (!st.contains(key)) {
                    st.put(key, new Queue<>());
                }
                if (!ts.contains(val)) {
                    ts.put(val, new Queue<>());
                }
                st.get(key).enqueue(val);
                ts.get(val).enqueue(key);
            }
        }

        StdOut.println("Done indexing");

        // read queries from standard input, one per line
        while (!StdIn.isEmpty()) {
            String query = StdIn.readLine();
            if (st.contains(query)) {
                for (String vals : st.get(query)) {
                    StdOut.println("  " + vals);
                }
            }
            if (ts.contains(query)) {
                for (String keys : ts.get(query)) {
                    StdOut.println("  " + keys);
                }
            }
        }
    }
}