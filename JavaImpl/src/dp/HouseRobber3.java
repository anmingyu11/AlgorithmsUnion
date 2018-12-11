package dp;

import java.util.HashMap;
import java.util.Map;

import base.BaseTree;

public class HouseRobber3 extends BaseTree {

    // Fib
    static class Solution1 {

        public int rob(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int val = 0;


            if (root.left != null) {
                val += rob(root.left.left) + rob(root.left.right);
            }
            if (root.right != null) {
                val += rob(root.right.left) + rob(root.right.right);
            }

            return Math.max(val + root.val, rob(root.left) + rob(root.right));
        }

    }

    static class Solution2 {

        public int rob(TreeNode root) {
            return robSub(root, new HashMap<>());
        }

        private int robSub(TreeNode n, Map<TreeNode, Integer> m) {
            if (n == null) {
                return 0;
            }
            Integer mval = m.get(n);
            if (mval != null) {
                return mval;
            }
            int val = 0;

            if (n.left != null) {
                val += robSub(n.left.left, m) + robSub(n.left.right, m);
            }

            if (n.right != null) {
                val += robSub(n.right.right, m) + robSub(n.right.left, m);
            }

            val = Math.max(val + n.val, robSub(n.left, m) + robSub(n.right, m));
            m.put(n, val);
            return val;
        }

    }

    static class Solution3 {
        public int rob(TreeNode root) {
            int[] res = robSub(root);
            return Math.max(res[0], res[1]);
        }

        public int[] robSub(TreeNode root) {
            int[] res = new int[2];
            if (root == null) {
                return res;
            }

            int[] left = robSub(root.left);
            int[] right = robSub(root.right);

            res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            res[1] = root.val + left[0] + right[0];

            return res;
        }
    }

    //这个更加合理一些，更像动归
    static class Solution4 {
        public int rob(TreeNode t) {
            return robSub(t)[1];
        }

        //参数列表和方法名决定方法是否独一无二
        //后序遍历 可以想一想，后续遍历就变成了自底向上的
        private int[] robSub(TreeNode t) {
            int[] res = new int[2];
            if (t == null) {
                return res;
            }

            //0代表不选参数t，1代表无论选还是不选的最大值
            int[] left = robSub(t.left);
            int[] right = robSub(t.right);

            //代表不选t,选子结点的最大值
            res[0] = left[1] + right[1];
            //t.val + left[0] + right[0] 代表选t且不选t.left 且不选t.right的最大值
            res[1] = Math.max(res[0], t.val + left[0] + right[0]);
            return res;
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = stringToTreeNode("[3,2,3,null,3,null,1]");
        TreeNode t2 = stringToTreeNode("[3,4,5,1,3,null,1]");

        println(new Solution3().rob(t1));
        println(new Solution3().rob(t2));
    }

}
