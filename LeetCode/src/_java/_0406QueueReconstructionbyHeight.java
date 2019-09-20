package _java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import base.Base;

/**
 * Suppose you have a random list of people standing in a queue.
 * Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number
 * of people in front of this person who have a height greater than or equal to h.
 * <p>
 * Write an algorithm to reconstruct the queue.
 * <p>
 * Note:
 * The number of people is less than 1,100.
 */
public class _0406QueueReconstructionbyHeight extends Base {

    private abstract static class Solution {
        public abstract int[][] reconstructQueue(int[][] people);
    }

    /**
     * Runtime: 6 ms, faster than 94.85% of Java online submissions for Queue Reconstruction by Height.
     * Memory Usage: 44.1 MB, less than 63.61% of Java online submissions for Queue Reconstruction by Height.
     * Brilliant
     */
    private static class Solution1 extends Solution {
        public int[][] reconstructQueue(int[][] people) {
            //pick up the tallest guy first
            //when insert the next tall guy, just need to insert him into kth position
            //repeat until all people are inserted into list
            Arrays.sort(people, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] != o2[0] ? (o2[0] - o1[0]) : (o1[1] - o2[1]);
                }
            });

            //print2DArr(people);
            List<int[]> res = new LinkedList<>();
            for (int[] cur : people) {
                res.add(cur[1], cur);
                printRes(res);
            }
            return res.toArray(new int[people.length][]);
        }

        private void printRes(List<int[]> res) {
            for (int[] a : res) {
                print(Arrays.toString(a) + " : ");
            }
            println("");
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        int[][] a = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        s.reconstructQueue(a);
        //print2DArr(s.reconstructQueue(a));
    }
}
