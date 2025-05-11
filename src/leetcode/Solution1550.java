package leetcode;

public class Solution1550 {
    // Fastest solution at 0ms (100.00%)
    // Made with ChatGPT
    // Conclusion: ChatGPT can solve easy LeetCode problems but medium requires intervention
    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int num : arr) {
            if (num % 2 != 0) {
                count++;
                if (count == 3) return true;
            } else {
                count = 0;
            }
        }
        return false;
    }

    public static void main(String... args) {
        Solution1550 solution = new Solution1550();
        System.out.println(solution.threeConsecutiveOdds(new int[] {2,6,4,1})); // false
        System.out.println(solution.threeConsecutiveOdds(new int[] {1,2,34,3,4,5,7,23,12})); // true
    }
}
