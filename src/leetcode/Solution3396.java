package leetcode;

import java.util.HashSet;
import java.util.Set;

public class Solution3396 {
	public int minimumOperations(int[] nums) {
		boolean[] dupe = new boolean[101];
		for (int i = nums.length - 1; i > -1; i--) {
			if (dupe[nums[i]]) {
				return i / 3 + 1;
			}
			dupe[nums[i]] = true;
		}
		return 0;
	}

	public int minimumOperations1(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int i = nums.length - 1; i > -1; i--) {
			if (set.contains(nums[i])) {
				return i / 3 + 1;
			}
			set.add(nums[i]);
		}
		return 0;
	}

	public static void main(String... args) {
		Solution3396 solution = new Solution3396();
		System.out.println(solution.minimumOperations(new int[] {1,2,3,4,2,3,3,5,7})); //2
		System.out.println(solution.minimumOperations(new int[] {4,5,6,4,4})); //2
		System.out.println(solution.minimumOperations(new int[] {6,7,8,9})); //0
	}
}
