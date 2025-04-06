package leetcode;

import java.util.Arrays;

public class Solution1718 {
    public int[] constructDistancedSequence(int n) {
        int[] result = new int[(n * 2) - 1];
        int[] opts = new int[n];
        for (int i = 0; i < n; i++) {
            opts[i] = n - i;
        }
        return placeNumber(result, opts);
    }

    public int[] placeNumber(int[] result, int[] opts) {
        int pos = getEmptyPos(result);
        for (int n : opts) {
            // try to place a number
            if (n == 1) {
                result[pos] = n; // only one position needs filling when it is a 1
            } else if (pos + n < result.length && result[pos + n] == 0) {
                result[pos] = n; // numbers not 1 need two positions filled
                result[pos + n] = n;
            } else {
                continue; // number n has no viable place, continue to the next number
            }

            // if it was the last number, return the result
            if (opts.length == 1) {
                return result;
            }

            // more numbers need trying, a new list of options is needed
            int[] newOpts = new int[opts.length - 1];
            int i = 0;
            for (int opt : opts) {
                if (opt != n) {
                    newOpts[i++] = opt;
                }
            }

            int[] newResult = placeNumber(result, newOpts);
            if (newResult != null) {
                return newResult;
            } else {
                result[pos] = 0;
                if (n != 1) { // also remove the second position
                    result[pos + n] = 0;
                }
            }
        }
        return null;
    }

    public int getEmptyPos(int[] result) {
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution1718 solution = new Solution1718();
        System.out.println(Arrays.toString(solution.constructDistancedSequence(3)));
        System.out.println(Arrays.toString(solution.constructDistancedSequence(5)));
    }
}
