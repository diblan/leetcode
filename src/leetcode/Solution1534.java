package leetcode;

public class Solution1534 {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int result = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (Math.abs(arr[i] - arr[j]) > a) {
                    continue;
                }
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.abs(arr[j] - arr[k]) > b || Math.abs(arr[i] - arr[k]) > c) {
                        continue;
                    }
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String... args) {
        Solution1534 solution = new Solution1534();
        System.out.println(solution.countGoodTriplets(new int[] {3,0,1,1,9,7}, 7, 2, 3)); // 4
        System.out.println(solution.countGoodTriplets(new int[] {1,1,2,2,3}, 0, 0, 1)); // 0
    }
}
