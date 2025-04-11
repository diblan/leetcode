package leetcode;

public class Solution416 {
	public boolean canPartition(int[] nums) {
		int tot = 0;
		for (int num : nums) {
			tot += num;
		}
		if (tot % 2 == 1) {
			return false;
		}
		int aim = tot / 2;
		boolean[] dp = new boolean[aim + 1];
		dp[0] = true;
		for (int num : nums) {
			for (int i = aim - num; i >= 0; i--) {
				if (dp[i]) {
					dp[i + num] = true;
				}
			}
		}
		return dp[aim];
	}

	public static void main(String... args) {
		Solution416 solution = new Solution416();
		System.out.println(solution.canPartition(new int[] {1,5,11,5})); // true
		System.out.println(solution.canPartition(new int[] {1,2,3,5})); // false
		System.out.println(solution.canPartition(new int[] {3,3,3,4,5})); // true
	}
}
