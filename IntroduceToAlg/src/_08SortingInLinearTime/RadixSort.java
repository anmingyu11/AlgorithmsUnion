package _08SortingInLinearTime;

import base.Base;
import base.interfaces.ISort;
import base.util.ArraysUtil;
import base.util.TestUtil;

public class RadixSort {

    private static class LSD implements ISort {

        /**
         * Rearranges the array of 32-bit integers in ascending order.
         * This is about 2-3x faster than Arrays.sort().
         * 低位优先的基数排序(能够处理正负号):
         * Java中的整型是32位,4字节整数,每字节八位,所以可以对整数的每8位进行低位优先的LSD排序
         * <p>
         * 1. BITS = 32                    每个整数32位
         * 2. R = 1<<8                     将1左移8位,得到R将作为后面(频率/索引)数组的长度 (二进制)R = 100000000(注意是8个0)对应的十进制是256
         * 3. MASK = R - 1                 R 本身是 100000000 , R - 1 = 011111111 , 用这个MASK做与运算可以截断整数的一部分.
         * 4. w = BITS / BITS_PER_BYTE     每字节8位, w代表位数 w = 4;
         * 5. 创建辅助数组 aux[]
         * 6. 开始迭代,迭代4个字节:
         * - 循环1: 设数字 v = a[i], (v >> (8 * d)) 如果遍历的是第一个字节,那么就右移0位,如果是第二个字节,那么就右移8位,
         * 这样再做按位与运算,就可以将对应的字节代表的数字c取出来,将频率count[c+1]自增.
         * - 循环2: 将数字出现的频率转化为索引
         * - d = w - 1 这一步,是在处理负数:
         * count[r]对应的是0-256的所有数区间每个数对应的索引位置,因为在整数中,最高位代表符号位,所以在取出来的八位数c中,后7位是对正数的排序,
         * 第一位对应的是负数,
         * 所以在这段对最高字节的特殊处理之中(前面已经对这个最高字节分配了索引,所以这里我们需要重新分配一下索引)
         * 第一步求出shift1 = count[R] - count[R/2], 此处对应的是所有负数,count[R]对应的是最小的负数count[R/2]对应的是最大的负数.
         * 第二步求出shift2 = count[R/2], 在count[R/2 - 1]是最大的正数对应的索引.
         * 第三步循环,这个循环是要处理所有的正数,正数排名在后,要将正数其对应的索引增加,那么增加的间隔正好最大负数和最小负数索引之间的间隔.
         * 第四步循环,这个循环是要处理所有的负数,负数排名在前,要将负数对应的索引减小,那么减小的间隔正好是最大正数的间隔.
         * - 将元素分类并写入aux[]
         * - 回写到a[i]里
         *
         * @param a the array to be sorted
         */
        public void sort(int[] a) {
            final int BITS = 32;
            final int BITS_PER_BYTE = 8;
            final int R = 1 << BITS_PER_BYTE;
            final int MASK = R - 1;
            final int w = BITS / BITS_PER_BYTE;
            int n = a.length;
            int[] aux = new int[n];
            for (int d = 0; d < w; d++) {
                int[] count = new int[R + 1];
                for (int i = 0; i < n; ++i) {
                    ++count[((a[i] >> (BITS_PER_BYTE * d)) & MASK) + 1];
                }
                for (int r = 0; r < R; ++r) {
                    count[r + 1] += count[r];
                }
                if (d == w - 1) {
                    int shift1 = count[R] - count[R / 2];
                    int shift2 = count[R / 2];
                    for (int r = 0; r < R / 2; ++r) {
                        count[r] += shift1;
                    }
                    for (int r = R / 2; r < R; ++r) {
                        count[r] -= shift2;
                    }
                }
                for (int i = 0; i < n; ++i) {
                    aux[count[(a[i] >> BITS_PER_BYTE * d) & MASK]++] = a[i];
                }
                for (int i = 0; i < n; ++i) {
                    a[i] = aux[i];
                }
            }
        }

    }

    /**
     * 另一种RadixSort,不能排序负数.
     * 着重说一下while里的第三个循环为什么是从n-1到0,而不是从0遍历到n-1
     * 比如说testcase : [1, 12, 123, 1234 ]
     * 这个总共需要进行四次大循环,
     * 第一次循环 exp = 1:
     * - 结果是[1, 12, 123, 1234]
     * 第二次循环 exp = 10:
     * - 结果是[1, 12, 123, 1234]
     * 第三次循环 exp = 100:
     * - 结果是[12, 1, 123, 1234]
     * 第四次循环 exp = 1000:
     * - 结果是[123, 1, 12, 1234]
     * <p>
     * 第三次循环,第四次循环的时候,对应[1],[1,12]对应的是0,其实此时前面的数已经排完了,
     * 如果从前往后排,就相当于把前面已经排序了的数字又给倒着排了一遍,导致结果不可预期.
     */
    private static class LSD2 implements ISort {

        @Override
        public void sort(int[] A) {
            final int n = A.length;
            int radix = 10;
            int exp = 1;

            int max = ArraysUtil.max(A);

            int[] aux = new int[n];
            while (max / exp > 0) {
                int[] count = new int[radix];
                for (int i = 0; i < n; ++i) {
                    ++count[A[i] / exp % radix];
                }
                for (int r = 1; r < radix; ++r) {
                    count[r] += count[r - 1];
                }
                for (int i = n - 1; i >= 0; --i) {
                    aux[--count[A[i] / exp % radix]] = A[i];
                }
                for (int i = 0; i < n; ++i) {
                    A[i] = aux[i];
                }
                exp *= 10;
            }
        }

    }

    /**
     * 输入必须是正数.
     * 处理负数的MSD比较复杂,Princeton的算法4里也没写出来这个,
     * 我想了一想,实现的难度超过想象,楞写是能写出来的,但是又变成基于比较的排序了,希望有大神能给写一下.
     */
    private static class MSD implements ISort {

        private static final int BITS = 32;
        private static final int BITS_PER_BYTE = 8;
        private static final int R = 1 << BITS_PER_BYTE;
        private static final int MASK = R - 1;
        private static final int w = BITS / BITS_PER_BYTE;

        @Override
        public void sort(int[] A) {
            final int n = A.length;
            int[] aux = new int[n];
            sort(A, aux, 0, n - 1, 0);
        }

        /**
         * 方法内部的数据结构,mask, count数组的建立与LSD一样.
         * I.   创建count[R + 1] , mask = R - 1用来对后八位做位运算
         * II.  shift,用来计算当前需要右移的步数,因为d只有4个(一个整型有32位,4个字节),且从0开始, shift = 32 - 8 * d - 8.
         * - d = 0,shift = 24,右移24位用mask做位运算取最高,d = 1,shift = 16,右移16位做位运算取第二个字节.
         * III. 开始键索引排序
         * 1. 第一次循环,统计频率.
         * 2. 第二次循环,将频率转化为索引.
         * 3. 第三次循环,写回.
         * IV.  如果此时已经到了第四位,终止排序
         * V.   MSD对递归的处理与字符串排序有差别,对字符串排序中,不需要单独处理count[0],即是字符串的长度==d的部分,
         * 而在这个中,需要处理count[0],count[0]中包含的是当前字节为0的序列,一个字节为0,后序字节还是需要被排序的,而字符串中如果小于当前d指的位数,就一定比count[1...n]的序列要小:w
         */
        private static void sort(int[] a, int[] aux, int lo, int hi, int d) {
            if (hi <= lo) {
                return;
            }
            int[] count = new int[R + 1];
            int shift = (w - 1 - d) * BITS_PER_BYTE;
            for (int i = lo; i <= hi; ++i) {
                ++count[((a[i] >> shift) & MASK) + 1];
            }
            for (int r = 0; r < R; ++r) {
                count[r + 1] += count[r];
            }
            for (int i = lo; i <= hi; ++i) {
                aux[count[((a[i] >> shift) & MASK)]++] = a[i];
            }
            for (int i = lo; i <= hi; ++i) {
                a[i] = aux[i - lo];
            }
            if (d == 3) {
                return;
            }
            if (count[0] > 0) {
                sort(a, aux, lo, lo + count[0] - 1, d + 1);
            }
            for (int r = 0; r < R; r++) {
                if (count[r + 1] > count[r]) {
                    sort(a, aux, lo + count[r], lo + count[r + 1] - 1, d + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        LSD lsd = new LSD();
        Base.println("Least significant digital Sort with negative number ");
        TestUtil.testSort(new ISort() {
            @Override
            public void sort(int[] A) {
                lsd.sort(A);
            }
        });
        Base.println("------------------------------------------");

        LSD2 lsd2 = new LSD2();
        Base.println("Least significant digital Sort 2 with negative number ");
        TestUtil.testSort(new ISort() {
            @Override
            public void sort(int[] A) {
                lsd2.sort(A);
            }
        }, false, 30, true);
        Base.println("------------------------------------------");

        MSD msd = new MSD();
        Base.println("Most significant digital Sort without negative number");
        TestUtil.testSort(new ISort() {
            @Override
            public void sort(int[] A) {
                msd.sort(A);
            }
        }, false, 30, true);

    }

}