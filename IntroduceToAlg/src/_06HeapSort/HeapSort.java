package _06HeapSort;

import base.interfaces.ISort;
import base.util.TestUtil;

/**
 * 堆排序
 * <p>
 * 二叉堆:
 * 二叉堆是一个数组,它可以被看成一个近似的完全二叉树(严蔚敏版的完全二叉树定义和ITA里的不一样).
 * 堆的性质:
 * 如果是最小堆:设结点为i,结点i的左右子节点大于等于结点i
 * 如果是最大堆:设结点为i,结点i的左右子节点小于等于结点i
 * <p>
 * 包含n个元素的堆可以看成是一棵完全二叉树,那么堆的高度是Θ(logn)
 * <p>
 * 保证堆有序是整个堆排序算法的核心思想.
 */
public class HeapSort implements ISort {

    /**
     * 有heapSize个堆元素存储在该数组中.A[0...heapSize]存放的是有效的元素
     * 0 <= heapSize <= A.length;
     */
    private int heapSize;
    /**
     * 堆
     */
    private int[] A;

    /**
     * A[0,...,n]
     * 1. 构造堆
     * 2. 因为数组的最大根结点总在0,通过它与A[n]进行交换,我们可以让该元素放到正确的位置
     * 这时候,如果我们从堆中去掉结点n(这一操作可以通过减少heapSize的值来实现),在剩余的结点中,原来根结点的孩子仍然是最大堆,
     * 而通过对新的堆顶进行堆有序化,在A[0,...,n-1]上构造一个新的堆.
     * 不断重复这个过程直到堆的大小从n-1下降到2
     * <p>
     * 时间复杂度O(nlgn)
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        buildMaxHeap(a);
        final int n = A.length;
        for (int i = n - 1; i >= 1; --i) {
            swap(i, 0);
            --heapSize;
            maxHeapify(A, 0);
        }
    }

    /**
     * 子数组A[n/2+1 ... n] 中的元素都是树的叶子结点.每个叶子结点都可以看成是只包含一个元素的堆.
     * <p>
     * 初始化: 在第一次循环迭代之前,i = n/2, 而n/2+1,n/2+2,...,n都是叶结点,因而是平凡最大堆的根节点.
     * 保持: 为了看到每次迭代都维护这个循环不变量,注意到结点i的孩子结点的下标均比i大.所以根据循环不变量,它们都是最大堆的根.
     * 这也是调用maxHeapify使结点i称为一个最大堆的根的先决条件.而且,maxHeapify维护了结点i+1,i+2,...,n都是一个最大堆的根节点性质.
     * 在循环中递减i的值,为下一次循环重新建立循环不变量.
     * 终止: 过程终止时,i=0,根据循环不变量,每个结点0,1,...,n都是一个最大堆的根.
     * <p>
     * 时间复杂度:每次调用maxHeapify的复杂度为O(lgn),buildMaxHeap需要O(n)次这样的调用.因此总的时间复杂度是O(nlgn).
     * <p>
     * 上渐进紧确界:O(n),计算略.
     * <p>
     * 因此我们可以在线性时间内,把一个无序数组构造成为一个最大堆
     *
     * @param a
     */
    private void buildMaxHeap(int[] a) {
        A = a;
        final int n = A.length;
        heapSize = n - 1;
        for (int i = n / 2; i >= 0; --i) {
            maxHeapify(A, i);
        }
    }

    /**
     * 最大二根堆化,维护堆的有序性;
     * 给定一个结点i,当结点i的子结点大于i的时候,结点i下沉,
     * 下沉的目标是左子节点和右子节点中较大的一个
     * 保证堆有序
     * <p>
     * 因为每个孩子的子树的大小至多为2n/3(最坏情况发生在树的最底层恰好半满的时候)
     * T(n) <= T(2*n/3) + Θ(1)
     * T(n) = O(lgn)
     * 也就是说,对一个树高为h的结点来说,这个方法的时间复杂度是O(h)
     *
     * @param A
     * @param i
     */
    private void maxHeapify(int[] A, int i) {
        int l = leftChild(i);
        int r = rightChild(i);
        int largest;
        if (l <= heapSize && A[l] > A[i]) {
            // 左子节点大于结点i
            largest = l;
        } else {
            largest = i;
        }
        if (r <= heapSize && A[r] > A[largest]) {
            // 右子节点大于左子节点或者i结点,即选出最大的子节点进行交换以保证堆有序
            largest = r;
        }
        // largest == i堆有序,递归结束,否则继续向下遍历
        if (largest != i) {
            swap(i, largest);
            maxHeapify(A, largest);
        }
    }

    private void swap(int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    /**
     * 获取结点i的父结点
     *
     * @param i
     * @return
     */
    private int parent(int i) {
        return i / 2;
    }

    /**
     * 获取结点i的左子节点
     *
     * @param i
     * @return
     */
    private int leftChild(int i) {
        return (i << 1) + 1;
    }

    /**
     * 获取结点i的右子节点
     *
     * @param i
     * @return
     */
    private int rightChild(int i) {
        return (i << 1) + 2;
    }

    public static void main(String[] args) {
        TestUtil.testSort(new HeapSort());
    }

}