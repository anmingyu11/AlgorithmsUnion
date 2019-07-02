package _1Fundamentals.DataAbstraction;

/******************************************************************************
 *  Compilation:  javac RectHV.java
 *  Execution:    none
 *  Dependencies: Point2D.java
 *
 *  Immutable data type for 2D axis-aligned rectangle.
 *
 ******************************************************************************/

import base.stdlib.StdDraw;

/**
 * The {@code RectHV} class is an immutable data type to encapsulate a
 * two-dimensional axis-aligned rectangle with real-value coordinates.
 * The rectangle is <em>closed</em>—it includes the points on the boundary.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * RectHV类是一个不可变的数据类型,
 * 用于封装具有实值坐标的二维轴对齐 长方形.
 * 矩形是封闭的 - 它包括边界上的点.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public final class RectHV {
    private final double xmin, ymin;   // minimum x- and y-coordinates
    private final double xmax, ymax;   // maximum x- and y-coordinates

    /**
     * Initializes a new rectangle [<em>xmin</em>, <em>xmax</em>]
     * x [<em>ymin</em>, <em>ymax</em>].
     * <p>
     * 初始化一个新的矩形[xmin，xmax] x [ymin，ymax]。
     *
     * @param xmin the <em>x</em>-coordinate of the lower-left endpoint
     * @param xmax the <em>x</em>-coordinate of the upper-right endpoint
     * @param ymin the <em>y</em>-coordinate of the lower-left endpoint
     * @param ymax the <em>y</em>-coordinate of the upper-right endpoint
     * @throws IllegalArgumentException if any of {@code xmin},
     *                                  {@code xmax}, {@code ymin}, or {@code ymax}
     *                                  is {@code Double.NaN}.
     * @throws IllegalArgumentException if {@code xmax < xmin} or {@code ymax < ymin}.
     */
    public RectHV(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        if (Double.isNaN(xmin) || Double.isNaN(xmax)) {
            throw new IllegalArgumentException("x-coordinate is NaN: " + toString());
        }
        if (Double.isNaN(ymin) || Double.isNaN(ymax)) {
            throw new IllegalArgumentException("y-coordinate is NaN: " + toString());
        }
        if (xmax < xmin) {
            throw new IllegalArgumentException("xmax < xmin: " + toString());
        }
        if (ymax < ymin) {
            throw new IllegalArgumentException("ymax < ymin: " + toString());
        }
    }

    /**
     * Returns the minimum <em>x</em>-coordinate of any point in this rectangle.
     * <p>
     * 返回此矩形中任意点的最小x坐标。
     *
     * @return the minimum <em>x</em>-coordinate of any point in this rectangle
     */
    public double xmin() {
        return xmin;
    }

    /**
     * Returns the maximum <em>x</em>-coordinate of any point in this rectangle.
     * <p>
     * 返回此矩形中任意点的最大x坐标。
     *
     * @return the maximum <em>x</em>-coordinate of any point in this rectangle
     */
    public double xmax() {
        return xmax;
    }

    /**
     * Returns the minimum <em>y</em>-coordinate of any point in this rectangle.
     * <p>
     * 返回此矩形中任意点的最小y坐标。
     *
     * @return the minimum <em>y</em>-coordinate of any point in this rectangle
     */
    public double ymin() {
        return ymin;
    }

    /**
     * Returns the maximum <em>y</em>-coordinate of any point in this rectangle.
     * <p>
     * 返回此矩形中任意点的最大y坐标。
     *
     * @return the maximum <em>y</em>-coordinate of any point in this rectangle
     */
    public double ymax() {
        return ymax;
    }

    /**
     * Returns the width of this rectangle.
     * <p>
     * 返回此矩形的宽度。
     *
     * @return the width of this rectangle {@code xmax - xmin}
     */
    public double width() {
        return xmax - xmin;
    }

    /**
     * Returns the height of this rectangle.
     * <p>
     * 返回此矩形的高度。
     *
     * @return the height of this rectangle {@code ymax - ymin}
     */
    public double height() {
        return ymax - ymin;
    }

    /**
     * Returns true if the two rectangles intersect. This includes
     * <em>improper intersections</em> (at points on the boundary
     * of each rectangle) and <em>nested intersctions</em>
     * (when one rectangle is contained inside the other)
     * <p>
     * 如果两个矩形相交，则返回true.
     * 这包括不正确的交叉点（在每个矩形的边界上的点）和嵌套的交互（当一个矩形包含在另一个内部时）.
     *
     * @param that the other rectangle
     * @return {@code true} if this rectangle intersect the argument
     * rectangle at one or more points
     */
    public boolean intersects(RectHV that) {
        return this.xmax >= that.xmin
                && this.ymax >= that.ymin
                && that.xmax >= this.xmin
                && that.ymax >= this.ymin;
    }

    /**
     * Returns true if this rectangle contain the point.
     * <p>
     * 如果此矩形包含该点，则返回true。
     *
     * @param p the point
     * @return {@code true} if this rectangle contain the point {@code p},
     * possibly at the boundary; {@code false} otherwise
     */
    public boolean contains(Point2D p) {
        return (p.x() >= xmin)
                && (p.x() <= xmax)
                && (p.y() >= ymin)
                && (p.y() <= ymax);
    }

    /**
     * Returns the Euclidean distance between this rectangle and the point {@code p}.
     * <p>
     * 返回此矩形与点p之间的欧几里德距离。
     *
     * @param p the point
     * @return the Euclidean distance between the point {@code p} and the closest point
     * on this rectangle; 0 if the point is contained in this rectangle
     */
    public double distanceTo(Point2D p) {
        return Math.sqrt(this.distanceSquaredTo(p));
    }

    /**
     * Returns the square of the Euclidean distance between this rectangle and the point {@code p}.
     * <p>
     * 返回此矩形与点p之间的欧几里德距离的平方。
     *
     * @param p the point
     * @return the square of the Euclidean distance between the point {@code p} and
     * the closest point on this rectangle; 0 if the point is contained
     * in this rectangle
     */
    public double distanceSquaredTo(Point2D p) {
        double dx = 0.0, dy = 0.0;
        if (p.x() < xmin) {
            dx = p.x() - xmin;
        } else if (p.x() > xmax) {
            dx = p.x() - xmax;
        }
        if (p.y() < ymin) {
            dy = p.y() - ymin;
        } else if (p.y() > ymax) {
            dy = p.y() - ymax;
        }
        return dx * dx + dy * dy;
    }

    /**
     * Compares this rectangle to the specified rectangle.
     *
     * @param other the other rectangle
     * @return {@code true} if this rectangle equals {@code other};
     * {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        RectHV that = (RectHV) other;
        if (this.xmin != that.xmin) return false;
        if (this.ymin != that.ymin) return false;
        if (this.xmax != that.xmax) return false;
        if (this.ymax != that.ymax) return false;
        return true;
    }

    /**
     * Returns an integer hash code for this rectangle.
     *
     * @return an integer hash code for this rectangle
     */
    @Override
    public int hashCode() {
        int hash1 = ((Double) xmin).hashCode();
        int hash2 = ((Double) ymin).hashCode();
        int hash3 = ((Double) xmax).hashCode();
        int hash4 = ((Double) ymax).hashCode();
        return 31 * (31 * (31 * hash1 + hash2) + hash3) + hash4;
    }

    /**
     * Returns a string representation of this rectangle.
     *
     * @return a string representation of this rectangle, using the format
     * {@code [xmin, xmax] x [ymin, ymax]}
     */
    @Override
    public String toString() {
        return "[" + xmin + ", " + xmax + "] x [" + ymin + ", " + ymax + "]";
    }

    /**
     * Draws this rectangle to standard draw.
     */
    public void draw() {
        StdDraw.line(xmin, ymin, xmax, ymin);
        StdDraw.line(xmax, ymin, xmax, ymax);
        StdDraw.line(xmax, ymax, xmin, ymax);
        StdDraw.line(xmin, ymax, xmin, ymin);
    }
}