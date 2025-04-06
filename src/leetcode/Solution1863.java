package leetcode;

public class Solution1863 {
    public int subsetXORSum(int[] nums) {
        int tot = 0;
        for (int num : nums) {
            tot |= num;
        }
        return (int) (tot * Math.pow(2, nums.length - 1));
    }

    public int subsetXORSum1(int[] nums) {
        int tot = 0;
        int max = (int) Math.pow(2, nums.length);
        for (int i = 1; i < max; i++) {
            int sub = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1L << j)) != 0) {
                    sub ^= nums[j];
                }
            }
            tot += sub;
        }
        return tot;
    }

    public static void main(String... args) {
        Solution1863 solution = new Solution1863();
        System.out.println(solution.subsetXORSum(new int[] {1,3})); // 6
        System.out.println(solution.subsetXORSum(new int[] {5,1,6})); // 28
        System.out.println(solution.subsetXORSum(new int[] {3,4,5,6,7,8})); // 480
    }
}
