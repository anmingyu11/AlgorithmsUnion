package array;

import java.util.Arrays;

import base.Base;

public class NextPermutation extends Base {

    public static void nextPermutation(int[] nums) {
        nextPermutation(nums, 0, nums.length);
    }

    private static boolean nextPermutation(int[] nums, int begin, int end) {
        // 1. cut the len < 2
        final int len = nums.length;
        if (len < 2) {
            return false;
        }

        //从右至左，找到的第一个数字（partitionNumber）违反上升趋势
        //从最右一个元素开始，向左遍历以找到索引i满足num [i-1] <num [i]的第一个元素，因此，从num [i]到num [n-1]的元素被反向排序。(非升序)
        int pN = end - 1;//最右
        while (pN > 0) {
            if (nums[pN - 1] < nums[pN]) {
                break;
            }
            --pN;
        }

        //println("partition index : " + pN + " number : " + nums[pN]);

        //如果没有找到，这意味着当前序列已经是最大的排列，那么重新排列到第一个排列并返回false
        if (pN == 0) {
            reverse(nums, begin, end - 1);
            return false;
        }

        //从右到左，找到大于partitionNumber的第一个数字，将其命名为changeNumber
        int cN = end - 1;
        int val = nums[pN - 1];
        while (cN > 0) {
            if (nums[cN] > val) {
                break;
            }
            --cN;
        }

        //println(" change number index : " + cN + " number : " + nums[cN]);

        swap(nums, cN, pN -1);
        // Reverse all the digits on the right of partitionNumber 右面是升序
        reverse(nums, pN , end - 1);
        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{3, 2, 1};
        int[] arr3 = new int[]{1, 3, 2};
        println("====== test 1 ======");
        println(Arrays.toString(arr1));
        nextPermutation(arr1);
        println(Arrays.toString(arr1));

        println("====== test 2 ======");
        println(Arrays.toString(arr2));
        nextPermutation(arr2);
        println(Arrays.toString(arr2));

        println("====== test 3 ======");
        println(Arrays.toString(arr3));
        nextPermutation(arr3);
        println(Arrays.toString(arr3));
    }
}
