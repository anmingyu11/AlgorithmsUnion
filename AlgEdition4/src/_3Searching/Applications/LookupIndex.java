package _3Searching.Applications;

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

import _1Fundamentals.Queue.Queue;
import _3Searching.SearchTestResources;
import base.stdlib.In;
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
 * <p>
 * LookupIndex类提供了一个数据驱动的客户端，用于从文件中读取键值对;
 * 然后，打印与标准输入上找到的键对应的值。
 * 键是字符串; 值是字符串列表。 分隔定界符被视为命令行参数。 此客户端有时称为倒排索引。
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class LookupIndex {

    // Do not instantiate.
    private LookupIndex() {
    }

    public static void main(String[] args) {
        In in = new In(SearchTestResources.Local.amino);
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
        String[] queries = {
                "Serine",
                "Leucine",
                "Arginine",
                "CTA",
                "CGG",
                "ATT",
        };
        for (String query : queries) {
            if (st.contains(query)) {
                StdOut.println("Contains : " + query);
                for (String vals : st.get(query)) {
                    StdOut.print("  " + vals);
                }
            }
            StdOut.println("");
            if (ts.contains(query)) {
                StdOut.println("Contains : " + query);
                for (String keys : ts.get(query)) {
                    StdOut.print("  " + keys);
                }
            }
            StdOut.println("");
        }
    }
}