package _01array;

public class MoveZeros {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int index = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }

        for (; index < nums.length; ++index) {
            nums[index] = 0;
        }
    }

}
