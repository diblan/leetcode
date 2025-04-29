package leetcode;

import java.util.Arrays;

public class Solution2962 {
    // edge case of not having enough max elements was not necessary, now it's fast at 5ms (84.53%)
    public long countSubarrays(int[] nums, int k) {
        int max = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }

        long result = 0;
        int c = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (c < k) {
                if (j < nums.length) {
                    if (nums[j++] == max) {
                        c++;
                    }
                } else {
                    return result;
                }
            }
            result += nums.length - j + 1;
            if (nums[i] == max) {
                c--;
            }
        }
        return result;
    }

    // using streams is a lot slower at 10ms (27.36%)
    public long countSubarrays1(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();

        long result = 0;
        int c = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (c < k) {
                if (j < nums.length) {
                    if (nums[j++] == max) {
                        c++;
                    }
                } else {
                    return result;
                }
            }
            result += nums.length - j + 1;
            if (nums[i] == max) {
                c--;
            }
        }
        return result;
    }

    // slightly slower than most other solutions at 7ms (30.94%)
    public long countSubarrays0(int[] nums, int k) {
        int max = 0;
        int maxC = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
                maxC = 1;
            } else if (num == max) {
                maxC++;
            }
        }

        if (maxC < k)
            return 0;

        long result = 0;
        int c = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (c < k) {
                if (j < nums.length) {
                    if (nums[j++] == max) {
                        c++;
                    }
                } else {
                    return result;
                }
            }
            result += nums.length - j + 1;
            if (nums[i] == max) {
                c--;
            }
        }
        return result;
    }

    public static void main(String... args) {
        Solution2962 solution = new Solution2962();
//        System.out.println(solution.countSubarrays(new int[] {1,3,2,3,3}, 2)); // 6
//        System.out.println(solution.countSubarrays(new int[] {1,4,2,1}, 3)); // 0
        System.out.println(solution.countSubarrays(new int[] {165,135,165,46,126,165,73,165,165,155,150,165,40,38,165,145,137,106,10}, 7)); // 5
    }
}
