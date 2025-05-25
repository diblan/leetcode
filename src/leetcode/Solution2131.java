package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution2131 {
    // Made with ChatGPT with a Runtime of 114ms (40.65%)
    public int longestPalindrome(String[] words) {
        Map<String, Integer> countMap = new HashMap<>();
        int length = 0;
        boolean hasCentralPalindrome = false;

        for (String word : words) {
            String reversed = new StringBuilder(word).reverse().toString();

            if (countMap.getOrDefault(reversed, 0) > 0) {
                countMap.put(reversed, countMap.get(reversed) - 1);
                length += 4;
            } else {
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            if (word.charAt(0) == word.charAt(1) && count > 0) {
                hasCentralPalindrome = true;
                break;
            }
        }

        return hasCentralPalindrome ? length + 2 : length;
    }

    public static void main(String[] args) {
        Solution2131 sol = new Solution2131();

        String[] words1 = {"lc", "cl", "gg"};
        System.out.println("Example 1 Output: " + sol.longestPalindrome(words1)); // 6

        String[] words2 = {"ab", "ty", "yt", "lc", "cl", "ab"};
        System.out.println("Example 2 Output: " + sol.longestPalindrome(words2)); // 8

        String[] words3 = {"cc", "ll", "xx"};
        System.out.println("Example 3 Output: " + sol.longestPalindrome(words3)); // 2
    }
}
