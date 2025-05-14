package leetcode;

public class Solution3335 {
    private static final int MOD = 1_000_000_007;

    // Solved by ChatGPT-4o with a Runtime of 125ms (56.96%)
    // The Runtime graph is very varied but there seems to be a faster solution around 40ms
    public int finalStringLength(String s, int t) {
        // dp[c][i] = length of resulting string if starting from character 'c' with i transformations
        int[][] dp = new int[26][t + 1];

        // Base case: 0 transformations => each character has length 1
        for (int c = 0; c < 26; c++) {
            dp[c][0] = 1;
        }

        // Fill the DP table
        for (int i = 1; i <= t; i++) {
            for (int c = 0; c < 26; c++) {
                if (c == 25) { // 'z'
                    dp[c][i] = (dp[0][i - 1] + dp[1][i - 1]) % MOD;
                } else {
                    dp[c][i] = dp[c + 1][i - 1];
                }
            }
        }

        // Sum the contributions of each character in the input string
        long result = 0;
        for (char ch : s.toCharArray()) {
            int index = ch - 'a';
            result = (result + dp[index][t]) % MOD;
        }

        return (int) result;
    }

    // Example usage
    public static void main(String[] args) {
        Solution3335 solution = new Solution3335();
        System.out.println(solution.finalStringLength("abcyy", 2));  // Output: 7
        System.out.println(solution.finalStringLength("azbk", 1));   // Output: 5
    }
}
