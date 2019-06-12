package _07QuickSort;

import base.interfaces.ISort;
import base.util.TestUtil;

/**
 * QuickSort是典型的分治法:
 * 1. 分解: 数组A[p...r] 被划分为两个(可能为空)的子数组A[p...q-1]和A[q+1...r]使得
 * A[p...q-1]中的每一个元素都小于等于A[q],A[q+1...r]中的每个元素都大于等于A[q]
 * 2. 解决: 通过递归调用快速排序,对数组A[p..q-1]和A[q+1,r]进行排序
 * 3. 合并:
 */
public class QuickSort implements ISort {

    @Override
    public void sort(int[] A) {
        quickSort(A, 0, A.length - 1);
    }

    /**
     * 主调用
     * 1. 用partition方法切分,切分元素在位置p
     * 2. 对A[lo...p-1],[p+1,hi]递归
     *
     * @param A
     * @param lo
     * @param hi
     */
    private void quickSort(int[] A, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int p = partition(A, lo, hi);
        quickSort(A, lo, p - 1);
        quickSort(A, p + 1, hi);
    }

    /**
     * 自己的理解:
     * 对数组进行划分
     * 小的放切分元素左面,大的放右面
     * I. 将切分元素设置为v = A[hi],算法导论叫这个为主元
     * II. i标记为lo-1, 循环不变式需要保证 A[..,i-1] <= v
     * III. j从lo开始迭代,一直迭代到hi - 1 , 即主元的前面
     * <p>
     * 迭代步:
     * 1. 如果 A[j] <= v, i = i + 1,
     * 2. 交换i和j中的元素,此处很subtle,需要多费点口舌:
     * 2.1. 前提 A[j] <= v , 现在将i向右移动, i = i + 1
     * 2.2. 此时 i 一定是 i >= lo,且 A[...i-1] 的值一定是小于切分元素v,哪怕i = lo = 0 也没关系,此时i左面的元素是不存在的.
     * 2.3. 那么此时 A[i]可能是任何元素 , 但是, 我们有这个条件可以保证, j >= i,
     * 2.4: 这个很容易证明:因为j的初始值是lo,不论A[j]与v的关系是什么样,j都会在循环结束时增加自身的值,但是i不一定,所以最多j = i , 所以j >= i
     * 2.5: 此时将j和i交换,A[i] <= v;
     * <p>
     * IV. 循环继续,此时A[...i] <= v A[i...hi]的元素是不确定的,但是当整个循环终止时,可以保证A[lo...i] <= v A[i +1...hi] > v
     * V. 循环结束之后将i+1与切分元素hi交换,因为A[i+1] > v 所以A[i+1]是切分元素,i+1是切分元素的最后位置
     * <p>
     * 算法导论中对此的描述,更加精炼且形式化:
     * 设v是主元,p = lo, r = hi
     * 第一轮迭代开始时,对于任意数组下标k,有:
     * 1. 若 p <= k <= i, 则 A[k] <= v.
     * 2. 若 i + 1 <= k <= j - 1, 则 A[k] > v
     * 3. 若 k = r, 则 A[k] = v;
     * <p>
     *
     * @return 返回切分元素的最后位置
     */
    private int partition(int[] A, int lo, int hi) {
        // 随机化的选主元
        // int ran = ShuffleUtil.randomPick(lo,hi);
        // swap(A, hi, ran);
        int v = A[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; ++j) {
            if (A[j] <= v) {
                ++i;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, hi);
        return i + 1;
    }

    private void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        TestUtil.testSort(quickSort);
    }

}