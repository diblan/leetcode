package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution2537 {

    public long countGood(int[] nums, int k) {
        long result = 0;
        int l = 0;
        int r = 0;
        Map<Integer, Integer> t = new HashMap<>(); // tally
        int c = 0; // count of Good
        while (r < nums.length) {
            while (r < nums.length && c < k) {
                int num = nums[r];
                int present = t.getOrDefault(num, 0);
                c += present;
                t.put(num, present + 1);
                r++;
            }

            while (c >= k) {
                result += nums.length - r + 1;
                int num = nums[l];
                int present = t.get(num) - 1;
                c -= present;
                t.put(num, present);
                l++;
            }
        }

        return result;
    }

    public static void main(String... args) {
        Solution2537 solution = new Solution2537();
        System.out.println(solution.countGood(new int[] {1,1,1,1,1}, 10)); // 1
        System.out.println(solution.countGood(new int[] {3,1,4,3,2,2,4}, 2)); // 4
    }
}
