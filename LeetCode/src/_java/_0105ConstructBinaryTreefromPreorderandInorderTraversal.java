package _java;

import java.util.HashMap;
import java.util.Map;

import base.BaseTree;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 * <pre>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * </pre>
 */
public class _0105ConstructBinaryTreefromPreorderandInorderTraversal extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode buildTree(int[] preorder, int[] inorder);
    }

    /**
     * Runtime: 2 ms, faster than 96.89% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
     * Memory Usage: 42.5 MB, less than 10.26% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
     */
    private static class Solution1 extends Solution {

        private int[] preorder;
        private int[] inorder;
        private int preIdx = 0;
        private Map<Integer, Integer> map = new HashMap<>();

        @Override
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            this.inorder = inorder;

            map = new HashMap<>();
            final int n = preorder.length;
            if (n == 0) {
                return null;
            }
            preIdx = 0;
            for (int i = 0; i < n; ++i) {
                map.put(inorder[i], i);
            }
            return bTAux(0, n);
        }

        private TreeNode bTAux(int l, int r) {
            // 如果传入的是 n-1 则这里是 l > r
            if (l == r) {
                // l == r相当于已经到达边界.
                return null;
            }
            int val = preorder[preIdx++];
            int idx = map.get(val);
            TreeNode t = new TreeNode(val);
            t.left = bTAux(l, idx); // idx是左子树的边界[l...idx]
            t.right = bTAux(idx + 1, r); // idx +1是右子树的左边界[idx+1...r]
            return t;
        }

    }

    /**
     * 换个传参方式,看看效率是提高了还是降低了,跟用成员变量有何区别.
     */
    private static class Solution2 extends Solution {

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < preorder.length; i++) {
                map.put(inorder[i], i);
            }
            return buildTree(preorder, inorder, map, 0, 0, preorder.length - 1);
        }

        /**
         * 下面这个参数preA来解释一下.
         * 画一个线段最好.
         * I hope this helps those folks who can't figure out how to get the index of the right child:
         * <p>
         * Our aim is to find out the index of right child for current node in the preorder array
         * We know the index of current node in the preorder array - preStart (or whatever your call it), it's the root of a subtree
         * Remember pre order traversal always visit all the node on left branch before going to the right ( root -> left -> ... -> right), therefore, we can get the immediate right child index by skipping all the node on the left branches/subtrees of current node
         * The inorder array has this information exactly. Remember when we found the root in "inorder" array we immediately know how many nodes are on the left subtree and how many are on the right subtree
         * Therefore the immediate right child index is preStart + numsOnLeft + 1 (remember in preorder traversal array root is always ahead of children nodes but you don't know which one is the left child which one is the right, and this is why we need inorder array)
         * numsOnLeft = root - inStart.
         *
         * @param preA
         * @param inA
         * @param map
         * @param pre
         * @param l
         * @param r
         * @return
         */
        public TreeNode buildTree(int[] preA, int[] inA, Map<Integer, Integer> map,
                                  int pre, int l, int r) {
            if (pre >= preA.length || l > r) {
                return null;
            }
            // end condition.
            int val = preA[pre];
            int idx = map.get(val);
            TreeNode t = new TreeNode(val);
            t.left = buildTree(preA, inA, map, pre + 1, l, idx - 1);
            t.right = buildTree(preA, inA, map, pre + idx - l + 1, idx + 1, r);
            return t;
        }

    }

    public static void main(String[] args) {
        int[] pre1 = {3, 9, 20, 15, 7}, in1 = {9, 3, 15, 20, 7};
        int[] pre2 = {1, 2}, in2 = {2, 1};
        int[] pre3 = {1, 2, 3}, in3 = {3, 2, 1};

        Solution s = new Solution2();

        println(s.buildTree(pre1, in1));
        println(s.buildTree(pre2, in2));
        println(s.buildTree(pre3, in3));
    }
}
