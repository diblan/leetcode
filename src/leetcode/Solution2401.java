package leetcode;

public class Solution2401 {
    public int longestNiceSubarray(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int max = 1;
        int l = 0;
        int r = 1;
        while (r < nums.length) {
            for (int i = r - 1; i >= l; i--) {
                if ((nums[i] & nums[r]) != 0) {
                    l = i + 1;
                    break;
                }
            }
            if (r - l + 1 > max) {
                max = r - l + 1;
                if (max == 30) {
                    return 30;
                }
            }
            r++;
        }
        return max;
    }

    public static void main(String... args) {
        Solution2401 solution = new Solution2401();
        System.out.println(solution.longestNiceSubarray(new int[] {1,3,8,48,10})); // 3
        System.out.println(solution.longestNiceSubarray(new int[] {3,1,5,11,13})); // 1
    }
}
