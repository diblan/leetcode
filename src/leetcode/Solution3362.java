package leetcode;

import java.util.Arrays;

public class Solution3362 {
    // This is a really hard problem and does not deserve the "medium" tag.
    // My own solution (maxRemoval0) got really close, but I couldn't work out the edge case (example 4)
    // I don't really see how moving from left to right and only then sort on range is deterministic
    // I guess it avoids double overlapping left and right
    public int maxRemoval(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] required = nums.clone();
        int[] coverage = new int[n];

        // Step 1: Build coverage counts from all queries
        for (int[] q : queries) {
            for (int i = q[0]; i <= q[1]; i++) {
                coverage[i]++;
            }
        }

        // Step 2: Check if even solvable
        for (int i = 0; i < n; i++) {
            if (coverage[i] < required[i]) {
                return -1;
            }
        }

        // Step 3: Try to remove as many queries as possible safely
        int removable = 0;
        boolean[] removed = new boolean[m];

        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            boolean canRemove = true;
            for (int j = q[0]; j <= q[1]; j++) {
                if (coverage[j] == required[j]) {
                    canRemove = false;
                    break;
                }
            }
            if (canRemove) {
                for (int j = q[0]; j <= q[1]; j++) {
                    coverage[j]--;
                }
                removed[i] = true;
                removable++;
            }
        }

        return removable;
    }

    public int maxRemoval0(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] numsCopy = Arrays.copyOf(nums, n);

        // Step 1: Apply all queries
        for (int[] q : queries) {
            for (int i = q[0]; i <= q[1]; i++) {
                numsCopy[i]--;
            }
        }

        // Step 2: Check if it's possible at all
        for (int val : numsCopy) {
            if (val > 0) {
                return -1;
            }
        }

        // Step 3: Sort queries by (r - l) ascending
        Arrays.sort(queries, (a, b) -> {
            int lenA = a[1] - a[0];
            int lenB = b[1] - b[0];
            return Integer.compare(lenA, lenB);
        });

        // Step 4: Try removing queries
        int removable = 0;
        for (int[] q : queries) {
            boolean allNegative = true;
            for (int i = q[0]; i <= q[1]; i++) {
                if (numsCopy[i] >= 0) {
                    allNegative = false;
                    break;
                }
            }
            if (allNegative) {
                // We can "remove" this query — simulate undoing it
                for (int i = q[0]; i <= q[1]; i++) {
                    numsCopy[i]++;
                }
                removable++;
            }
        }

        return removable;
    }

    public static void main(String[] args) {
        Solution3362 sol = new Solution3362();

        int[] nums1 = {2, 0, 2};
        int[][] queries1 = {{0, 2}, {0, 2}, {1, 1}};
        System.out.println("Example 1: " + sol.maxRemoval(nums1, queries1)); // Expected: 1

        int[] nums2 = {1, 1, 1, 1};
        int[][] queries2 = {{1, 3}, {0, 2}, {1, 3}, {1, 2}};
        System.out.println("Example 2: " + sol.maxRemoval(nums2, queries2)); // Expected: 2

        int[] nums3 = {1, 2, 3, 4};
        int[][] queries3 = {{0, 3}};
        System.out.println("Example 3: " + sol.maxRemoval(nums3, queries3)); // Expected: -1

        int[] nums4 = {0, 0, 1, 1, 0, 0};
        int[][] queries4 = {{2, 3}, {0, 2}, {3, 5}};
        System.out.println("Example 4: " + sol.maxRemoval(nums4, queries4)); // Expected: 2
    }
}
