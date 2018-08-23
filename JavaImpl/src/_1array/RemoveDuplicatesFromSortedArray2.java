package _1array;

import java.util.Arrays;

import _0base.Base;

public class RemoveDuplicatesFromSortedArray2 extends Base {

    public static int removeDuplicates1(int[] nums) {
        final int len = nums.length;
        int slow = 2;
        for (int fast = 2; fast < len; ++fast) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    public static int removeDuplicates2(int[] nums) {
        final int len = nums.length;
        int slow = 0;
        for (int fast = 0; fast < len; ++fast) {
            if (fast < len - 1 && fast > 0 && nums[fast] == nums[fast - 1] && nums[fast] == nums[fast + 1])
                continue;
            nums[slow++] = nums[fast];
        }
        return slow;
    }

    private static int[][] testData() {
        return new int[][]{
                {1, 1, 1, 2, 2, 3}
        };
    }

    public static void main(String[] args) {
        int[][] a = testData();
        for (int i = 0; i < a.length; ++i) {
            final int[] aSub = a[i];
            println("arr " + i + " : " + Arrays.toString(aSub));
            println("remove : " + removeDuplicates1(aSub));
            println("arr " + i + " : " + Arrays.toString(aSub));
        }
    }
}
