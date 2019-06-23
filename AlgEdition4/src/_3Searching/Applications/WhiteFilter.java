package _3Searching.Applications;
/******************************************************************************
 *  Compilation:  javac WhiteFilter.java
 *  Execution:    java WhiteFilter whitelist.txt < input.txt
 *  Dependencies: SET In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/35applications/tinyTale.txt
 *                https://algs4.cs.princeton.edu/35applications/list.txt
 *
 *  Read in a whitelist of words from a file. Then read in a list of
 *  words from standard input and print out all those words that
 *  are in the first file.
 *
 *  % more tinyTale.txt
 *  it was the best of times it was the worst of times
 *  it was the age of wisdom it was the age of foolishness
 *  it was the epoch of belief it was the epoch of incredulity
 *  it was the season of light it was the season of darkness
 *  it was the spring of hope it was the winter of despair
 *
 *  % more list.txt
 *  was it the of
 *
 *  % java WhiteFilter list.txt < tinyTale.txt
 *  it was the of it was the of
 *  it was the of it was the of
 *  it was the of it was the of
 *  it was the of it was the of
 *  it was the of it was the of
 *
 ******************************************************************************/

import _3Searching.SearchTestResources;
import base.stdlib.In;
import base.stdlib.StdOut;

/**
 * The {@code WhiteFilter} class provides a client for reading in a <em>whitelist</em>
 * of words from a file; then, reading in a sequence of words from standard input,
 * printing out each word that appears in the file.
 * It is useful as a test client for various symbol table implementations.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * WhiteFilter类提供了一个客户端，用于读取文件中的单词白名单;
 * 然后，从标准输入中读取一系列单词，打印出文件中出现的每个单词。
 * 它可用作各种符号表实现的测试客户端。
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class WhiteFilter {

    // Do not instantiate.
    private WhiteFilter() {
    }

    public static void main(String[] args) {
        SET<String> set = new SET<>();

        // read in strings and add to set
        In in = new In(SearchTestResources.Local.tale);
        while (!in.isEmpty()) {
            String word = in.readString();
            set.add(word);
        }
        // read in string from standard input, printing out all exceptions
        String[] testString = {"do", "i", "fuck", "beauty", "mother"};
        for (String word : testString) {
            if (set.contains(word))
                StdOut.println(word);
        }
    }
}