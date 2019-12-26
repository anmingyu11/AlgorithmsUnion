package _java;

import base.Base;

/**
 * Alice and Bob take turns playing a game , with Alice starting first.
 * <p>
 * Initially , there is a number N on the chalkboard.
 * On each player's turn, that player makes a move consisting of:
 * <p>
 * - Choosing any x with 0 < x < N and N % x == 0.
 * Replacing the number N on the chalkboard with N - x.
 * Also , if a player cannot make a move, they lose the game.
 * - Return True if and only if Alice wins the game ,
 * assuming both players play optimally.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 * Example 2:
 * <p>
 * Input: 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 * <p>
 * Note:
 * 1 <= N <= 1000
 */
public class _1025DivisorGame extends Base {

    private static abstract class Solution {
        public abstract boolean divisorGame(int N);
    }

    /**
     * Conclusion
     * If N is even, can win.
     * If N is odd, will lose.
     * <p>
     * Recursive Prove （Top-down)
     * If N is even.
     * We can choose x = 1.
     * The opponent will get N - 1, which is a odd.
     * Reduce to the case odd and he will lose.
     * <p>
     * If N is odd,
     * 2.1 If N = 1, lose directly.
     * 2.2 We have to choose an odd x.
     * The opponent will get N - x, which is a even.
     * Reduce to the case even and he will win.
     * <p>
     * So the N will change odd and even alternatively until N = 1.
     * <p>
     * -------------------------------------------------------------
     * -------------------------------------------------------------
     * -------------------------------------------------------------
     * <p>
     * Mathematical Induction Prove （Bottom-up)
     * N = 1, lose directly
     * N = 2, will win choosing x = 1.
     * N = 3, must lose choosing x = 1.
     * N = 4, will win choosing x = 1.
     * ....
     * <p>
     * For N <= n, we have find that:
     * If N is even, can win.
     * If N is odd, will lose.
     * <p>
     * For the case N = n + 1
     * If N is even, we can win choosing x = 1,
     * give the opponent an odd number N - 1 = n,
     * force him to lose,
     * because we have found that all odd N <= n will lose.
     * <p>
     * If N is odd, there is no even x that N % x == 0.
     * As a result, we give the opponent a even number N - x,
     * and the opponent can win,
     * because we have found that all even N <= n can win.
     * <p>
     * Now we prove that, for all N <= n,
     * If N is even, can win.
     * If N is odd, will lose.
     * <p>
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Divisor Game.
     * Memory Usage: 33 MB, less than 13.33% of Java online submissions for Divisor Game.
     */
    private static class Solution1 extends Solution {

        // (even - odd) % 2 == even % 2 - odd % 2 == 1
        // odd - odd2 == odd % 2 - odd2 % 2 == 0
        // even - even2 == even % 2 - even2 % 2 == 0
        // (odd - even) % 2 == 1
        public boolean divisorGame(int N) {
            return (N & 1) == 0;
        }

    }

    /**
     * dp is better
     */
    private static class Solution2 extends Solution {

        public boolean divisorGame(int N) {
            boolean[] dp = new boolean[N + 1];
            dp[0] = false;
            dp[1] = false;
            // Alice will do her best to give opponent a losing value
            for (int i = 2; i <= N; ++i) {
                for (int j = 1; j < i; ++j) {
                    if (i % j == 0) {
                        if (!dp[i - j]) { // val given to the opponent
                            dp[i] = true;
                            break;
                        }
                    }
                }
            }
            return dp[N];
        }

    }

    public static void main(String[] args) {
        Solution s = new Solution1();

        println(s.divisorGame(2)); // true
        println(s.divisorGame(3)); // false
        println(s.divisorGame(4)); // true

    }
}
