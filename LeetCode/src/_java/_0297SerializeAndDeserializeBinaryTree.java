package _java;

import java.util.Arrays;
import java.util.LinkedList;

import base.Base;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Example:
 * <pre>
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * </pre>
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class _0297SerializeAndDeserializeBinaryTree extends Base {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private abstract static class Codec {

        // Encodes a tree to a single string.
        abstract String serialize(TreeNode root);

        // Decodes your encoded data to tree.
        abstract TreeNode deserialize(String data);
    }

    /**
     * Runtime: 8 ms, faster than 94.69% of Java online submissions for Serialize and Deserialize Binary Tree.
     * Memory Usage: 39.4 MB, less than 65.77% of Java online submissions for Serialize and Deserialize Binary Tree.
     * 前序顺序输出
     */
    private static class Codec1 extends Codec {

        String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            rSerialize(root, sb);
            return sb.toString();
        }

        void rSerialize(TreeNode t, StringBuilder sb) {
            sb.append(t == null ? "null" : t.val);
            sb.append(",");
            if (t != null) {
                rSerialize(t.left, sb);
                rSerialize(t.right, sb);
            }
        }

        TreeNode deserialize(String data) {
            String[] dataNodes = data.split(",");
            if (dataNodes.length < 1) {
                return null;
            }
            int[] pos = new int[1];
            return rDeserialize(null, dataNodes, pos);
        }

        TreeNode rDeserialize(TreeNode t, String[] nodes, int[] pos) {
            String val = nodes[pos[0]++];
            if (!val.equals("null")) {
                t = new TreeNode(Integer.valueOf(val));
                t.left = rDeserialize(t.left, nodes, pos);
                t.right = rDeserialize(t.right, nodes, pos);
            }
            return t;
        }

    }

    /**
     * Runtime: 11 ms, faster than 68.97% of Java online submissions for Serialize and Deserialize Binary Tree.
     * Memory Usage: 40 MB, less than 38.24% of Java online submissions for Serialize and Deserialize Binary Tree.
     * 这个更容易理解一些.
     */
    private static class Codec2 extends Codec {

        @Override
        String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeAux(root, sb);
            return sb.toString();
        }

        void serializeAux(TreeNode x, StringBuilder sb) {
            sb.append(x != null ? x.val : "null");
            sb.append(",");
            if (x != null) {
                serializeAux(x.left, sb);
                serializeAux(x.right, sb);
            }
        }

        @Override
        TreeNode deserialize(String data) {
            if (data.length() < 1) {
                return null;
            }
            LinkedList<String> dataL = new LinkedList<>(Arrays.asList(data.split(",")));
            return deserialize(dataL);
        }

        TreeNode deserialize(LinkedList<String> dataL) {
            String v = dataL.removeFirst();
            if (v.equals("null")) {
                return null;
            } else {
                TreeNode x = new TreeNode(Integer.valueOf(v));
                x.left = deserialize(dataL);
                x.right = deserialize(dataL);
                return x;
            }
        }
    }

    public static void main(String[] args) {
        Codec c = new Codec2();
        int[] nums = new int[1];
        test1(c);
    }

    private static void test1(Codec c) {
        TreeNode t = new TreeNode(1);
        TreeNode pt = t;
        t.left = new TreeNode(2);
        t.right = new TreeNode(3);
        pt = t.right;
        pt.left = new TreeNode(4);
        pt.right = new TreeNode(5);

        println(t);
        String serial = c.serialize(t);
        println(serial);
        TreeNode newT = c.deserialize(serial);
        println(newT);
        println("correct?  " + t.toString().equals(newT.toString()));
    }
}

