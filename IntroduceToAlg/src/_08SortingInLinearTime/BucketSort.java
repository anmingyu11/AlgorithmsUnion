package _08SortingInLinearTime;

import java.util.ArrayList;
import java.util.Comparator;

import base.BaseLinkedList;
import base.interfaces.ISortDouble;
import base.util.TestUtil;

/**
 * 桶排序:
 * 桶排序是一个对输入数据做出假设的排序方法.
 * 最佳前提:
 * 桶排序假设输入数据服从均匀分布,平均情况下它的时间代价为O(n).与计数排序类似,因为对输入做了某种假设,桶排序的速度也很快.
 * 具体来说,计数排序假设输入数据都属于一个小区间内的整数,而桶排序假设输入是由一个随机过程产生,该过程将元素,均匀,独立地分布在[0,1)的区间上.
 * <p>
 * 思想:
 * 桶排序将[0,1)区间划分为n个相同大小的子区间,或称为桶.然后,将n个输入数分别放到各个桶中.因为输入数据是均匀,独立地分布在(0,1]区间上,
 * 所以一般不会出现很多数落在同一个桶中的情况.为了得到输出结果,我们先对每个桶中的数进行排序,然后遍历每个桶,按照次序把桶中的元素列出来即可.
 */
public class BucketSort {

    /**
     * 为了实现方便,定义数据集A{}均匀分布在[0,1)之间
     */
    private abstract static class Solution extends BaseLinkedList implements ISortDouble {
    }

    /**
     * 动态数组实现,过于笨重.
     */
    private static class Solution1 extends Solution {

        @Override
        public void sort(double[] D) {
            final int n = D.length;
            // 从0-9分为10个桶,每个桶所对应的是小数点后的第一位.
            final int bucketSize = 10;
            ArrayList<Double>[] bucket = new ArrayList[bucketSize];
            // 初始化桶
            for (int i = 0; i < 10; ++i) {
                bucket[i] = new ArrayList<>();
            }
            // 装填桶
            for (int i = 0; i < n; ++i) {
                bucket[(int) (bucketSize * D[i])].add(D[i]);
            }
            // 排序每个桶
            for (int i = 0; i < bucketSize; ++i) {
                bucket[i].sort(new Comparator<Double>() {
                    @Override
                    public int compare(Double o1, Double o2) {
                        return o1.compareTo(o2);
                    }
                });
            }
            // 归并所有桶
            int j = 0;
            for (int i = 0; i < bucketSize; ++i) {
                for (double e : bucket[i]) {
                    D[j++] = e;
                }
            }
        }

    }

    private static class Solution2 extends Solution {

        @Override
        public void sort(double[] D) {
            final int n = D.length;
            final int bucketSize = 10;
            ListNode<Double>[] bucket = new ListNode[bucketSize];
            // 初始化桶
            for (int i = 0; i < bucketSize; ++i) {
                bucket[i] = new ListNode<>(Double.MAX_VALUE);
            }
            // 分配桶
            for (int i = 0; i < n; ++i) {
                // D[i] 应该被分配在 bucketSize * D[i]
                ListNode node = bucket[(int) (bucketSize * D[i])];
                ListNode next = node.next;
                node.next = new ListNode(D[i]);
                node.next.next = next;
            }
            // 排序桶
            for (int i = 0; i < bucketSize; ++i) {
                bucket[i].next = mergeSort(bucket[i].next);
            }
            // 归并所有桶
            int j = 0;
            for (int i = 0; i < bucketSize; ++i) {
                if (bucket[i].next == null) {
                    continue;
                }
                for (ListNode<Double> a : bucket[i].next) {
                    D[j++] = a.val;
                }
            }
        }

        // 对链表进行归并排序
        private ListNode mergeSort(ListNode<Double> head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode prev = null, slow = head, fast = head;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null;

            ListNode<Double> l1 = mergeSort(head);
            ListNode<Double> l2 = mergeSort(slow);
            return merge(l1, l2);
        }

        private ListNode<Double> merge(ListNode<Double> l1, ListNode<Double> l2) {
            ListNode<Double> fake = new ListNode<>(Double.MIN_VALUE);
            ListNode p = fake;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    p.next = l1;
                    l1 = l1.next;
                } else {
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            }
            if (l1 != null) {
                p.next = l1;
            }
            if (l2 != null) {
                p.next = l2;
            }
            return fake.next;
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution2();
        TestUtil.testSortUniformDistribution(s, 0, 1, 15, 100);
    }

}
