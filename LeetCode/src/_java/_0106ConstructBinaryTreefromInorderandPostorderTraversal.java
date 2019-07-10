package _java;

import java.util.HashMap;
import java.util.Map;

import base.BaseTree;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 */
public class _0106ConstructBinaryTreefromInorderandPostorderTraversal extends BaseTree {
    private abstract static class Solution {
        public abstract TreeNode buildTree(int[] inorder, int[] postorder);
    }

    /**
     * Runtime: 2 ms, faster than 92.33% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
     * Memory Usage: 41 MB, less than 11.05% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
     */
    private static class Solution1 extends Solution {

        private int[] in;
        private int[] post;
        private Map<Integer, Integer> map;
        private int postIdx;

        @Override
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            in = inorder;
            post = postorder;
            map = new HashMap<>();
            final int n = inorder.length;// 应该不会有人很无聊给两个长度不一致的.
            postIdx = n - 1;
            for (int i = 0; i < n; ++i) {
                map.put(inorder[i], i);
            }
            return buildTreeAux(0, n - 1);
        }

        private TreeNode buildTreeAux(int lIn, int rIn) {
            if (lIn > rIn) {
                return null;
            }
            // 这里postIdx和105里的preIdx很有讲究,这个Idx的精妙之处在于,构建树我们用的是前序遍历来构建
            // 在这个postorder里会按照从右子树到左子树来构建, 这里一定会先构造完右子树再构造左子树
            // 比如 一个后序遍历的输出是这样的.
            // Root的左右子树是L1 R1,L1的左右子树是L1,R1
            // [((L1L1)(L1R1)L1)((R1L1)(R1R1)R1)Root] 就是每个小括号内从后往前数是一个递归序列
            // [lIn...idx-1] 对应((L1L1)(L1R1)L1) [idx+1...rIn]对应((R1L1)(R1R1)R1)
            // 所以递归过程一定是 先走完右子树再走左子树的. postIdx可以以单调递减的形式来指定对应的值.
            // 前序遍历同理.
            int val = post[postIdx--];
            int idx = map.get(val);
            TreeNode t = new TreeNode(val);
            t.right = buildTreeAux(idx + 1, rIn);
            t.left = buildTreeAux(lIn, idx - 1);
            return t;
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return null;
        }

    }

    public static void main(String[] args) {
        int[] in1 = {9, 3, 15, 20, 7}, post1 = {9, 15, 7, 20, 3};

        Solution s = new Solution1();

        println(s.buildTree(in1, post1));
    }
}
