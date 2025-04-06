package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Solution3394 {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        Arrays.sort(rectangles, Comparator.comparingInt(a -> a[0]));

        int last = 0;
        for (int i = 1; i < rectangles.length; i++) {
            if (rectangles[i][0] < rectangles[last][2]) {
                rectangles[last][2] = Math.max(rectangles[i][2], rectangles[last][2]);
            } else {
                last++;
                rectangles[last][2] = rectangles[i][2];
            }
        }

        if (last > 1) {
            return true;
        }

        Arrays.sort(rectangles, Comparator.comparingInt(a -> a[1]));

        last = 0;
        for (int i = 1; i < rectangles.length; i++) {
            if (rectangles[i][1] < rectangles[last][3]) {
                rectangles[last][3] = Math.max(rectangles[i][3], rectangles[last][3]);
            } else {
                last++;
                rectangles[last][3] = rectangles[i][3];
            }
        }

        return last > 1;
    }

    public static void main(String... args) {
        Solution3394 solution = new Solution3394();
        System.out.println(solution.checkValidCuts(5, new int[][] {{1,0,5,2},{0,2,2,4},{3,2,5,3},{0,4,4,5}})); // true
        System.out.println(solution.checkValidCuts(4, new int[][] {{0,0,1,1},{2,0,3,4},{0,2,2,3},{3,0,4,3}})); // true
        System.out.println(solution.checkValidCuts(4, new int[][] {{0,2,2,4},{1,0,3,2},{2,2,3,4},{3,0,4,2},{3,2,4,4}})); // false
    }
}
