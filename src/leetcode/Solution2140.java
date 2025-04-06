package leetcode;

public class Solution2140 {
    public long mostPoints(int[][] questions) {
        long[] m = new long[questions.length];
        for (int i = questions.length - 1; i > -1; i--) {
            int take = i + questions[i][1] + 1;
            long t = questions[i][0] + (take < m.length ? m[take] : 0);
            int skip = i + 1;
            long s = skip < m.length ? m[skip] : 0;
            m[i] = Math.max(t, s);
        }
        return m[0];
    }

    public static void main(String... args) {
        Solution2140 solution = new Solution2140();
        System.out.println(solution.mostPoints(new int[][] {{3,2},{4,3},{4,4},{2,5}})); // 5
        System.out.println(solution.mostPoints(new int[][] {{1,1},{2,2},{3,3},{4,4},{5,5}})); // 7
    }
}
