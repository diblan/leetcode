package leetcode;

import java.util.Arrays;

public class Solution2551 {
    public long putMarbles(int[] weights, int k) {
        int[] splits = new int[weights.length - 1];
        for (int i = 0; i < splits.length; i++) {
            splits[i] = weights[i] + weights[i + 1];
        }

        Arrays.sort(splits);

        long max = 0;
        for (int i = 0; i < k - 1; i++) {
            max += splits[splits.length - i - 1];
        }
        long min = 0;
        for (int i = 0; i < k - 1; i++) {
            min += splits[i];
        }
        return max - min;
    }

    public static void main(String... args) {
        Solution2551 solution = new Solution2551();
        System.out.println(solution.putMarbles(new int[] {1,3,5,1}, 2)); // 4
        System.out.println(solution.putMarbles(new int[] {1, 3}, 2)); // 0
    }
}
