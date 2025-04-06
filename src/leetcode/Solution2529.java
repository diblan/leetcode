package leetcode;

public class Solution2529 {
    public int maximumCount(int[] nums) {
        int begin = 0;                  // begin at the first number
        int end = nums.length - 1;      // end should be within range hence the -1
        int range = Integer.MAX_VALUE;
        while (range > 1) {
            range = end - begin;
            int index = begin + (range / 2);
            int guess = nums[index];
            if (guess <= 0) {
                begin = index;
            }
            if (guess >= 0) {
                end = index;
            }
        }

        while (end > -1 && nums[end] > -1) {
            end--;
        }

        while (begin < nums.length && nums[begin] < 1) {
            begin++;
        }

        int posNums = nums.length - begin;
        int negNums = end + 1;
        return Integer.max(posNums, negNums);
    }

    public static void main(String... args) {
        Solution2529 solution = new Solution2529();
        System.out.println(solution.maximumCount(new int[] {-1}));
        System.out.println(solution.maximumCount(new int[] {-2,-1,-1,1,2,3}));
        System.out.println(solution.maximumCount(new int[] {-3,-2,-1,0,0,1,2}));
        System.out.println(solution.maximumCount(new int[] {5,20,66,1314}));
    }
}
