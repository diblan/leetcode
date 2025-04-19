package leetcode;

import java.util.Arrays;

public class Solution2563 {
    public long countFairPairs(int[] nums, int lower, int upper) {
        long result = 0;
        Arrays.sort(nums);
        int l = nums.length - 1, u = l;
        for (int i = 0; i < u; i++) {
            while (l > i && nums[i] + nums[l] >= lower) {
                l--;
            }
            while (u > i && nums[i] + nums[u] > upper) {
                u--;
            }
            result += u - (Math.max(l, i));
        }
        return result;
    }

    public static void main(String... args) {
        Solution2563 solution = new Solution2563();
        System.out.println(solution.countFairPairs(new int[] {0,1,7,4,4,5}, 3, 6)); // 6
        System.out.println(solution.countFairPairs(new int[] {1,7,9,2,5}, 11, 11)); // 1
        System.out.println(solution.countFairPairs(new int[] {-1,-1,0,0}, 1, 1)); // 0 (probably)
        System.out.println(solution.countFairPairs(new int[] {-2,-6,4,0,-1000000000,-1000000000,-1000000000,-1000000000}, -15, 15)); // 6
        System.out.println(solution.countFairPairs(new int[] {0,0,0,0,0,0}, 0, 0)); // 15
    }
}
