package _java;

import base.BaseTree;
import base.util.TreeUtil;

public class _1261FindElementsinaContaminatedBinaryTree extends BaseTree {

    static class FindElements {
        TreeNode root;

        public FindElements(TreeNode root) {
            this.root = root;
            if (root != null) {
                recover(root, 0);
            }
        }

        private void recover(TreeNode x, int cur) {
            if (x.val == -1) {
                x.val = cur;
            }
            if (x.left != null) {
                recover(x.left, cur * 2 + 1);
            }
            if (x.right != null) {
                recover(x.right, cur * 2 + 2);
            }
        }

        public boolean find(int target) {
            return findAux(root, target);
        }

        private boolean findAux(TreeNode x, int target) {
            if (x == null) {
                return false;
            } else {
                if (x.val == target) {
                    return true;
                } else {
                    return findAux(x.left, target) || findAux(x.right, target);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] t = new Integer[12];
        t[0]=0;
        t[2]=2;
        t[5]=5;
        t[11]=11;
        TreeNode tree = TreeUtil.generateACompleteBT(t);
        FindElements f= new FindElements(tree);
        TreeUtil.printTreeInLevel(tree);
        f.find(5);
    }
}
