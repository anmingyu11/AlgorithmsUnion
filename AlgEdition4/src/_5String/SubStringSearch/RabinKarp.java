package _5String.SubStringSearch;
/******************************************************************************
 *  Compilation:  javac RabinKarp.java
 *  Execution:    java RabinKarp pat txt
 *  Dependencies: StdOut.java
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using the
 *  Las Vegas version of the Rabin-Karp algorithm.
 *
 *  % java RabinKarp abracadabra abacadabrabracabracadabrabrabracad
 *  pattern: abracadabra
 *  text:    abacadabrabracabracadabrabrabracad 
 *  match:                 abracadabra          
 *
 *  % java RabinKarp rab abacadabrabracabracadabrabrabracad
 *  pattern: rab
 *  text:    abacadabrabracabracadabrabrabracad 
 *  match:           rab                         
 *
 *  % java RabinKarp bcara abacadabrabracabracadabrabrabracad
 *  pattern: bcara
 *  text:         abacadabrabracabracadabrabrabracad 
 *
 *  %  java RabinKarp rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java RabinKarp abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ******************************************************************************/

import java.math.BigInteger;
import java.util.Random;

import base.stdlib.StdOut;

/**
 * The {@code RabinKarp} class finds the first occurrence of a pattern string
 * in a text string.
 * <p>
 * This implementation uses the Rabin-Karp algorithm.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/53substring">Section 5.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class RabinKarp {
    private String pat;      // the pattern  // needed only for Las Vegas
    private long patHash;    // pattern hash value
    private int m;           // pattern length
    private long q;          // a large prime, small enough to avoid long overflow
    private int R;           // radix
    private long RM;         // R^(M-1) % Q

    /**
     * Preprocesses the pattern string.
     * <p>
     * 预处理模式字符串.
     *
     * @param pat the pattern string
     */
    public RabinKarp(String pat) {
        this.pat = pat; // save pattern (needed only for Las Vegas)
        R = 256;
        m = pat.length();
        q = longRandomPrime();
        RM = 1; // precompute R^(m-1) % q for use in removing leading digit 计算RM值用来删除高位数字.
        for (int i = 1; i < m; ++i) {
            RM = (R * RM) % q;
        }
        patHash = hash(pat, m); // 计算模式字符串的hash
    }

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     * <p>
     * 返回文本字符串中模式字符串第一次出现的索引。
     * 如果没有匹配,返回txt的长度
     *
     * @param txt the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; n if no such match
     */
    public int search(String txt) {
        int n = txt.length();
        if (n < m) {
            return -n;
        }
        long txtHash = hash(txt, m);
        if ((patHash == txtHash) && check(txt, 0)) { // check for match at offset 0
            return 0;
        }
        for (int i = m; i < n; ++i) { // check for hash match; if hash match, check for exact match
            txtHash = (txtHash + q - RM * txt.charAt(i - m) % q) % q; // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash * R + txt.charAt(i)) % q;
            int offset = i - m + 1; // match
            if ((patHash == txtHash) && check(txt, offset)) {
                return offset;
            }
        }
        return -n; // no match
    }

    // Compute hash for key[0..m-1]. 
    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; ++j) {
            h = (R * h + key.charAt(j)) % q;
        }
        return h;
    }

    // Las Vegas version: does pat[] match txt[i..i-m+1] ?
    //private boolean check(String txt, int i) {
    //    for (int j = 0; j < m; ++j) {
    //        if (pat.charAt(j) != txt.charAt(i + j)) {
    //            return false;
    //        }
    //    }
    //    return true;
    //}

    // Monte Carlo version: always return true 错误率10^(-20)
     private boolean check(String dump,int i) {
        return true;
    }

    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    /**
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        test("abracadabra", "abacadabrabracabracadabrabrabracad");// T
        test("rab", "abacadabrabracabracadabrabrabracad");// T
        test("bcara", "abacadabrabracabracadabrabrabracad");// F
        test("rabrabracad", "abacadabrabracabracadabrabrabracad");// T
        test("abacad", "abacadabrabracabracadabrabrabracad");// T
    }

    private static void test(String pattern, String txt) {
        RabinKarp rk = new RabinKarp(pattern);
        int searchRes = rk.search(txt);
        StdOut.println("-----------------------------------------------------");
        StdOut.println("searching : " + " pattern : " + pattern + " txt : " + txt);
        StdOut.println("searched : " + (searchRes >= 0));
        StdOut.println("index : " + searchRes);
    }

}