package bit;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import base.Base;

public class RepeatedDNASequence extends Base {

    static class Solution1 {

        public List<String> findRepeatedDnaSequences(String s) {
            Set<String> exist = new HashSet<>(), repeated = new HashSet<>();

            for (int i = 0; i <= s.length() - 10; ++i) {
                String sub = s.substring(i, i + 10);
                if (!exist.add(sub)) {
                    repeated.add(sub);
                }
            }

            return new LinkedList<>(repeated);
        }

    }

    static class Solution2 {

        //高位丢弃 低位补0
        public List<String> findRepeatedDnaSequences(String s) {
            if (s.length() < 10) {
                return new LinkedList<>();
            }
            Set<Integer> set = new HashSet<>();
            Set<String> res = new HashSet<>();

            int t = 0, i = 0, strLen = s.length();
            while (i < 9) {
                t = t << 3 | s.charAt(i++) & 7;
            }

            int mask = 0x07ffffff; // 取后27位
            while (i < strLen) {
                // get 10 char bit
                t = ((t & mask) << 3) | (s.charAt(i++) & 7);
                // 如果在这里 & mask 则 set会添加这个t的头部
                if (!set.add(t)) {
                    res.add(s.substring(i - 10, i));
                }
            }

            return new LinkedList<>(res);
        }
    }

    public static void main(String[] args) {
        String s1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        String s2 = "GCCTCAAGCTATCCTAATTTGCCTGTTCTACTC" +
                "TGAGTCTCACAAGCTCCCTGGGGGGCCGAACGGACTC" +
                "GCAGCTTCACGATTAATGAATGTTTCGACAATGAACT" +
                "TCCTGTGACGAATCTTTGCCGAGCACGGTCTAGCACT" +
                "ATGAGGATTCTCTTCCCGTGTACTCAACGCGGCACAT" +
                "GTTGGAGGTCACCTCGCCGAGCTACCTGTACCCGGGT" +
                "CTGTAATTCGGATAATTCAGCTAGGGAGCAAATGTGC" +
                "AGTCAGAGCTTAAGGTACTTCATGTCGCCTTCGCCTG" +
                "AAGTCCCTTCTTGCACATTATATCCGTTTTGAGGATT" +
                "CTACTGATAGATAGGGCGCAAACCTCGTTGACGCCCA" +
                "CGACCAAGGATGGTTACTTTTTACAATATGGAATGCA" +
                "CGAGACCGATTCCGGCCCAGAGGAAAGATTCAAGTCT" +
                "AAGTAAGCACGGCATGAGGCGCTACGCACCCTTGCCC" +
                "ATGACCCCGCAACGGGAACTATGGCCCCGCGGCATGC" +
                "GTTATACATTATTAACCCACCGCAGCACCCCCGGACT" +
                "ATTCACGCCAAGTGAGGGATTTATCGATTGGACCCTA" +
                "GGGGGACTGGCGAGCCGTCTTCCTCGGGAGCGGGGTG" +
                "GAGTGTTGAACTCGACTCACTATGATAACCGTGTCCA" +
                "CCATCAATGGAAGTGAACCCGCGAGCATCATGCTTTA" +
                "TCCAAATTCGACCACTATCGTTTGTATATGATGACCT" +
                "TGTATCACTGGCTGGCAGTGGTAACGCTTTAAGCCGT" +
                "TGTAATATAGAGTCCGCGATATTCACTGACCCTGTTT" +
                "CCTCAAACCCTTCTCTCGTAAAATAGTGGTGCCCACT" +
                "CCTTCGGAGTTGGAGAGGTTGATCGTGTCAGAATGAC" +
                "GTCACGGTCACGCAACACTTCTATCTTGGCGAGCACC" +
                "GCATCTCATGTACCCTTCGTATAGTTAGAGGGTAAGA" +
                "TGTGTCAGCCTCCAAACGAAGTGAACTGTAAAGTGTT" +
                "CGCCT";
        println(new Solution2().findRepeatedDnaSequences(s1));
        println(new Solution2().findRepeatedDnaSequences(s2));
    }
}
