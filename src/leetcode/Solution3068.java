package leetcode;

public class Solution3068 {
    // Runtime of 2ms (76.32%)
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long baseSum = 0;
        long totalGain = 0;
        int minPositiveGain = Integer.MAX_VALUE;
        int countPositiveGain = 0;

        for (int num : nums) {
            int xorValue = num ^ k;
            baseSum += num;
            int gain = xorValue - num;
            if (gain > 0) {
                totalGain += gain;
                countPositiveGain++;
                minPositiveGain = Math.min(minPositiveGain, gain);
            } else {
                minPositiveGain = Math.min(minPositiveGain, -gain);
            }
        }

        if (countPositiveGain % 2 == 0) {
            return baseSum + totalGain;
        } else {
            return baseSum + totalGain - minPositiveGain;
        }
    }

    public static void main(String[] args) {
        Solution3068 sol = new Solution3068();

        int[] nums1 = {1, 2, 1};
        int k1 = 3;
        int[][] edges1 = {{0, 1}, {0, 2}};
        System.out.println("Example 1: " + sol.maximumValueSum(nums1, k1, edges1)); // 6

        int[] nums2 = {2, 3};
        int k2 = 7;
        int[][] edges2 = {{0, 1}};
        System.out.println("Example 2: " + sol.maximumValueSum(nums2, k2, edges2)); // 9

        int[] nums3 = {7, 7, 7, 7, 7, 7};
        int k3 = 3;
        int[][] edges3 = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}};
        System.out.println("Example 3: " + sol.maximumValueSum(nums3, k3, edges3)); // 42
    }
}
