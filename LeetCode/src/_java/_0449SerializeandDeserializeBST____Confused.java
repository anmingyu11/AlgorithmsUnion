package _java;

import java.util.LinkedList;

import base.BaseTree;
import base.util.TreeUtil;

/**
 * Serialization is the process of converting a data structure or object
 * into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later
 * in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary search tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary search tree
 * can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * The encoded string should be as compact as possible.
 * <p>
 * Note: Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 */
public class _0449SerializeandDeserializeBST____Confused extends BaseTree {

    private abstract static class Solution {
        // Encodes a tree to a single string.
        public abstract String serialize(TreeNode root);

        // Decodes your encoded data to tree.
        public abstract TreeNode deserialize(String data);
    }

    /**
     * Runtime: 5 ms, faster than 92.12% of Java online submissions for Serialize and Deserialize BST.
     * Memory Usage: 38.3 MB, less than 99.91% of Java online submissions for Serialize and Deserialize BST.
     */
    private static class Solution1 extends Solution {

        @Override
        public String serialize(TreeNode root) {
            if (root == null) {
                return " ";
            }
            StringBuilder sb = new StringBuilder();
            serializeAux(root, sb);
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        private void serializeAux(TreeNode t, StringBuilder sb) {
            if (t == null) {
                return;
            }
            serializeAux(t.left, sb);
            serializeAux(t.right, sb);
            sb.append(t.val);
            sb.append(" ");
        }

        @Override
        public TreeNode deserialize(String data) {
            LinkedList<Integer> serial = new LinkedList<>();
            for (String str : data.split(" ")) {
                serial.add(Integer.valueOf(str));
            }
            return deserializeAux(Integer.MIN_VALUE, Integer.MAX_VALUE, serial);
        }

        private TreeNode deserializeAux(int lo, int hi, LinkedList<Integer> serial) {
            if (serial.isEmpty()) {
                return null;
            }
            int last = serial.getLast();
            if (last < lo || last > hi) {
                return null;
            }
            serial.removeLast();
            TreeNode t = new TreeNode(last);
            t.right = deserializeAux(last, hi, serial);
            t.left = deserializeAux(lo, last, serial);
            return t;
        }

    }

    private static void test1(Solution c) {
        TreeNode t = TreeUtil.generateACompleteBT(new Integer[]{3, 1, 4, null, 2});

        //println(t);
        String serial = c.serialize(t);
        println(serial);
        //TreeNode newT = c.deserialize(serial);
        //println(newT);
        //println("correct?  " + t.toString().equals(newT.toString()));
    }

    public static void main(String[] args) {

        Solution s = new Solution1();

        test1(s);
    }
}
