package _java;

import base.Base;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * Initially, there are n stones in a pile.
 * On each player's turn,
 * that player makes a move consisting of removing any non-zero square number
 * of stones in the pile.
 * <p>
 * Also, if a player cannot make a move, he/she loses the game.
 * <p>
 * Given a positive integer n.
 * Return True if and only if Alice wins the game otherwise return False,
 * assuming both players play optimally.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
 * Example 3:
 * <p>
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 * <p>
 * Example 4:
 * <p>
 * Input: n = 7
 * Output: false
 * Explanation: Alice can't win the game if Bob plays optimally.
 * If Alice starts removing 4 stones, Bob will remove 1 stone then Alice should remove only 1 stone and finally Bob removes the last one (7 -> 3 -> 2 -> 1 -> 0).
 * If Alice starts removing 1 stone, Bob will remove 4 stones then Alice only can remove 1 stone and finally Bob removes the last one (7 -> 6 -> 2 -> 1 -> 0).
 * <p>
 * Example 5:
 * <p>
 * Input: n = 17
 * Output: false
 * Explanation: Alice can't win the game if Bob plays optimally.
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^5
 */
public class _1510StoneGame4 extends Base {

    private static abstract class Solution {
        public abstract boolean winnerSquareGame(int n);
    }

    // minus
    private static class Solution1 extends Solution {

        public boolean winnerSquareGame(int n) {
            boolean[] dp = new boolean[n + 1];
            dp[0] = false;
            for (int i = 1; i <= n; ++i) {
                if (dp[i]) {
                    continue;
                }
                for (int k = 1; k * k <= i; ++k) {
                    if (!dp[i - k * k]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            printArr(dp);
            return dp[n];
        }

    }

    // plus
    private static class Solution2 extends Solution {

        public boolean winnerSquareGame(int n) {
            boolean[] dp = new boolean[n + 1];
            for (int i = 0; i <= n; ++i) {
                if (dp[i]) {
                    continue;
                }
                if (dp[n]) {
                    break;
                }
                for (int k = 1; i + k * k <= n; ++k) {
                    dp[i + k * k] = true;
                }
            }
            return dp[n];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution1();
        s.winnerSquareGame(100000);
    }

}
