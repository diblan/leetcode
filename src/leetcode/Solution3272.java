package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution3272 {
    int[] digits;
    Set<List<Integer>> dfm; // digit frequency map
    long[] fact;

    public long countGoodIntegers(int n, int k) {
        long result = 0;
        digits = new int[n];
        dfm = new HashSet<>();
        fact = new long[n + 1];

        long f = 1;
        fact[0] = 1;
        fact[1] = 1;
        for (int i = 2; i <= n; i++) {
            f = f * i;
            fact[i] = f;
        }

        palindrome(n, k, 0);
        for (List<Integer> df : dfm) {
            long div = 1;
            for (Integer integer : df) {
                if (integer > 1) {
                    div *= fact[integer];
                }
            }
            result += fact[n] / div;
            if (df.getFirst() > 0) {
                div /= fact[df.getFirst()];
                div *= fact[df.getFirst() - 1];
                result -= (fact[n - 1] / div);
            }
        }
        return result;
    }

    public void palindrome(int n, int k, int pos) {
        int half = (n + 1) / 2;
        if (pos < half) { // we try all numbers at that position
            for (int i = pos == 0 ? 1 : 0; i < 10; i++) {
                digits[pos] = i;
                int next = pos + 1;
                palindrome(n, k, next);
            }
        }
        for (int i = 0, j = n - 1; j >= half; i++, j--) {
            digits[j] = digits[i];
        }

        long palindrome = 0;
        long order = 1;
        for (int i = digits.length - 1; i > -1; i--) {
            palindrome += digits[i] * order;
            order *= 10;
        }

        if (palindrome % k == 0) {
            List<Integer> df = new ArrayList<>(Collections.nCopies(10, 0));
            for (int digit : digits) {
                df.set(digit, df.get(digit) + 1);
            }
            dfm.add(df);
        }
    }

    public static void main(String... args) {
        Solution3272 solution = new Solution3272();
        System.out.println(solution.countGoodIntegers(3, 5)); // 27
        System.out.println(solution.countGoodIntegers(1, 4)); // 2
        System.out.println(solution.countGoodIntegers(5, 6)); // 2468
        System.out.println(solution.countGoodIntegers(10, 3)); // 13831104
    }
}
