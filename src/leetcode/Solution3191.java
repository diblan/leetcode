package leetcode;

public class Solution3191 {
    public int minOperations(int[] nums) {
        int result = 0;
        int edge = nums.length - 2;
        for (int i = 0; i < edge; i++) {
            if (nums[i] == 0) {
                result++;
                nums[i] = 1 - nums[i];
                nums[i + 1] = 1 - nums[i + 1];
                nums[i + 2] = 1 - nums[i + 2];
            }
        }

        if (nums[nums.length - 1] == 0 || nums[nums.length - 2] == 0) {
            return -1;
        }
        return result;
    }

    public static void main(String... args) {
        Solution3191 solution = new Solution3191();
        System.out.println(solution.minOperations(new int[] {0,1,1,1,0,0})); // 3
        System.out.println(solution.minOperations(new int[] {0,1,1,1})); // -1
    }
}
