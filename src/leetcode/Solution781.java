package leetcode;

public class Solution781 {
    public int numRabbits(int[] answers) {
        int result = 0;
        int[] c = new int[1000];
        for (int ans : answers) {
            c[ans]++;
        }
        for (int i = 0; i < c.length; i++) {
            if (c[i] > 0) {
                int groups = c[i] / (i + 1);
                int mod = c[i] % (i + 1);
                if (mod > 0) {
                    groups++;
                }
                result += groups * (i + 1);
            }
        }
        return result;
    }

    public static void main(String... args) {
        Solution781 solution = new Solution781();
        System.out.println(solution.numRabbits(new int[] {1,1,2})); // 5
        System.out.println(solution.numRabbits(new int[] {10,10,10})); // 11
        System.out.println(solution.numRabbits(new int[] {1,0,1,0,0})); // 5
    }
}
