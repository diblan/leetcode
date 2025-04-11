package leetcode;

public class Solution3375 {
	public int minOperations(int[] nums, int k) {
		boolean[] dupe = new boolean[101];
		int c = 0;
		for (int num : nums) {
			if (num < k) {
				return -1;
			}
			if (num > k && !dupe[num]) {
				dupe[num] = true;
				c++;
			}
		}
		return c;
	}

	public static void main(String... args) {
		Solution3375 solution = new Solution3375();
		System.out.println(solution.minOperations(new int[] {5,2,5,4,5}, 2)); // 2
		System.out.println(solution.minOperations(new int[] {2,1,2}, 2)); // -1
		System.out.println(solution.minOperations(new int[] {9,7,5,3}, 1)); // 4
	}
}
