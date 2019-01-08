package bit;

import base.Base;

public class RectangleArea extends Base {

    abstract static class Solution {
        public abstract int computeArea(int A, int B, int C, int D, int E, int F, int G, int H);
    }

    static class Solution1 extends Solution {

        @Override
        public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
            final int area = (D - B) * (C - A) + (H - F) * (G - E);

            // 不相交
            if (C < E || G < A || D < F || H < B) {
                return area;
            }

            // 伪相交
            final int h = Math.max(Math.min(D, H) - Math.max(B, F), 0);
            final int w = Math.max(Math.min(C, G) - Math.max(A, E), 0);
            final int intersection = h * w;

            return area - intersection;
        }

    }

    public static void main(String[] args) {
        int A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2;

        println(new Solution1().computeArea(A, B, C, D, E, F, G, H));
    }
}
