package _5strings;

import java.util.Arrays;

import base.stdlib.StdOut;

public class LSD {

    public static void sort(String[] a, int w) {
        int len = a.length;
        int R = 256;
        String[] aux = new String[len];
        for (int d = w - 1; d >= 0; --d) {

            //Todo :Why r+1
            int[] count = new int[R + 1];

            // 1. 计算频率
            for (int i = 0; i < len; ++i) {
                //Todo : why +1
                ++count[a[i].charAt(d) + 1];
            }

            // 2. 生成索引
            for (int r = 0; r < R; ++r) {
                count[r + 1] += count[r];
            }

            // 3. 将元素分类
            for (int i = 0; i < len; ++i) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // 4. 回写
            for (int i = 0; i < len; ++i) {
                a[i] = aux[i];
            }
        }
    }


    public static void main(String[] args) {
        String word3 =
                "bed bug dad yes zoo " +
                        "now for tip ilk dim " +
                        "tag jot sob nob sky " +
                        "hut men egg few jay " +
                        "owl joy rap gig wee " +
                        "was wad fee tap tar " +
                        "dug jam all bad yet";
        String[] a = word3.split(" ");
        //StdOut.println(Arrays.toString(a));
        sort(a, 3);
        StdOut.println(Arrays.toString(a));
    }
}