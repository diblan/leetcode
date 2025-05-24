package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution2942 {
    // Runtime of 1ms (100.00%)
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].indexOf(x) != -1) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution2942 solution = new Solution2942();

        String[] words1 = {"leet", "code"};
        char x1 = 'e';
        System.out.println("Example 1 Output: " + solution.findWordsContaining(words1, x1));

        String[] words2 = {"abc", "bcd", "aaaa", "cbc"};
        char x2 = 'a';
        System.out.println("Example 2 Output: " + solution.findWordsContaining(words2, x2));

        String[] words3 = {"abc", "bcd", "aaaa", "cbc"};
        char x3 = 'z';
        System.out.println("Example 3 Output: " + solution.findWordsContaining(words3, x3));
    }
}
