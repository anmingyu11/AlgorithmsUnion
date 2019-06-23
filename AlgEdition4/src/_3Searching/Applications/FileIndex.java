package _3Searching.Applications;

/******************************************************************************
 *  Compilation:  javac FileIndex.java
 *  Execution:    java FileIndex file1.txt file2.txt file3.txt ...
 *  Dependencies: ST.java SET.java In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/35applications/ex1.txt
 *                https://algs4.cs.princeton.edu/35applications/ex2.txt
 *                https://algs4.cs.princeton.edu/35applications/ex3.txt
 *                https://algs4.cs.princeton.edu/35applications/ex4.txt
 *
 *  % java FileIndex ex*.txt
 *  age
 *   ex3.txt
 *   ex4.txt
 * best
 *   ex1.txt
 * was
 *   ex1.txt
 *   ex2.txt
 *   ex3.txt
 *   ex4.txt
 *
 *  % java FileIndex *.txt
 *
 *  % java FileIndex *.java
 *
 ******************************************************************************/

import java.io.File;

import _3Searching.SearchTestResources;
import base.stdlib.In;
import base.stdlib.StdOut;

/**
 * The {@code FileIndex} class provides a client for indexing a set of files,
 * specified as command-line arguments. It takes queries from standard input
 * and prints each file that contains the given query.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * FileIndex类提供了一个客户端，用于索引一组文件，指定为命令行参数。
 * 它从标准输入中获取查询并打印包含给定查询的每个文件。
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class FileIndex {

    // Do not instantiate.
    private FileIndex() {
    }

    public static void main(String[] args) {
        // key = word, value = set of files containing that word
        ST<String, SET<File>> st = new ST<>();
        // create inverted index of all files
        StdOut.println("Indexing files");
        for (String filename : SearchTestResources.Local.fileIndex) {
            StdOut.println("  " + filename);
            File file = new File(filename);
            In in = new In(file);
            while (!in.isEmpty()) {
                String word = in.readString();
                if (!st.contains(word)) {
                    st.put(word, new SET<>());
                }
                SET<File> set = st.get(word);
                set.add(file);
            }
        }
        // read queries from standard input, one per line
        String[] queries = {"it", "was", "times", "wisdom"};
        for (String query : queries) {
            if (st.contains(query)) {
                SET<File> set = st.get(query);
                for (File file : set) {
                    StdOut.print(file.getName() + " - ");
                }
                StdOut.println("");
            }
        }
    }
}