package leetcode;

// I tried to let ChatGPT solve the problem but it got nowhere and eventually I made it myself.
// I did ask it to make the first part all the way to line 30 by explaining what I wanted from it.
// The last part, the logic, I did myself because it was easier to do myself than to explain it to ChatGPT.
// It's close enough to the fastest at 3ms (88.73%)
public class Solution2918 {
    public long minSum(int[] nums1, int[] nums2) {
        long sum1 = 0;
        int zero1 = 0;

        for (int num : nums1) {
            if (num == 0) {
                zero1++;
            } else {
                sum1 += num;
            }
        }

        long sum2 = 0;
        int zero2 = 0;

        for (int num : nums2) {
            if (num == 0) {
                zero2++;
            } else {
                sum2 += num;
            }
        }

        long adjustedSum1 = sum1 + zero1;
        long adjustedSum2 = sum2 + zero2;

        if (zero1 == 0 && zero2 == 0) {
            if (adjustedSum1 == adjustedSum2) {
                return adjustedSum2;
            }
        } else {
            if (zero1 == 0 && adjustedSum1 >= adjustedSum2) {
                return adjustedSum1;
            }
            if (zero2 == 0 && adjustedSum2 >= adjustedSum1) {
                return adjustedSum2;
            }
            if (zero1 != 0 && zero2 != 0) {
                return Math.max(adjustedSum1, adjustedSum2);
            }
        }

        return -1;
    }

    public static void main(String... args) {
        Solution2918 solution = new Solution2918();
        System.out.println(solution.minSum(new int[] {3,2,0,1,0}, new int[] {6,5,0})); // 12
        System.out.println(solution.minSum(new int[] {2,0,2,0}, new int[] {1,4})); // -1
    }
}
