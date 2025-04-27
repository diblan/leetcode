package leetcode;

public class Solution3392 {
    public int countSubarrays(int[] nums) {
        int result = 0;
        for (int i = 2; i < nums.length; i++) {
            if ((nums[i] + nums[i - 2]) * 2 == nums[i - 1]) {
                result++;
            }
        }
        return result;
    }

    public static void main(String... args) {
        Solution3392 solution = new Solution3392();
        System.out.println(solution.countSubarrays(new int[] {1,2,1,4,1})); // 1
        System.out.println(solution.countSubarrays(new int[] {1,1,1})); // 0
    }
}
