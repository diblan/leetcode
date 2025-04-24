package leetcode;

import java.util.HashSet;
import java.util.Set;

public class Solution2799 {
    public int countCompleteSubarrays(int[] nums) {
        int result = 0;
        Set<Integer> dist = new HashSet<>();
        for (int num : nums) {
            dist.add(num);
        }
        int k = dist.size();
        dist.clear();

        int l = -1;
        int r = -1;
        int[] c = new int[2001]; // count tally array
        while (dist.size() < k) {
            dist.add(nums[++r]);
            c[nums[r]]++;
        }
        do {
            do {
                result += nums.length - r;
                c[nums[++l]]--;
            } while (c[nums[l]] != 0);
            while (++r < nums.length) {
                c[nums[r]]++;
                if (nums[l] == nums[r]) {
                    break;
                }
            }
        } while (r < nums.length);
        return result;
    }

    public static void main(String... args) {
        Solution2799 solution = new Solution2799();
        System.out.println(solution.countCompleteSubarrays(new int[] {1,3,1,2,2})); // 4
        System.out.println(solution.countCompleteSubarrays(new int[] {5,5,5,5})); // 10
    }
}
