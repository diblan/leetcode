package leetcode;

public class Solution2843 {
	public int countSymmetricIntegers(int low, int high) {
		int result = 0;
		for (int i = low; i <= high; i++) {
			String num = String.valueOf(i);
			int l = 0;
			for (int j = 0; j < num.length() / 2; j++) {
				l += num.charAt(j);
			}
			int r = 0;
			for (int j = num.length() / 2; j < num.length(); j++) {
				r += num.charAt(j);
			}
			if (l == r) {
				result++;
			}
		}
		return result;
	}

	public static void main(String... args) {
		Solution2843 solution = new Solution2843();
		System.out.println(solution.countSymmetricIntegers(1, 100)); // 9
		System.out.println(solution.countSymmetricIntegers(1200, 1230)); // 4
	}
}
