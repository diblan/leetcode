package leetcode;

public class Solution2579 {
    public long coloredCells(int n) {
        return n * ((n - 1L) * 2) + 1;
    }

    public static void main(String[] args) {
        Solution2579 solution = new Solution2579();
        System.out.println(solution.coloredCells(1)); // 1 * 0 + 1
        System.out.println(solution.coloredCells(2)); // 2 * 2 + 1
        System.out.println(solution.coloredCells(3)); // 3 * 4 + 1
        System.out.println(solution.coloredCells(4)); // 4 * 6 + 1
        System.out.println(solution.coloredCells(5)); // 5 * 8 + 1
        System.out.println(solution.coloredCells(6)); // 6 * 10 + 1
        System.out.println(solution.coloredCells(7)); // 7 * 12 + 1
    }
}
