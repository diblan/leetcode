package leetcode;

import java.util.Arrays;

public class Solution2033 {
    public int minOperations(int[][] grid, int x) {
        int result = 0;
        int[] g = new int[grid.length * grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                g[i + (j * grid.length)] = grid[i][j];
            }
        }

        Arrays.sort(g);

        int m = g[g.length / 2]; // median

        for (int i : g) {
            int diff = Math.abs(m - i);
            if (diff % x != 0) {
                return -1;
            }
            result += diff / x;
        }

        return result;
    }

    public static void main(String... args) {
        Solution2033 solution = new Solution2033();
        System.out.println(solution.minOperations(new int[][] {{2,4},{6,8}}, 2)); // 4
        System.out.println(solution.minOperations(new int[][] {{1,5},{2,3}}, 1)); // 5
        System.out.println(solution.minOperations(new int[][] {{1,2},{3,4}}, 2)); // -1
    }
}
