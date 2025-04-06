package leetcode;

public class Solution2206 {
    public boolean divideArray(int[] nums) {
        int[] count = new int[500];
        for (int num : nums) {
            count[num]++;
        }
        for (int c : count) {
            if (c % 2 > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String... args) {
        Solution2206 solution = new Solution2206();
        System.out.println(solution.divideArray(new int[] {3,2,3,2,2,2})); // true
        System.out.println(solution.divideArray(new int[] {1,2,3,4})); // false
    }
}
