package leetcode;

import java.util.Arrays;
import java.util.List;

public class Solution2780 {
    public int minimumIndex(List<Integer> nums) {
        // Boyer-Moore Majority Voting Algorithm
        int can = -1;
        int cnt = 0;
        for (Integer num : nums) {
            if (cnt == 0) {
                can = num;
                cnt = 1;
            } else {
                if (can == num) {
                    cnt++;
                } else {
                    cnt--;
                }
            }
        }

        cnt = 0;
        int index = -1;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == can) {
                cnt++;
            } else {
                cnt--;
            }
            if (cnt > 0) {
                if (index < 0) {
                    index = i;
                    cnt = 0;
                }
            }
        }

        if (cnt > 0) {
            return index;
        }
        return -1;
    }

    public static void main(String... args) {
        Solution2780 solution = new Solution2780();
        System.out.println(solution.minimumIndex(Arrays.asList(1,2,2,2))); // 2
        System.out.println(solution.minimumIndex(Arrays.asList(2,1,3,1,1,1,7,1,2,1))); // 4
        System.out.println(solution.minimumIndex(Arrays.asList(3,3,3,3,7,2,2))); // -1
    }
}
