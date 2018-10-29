package tree;

import java.util.ArrayList;
import java.util.List;

import base.BaseTree;

public class BinaryTreePreorderTraversal extends BaseTree {

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        realTraversal(root, result);
        return result;
    }

    private static void realTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        realTraversal(root.left, result);
        realTraversal(root.right, result);
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.right = new TreeNode(2);
        t.right.left = new TreeNode(3);
        println(preorderTraversal(t));
    }
}
