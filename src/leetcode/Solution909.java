package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution909 {
    // Runtime of 6ms (68.69%)
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;
        boolean[] visited = new boolean[target + 1];
        Queue<int[]> queue = new LinkedList<>();  // each element: [square, moves]
        queue.offer(new int[]{1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int square = curr[0];
            int moves = curr[1];
            if (square == target) {
                return moves;
            }
            for (int next = square + 1; next <= Math.min(square + 6, target); next++) {
                int[] pos = getPosition(next, n);
                int r = pos[0], c = pos[1];
                int dest = board[r][c] == -1 ? next : board[r][c];
                if (!visited[dest]) {
                    visited[dest] = true;
                    queue.offer(new int[]{dest, moves + 1});
                }
            }
        }
        return -1;  // not reachable
    }

    // Convert square number to (row, col)
    private int[] getPosition(int square, int n) {
        int quot = (square - 1) / n;
        int rem = (square - 1) % n;
        int row = n - 1 - quot;
        int col = (quot % 2 == 0) ? rem : n - 1 - rem;
        return new int[]{row, col};
    }

    public static void main(String[] args) {
        Solution909 solver = new Solution909();

        int[][] board1 = {
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}
        };
        System.out.println("Example 1: " + solver.snakesAndLadders(board1));  // Output: 4

        int[][] board2 = {
                {-1,-1},
                {-1,3}
        };
        System.out.println("Example 2: " + solver.snakesAndLadders(board2));  // Output: 1
    }
}
