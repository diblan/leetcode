package leetcode;

public class Solution1295 {
    public int findNumbers(int[] nums) {
        int result = 0;
        for (int num : nums) {
            if ((((int) Math.log10(num) + 1) & 1) == 0) {
                result++;
            }
        }
        return result;
    }

    public static void main(String... args) {
        Solution1295 solution = new Solution1295();
        System.out.println(solution.findNumbers(new int[] {12,345,2,6,7896})); // 2
        System.out.println(solution.findNumbers(new int[] {555,901,482,1771})); // 1
    }
}
