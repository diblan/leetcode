package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution763 {
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        int[] last = new int[26];

        for (int i = s.length() - 1; i > -1; i--) {
            int c = s.charAt(i) - 97;
            if (last[c] == 0) {
                last[c] = i;
            }
        }

        int i = 0;
        while (i < s.length()) {
            int lpi = last[s.charAt(i) - 97]; // last partition index

            int j = i + 1;
            while (j < lpi) {
                int i1 = last[s.charAt(j) - 97];
                if (i1 > lpi) {
                    lpi = i1;
                }
                j++;
            }

            result.add(lpi - i + 1);
            i = lpi + 1;
        }

        return result;
    }

    public static void main(String... args) {
        Solution763 solution = new Solution763();
        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij")); // [9,7,8]
        System.out.println(solution.partitionLabels("eccbbbbdec")); // [10]
    }
}
