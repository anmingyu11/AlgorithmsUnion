package _1Fundamentals.DataAbstraction;

/******************************************************************************
 *  Compilation:  javac Vector.java
 *  Execution:    java Vector
 *  Dependencies: StdOut.java
 *
 *  Implementation of a vector of real numbers.
 *
 *  This class is implemented to be immutable: once the client program
 *  initialize a Vector, it cannot change any of its fields
 *  (d or data[i]) either directly or indirectly. Immutability is a
 *  very desirable feature of a data type.
 *
 *  % java Vector
 *     x     = [ 1.0 2.0 3.0 4.0 ]
 *     y     = [ 5.0 2.0 4.0 1.0 ]
 *     z     = [ 6.0 4.0 7.0 5.0 ]
 *   10z     = [ 60.0 40.0 70.0 50.0 ]
 *    |x|    = 5.477225575051661
 *   <x, y>  = 25.0
 *
 *
 *  Note that Vector is also the name of an unrelated Java library class
 *  in the package java.util.
 *
 ******************************************************************************/

import base.stdlib.StdOut;

/**
 * The {@code Vector} class represents a <em>d</em>-dimensional Euclidean vector.
 * Vectors are immutable: their values cannot be changed after they are created.
 * It includes methods for addition, subtraction,
 * dot product, scalar product, unit vector, Euclidean norm, and the Euclidean
 * distance between two vectors.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * Vector类表示d维欧几里德矢量。
 * 向量是不可变的：它们的值在创建后无法更改。
 * 它包括加法，减法，点积，标量积，单位向量，欧几里德范数和两个向量之间的欧几里德距离的方法。
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Vector {

    private int d;               // dimension of the vector
    private double[] data;       // array of vector's components


    /**
     * Initializes a d-dimensional zero vector.
     * <p>
     * 初始化d维零向量。
     *
     * @param d the dimension of the vector
     */
    public Vector(int d) {
        this.d = d;
        data = new double[d];
    }

    /**
     * Initializes a vector from either an array or a vararg list.
     * The vararg syntax supports a constructor that takes a variable number of
     * arugments such as Vector x = new Vector(1.0, 2.0, 3.0, 4.0).
     * <p>
     * 从数组或vararg列表初始化向量。
     * vararg语法支持一个构造函数，它采用可变数量的arugments，例如Vector x = new Vector（1.0,2.0,3.0,4.0）。
     *
     * @param a the array or vararg list
     */
    public Vector(double... a) {
        d = a.length;
        // defensive copy so that client can't alter our copy of data[]
        data = new double[d];
        for (int i = 0; i < d; i++) {
            data[i] = a[i];
        }
    }

    /**
     * Returns the length of this vector.
     * <p>
     * 返回此向量的长度。
     *
     * @return the dimension of this vector
     * @deprecated Replaced by {@link #dimension()}.
     */
    @Deprecated
    public int length() {
        return d;
    }

    /**
     * Returns the dimension of this vector.
     * <p>
     * 返回此向量的维度。
     *
     * @return the dimension of this vector
     */
    public int dimension() {
        return d;
    }

    /**
     * Returns the dot product of this vector with the specified vector.
     * <p>
     * 返回与指定向量的点积.
     *
     * @param that the other vector
     * @return the dot product of this vector and that vector
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public double dot(Vector that) {
        if (this.d != that.d) {
            throw new IllegalArgumentException("Dimensions don't agree");
        }
        double sum = 0.0;
        for (int i = 0; i < d; i++) {
            sum = sum + (this.data[i] * that.data[i]);
        }
        return sum;
    }

    /**
     * Returns the magnitude of this vector.
     * This is also known as the L2 norm or the Euclidean norm.
     * <p>
     * 返回此向量的大小。 这也称为L2范数或欧几里德范数。
     *
     * @return the magnitude of this vector
     */
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * Returns the Euclidean distance between this vector and the specified vector.
     * <p>
     * 返回此向量与指定向量之间的欧几里德距离。
     *
     * @param that the other vector
     * @return the Euclidean distance between this vector and that vector
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public double distanceTo(Vector that) {
        if (this.d != that.d) {
            throw new IllegalArgumentException("Dimensions don't agree");
        }
        return this.minus(that).magnitude();
    }

    /**
     * Returns the sum of this vector and the specified vector.
     * <p>
     * 返回此向量和指定向量的和。
     *
     * @param that the vector to add to this vector
     * @return the vector whose value is {@code (this + that)}
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public Vector plus(Vector that) {
        if (this.d != that.d) {
            throw new IllegalArgumentException("Dimensions don't agree");
        }
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++) {
            c.data[i] = this.data[i] + that.data[i];
        }
        return c;
    }

    /**
     * Returns the difference between this vector and the specified vector.
     * <p>
     * 返回此向量与指定向量之间的差。
     *
     * @param that the vector to subtract from this vector
     * @return the vector whose value is {@code (this - that)}
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public Vector minus(Vector that) {
        if (this.d != that.d) {
            throw new IllegalArgumentException("Dimensions don't agree");
        }
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++) {
            c.data[i] = this.data[i] - that.data[i];
        }
        return c;
    }

    /**
     * Returns the ith cartesian coordinate.
     * <p>
     * 返回第i个笛卡尔坐标。
     *
     * @param i the coordinate index
     * @return the ith cartesian coordinate
     */
    public double cartesian(int i) {
        return data[i];
    }

    /**
     * Returns the scalar-vector product of this vector and the specified scalar
     * <p>
     * 返回此向量*标量的乘积,返回一个新的向量
     *
     * @param alpha the scalar
     * @return the vector whose value is {@code (alpha * this)}
     * @deprecated Replaced by {@link #scale(double)}.
     */
    @Deprecated
    public Vector times(double alpha) {
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++) {
            c.data[i] = alpha * data[i];
        }
        return c;
    }

    /**
     * Returns the scalar-vector product of this vector and the specified scalar
     * <p>
     * 返回此向量*标量的乘积,返回一个新的向量
     *
     * @param alpha the scalar
     * @return the vector whose value is {@code (alpha * this)}
     */
    public Vector scale(double alpha) {
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++) {
            c.data[i] = alpha * data[i];
        }
        return c;
    }

    /**
     * Returns a unit vector in the direction of this vector.
     * <p>
     * 返回此向量方向的单位向量。
     *
     * @return a unit vector in the direction of this vector
     * @throws ArithmeticException if this vector is the zero vector
     */
    public Vector direction() {
        if (this.magnitude() == 0.0) {
            throw new ArithmeticException("Zero-vector has no direction");
        }
        return this.times(1.0 / this.magnitude());
    }


    /**
     * Returns a string representation of this vector.
     *
     * @return a string representation of this vector, which consists of the
     * the vector entries, separates by single spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < d; i++) {
            s.append(data[i] + " ");
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code Vector} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        double[] xdata = {1.0, 2.0, 3.0, 4.0};
        double[] ydata = {5.0, 2.0, 4.0, 1.0};
        Vector x = new Vector(xdata);
        Vector y = new Vector(ydata);

        StdOut.println("   x       = " + x);
        StdOut.println("   y       = " + y);

        Vector z = x.plus(y);
        StdOut.println("   z       = " + z);

        z = z.times(10.0);
        StdOut.println(" 10z       = " + z);

        StdOut.println("  |x|      = " + x.magnitude());
        StdOut.println(" <x, y>    = " + x.dot(y));
        StdOut.println("dist(x, y) = " + x.distanceTo(y));
        StdOut.println("dir(x)     = " + x.direction());

    }
}