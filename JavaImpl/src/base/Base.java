package base;

public class Base {
    public static void swap(int nums[], int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void swap(int nums[][], int ai, int aj, int bi, int bj) {
        int tmp = nums[bi][bj];
        nums[bi][bj] = nums[ai][aj];
        nums[ai][aj] = tmp;
    }

    public static void reverse(int nums[], int begin, int end) {
        while (begin < end) {
            swap(nums, begin++, end--);
        }
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println(Object o) {
        System.out.println(o);
    }
}
