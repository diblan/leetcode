package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Solution2161 {
    public int[] pivotArray(int[] nums, int pivot) {
        int resultCount = 0;
        ArrayDeque<Integer> more = new ArrayDeque<>(nums.length / 2);
        int equalCount = 0;

        for (Integer num : nums) {
            if (num < pivot) {
                nums[resultCount++] = num;
            } else if (num > pivot) {
                more.add(num);
            } else {
                equalCount++;
            }
        }
        for (int i = 0; i < equalCount; i++) {
            nums[resultCount++] = pivot;
        }
        for (Integer num : more) {
            nums[resultCount++] = num;
        }

        return nums;
    }


    public int[] pivotArray2(int[] nums, int pivot) {
        int resultCount = 0;
        int[] stack = new int[nums.length];
        int stackCount = 0;
        int equalCount = 0;

        for (Integer num : nums) {
            if (num < pivot) {
                nums[resultCount++] = num;
            } else if (num > pivot) {
                stack[stackCount++] = num;
            } else {
                equalCount++;
            }
        }
        for (int i = 0; i < equalCount; i++) {
            nums[resultCount++] = pivot;
        }
        for (int i = 0; i < stackCount; i++) {
            nums[resultCount++] = stack[i];
        }

        return nums;
    }

    public static void main(String[] args) {
        Solution2161 solution = new Solution2161();
        System.out.println(Arrays.toString(solution.pivotArray2(new int[]{9, 12, 5, 10, 14, 3, 10}, 10)));
        System.out.println(Arrays.toString(solution.pivotArray2(new int[]{-3,4,3,2}, 2)));
    }
}
