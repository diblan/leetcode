package leetcode;

public class Solution2145 {
    // 4ms Beats 92.06%
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long diff = differences[0];
        long min = diff;
        long max = diff;
        for (int d : differences) {
            diff += d;
            min = Math.min(min, diff);
            max = Math.max(max, diff);
        }
        long range = (upper - lower) - (max - min) + 1;
        return Math.max((int) range, 0);
    }

    // 6ms Beats 15.87%
    public int numberOfArrays0(int[] differences, int lower, int upper) {
        int diff = differences[0];
        int min = diff;
        int max = diff;
        for (int d : differences) {
            if (d > 0) {
                diff = diff + d >= diff ? diff + d : Integer.MAX_VALUE;
            } else {
                diff = diff + d <= diff ? diff + d : Integer.MIN_VALUE;
            }
            min = Math.min(min, diff);
            max = Math.max(max, diff);
        }
        int range = (upper - lower) - (max - min) + 1;
        return Math.max(range, 0);
    }

    public static void main(String... args) {
        Solution2145 solution = new Solution2145();
        System.out.println(solution.numberOfArrays(new int[] {1,-3,4}, 1, 6)); // 2
        System.out.println(solution.numberOfArrays(new int[] {3,-4,5,1,-2}, -4, 5)); // 4
        System.out.println(solution.numberOfArrays(new int[] {4,-7,2}, 3, 6)); // 0
    }
}
