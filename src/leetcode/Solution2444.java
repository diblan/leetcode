package leetcode;

// Very interesting problem. I was very close to the solution except I couldn't come up with moving the goal post.
// I used pseudocode from the comments
public class Solution2444 {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long result = 0;
        int minI = -1;
        int maxI = -1;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > maxK || num < minK) {
                minI = -1;
                maxI = -1;
                start = i + 1;
            }
            if (num == minK) {
                minI = i;
            }
            if (num == maxK) {
                maxI = i;
            }
            result += Math.max(0, Math.min(minI, maxI) - start + 1);
        }
        return result;
    }

    // The idea was in the right direction, but I fumbled once I met a new pair of min,max.
    // I fumbled on how to move my combos number to the new pair of min,max because I iterated to get the first combos.
    // Whereas the solution keeps track of start instead and math it out that way.
    public long countSubarrays0(int[] nums, int minK, int maxK) {
        long result = 0;
        long combos = 0;
        int multi = 1;
        boolean minOnce = false;
        boolean maxOnce = false;
        boolean minAgain = false;
        boolean maxAgain = false;
        for (int num : nums) {
            if (num > maxK || num < minK) {
                combos = 0;
                multi = 1;
                minOnce = false;
                maxOnce = false;
                minAgain = false;
                maxAgain = false;
            } else {
                if (num == minK) {
                    if (minOnce) {
                        minAgain = true;
                    } else {
                        minOnce = true;
                    }
                }
                if (num == maxK) {
                    if (maxOnce) {
                        maxAgain = true;
                    } else {
                        maxOnce = true;
                    }
                }
                if (!minOnce && !maxOnce) {
                    combos++;
                }
                if (minAgain && maxAgain) {
                    minAgain = false;
                    maxAgain = false;
                    multi++;
                }
                if (minOnce && maxOnce) {
                    combos++;
                    result += combos * multi;
                }
            }
        }
        return 0;
    }

    public static void main(String... args) {
        Solution2444 solution = new Solution2444();
        System.out.println(solution.countSubarrays(new int[] {1,3,5,2,7,5}, 1, 5)); // 2
        System.out.println(solution.countSubarrays(new int[] {1,1,1,1}, 1, 1)); // 10
    }
}
