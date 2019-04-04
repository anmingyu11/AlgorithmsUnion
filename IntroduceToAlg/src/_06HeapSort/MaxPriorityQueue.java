package _06HeapSort;

import base.interfaces.ISort;
import base.util.TestUtil;

/**
 * 最大优先队列
 * <p>
 * 优先队列是一种用来维护由一组元素构成的集合S的数据结构,其中的每一个元素都有一个相关的值,称为关键字(key).
 * 一个最大优先队列支持以下操作:
 * Insert(S,x): 把元素x插入集合中.这一操作等价于S = S U {x}
 * Maximum(S): 返回S中具有的最大元素.
 * Extract-Max(S): 去掉并返回S中的具有最大关键字的元素
 * Increase-Key(S,x,k): 将元素x的关键字增加到k,这里假设k的值不小于x原来的值
 * <p>
 * 这个优先队列的实现即为简略,其实有很多的细节在内,但当前按照算法导论上的写法,给出大体的思路.
 */
public class MaxPriorityQueue {

    /**
     * 应该有resize的操作,省略了.
     */
    private int[] heap;
    /**
     * 堆的大小.
     */
    private int heapSize;

    /**
     * @param size 优先队列的最大元素数量
     */
    public MaxPriorityQueue(int size) {
        heap = new int[size];
        heapSize = 0;
    }

    /**
     * 返回最大元素
     *
     * @return
     */
    public int max() {
        return heap[0];
    }

    /**
     * 删除优先队列里的最大元素并返回.
     * <p>
     * 1. 用max保存堆顶元素.
     * 2. 用顶元素替换堆底元素
     * 3. 堆的元素数量减小.
     * 4. 堆有序化
     *
     * @return
     */
    public int deleteMax() {
        if (heapSize < 0) {
            throw new IllegalArgumentException("heap underflow");
        }
        int max = heap[0];
        heap[heapSize - 1] = heap[0];
        --heapSize;
        // heap[heapSize + 1] = null;
        maxHeapify(heap, 0);
        return max;
    }

    /**
     * 插入操作
     * heapSize + 1
     */
    public void insert(int key) {
        heap[heapSize] = Integer.MIN_VALUE;
        heapIncreaseKey(heapSize, key);
        ++heapSize;
    }

    /**
     * 将元素heap[i]更新为新值,并保证堆的有序性.
     *
     * @param i
     */
    public void heapIncreaseKey(int i, int key) {
        if (key < heap[i]) {
            throw new IllegalArgumentException("new key is smaller than current key");
        }
        heap[i] = key;
        while (i > 0 && heap[parent(i)] < heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * 将maxHeapify方法命名为sink(来自普林斯顿)
     * <p>
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

    private void swap(int i, int j) {
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    public static void main(String[] args) {
        // 正确性验证,不是真的要拿这个排序.
        TestUtil.testSort(new ISort() {
            @Override
            public void sort(int[] a) {
                final int n = a.length;
                MaxPriorityQueue pq = new MaxPriorityQueue(n);
                for (int e : a) {
                    pq.insert(e);
                }
                for (int i = 0; i < n; ++i) {
                    a[i] = pq.deleteMax();
                }
            }
        });
    }

}
