package _1array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import _0base.Base;

public class PermutationSequence extends Base {

    public static String getPermutation(int n, int k) {
        // 1. 创建缓存数组
        int[] factorial = new int[n + 1];
        int sum = 1;
        factorial[0] = 1;
        for (int i = 1; i <= n; ++i) {
            sum *= i;
            factorial[i] = sum;
        }

        println(" factorial : " + Arrays.toString(factorial));

        // 2. 创建数字序列数组
        List<Integer> nums = new ArrayList<>(n + 1);
        for (int i = 1; i <= n; ++i) {
            nums.add(i);
        }

        StringBuilder result = new StringBuilder();
        // 3. 康托逆展开
        k -= 1;
        for (int i = 1; i <= n; ++i) {
            int num = k / factorial[n - i];
            k = k % factorial[n - i];
            result.append(nums.remove(num));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        getPermutation(10, 11);
    }
}
