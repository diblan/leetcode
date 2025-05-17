package leetcode;

public class Solution75 {
    // 0ms (100%)
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;

        // First pass: count 0s, 1s, and 2s
        for (int num : nums) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }

        // Second pass: overwrite array
        int index = 0;
        while (count0-- > 0) nums[index++] = 0;
        while (count1-- > 0) nums[index++] = 1;
        while (count2-- > 0) nums[index++] = 2;
    }

    public static void main(String[] args) {
        Solution75 sorter = new Solution75();

        int[] nums1 = {2, 0, 2, 1, 1, 0};
        sorter.sortColors(nums1);
        System.out.println("Output: " + java.util.Arrays.toString(nums1));

        int[] nums2 = {2, 0, 1};
        sorter.sortColors(nums2);
        System.out.println("Output: " + java.util.Arrays.toString(nums2));
    }
}
