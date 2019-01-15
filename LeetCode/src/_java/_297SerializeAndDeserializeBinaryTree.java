package _java;

import base.Base;

public class _297SerializeAndDeserializeBinaryTree extends Base {

    static class TreeNode {
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

    abstract static class Codec {

        // Encodes a tree to a single string.
        abstract String serialize(TreeNode root);

        // Decodes your encoded data to tree.
        abstract TreeNode deserialize(String data);
    }

    static class Codec1 extends Codec {

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

    public static void main(String[] args) {
        Codec c = new Codec1();
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

