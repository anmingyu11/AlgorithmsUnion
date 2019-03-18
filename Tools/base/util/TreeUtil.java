package base.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import base.BaseTree;

public class TreeUtil {

    public static void printTree(BaseTree.TreeNode root) {
        BTreePrinter.printNode(root);
    }

    public static int maxLevel(BaseTree.TreeNode root) {
        if (root == null){
            return 0;
        }
        return Math.max(maxLevel(root.left), maxLevel(root.right)) + 1;
    }

    private static class BTreePrinter {

        public static void printNode(BaseTree.TreeNode root) {
            int maxLevel = maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static void printNodeInternal(List<BaseTree.TreeNode> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || isAllElementsNull(nodes)) {
                return;
            }

            int floor = maxLevel - level;
            int endLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            PrintUtil.printWhitespaces(firstSpaces);

            List<BaseTree.TreeNode> newNodes = new ArrayList<>();
            for (BaseTree.TreeNode node : nodes) {
                if (node != null) {
                    PrintUtil.print(node.val);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    PrintUtil.printWhitespaces(1);
                }

                PrintUtil.printWhitespaces(betweenSpaces);
            }
            PrintUtil.println("");

            for (int i = 1; i <= endLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    PrintUtil.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        PrintUtil.printWhitespaces(endLines + endLines + i + 1);
                        continue;
                    }
                    if (nodes.get(j).left != null) {
                        PrintUtil.print("/");
                    } else {
                        PrintUtil.printWhitespaces(1);
                    }
                    PrintUtil.printWhitespaces(i + i - 1);
                    if (nodes.get(j).right != null) {
                        PrintUtil.print("\\");
                    } else {
                        PrintUtil.printWhitespaces(1);
                    }
                    PrintUtil.printWhitespaces(endLines + endLines - i);
                }
                PrintUtil.printWhitespaces(1);
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static boolean isAllElementsNull(List list) {
            for (Object object : list) {
                if (object != null) {
                    return false;
                }
            }
            return true;
        }

    }

}