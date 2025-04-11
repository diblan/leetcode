package leetcode;

// TODO: review, make your own solution, try beat the 3ms
public class Solution2999 {
	private String suffix;
	private String target;
	private Long[] f;
	private int limit;

	public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
		this.suffix = s;
		this.limit = limit;
		target = String.valueOf(start - 1);
		f = new Long[20];
		long a = dfs(0, true);
		target = String.valueOf(finish);
		f = new Long[20];
		long b = dfs(0, true);
		return b - a;
	}

	private long dfs(int pos, boolean lim) {
		if (target.length() < suffix.length()) {
			return 0;
		}
		if (!lim && f[pos] != null) {
			return f[pos];
		}
		if (target.length() - pos == suffix.length()) {
			return lim ? (suffix.compareTo(target.substring(pos)) <= 0 ? 1 : 0) : 1;
		}
		int up = lim ? target.charAt(pos) - '0' : 9;
		up = Math.min(up, limit); // WOW SOMEHOW I CAN'T SIMPLIFY THIS
		long ans = 0;
		for (int i = 0; i <= up; ++i) {
			ans += dfs(pos + 1, lim && i == (target.charAt(pos) - '0'));
		}
		if (!lim) {
			f[pos] = ans;
		}
		return ans;
	}
	int[][] dp = new int[0][];
	public long numberOfPowerfulInt2(long start, long finish, int limit, String s) {
		for (int i = 0; i <= limit; i++) {

		}
		return 0;
	}

	public static void main(String... args) {
		Solution2999 solution = new Solution2999();
		System.out.println(solution.numberOfPowerfulInt(1, 6000, 4, "124")); // 5
		System.out.println(solution.numberOfPowerfulInt(15, 215, 6, "10")); // 2
		System.out.println(solution.numberOfPowerfulInt(1000, 2000, 4, "3000")); // 0
	}
}
