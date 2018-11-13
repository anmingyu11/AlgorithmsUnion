package greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import base.Base;

public class JumpGame2 extends Base {

    //BFS1 Space O(n)
    static class Solution1 {

        public static int jump(int[] nums) {
            if (nums.length <= 1) {
                return 0;
            }
            int[] jumps = new int[nums.length];
            Arrays.fill(jumps, Integer.MAX_VALUE);
            jumps[0] = 0;
            Queue<Integer> q = new LinkedList<>();
            q.add(0);
            final int len = nums.length;
            while (!q.isEmpty()) {
                int cur = q.remove();
                int next = cur + nums[cur];
                for (int i = cur; i <= next; ++i) {
                    int jumpTo = cur + nums[i];
                    if (jumpTo >= len - 1) {
                        return jumps[cur] + 1;
                    }
                    if (jumps[i] == Integer.MAX_VALUE) {
                        jumps[i] = jumps[cur] + 1;
                        q.add(i);
                    }
                }
            }
            return jumps[len - 1];
        }

    }

    //BFS2
    static class Solution2 {

        public static int jump(int[] nums) {
            int len = nums.length;
            if (len < 2) {
                return 0;
            }

            int level = 0, i = 0, cur = 0, next = 0;
            while (cur < len) {
                ++level;
                for (; i < cur; ++i) {
                    next = Math.max(next, cur + nums[cur]);
                    if (next > len - 1) {
                        return level;
                    }
                }
                cur = next;
            }
            return 0;
        }

    }

    //DP 破坏了原来的数组 难以理解。
    static class Solution3 {
        public static int jump(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return 0;
            }
            nums[nums.length - 1] = 0;
            nums[nums.length - 2] = 1;
            for (int i = nums.length - 3; i >= 0; --i) {
                //reachable i
                if (i + nums[i] >= nums.length) {
                    nums[i] = 1;
                    continue;
                }
                int minJumps = Integer.MAX_VALUE;
                for (int j = i + nums[i]; j >= i + 1; --j) {
                    minJumps = Math.min(1 + nums[j], minJumps);
                    if (minJumps == 2) {
                        break;
                    }
                }
                nums[i] = minJumps;
                println("Arr : " + Arrays.toString(nums));
            }
            return nums[0];
        }
    }

    //Greedy1
    static class Solution4 {

        public static int jump(int[] nums) {

            int step = 0, left = 0, right = 0;
            while (left <= right) {
                ++step;
                final int oldRight = right;
                for (int i = left; i <= oldRight; ++i) {
                    if (nums[i] + i >= nums.length - 1) {
                        return step;
                    }
                    right = Math.max(right, nums[i] + i);
                }
                left = oldRight + 1;
            }

            return 0;
        }

    }

    //Greedy2
    static class Solution5 {
        public static int jump(int[] nums) {
            int jumps = 0, curEnd = 0, curFarthest = 0;
            for (int i = 0; i < nums.length; ++i) {
                curFarthest = Math.max(curFarthest, nums[i] + i);
                if (i == curEnd) {
                    ++jumps;
                    curEnd = curFarthest;
                }
            }
            return jumps;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        println(Arrays.toString(nums) + " need : " + Solution5.jump(nums));
    }

}
