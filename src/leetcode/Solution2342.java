package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2342 {

    // Leetcode 2342
    public static int maximumSum(int[] nums) {
        Map<Integer, List<Integer>> sumMap = new HashMap<>();
        for (int num : nums) {
            int digitNumber = getDigitNumber(num);
            sumMap.computeIfAbsent(digitNumber, k -> new ArrayList<>()).add(num);
        }

        return sumMap.values().stream().filter(value -> value.size() > 1).map(value -> {
            int max1 = -1;
            int max2 = -1;
            for (Integer num : value) {
                if (num > max1) {
                    max2 = max1;
                    max1 = num;
                } else if (num > max2) {
                    max2 = num;
                }
            }
            return max1 + max2;
        }).max(Integer::compareTo).orElse(0);
    }

    public static int getDigitNumber(int num) {
        int result = 0;
        while (num != 0) {
            result += num % 10;
            num = num / 10;
        }
        return result;
    }

    public static void main(String[] args) {
        int result = maximumSum(new int[] {229,398,269,317,420,464,491,218,439,153,482,169,411,93,147,50,347,210,251,366,401});
        System.out.println(result);
    }
}
