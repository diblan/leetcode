package leetcode;

public class Solution2873 {
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

    public long maximumTripletValue0(int[] nums) {
        int[] max = new int[nums.length];
        max[0] = 0;

        int[] diff = new int[nums.length];
        diff[1] = 1;

        int[] trip = new int[nums.length];
        trip[2] = 2;

        int m = 1;
        int d = 2;
        int t = 3;
        while (t < nums.length) {
            max[m] = nums[m] > nums[max[m - 1]] ? m : max[m - 1];
            diff[d] = nums[max[m]] - nums[d] > nums[max[m - 1]] - nums[diff[d - 1]] ? d : diff[d - 1];
            trip[t] = (nums[max[diff[d] - 1]] - nums[diff[d]]) * nums[t] > (nums[max[diff[d] - 1]] - nums[diff[d - 1]]) * nums[trip[t - 1]] ? t : trip[t - 1];
            m++;
            d++;
            t++;
        }
        t = trip[t - 1];
        d = diff[t - 1];
        m = max[d - 1];
        long result = (nums[m] - nums[diff[d]]) * (long) nums[t];
        return result > 0 ? result : 0;
    }

    public static void main(String... args) {
        Solution2873 solution = new Solution2873();
        System.out.println(solution.maximumTripletValue(new int[] {12,6,1,2,7})); // 77
        System.out.println(solution.maximumTripletValue(new int[] {1,10,3,4,19})); // 133
        System.out.println(solution.maximumTripletValue(new int[] {1,2,3})); // 0
        System.out.println(solution.maximumTripletValue(new int[] {8,6,3,13,2,12,19,5,19,6,10,11,9})); // 266 (190)
        System.out.println(solution.maximumTripletValue(new int[] {6,11,12,12,7,9,2,11,12,4,19,14,16,8,16})); // 190 (220)

    }
}
