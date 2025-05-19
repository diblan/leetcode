package leetcode;

public class Solution3024 {
    // This is faster at 0ms (100.00%)
    public String triangleType(int[] nums) {
        int a = nums[0], b = nums[1], c = nums[2];

        // Check triangle inequality directly
        if (a + b <= c || a + c <= b || b + c <= a) {
            return "none";
        }

        // Determine the type of triangle
        if (a == b && b == c) {
            return "equilateral";
        } else if (a == b || b == c || a == c) {
            return "isosceles";
        } else {
            return "scalene";
        }
    }

    // Sorting makes it slow at 1ms (34.56%)
    public String triangleType0(int[] nums) {
        // Sort the array for easier triangle inequality check
        java.util.Arrays.sort(nums);
        int a = nums[0], b = nums[1], c = nums[2];

        // Check triangle inequality
        if (a + b <= c) {
            return "none";
        }

        // Determine the type of triangle
        if (a == b && b == c) {
            return "equilateral";
        } else if (a == b || b == c || a == c) {
            return "isosceles";
        } else {
            return "scalene";
        }
    }

    public static void main(String[] args) {
        Solution3024 solution = new Solution3024();

        int[] example1 = {3, 3, 3};
        int[] example2 = {3, 4, 5};
        int[] example3 = {1, 2, 3}; // Not a triangle

        System.out.println("Example 1 Output: " + solution.triangleType(example1)); // "equilateral"
        System.out.println("Example 2 Output: " + solution.triangleType(example2)); // "scalene"
        System.out.println("Example 3 Output: " + solution.triangleType(example3)); // "none"
    }
}
