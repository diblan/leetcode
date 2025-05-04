package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution1128 {
    // Why calculate when you can just hard code the reference table (:
    // superfast at 2ms (98.22%)
    public int numEquivDominoPairs(int[][] dominoes) {
        int result = 0;
        int[] pairs = new int[45];
        int[][] ref = new int[][] {
                {},
                {0,  0,  1,  2,  3,  4,  5,  6,  7,  8},
                {0,  1,  9, 10, 11, 12, 13, 14, 15, 16},
                {0,  2, 10, 17, 18, 19, 20, 21, 22, 23},
                {0,  3, 11, 18, 24, 25, 26, 27, 28, 29},
                {0,  4, 12, 19, 25, 30, 31, 32, 33, 34},
                {0,  5, 13, 20, 26, 31, 35, 36, 37, 38},
                {0,  6, 14, 21, 27, 32, 36, 39, 40, 41},
                {0,  7, 15, 22, 28, 33, 37, 40, 42, 43},
                {0,  8, 16, 23, 29, 34, 38, 41, 43, 44}
        };
        for (int[] dom : dominoes) {
            int hash = ref[dom[0]][dom[1]];
            result += pairs[hash];
            pairs[hash]++;
        }
        return result;
    }

    // Instead of calculating the hash over and over
    // put it in a reference table 1 time
    // this is as close as I get to the fastest solution at 3ms (91.60%)
    public int numEquivDominoPairs2(int[][] dominoes) {
        int result = 0;
        int[] pairs = new int[45];
        int[][] ref = new int[10][];
        for (int i = 1; i < 10; i++) {
            ref[i] = new int[10];
            for (int j = 1; j < 10; j++) {
                ref[i][j] = i > j ? ((j - 1) * 9) + (i - 1) - (j * (j - 1) / 2) : ((i - 1) * 9) + (j - 1) - (i * (i - 1) / 2);
            }
        }
        for (int[] dom : dominoes) {
            int hash = ref[dom[0]][dom[1]];
            result += pairs[hash];
            pairs[hash]++;
        }
        return result;
    }

    // using your own hash function is way faster at 4ms (75.57%)
//    m n1  2  3  4  5  6  7  8  9  shift from a normal ((m - 1) * 9) + (n - 1)
//    1  0  1  2  3  4  5  6  7  8
//    2     9 10 11 12 13 14 15 16  -1  = -(2 * 1) / 2
//    3       17 18 19 20 21 22 23  -3  = -(3 * 2) / 2
//    4          24 25 26 27 28 29  -6  = -(4 * 3) / 2
//    5             30 31 32 33 34  -10 = -(5 * 4) / 2
//    6                35 36 37 38  -15 = -(6 * 5) / 2
//    7                   39 40 41  -21 = -(7 * 6) / 2
//    8                      42 43  -28 = -(8 * 7) / 2
//    9                         44  -36 = -(9 * 8) / 2
    public int numEquivDominoPairs1(int[][] dominoes) {
        int result = 0;
        int[] pairs = new int[45];
        for (int[] dom : dominoes) {
            int hash = dom[0] > dom[1] ? ((dom[1] - 1) * 9) + (dom[0] - 1) - (dom[1] * (dom[1] - 1) / 2) : ((dom[0] - 1) * 9) + (dom[1] - 1) - (dom[0] * (dom[0] - 1) / 2);
            result += pairs[hash];
            pairs[hash]++;
        }
        return result;
    }

    // slow solution because HashMap at 12ms (53.44%)
    // you were close with making your own hash
    // number of possible hashes is limited so you can store in an int[] which is much faster than HashMap
    public int numEquivDominoPairs0(int[][] dominoes) {
        int result = 0;
        Map<Integer, Integer> pairs = new HashMap<>();
        for (int[] dom : dominoes) {
            int key = dom[0] > dom[1] ? (dom[1] * 10) + dom[0] : (dom[0] * 10) + dom[1];
            int value;
            if (pairs.containsKey(key)) {
                value = pairs.get(key) + 1;
            } else {
                value = 0;
            }
            result += value;
            pairs.put(key, value);
        }
        return result;
    }

    public static void main(String... args) {
        Solution1128 solution = new Solution1128();
        System.out.println(solution.numEquivDominoPairs(new int[][] {{1,2},{2,1},{3,4},{5,6}}));
        System.out.println(solution.numEquivDominoPairs(new int[][] {{1,2},{1,2},{1,1},{1,2},{2,2}}));
        System.out.println(solution.numEquivDominoPairs(new int[][] {{2,1},{5,4},{3,7},{6,2},{4,4},{1,8},{9,6},{5,3},{7,4},{1,9},{1,1},{6,6},{9,6},{1,3},{9,7},{4,7},{5,1},{6,5},{1,6},{6,1},{1,8},{7,2},{2,4},{1,6},{3,1},{3,9},{3,7},{9,1},{1,9},{8,9}}));
    }
}
