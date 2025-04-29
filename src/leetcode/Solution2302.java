package leetcode;

public class Solution2302 {
    // TODO: try with binary search
    // the solution should use binary search
//    public long countSubarrays(int[] nums, long k) {
//        long result = 0;
//        return result;
//    }

    // slightly faster as the previous one at 5ms (5.84%)
    public long countSubarrays(int[] nums, long k) {
        long result = 0;
        int l = 0;
        int r = 1;
        long sum = 0;
        long score;
        while (l < nums.length) {
            if (sum == 0) {
                if (nums[l] < k) {
                    result++;
                    sum = nums[l];
                } else {
                    l++;
                    continue;
                }
            }
            if (r <= l) {
                r = l + 1;
            }
            while (r != nums.length) {
                sum += nums[r++];
                score = sum * (r - l);
                if (score < k) {
                    result++;
                } else {
                    sum -= nums[--r];
                    break;
                }
            }
            sum -= nums[l++];
            score = sum * (r - l);
            if (score < k) {
                result += r - l;
            }
        }
        return result;
    }

    // this solution is really slow 7ms (5.22%), fastest is 2ms
    public long countSubarrays0(int[] nums, long k) {
        long result = 0;
        int l = 0;
        int r = 0;
        long[] sums = new long[nums.length + 1];
        sums[0] = 0;
        long score;
        while (l < nums.length) {
            while (r < nums.length) {
                r++;
                sums[r] = sums[r - 1] + nums[r - 1];
                score = (sums[r] - sums[l]) * (r - l);
                if (score < k) {
                    result++;
                } else {
                    r--;
                    break;
                }
            }
            while (l < nums.length) {
                if (l < r) {
                    l++;
                }
                if (l == r) {
                    if (r < nums.length) {
                        r++;
                        sums[r] = sums[r - 1] + nums[r - 1];
                    } else {
                        return result;
                    }
                }
                score = (sums[r] - sums[l]) * (r - l);
                if (score < k) {
                    result += r - l;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String... args) {
        Solution2302 solution = new Solution2302();
        System.out.println(solution.countSubarrays(new int[] {2,1,4,3,5}, 10)); // 6
        System.out.println(solution.countSubarrays(new int[] {1,1,1}, 5)); // 5
        System.out.println(solution.countSubarrays(new int[] {9,5,3,8,4,7,2,7,4,5,4,9,1,4,8,10,8,10,4,7}, 4)); // 3
        System.out.println(solution.countSubarrays(new int[] {100000,100000}, 1)); // 0
    }
}
