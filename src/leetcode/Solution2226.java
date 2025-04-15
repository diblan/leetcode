package leetcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Solution2226 {
    public int maximumCandies(int[] candies, long k) {
        long allCandies = 0;
        for (int j : candies) {
            allCandies += j;
        }

        if (allCandies < k) {
            return 0;
        }

        long l = 1;
        long r = allCandies / k;

        if (r == 1) {
            return 1;
        }

        long test = r;

        while (true) {
            long piles = 0;
            for (int candy : candies) {
                piles += candy / test;
            }

            if (piles >= k) {
                l = test;
            }
            if (piles < k) {
                r = test;
            }
            if (r - l < 2) {
                return (int) l;
            } else {
                test = (r + l) / 2;
            }
        }
    }

    public static void main(String... args) {
        Solution2226 solution = new Solution2226();
        System.out.println(solution.maximumCandies(new int[] {5,8,6}, 3)); // 5
        System.out.println(solution.maximumCandies(new int[] {2,5}, 11)); // 5
        System.out.println(solution.maximumCandies(new int[] {4,7,5}, 16)); // (4/100)

        String filePath = "resources/Solution2666-case79.txt"; // Change this to your file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(solution.maximumCandies(Arrays.stream(values).mapToInt(Integer::parseInt).toArray(), 10000)); // 1000000 (79)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
