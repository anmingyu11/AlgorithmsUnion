package _3Searching.Applications;

/******************************************************************************
 *  Compilation:  javac LookupCSV.java
 *  Execution:    java LookupCSV file.csv keyField valField
 *  Dependencies: ST.java In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/35applications/DJIA.csv
 *                https://algs4.cs.princeton.edu/35applications/UPC.csv
 *                https://algs4.cs.princeton.edu/35applications/amino.csv
 *                https://algs4.cs.princeton.edu/35applications/elements.csv
 *                https://algs4.cs.princeton.edu/35applications/ip.csv
 *                https://algs4.cs.princeton.edu/35applications/morse.csv
 *
 *  Reads in a set of key-value pairs from a two-column CSV file
 *  specified on the command line; then, reads in keys from standard
 *  input and prints out corresponding values.
 *
 *  % java LookupCSV amino.csv 0 3     % java LookupCSV ip.csv 0 1
 *  TTA                                www.google.com
 *  Leucine                            216.239.41.99
 *  ABC
 *  Not found                          % java LookupCSV ip.csv 1 0
 *  TCT                                216.239.41.99
 *  Serine                             www.google.com
 *
 *  % java LookupCSV amino.csv 3 0     % java LookupCSV DJIA.csv 0 1
 *  Glycine                            29-Oct-29
 *  GGG                                252.38
 *                                     20-Oct-87
 *                                     1738.74
 *
 *
 ******************************************************************************/

import _3Searching.SearchTestResources;
import base.stdlib.In;
import base.stdlib.StdOut;

/**
 * The {@code LookupCSV} class provides a data-driven client for reading in a
 * key-value pairs from a file; then, printing the values corresponding to the
 * keys found on standard input. Both keys and values are strings.
 * The fields to serve as the key and value are taken as command-line arguments.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * LookupCSV类提供了一个数据驱动的客户端，用于从文件中读取键值对;
 * 然后，打印与标准输入上找到的键对应的值。 键和值都是字符串。 用作键和值的字段被视为命令行参数。
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class LookupCSV {

    // Do not instantiate.
    private LookupCSV() {
    }

    public static void main(String[] args) {
        ST<String, String> st = new ST<>();
        // read in the data from csv file
        In in = new In(SearchTestResources.Local.ip);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[0];
            String val = tokens[1];
            st.put(key, val);
        }
        String[] testAddr = {
                "msn.com",
                "baidu.com",
                "qq.com",
                "amazon.com",
                "taobao.com"
        };
        for (String addr : testAddr) {
            if (st.contains(addr)) {
                StdOut.println("addr : " + addr + " -- " + st.get(addr));
            } else {
                StdOut.println("Not found");
            }
        }
    }
}