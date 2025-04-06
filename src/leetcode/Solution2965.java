package leetcode;

import java.util.Arrays;

public class Solution2965 {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int[] tally = new int[grid.length * grid.length];
        for (int[] column : grid) {
            for (int row : column) {
                tally[row - 1]++;
            }
        }

        int[] ans = new int[2];
        for (int i = 0; i < tally.length; i++) {
            if (tally[i] == 0) {
                ans[1] = i + 1;
            } else if (tally[i] == 2) {
                ans[0] = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution2965 solution = new Solution2965();
        System.out.println(Arrays.toString(solution.findMissingAndRepeatedValues(new int[][]{{1, 3}, {2, 2}}))); // [2, 4]
        System.out.println(Arrays.toString(solution.findMissingAndRepeatedValues(new int[][]{{9,1,7},{8,9,2},{3,4,6}}))); // [9, 5]
    }
}
