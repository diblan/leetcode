package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution2094 {

    // This solution is much, much faster at 3ms (97.44%)
    // Created with ChatGPT including the hint this time + my own reasoning on how to use the hint
    public static int[] findEvenNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }

        List<Integer> result = new ArrayList<>();

        for (int num = 100; num <= 999; num += 2) {
            int hundreds = num / 100;
            int tens = (num / 10) % 10;
            int units = num % 10;

            int[] needed = new int[10];
            needed[hundreds]++;
            needed[tens]++;
            needed[units]++;

            boolean canForm = true;
            for (int d = 0; d < 10; d++) {
                if (needed[d] > freq[d]) {
                    canForm = false;
                    break;
                }
            }

            if (canForm) {
                result.add(num);
            }
        }

        // Convert List to int[]
        int[] output = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }

        return output;
    }

    // This is a very slow solution at 282ms (27.99%)
    // Created with ChatGPT without using the hint
    public static int[] findEvenNumbers1(int[] digits) {
        Set<Integer> result = new HashSet<>();

        int n = digits.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;

                    int d1 = digits[i], d2 = digits[j], d3 = digits[k];

                    if (d1 == 0) continue;         // No leading zero
                    if (d3 % 2 != 0) continue;     // Must end in even digit

                    int number = d1 * 100 + d2 * 10 + d3;
                    result.add(number);
                }
            }
        }

        // Convert the set to a sorted int[]
        List<Integer> sortedList = new ArrayList<>(result);
        Collections.sort(sortedList);

        int[] output = new int[sortedList.size()];
        for (int i = 0; i < sortedList.size(); i++) {
            output[i] = sortedList.get(i);
        }

        return output;
    }

    public static void main(String[] args) {
        int[] digits1 = {2, 1, 3, 0};
        int[] digits2 = {2, 2, 8, 8, 2};
        int[] digits3 = {3, 7, 5};

        System.out.println(Arrays.toString(findEvenNumbers(digits1))); // [102, 120, 130, 132, 210, 230, 302, 310, 312, 320]
        System.out.println(Arrays.toString(findEvenNumbers(digits2))); // [222, 228, 282, 288, 822, 828, 882]
        System.out.println(Arrays.toString(findEvenNumbers(digits3))); // []
    }
}
