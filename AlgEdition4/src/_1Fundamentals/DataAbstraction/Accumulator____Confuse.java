package _1Fundamentals.DataAbstraction;

import base.stdlib.StdOut;

/**
 * The {@code Accumulator} class is a data type for computing the running
 * mean, sample standard deviation, and sample variance of a stream of real
 * numbers. It provides an example of a mutable data type and a streaming
 * algorithm.
 * <p>
 * This implementation uses a one-pass algorithm that is less susceptible
 * to floating-point roundoff error than the more straightforward
 * implementation based on saving the sum of the squares of the numbers.
 * This technique is due to
 * <a href = "https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance#Online_algorithm">B. P. Welford</a>.
 * Each operation takes constant time in the worst case.
 * The amount of memory is constant - the data values are not stored.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * Accumulator类是一种数据类型，用于计算实数流的running平均值，样本标准偏差和样本方差。 它提供了可变数据类型和流式算法的示例。
 * 该实现使用一次性算法，该算法比基于保存数字的平方和的更直接的实现更不易受浮点舍入误差的影响。
 * 这种技术归功于B. P. Welford。
 * 在最坏的情况下，每个操作都需要constant的时间
 * 内存量是constant的 - 不存储数据值。
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Accumulator____Confuse {
    private int n = 0;          // number of data values
    private double sum = 0.0;   // sample variance * (n-1)
    private double mu = 0.0;    // sample mean

    /**
     * Initializes an accumulator.
     * <p>
     * 初始化累加器。
     */
    public Accumulator____Confuse() {
    }

    /**
     * Adds the specified data value to the accumulator.
     * <p>
     * 将指定的数据值添加到累加器。
     * Todo : 不是很懂.
     *
     * @param x the data value
     */
    public void addDataValue(double x) {
        ++n;
        double delta = x - mu;
        mu += delta / n;
        sum += (double) (n - 1) / n * delta * delta;
    }

    /**
     * Returns the mean of the data values.
     * <p>
     * 返回数据的平均值。
     *
     * @return the mean of the data values
     */
    public double mean() {
        return mu;
    }

    /**
     * Returns the sample variance of the data values.
     * <p>
     * 返回数据的样本方差.
     *
     * @return the sample variance of the data values
     */
    public double var() {
        if (n <= 1) {
            return Double.NaN;
        }
        return sum / (n - 1);
    }

    /**
     * Returns the sample standard deviation of the data values.
     * <p>
     * 返回数据的标准差
     *
     * @return the sample standard deviation of the data values
     */
    public double stddev() {
        return Math.sqrt(this.var());
    }

    /**
     * Returns the number of data values.
     * <p>
     * 返回数据的数量。
     *
     * @return the number of data values
     */
    public int count() {
        return n;
    }

    /**
     * Returns a string representation of this accumulator.
     *
     * @return a string representation of this accumulator
     */
    public String toString() {
        return "n = " + n + ", mean = " + mean() + ", stddev = " + stddev();
    }

    /**
     * Unit tests the {@code Accumulator} data type.
     * Reads in a stream of real number from standard input;
     * adds them to the accumulator; and prints the mean,
     * sample standard deviation, and sample variance to standard
     * output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Accumulator____Confuse stats = new Accumulator____Confuse();
        int[] a = {1, 2, 3, 4, 5};
        for (int v : a) {
            stats.addDataValue(v);
        }

        StdOut.printf("n      = %d\n", stats.count());
        StdOut.printf("mean   = %.5f\n", stats.mean());
        StdOut.printf("stddev = %.5f\n", stats.stddev());
        StdOut.printf("var    = %.5f\n", stats.var());
        StdOut.println(stats);
    }
}