package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// weird problem with weird tips and the end result using map only is 36ms (60.55%) so not even the fastest
// switched to only using map for extra large modulo numbers and that solution is 28ms (92.66%)
public class Solution2845 {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int[] count = new int[nums.size() + 1];
        int prefix = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) % modulo == k) {
                prefix++;
            }
            count[i + 1] = prefix;
        }

        long result = 0;
        if (modulo > 1000000) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i <= nums.size(); i++) {
                int ind = (count[i] + modulo - k) % modulo;
                result += map.getOrDefault(ind, 0);
                ind = count[i] % modulo;
                map.put(ind, map.getOrDefault(ind, 0) + 1);
            }
        } else {
            int[] map = new int[modulo];
            for (int i = 0; i <= nums.size(); i++) {
                int ind = (count[i] + modulo - k) % modulo;
                result += map[ind];
                map[count[i] % modulo]++;
            }
        }
        return result;
    }

    public static void main(String... args) {
        Solution2845 solution = new Solution2845();
        System.out.println(solution.countInterestingSubarrays(List.of(3,2,4), 2, 1)); // 3
        System.out.println(solution.countInterestingSubarrays(List.of(3,1,9,6), 3, 0)); // 2
    }
}
