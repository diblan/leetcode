package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution3066 {
    public int minOperations(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int i = 0;
        int operations = 0;
        while ((i < nums.length && nums[i] < k) || (!queue.isEmpty() && queue.peek() < k)) {
            operations++;
            int min = queue.isEmpty() ? nums[i++] : i < nums.length ? nums[i] < queue.peek() ? nums[i++] : queue.poll() : queue.poll();
            int max = queue.isEmpty() ? nums[i++] : i < nums.length ? nums[i] < queue.peek() ? nums[i++] : queue.poll() : queue.poll();
            int merge = min * 2 + max;
            if (merge < 0) {
                merge = Integer.MAX_VALUE;
            }
            queue.add(merge);
        }
        return operations;
    }

    public static void main(String[] args) {
        Solution3066 solution = new Solution3066();
        System.out.println(solution.minOperations(new int[] {999999999,999999999,999999999}, 1000000000));
        System.out.println(solution.minOperations(new int[] {69,89,57,31,84,97,50,38,91,86}, 91));
        System.out.println(solution.minOperations(new int[] {2,11,10,1,3}, 10));
        System.out.println(solution.minOperations(new int[] {1,1,2,4,9}, 20));
    }
}
