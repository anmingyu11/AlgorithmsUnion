package _java;

import java.util.Arrays;

import base.BaseTree;

public class _0889ConstructBinaryTreefromPreorderandPostorderTraversal extends BaseTree {

    private abstract static class Solution {
        public abstract TreeNode constructFromPrePost(int[] pre, int[] post);
    }

    private static class Solution1 extends Solution {

        @Override
        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            final int N = pre.length;
            if (N == 0) {
                return null;
            }
            TreeNode t = new TreeNode(pre[0]);
            if (N == 1) {
                return t;
            }
            int L = 0;
            for (int i = 0; i < N; ++i) {
                if (post[i] == pre[1]) {
                    L = i + 1;
                }
            }

            //[1,L+1)
            //[0,L)
            t.left = constructFromPrePost(
                    Arrays.copyOfRange(pre, 1, L + 1)
                    , Arrays.copyOfRange(post, 0, L)
            );
            //[L+1,N)
            //[L,n-1)
            t.right = constructFromPrePost(
                    Arrays.copyOfRange(pre, L + 1, N)
                    , Arrays.copyOfRange(post, L, N - 1)
            );
            return t;
        }

    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Construct Binary Tree from Preorder and Postorder Traversal.
     * Memory Usage: 40.7 MB, less than 20.69% of Java online submissions for Construct Binary Tree from Preorder and Postorder Traversal.
     */
    private static class Solution2 extends Solution {

        private int[] pre;
        private int[] post;

        @Override
        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            this.pre = pre;
            this.post = post;
            return constructAux(0, 0, pre.length);
        }

        private TreeNode constructAux(int preLo, int postLo, int L) {
            if (L == 0) {
                return null;
            }
            TreeNode t = new TreeNode(pre[preLo]);
            if (L == 1) {
                return t;
            }
            int len = 1;
            for (; len < L; ++len) {
                // postLo often start with 0,[postLo,len)
                if (post[postLo + len - 1] == pre[preLo + 1]) {
                    break;
                }
            }
            t.left = constructAux(preLo + 1, postLo, len);
            t.right = constructAux(preLo + len + 1, postLo + len, L - 1 - len);
            return t;
        }

    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6, 7}, post = {4, 5, 2, 6, 7, 3, 1};

        Solution s = new Solution2();

        println(s.constructFromPrePost(pre, post));
    }
}
