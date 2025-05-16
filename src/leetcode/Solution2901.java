package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution2901 {
    // I tried to steer ChatGPT to create a more efficient solution but he often gives me theoretical improvements
    // Like precomputing a string to a char[] as the .charAt() is more expensive than a char[] call
    // He was able to improve the Runtime from 103ms to 89ms
    // In the conversations with ChatGPT I got the understanding that the Hamming Distance method was called a lot
    // So I asked to write the fastest possible Hamming Distance method, which was pretty much what he already used
    // Then I inspected the code myself and noticed we only care if the Hamming Distance is 1
    // So I asked to write a solution where we cut short the Hamming Distance method the moment it goes above 1
    // This resulted in this solution with a fantastic Runtime score of 24ms (97.56%)
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        int[] dp = new int[n];       // dp[i] = longest valid subsequence ending at i
        int[] prev = new int[n];     // for backtracking
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        // Precompute word character arrays
        char[][] wordChars = new char[n][];
        for (int i = 0; i < n; i++) {
            wordChars[i] = words[i].toCharArray();
        }

        int maxLen = 1;
        int maxIndex = 0;

        // Main DP loop
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (groups[i] != groups[j] &&
                        wordChars[i].length == wordChars[j].length &&
                        hammingDistanceIsOne(wordChars[i], wordChars[j])) {

                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        // Reconstruct the result
        LinkedList<String> result = new LinkedList<>();
        int i = maxIndex;
        while (i != -1) {
            result.addFirst(words[i]);
            i = prev[i];
        }

        return result;
    }

    // Early-exit Hamming distance == 1 check
    private boolean hammingDistanceIsOne(char[] a, char[] b) {
        int diff = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                if (++diff > 1) return false;
            }
        }
        return diff == 1;
    }

    // The first solution ChatGPT provided had a recursive function that resulted in a Stackoverflow Error
    // This, the second, version had a Runtime of 103ms
    public List<String> getWordsInLongestSubsequence0(String[] words, int[] groups) {
        int n = words.length;
        int[] dp = new int[n];       // dp[i] = max subsequence length ending at i
        int[] prev = new int[n];     // prev[i] = previous index in sequence
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxLen = 1;
        int maxIndex = 0;

        for (int i = 0; i < n; i++) {
            String wordI = words[i];
            int groupI = groups[i];
            for (int j = 0; j < i; j++) {
                if (groupI != groups[j] &&
                        wordI.length() == words[j].length() &&
                        hammingDistance(wordI, words[j]) == 1) {

                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        // Reconstruct the result using prev[]
        LinkedList<String> result = new LinkedList<>();
        while (maxIndex != -1) {
            result.addFirst(words[maxIndex]);
            maxIndex = prev[maxIndex];
        }

        return result;
    }

    private int hammingDistance(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
        }
        return count;
    }
    public static void main(String[] args) {
        Solution2901 sol = new Solution2901();

        // Example 1
        String[] words1 = {"bab", "dab", "cab"};
        int[] groups1 = {1, 2, 2};
        List<String> result1 = sol.getWordsInLongestSubsequence(words1, groups1);
        System.out.println("Example 1 Output: " + result1);

        // Example 2
        String[] words2 = {"a", "b", "c", "d"};
        int[] groups2 = {1, 2, 3, 4};
        List<String> result2 = sol.getWordsInLongestSubsequence(words2, groups2);
        System.out.println("Example 2 Output: " + result2);
    }
}
