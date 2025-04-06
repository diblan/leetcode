package leetcode;

public class Solution2560 {
    public int minCapability(int[] nums, int k) {
        int l = 0;
        int r = 0;
        for (int num : nums) {
            if (num > r) {
                r = num;
            } else if (num < l) {
                l = num;
            }
        }

        int guess;
        while (r - l > 1) {
            guess = (l + r) / 2;
            int robs = 0;
            int i = 0;
            while (i < nums.length && robs < k) {
                if (nums[i] <= guess) {
                    robs++;
                    i += 2;
                } else {
                    i++;
                }
            }

            if (robs < k) {
                l = guess;
            } else {
                r = guess;
            }
        }
        return r;
    }

    public static void main(String... args) {
        Solution2560 solution = new Solution2560();
        System.out.println(solution.minCapability(new int[] {2,3,5,9}, 2)); // 5
        System.out.println(solution.minCapability(new int[] {2,7,9,3,1}, 2)); // 5
    }
}
