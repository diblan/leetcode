package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Solution3169 {
    public int countDays(int days, int[][] meetings) {
//        int count = 0;
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        int last = 0; // index last merged

        for (int i = 1; i < meetings.length; i++) {
            if (meetings[last][1] > meetings[i][0] - 2) {
                meetings[last][1] = Math.max(meetings[last][1], meetings[i][1]);
            } else {
                last++;
                meetings[last] = meetings[i];
            }
        }

        int m = 0;
        for (int i = 0; i <= last; i++) {
            m += meetings[i][1] - meetings[i][0] + 1;
        }

        return days - m;

//        int day = 0;
//        int meeting = 0;

//        while (meeting <= last) {
//            if (meetings[meeting][1] > day) {
//                if (day < meetings[meeting][0]) {
//                    count += meetings[meeting][0] - day - 1;
//                }
//                day = meetings[meeting][1];
//            }
//            meeting++;
//        }
//        if (day < days) {
//            count += days - day;
//        }
//        return count;
    }

    public int countDays2(int days, int[][] meetings) {
        int count = 0;
        Arrays.sort(meetings, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // compare first element
            } else {
                return Integer.compare(a[1], b[1]); // if first is equal, compare second
            }
        });

        int day = 0;
        int meeting = 0;

        while (meeting < meetings.length) {
            if (meetings[meeting][1] > day) {
                if (day < meetings[meeting][0]) {
                    count += meetings[meeting][0] - day - 1;
                }
                day = meetings[meeting][1];
            }
            meeting++;
        }
        if (day < days) {
            count += days - day;
        }
        return count;
    }

    public static void main(String... args) {
        Solution3169 solution = new Solution3169();
        System.out.println(solution.countDays(10, new int[][] {{5,7},{1,3},{9,10}})); // 2
        System.out.println(solution.countDays(5, new int[][] {{2,4},{1,3}})); // 1
        System.out.println(solution.countDays(6, new int[][] {{1,6}})); // 0
    }
}
