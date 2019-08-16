package _java;

import base.Base;
import base.BaseTree;
import base.util.ArraysUtil;
import base.util.TreeUtil;


/**
 * Given a complete binary tree, count the number of nodes.
 * <p>
 * Note:
 * <p>
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level,
 * except possibly the last,
 * is completely filled, and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2^h nodes
 * inclusive at the last level h.
 */
public class _0222CountCompleteTreeNodes extends Base {

    private abstract static class Solution {
        public abstract int countNodes(BaseTree.TreeNode root);
    }

    // Runtime: 1 ms, faster than 99.60% of Java online submissions for Count Complete Tree Nodes.
    // Memory Usage: 38.8 MB, less than 92.80% of Java online submissions for Count Complete Tree Nodes.
    private static class Solution1 extends Solution {

        @Override
        public int countNodes(BaseTree.TreeNode root) {
            return traversal(root);
        }

        private int traversal(BaseTree.TreeNode node) {
            if (node == null) {
                return 0;
            }
            int left = traversal(node.left);
            int right = traversal(node.right);
            return left + right + 1;
        }
    }

    // 妙,妙,妙,二分查找
    private static class Solution2 extends Solution {

        private int lay = 0;

        // 一棵满二叉树的结点总数是 (2^h)  - 1 (h为高度)
        // 1. 检查右子树的高度是否只比整个树的高度小1，这意味着左右子树具有相同的高度。
        // 1.1 如果是，则最后一个树行上的最后一个节点在右子树中，而左子树是一个高度为h-1的完整树。
        // 因此，我们将左子树的2^h - 1个节点加上1个根节点加上递归的右子树中的节点数。
        // 1.2 如果否，则最后一个树行上的最后一个节点在左子树中，右子树是一个高度为h-2的完整树。
        // 因此，我们采用右子树的2 ^（h-1）-1个节点加上1个根节点加上递归的左子树中的节点数。
        @Override
        public int countNodes(BaseTree.TreeNode root) {
            // 当前节点的高度, h.
            int h = height(root);
            return h < 0 ? 0 :
                    height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
                            : (1 << h - 1) + countNodes(root.left);
        }

        // 只需向左走即可找到树的高度。 让单个节点树的高度为0.找到整个树的高度h。 如果整个树是空的，即高度为-1，则有0个节点。
        private int height(BaseTree.TreeNode node) {
            return node == null ? -1 : 1 + height(node.left);
        }
    }

    // 上面那个技巧实在是有些费解,因为是化简后的,我来重写一个,
    // Runtime: 1 ms, faster than 99.61% of Java online submissions for Count Complete Tree Nodes.
    // Memory Usage: 40.5 MB, less than 32.97% of Java online submissions for Count Complete Tree Nodes.
    private static class Solution3 extends Solution {

        @Override
        public int countNodes(BaseTree.TreeNode root) {
            int h = height(root);
            if (h <= 1) {
                return h;
            } else {
                int hr = height(root.right);
                if (hr == h - 1) {
                    int leftCount = (1 << hr) - 1;
                    return 1 + leftCount + countNodes(root.right);
                } else {
                    int rightCount = (1 << hr) - 1;
                    return 1 + rightCount + countNodes(root.left);
                }
            }
        }

        private int height(BaseTree.TreeNode root) {
            if (root == null) {
                return 0;
            }
            return 1 + height(root.left);
        }
    }

    private static class Solution4 extends Solution {

        private int height(BaseTree.TreeNode root) {
            return root == null ? -1 : 1 + height(root.left);
        }

        @Override
        public int countNodes(BaseTree.TreeNode root) {
            int nodes = 0, h = height(root);
            while (root != null) {
                int hr = height(root.right);
                if (hr == h - 1) {
                    nodes += (1 << h);
                    root = root.right;
                } else {
                    nodes += (1 << h - 1);
                    root = root.left;
                }
                --h;
            }
            return nodes;
        }

    }

    public static void main(String[] args) {
        BaseTree.TreeNode tree1 = TreeUtil.generateACompleteBT(
                ArraysUtil.generateFromTo(0, 6));
        BaseTree.TreeNode tree2 = TreeUtil.generateACompleteBT(
                ArraysUtil.generateFromTo(0, 10));
        BaseTree.TreeNode tree3 = TreeUtil.generateACompleteBT(
                ArraysUtil.generateFromTo(0, 17));
        BaseTree.TreeNode tree4 = TreeUtil.generateACompleteBT(
                new int[]{});
        BaseTree.TreeNode tree5 = TreeUtil.generateACompleteBT(
                new int[]{1});
        BaseTree.TreeNode tree6 = TreeUtil.generateACompleteBT(
                ArraysUtil.generateFromTo(0, 14));

        Solution s = new Solution4();
        println(s.countNodes(tree1)); // 7
        println(s.countNodes(tree2)); // 11
        println(s.countNodes(tree3)); // 18
        println(s.countNodes(tree4)); // 0
        println(s.countNodes(tree5)); // 1
        println(s.countNodes(tree6)); // 15
    }
}
