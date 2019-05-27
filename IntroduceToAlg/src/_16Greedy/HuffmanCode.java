package _16Greedy;

import java.util.LinkedList;
import java.util.PriorityQueue;

import base.Base;

public class HuffmanCode extends Base {

    public static class Node implements Comparable<Node> {
        public Node left, right;
        public int freq;
        public int character;

        public Node() {
        }

        public Node(int freq, int character) {
            this.freq = freq;
            this.character = character;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "freq=" + freq +
                    ", character=" + (char) character +
                    ", \nleft=" + left +
                    ", \nright=" + right +
                    '}';
        }

        @Override
        public int compareTo(Node other) {
            return freq - other.freq;
        }
    }

    private abstract static class Solution {
        private char[] chars = {'a', 'b', 'c', 'd', 'e', 'f'};
        private int[] freq = {45, 13, 12, 16, 9, 5};
        protected final int n = freq.length;
        protected final Node[] C = new Node[n];

        {
            for (int i = 0; i < n; ++i) {
                C[i] = new Node(freq[i], chars[i]);
            }
        }

        public abstract Node huffman();
    }

    private static class Solution1 extends Solution {

        @Override
        public Node huffman() {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for (int i = 0; i < n; ++i) {
                pq.add(C[i]);
            }
            //Todo n-1 次合并
            for (int i = 0; i < n - 1; ++i) {
                Node z = new Node();
                z.left = pq.poll();
                z.right = pq.poll();
                z.freq = (z.left != null ? z.left.freq : 0) + (z.right != null ? z.right.freq : 0);
                z.character = '*';
                pq.add(z);
            }
            return pq.peek();
        }

    }

    /**
     * Todo 按层打印树
     *
     * @param root
     */
    public static void bfsPrint(Node root) {
        LinkedList<Node> l1 = new LinkedList<>();
        LinkedList<Node> l2 = new LinkedList<>();
        l1.add(root);
        while (!l1.isEmpty()) {
            while (!l1.isEmpty()) {
                Node node = l1.poll();
                print(" [" + (char) node.character + " : " + node.freq + "] ");
                if (node.left != null) {
                    l2.add(node.left);
                }
                if (node.right != null) {
                    l2.add(node.right);
                }
            }
            println("");

            l1.addAll(l2);
            l2.clear();
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        Node huff = s.huffman();
        bfsPrint(huff);
    }

}
