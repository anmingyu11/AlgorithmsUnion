package base.util;

import java.util.LinkedList;

import base.Base;
import base.BaseTree;

public class TreeUtil {

    /**
     * 将一个数组转化成为完全二叉树.
     *
     * @param A
     * @return
     */
    public static BaseTree.TreeNode generateACompleteBT(int[] A) {
        final int n = A.length;
        if (n == 0) {
            return null;
        }
        BaseTree.TreeNode[] all = new BaseTree.TreeNode[n];
        BaseTree.TreeNode node = null;
        for (int i = 0; i < n; ++i) {
            all[i] = new BaseTree.TreeNode(A[i]);
        }
        for (int i = 0; i <= n / 2; ++i) {
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left < n) {
                all[i].left = all[left];
            }
            if (right < n) {
                all[i].right = all[right];
            }
        }

        return all[0];
    }

    public static BaseTree.TreeNode generateACompleteBT(Integer[] A) {
        final int n = A.length;
        if (n == 0) {
            return null;
        }
        BaseTree.TreeNode[] all = new BaseTree.TreeNode[n];
        BaseTree.TreeNode node = null;
        for (int i = 0; i < n; ++i) {
            if (A[i] != null) {
                all[i] = new BaseTree.TreeNode(A[i]);
            }
        }
        for (int i = 0; i <= n / 2; ++i) {
            if (all[i] == null) {
                continue;
            }
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left < n) {
                all[i].left = all[left];
            }
            if (right < n) {
                all[i].right = all[right];
            }
        }

        return all[0];
    }

    /**
     * 将一个常规二叉树转换为完全二叉树
     */
    public static BaseTree.TreeNode convertBT2CompleteBT(BaseTree.TreeNode root) {
        return null;
    }

    public static int level(BaseTree.TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(level(root.left), level(root.right)) + 1;
    }

    public static void printTree(BaseTree.TreeNode root) {
//        if (root == null) {
//            throw new IllegalArgumentException("Am i fucking playing with you?");
//        }
//        // 转换成完全二叉树
//        root = TreeUtil.convertBT2CompleteBT(root);
//        // 层次遍历
//        LinkedList<BaseTree.TreeNode> q = new LinkedList<>();
//        q.addLast(root);
//        while (!q.isEmpty()) {
//            LinkedList<BaseTree.TreeNode> nextLevel = new LinkedList<>();
//            while (!q.isEmpty()) {
//                BaseTree.TreeNode node = q.removeFirst();
//                Base.print("[" + node.val + "]");
//                if (node.left != null) {
//                    nextLevel.addLast(node.left);
//                }
//                if (node.right != null) {
//                    nextLevel.addLast(node.right);
//                }
//            }
//            q = nextLevel;
//            Base.println("");
//        }
    }

    public static void printTreeInLevel(BaseTree.TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Am i fucking playing with you?");
        }
        // 总层数
        int maxLevel = level(root);
        if (maxLevel > 31) {
            Base.println("only can print max level : " + maxLevel);
        }
        // 最底层的结点数
        int bottomNodes = 1 << 2 << (maxLevel - 1);
        int curLevel = 1;
        // 首结点加入队列
        LinkedList<BaseTree.TreeNode> q = new LinkedList<>();
        q.addLast(root);
        // 迭代
        while (!q.isEmpty()) {
            LinkedList<BaseTree.TreeNode> nextLevel = new LinkedList<>();
            StringBuilder levelStr = new StringBuilder();
            StringBuilder pretty = new StringBuilder();
            // 层迭代
            while (!q.isEmpty()) {
                BaseTree.TreeNode node = q.removeFirst();
                levelStr.append(StringUtil.spaces((bottomNodes / (1 << curLevel)) - 2));
                levelStr.append("[" + node.val + "]");
                if (node.left != null) {
                    pretty.append("/");
                    nextLevel.addLast(node.left);
                }
                if (node.right != null) {
                    pretty.append("\\");
                    nextLevel.addLast(node.right);
                }
            }
            Base.println(levelStr);
            //Base.println(pretty);
            q = nextLevel;
            ++curLevel;
        }
    }

}