package leetcode;

public class Solution2894 {
    // Easy: Runtime of 1ms (74.69%)
    public int differenceOfSums(int n, int m) {
        int totalSum = n * (n + 1) / 2;

        int divisibleCount = n / m;
        int lastDivisible = divisibleCount * m;
        int divisibleSum = divisibleCount * (m + lastDivisible) / 2;

        return totalSum - 2 * divisibleSum;
    }

    public static void main(String[] args) {
        Solution2894 solution = new Solution2894();

        // Example 1
        int result1 = solution.differenceOfSums(10, 3);
        System.out.println("Example 1: " + result1); // Expected: 19

        // Example 2
        int result2 = solution.differenceOfSums(5, 6);
        System.out.println("Example 2: " + result2); // Expected: 15

        // Example 3
        int result3 = solution.differenceOfSums(5, 1);
        System.out.println("Example 3: " + result3); // Expected: -15
    }
}

