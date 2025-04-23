package leetcode;

public class Solution1399 {
    public int countLargestGroup(int n) {
        int[] c = new int[37];
        for (int i = 1; i <= n; i++) {
            c[sumDigits(i)]++;
        }
        int max = 0;
        int result = 0;
        for (int i : c) {
            if (i > max) {
                max = i;
                result = 1;
            } else if (i == max) {
                result++;
            }

        }
        return result;
    }

    public static int sumDigits(int number) {
        int sum = 0;
        number = Math.abs(number); // Handle negative numbers

        while (number > 0) {
            sum += number % 10; // Get the last digit
            number /= 10;       // Remove the last digit
        }

        return sum;
    }

    public static void main(String... args) {
        Solution1399 solution = new Solution1399();
        System.out.println(solution.countLargestGroup(13)); // 4
        System.out.println(solution.countLargestGroup(2)); // 2
    }
}
