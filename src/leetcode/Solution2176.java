package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2176 {
    // this is the 3ms solution, I assume the size of the Problem makes this solution faster
    public int countPairs(int[] nums, int k) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    // Very slow, everyone else does it in 3ms, this solution does it in 5ms (5.86%)
    public int countPairs0(int[] nums, int k) {
        int result = 0;
        List<Integer>[] indexes = new List[100];
        Arrays.setAll(indexes, i -> new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            indexes[nums[i]].add(i);
        }

        for (List<Integer> l : indexes) {
            for (int i = 0; i < l.size(); i++) {
                for (int j = i + 1; j < l.size(); j++) {
                    if ((l.get(i) * l.get(j) % k == 0)) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String... args) {
        Solution2176 solution = new Solution2176();
        System.out.println(solution.countPairs(new int[] {3,1,2,2,2,1,3}, 2)); // 4
        System.out.println(solution.countPairs(new int[] {1,2,3,4}, 1)); // 0
    }
}
