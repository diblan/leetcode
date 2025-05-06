package leetcode;

import java.util.Arrays;

public class Solution1920 {

    // this is of course slower but at least the time complexity is O(1)
    // you can even skip the int a,b,og values if you use modulo to store two numbers
    // but I don't want to do that solution
    public int[] buildArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > -1) {
                int a = i;
                int b = nums[a];
                int og = nums[a];
                while (b != i) {
                    nums[a] = nums[b] - 1001;
                    a = b;
                    b = nums[b];
                }
                nums[a] = og - 1001;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] + 1001;
        }
        return nums;
    }

    // this is the fastest solution at 1ms (98.72%)
    // but the problem asks to do it in O(1) space complexity, which it is not
    public int[] buildArray0(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[nums[i]];
        }
        return result;
    }

    public static void main(String... args) {
        Solution1920 solution = new Solution1920();
//        System.out.println(Arrays.toString(solution.buildArray(new int[]{0,2,1,5,3,4}))); // [0,1,2,4,5,3]
        System.out.println(Arrays.toString(solution.buildArray(new int[]{5,0,1,2,3,4}))); // [4,5,0,1,2,3]
    }
}
