package leetcode;

public class Solution3355 {
    // It took ChatGPT three tries but here we are at 3ms (79.52%)
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];

        // Count how many times each index can potentially be decremented
        for (int[] query : queries) {
            int l = query[0], r = query[1];
            diff[l] += 1;
            if (r + 1 < n) diff[r + 1] -= 1;
        }

        // Compute prefix sum to get total opportunities to decrement at each index
        int curr = 0;
        for (int i = 0; i < n; i++) {
            curr += diff[i];
            if (nums[i] > curr) return false; // Not enough decrements allowed
        }

        return true;
    }

    public static void main(String[] args) {
        Solution3355 sol = new Solution3355();

        int[] nums1 = {1, 0, 1};
        int[][] queries1 = {{0, 2}};
        System.out.println("Example 1: " + sol.isZeroArray(nums1, queries1)); // true

        int[] nums2 = {4, 3, 2, 1};
        int[][] queries2 = {{1, 3}, {0, 2}};
        System.out.println("Example 2: " + sol.isZeroArray(nums2, queries2)); // false
    }
}
