package leetcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Solution2594 {
    public long repairCars(int[] ranks, int cars) {
        int maxRank = 0;
        for (int rank : ranks) {
            if (rank > maxRank) {
                maxRank = rank;
            }
        }

        long l = 0;
        long r = maxRank * ((long) cars * cars);
        long guess;

        while (r - l > 1) {
            guess = (l + r) / 2;
            int left = cars;
            for (int rank : ranks) {
                int can = (int) Math.sqrt(guess / rank);
                left -= can;
                if (left < 0) {
                    break;
                }
            }

            if (left > 0) {
                l = guess;
            } else {
                r = guess;
            }
        }
        return r;
    }

    public static void main(String... args) {
        Solution2594 solution = new Solution2594();
        System.out.println(solution.repairCars(new int[] {4,2,3,1}, 10)); // 16
        System.out.println(solution.repairCars(new int[] {5,1,8}, 6)); // 16

        String filePath = "resources/Solution2594-case1060.txt"; // Change this to your file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(solution.repairCars(Arrays.stream(values).mapToInt(Integer::parseInt).toArray(), 986873)); // 3543876 (1060)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
