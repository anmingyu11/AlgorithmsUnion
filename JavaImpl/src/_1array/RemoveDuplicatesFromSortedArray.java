package _1array;

import java.util.Arrays;

import _0base.Base;

public class RemoveDuplicatesFromSortedArray extends Base {

    public static int removeDuplicates(int[] nums) {
        final int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int slow = 1;//slow = 1 slow是长度，slow=0 slow是长度-1 下面的逻辑会有相应变化
        for (int fast = 1; fast < len; fast++) {
            if (nums[fast] != nums[slow - 1]) {//
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    private static int[][] testData() {
        return new int[][]{
                {1, 1, 2}
        };
    }

    public static void main(String[] args) {
        int[][] a = testData();
        for (int i = 0; i < a.length; ++i) {
            final int[] aSub = a[i];
            println("arr " + i + " : " + Arrays.toString(aSub));
            println("remove : " + removeDuplicates(aSub));
            println("arr " + i + " : " + Arrays.toString(aSub));
        }
    }
}
