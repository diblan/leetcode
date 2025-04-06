package leetcode;

public class Solution2874 {
    public long maximumTripletValue(int[] nums) {
        int max = nums[0];
        int diff = nums[0] - nums[1];
        long result = (long) diff * nums[2];

        int m = 1;
        int d = 2;
        int t = 3;
        while (t < nums.length) {
            max = Math.max(nums[m], max);
            diff = Math.max(max - nums[d], diff);
            result = Math.max((long) diff * nums[t], result);
            m++;
            d++;
            t++;
        }
        return result > 0 ? result : 0;
    }

    public static void main(String... args) {
        Solution2874 solution = new Solution2874();
        System.out.println(solution.maximumTripletValue(new int[] {12,6,1,2,7})); // 77
        System.out.println(solution.maximumTripletValue(new int[] {1,10,3,4,19})); // 133
        System.out.println(solution.maximumTripletValue(new int[] {1,2,3})); // 0
    }
}
