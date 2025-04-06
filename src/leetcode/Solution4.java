package leetcode;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution4 {
    // slow but easy version
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2)).sorted().toArray();
        if (merged.length % 2 == 1) {
            return merged[merged.length / 2];
        } else {
            return (merged[merged.length / 2] + merged[(merged.length / 2) - 1]) / 2D;
        }
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // [1,2,3,4,5] [6,7,8,9]
        int al = 0;
        int au = nums1.length - 1;
        int bl = 0;
        int bu = nums2.length - 1;
        int a = nums1[nums1.length / 2];
        int b = nums2[nums2.length / 2];
        if (a < b) {

        }
        return 0.0;
    }

    public static void main(String... args) {
        Solution4 solution = new Solution4();
        System.out.println(solution.findMedianSortedArrays(new int[] {1,3}, new int[] {2}));
        System.out.println(solution.findMedianSortedArrays(new int[] {1,2}, new int[] {3,4}));
    }
}
