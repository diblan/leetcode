package leetcode;

public class Solution1780 {
    public boolean checkPowersOfThree(int n) {
        int base = 3;
        // Let's note that the maximum power of 3 you'll use in your soln is 3^16
        for (int i = 1; i < 16; i++) {
            base*=3;
        }
        for (int i = 16; i > -1; i--) {
            // The number can not be represented as a sum of powers of 3 if it's ternary presentation has a 2 in it
            if (n - (2 * base) >= 0) {
                return false;
            }
            if (n - base >= 0) {
                n = n - base;
            }
            base /= 3;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution1780 solution = new Solution1780();
        System.out.println(solution.checkPowersOfThree(12));
        System.out.println(solution.checkPowersOfThree(91));
        System.out.println(solution.checkPowersOfThree(21));
    }
}
