package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int[] prev = new int[nums.length];
        Arrays.fill(prev, -1);
        int maxi = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int c = -1;
            for (int j = 0; j < i; j++) {
                if (num % nums[j] == 0) {
                    if (c == -1 || dp[j] > dp[c]) {
                        c = j;
                    }
                }
            }
            dp[i] = c == -1 ? 0 : dp[c] + 1;
            prev[i] = c;
            if (dp[i] > dp[maxi]) {
                maxi = i;
            }
        }
        List<Integer> result = new ArrayList<>();
        while (maxi != -1) {
            result.add(nums[maxi]);
            maxi = prev[maxi];
        }
        return result;
    }

    public List<Integer> largestDivisibleSubset1(int[] nums) { // waay too slow
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int num : nums) {
            List<List<Integer>> newLists = new ArrayList<>();
            for (List<Integer> l : list) {
                if (num % l.getLast() == 0) {
                    List<Integer> newList = new ArrayList<>(l);
                    newList.add(num);
                    newLists.add(newList);
                }
            }
            if (newLists.isEmpty()) {
                List<Integer> newList = new ArrayList<>();
                newList.add(num);
                list.add(newList);
            } else {
                list.addAll(newLists);
            }
        }
        return list.stream().reduce(new ArrayList<>(), (a, b) -> a.size() > b.size() ? a : b);
    }

    public static void main(String... args) {
        Solution368 solution = new Solution368();
        System.out.println(solution.largestDivisibleSubset(new int[] {1,2,3})); // [1, 2] OR [1, 3]
        System.out.println(solution.largestDivisibleSubset(new int[] {1,2,4,8})); // [1,2,4,8]
    }
}
