package leetcode;

public class Solution790 {
    public static long N = 1000000007;

    // did not like this problem
    // it was work out the results for 4 and 5 and then spot the pattern with dp
    // got the fastest solution at 0ms (100.00%) even though someone in the comments said there's an O(1) solution
    public int numTilings(int n) {
        if (n < 3) return n;
        long[] dp = new long[n];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n; i++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 3]) % N;
        }
        return (int) ((dp[n - 1] * 2 + dp[n - 3]) % N);
    }

    public static void main(String... args) {
        Solution790 solution = new Solution790();
        System.out.println(solution.numTilings(1)); // 1
        System.out.println(solution.numTilings(2)); // 2
        System.out.println(solution.numTilings(3)); // 5
        System.out.println(solution.numTilings(4)); // 11
        System.out.println(solution.numTilings(4)); // 24
    }
}
