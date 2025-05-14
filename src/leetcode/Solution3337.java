package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution3337 {

    private static final int MOD = 1_000_000_007;
    private static final int SIZE = 26;

    // Impressive, first time ChatGPT solves a hard problem on the first try at 113ms (67.57%)
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        // Build the transformation matrix
        long[][] matrix = new long[SIZE][SIZE];
        for (int c = 0; c < SIZE; c++) {
            for (int i = 1; i <= nums.get(c); i++) {
                int to = (c + i) % SIZE;
                matrix[c][to] = (matrix[c][to] + 1) % MOD;
            }
        }

        // Raise matrix to the power of t
        long[][] powered = matrixPower(matrix, t);

        // Calculate the final total length
        long total = 0;
        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            for (int j = 0; j < SIZE; j++) {
                total = (total + powered[idx][j]) % MOD;
            }
        }

        return (int) total;
    }

    private static long[][] matrixMultiply(long[][] A, long[][] B) {
        long[][] result = new long[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int k = 0; k < SIZE; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < SIZE; j++) {
                    result[i][j] = (result[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return result;
    }

    private static long[][] matrixPower(long[][] base, int exp) {
        long[][] result = new long[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) result[i][i] = 1; // identity matrix

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = matrixMultiply(result, base);
            }
            base = matrixMultiply(base, base);
            exp >>= 1;
        }

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        Solution3337 solution = new Solution3337();
        String s1 = "abcyy";
        int t1 = 2;
        List<Integer> nums1 = new ArrayList<>(Collections.nCopies(26, 1));
        nums1.set(25, 2);
        System.out.println(solution.lengthAfterTransformations(s1, t1, nums1)); // Output: 7

        String s2 = "azbk";
        int t2 = 1;
        List<Integer> nums2 = new ArrayList<>(Collections.nCopies(26, 2));
        System.out.println(solution.lengthAfterTransformations(s2, t2, nums2)); // Output: 8
    }
}
