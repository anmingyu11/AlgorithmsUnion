package _01array;

import java.util.HashSet;

import _00base.Base;

public class LongestConsecutiveSequence extends Base {
    public static int longestConsecutive(int[] nums) {
        HashSet set = new HashSet();
        for (int i : nums) {
            set.add(i);
        }

        int longest = 0;
        for (int i : nums) {
            int len = 1;

            for (int j = i - 1; set.contains(j); --j) {
                set.remove(j);
                ++len;
            }

            for (int j = i + 1; set.contains(j); ++j) {
                set.remove(j);
                ++len;
            }

            longest = Math.max(longest, len);
        }

        return longest;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{100, 4, 200, 1, 3, 2};

        println("longest : " + longestConsecutive(arr));
    }
}
