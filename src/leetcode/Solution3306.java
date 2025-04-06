package leetcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution3306 {
    public long countOfSubstrings(String word, int k) {
        long result = 0;
        int[] types = new int[word.length()];
        for (int i = 0; i < word.length(); i++) {
            types[i] = getType(word.charAt(i));
        }
        int begin = 0;
        int end = k + 5 - 1; // included
        int extra = -1;
        int[] counts = new int[6];
        for (int i = begin; i <= end; i++) {
            counts[types[i]]++;
        }

        OUTER_LOOP: while (begin < word.length() - k - 5 + 1) {
            // too many consonants means moving the range
            if (counts[5] > k) {
                if (begin + 1 < word.length() - k - 5 + 1) {
                    counts[types[begin++]]--;
                    continue;
                } else {
                    return result;
                }
            }

            // loop until enough consonants
            while (counts[5] < k) {
                end++;
                if (end < word.length()) {
                    counts[types[end]]++;
                } else {
                    return result;
                }
            }

            // we have enough consonants, now check the vowels
            boolean enoughVowels = true;
            for (int i = 0; i < 5; i++) {
                if (counts[i] == 0) {
                    enoughVowels = false;
                    break;
                }
            }

            // expand until enough vowels
            while (!enoughVowels) {
                if (end + 1 < word.length()) {
                    if (types[end + 1] == 5) {
                        counts[types[++end]]++;
                        continue OUTER_LOOP;
                    }
                    counts[types[++end]]++;
                } else {
                    return result;
                }

                enoughVowels = true;
                for (int i = 0; i < 5; i++) {
                    if (counts[i] == 0) {
                        enoughVowels = false;
                        break;
                    }
                }
            }
            // we have enough consonants and vowels
            result++;

            // for every extra vowel after, one extra result
            if (extra < end) {
                int extraCount = end + 1;
                while (extraCount < word.length()) {
                    if (types[extraCount] == 5) {
                        break;
                    }
                    extraCount++;
                }
                extra = --extraCount;
            }

            if (extra > end) {
                result += extra - end;
            }

            // move over to the next iteration
            counts[types[begin++]]--;
        }

        return result;
    }

    public long countOfSubstrings2(String word, int k) {
        long result = 0;
        int minLength = k + 5;
        for (int i = 0; i < word.length() - minLength + 1; i++) {
            int[] vowels = new int[5];
            int consonants = 0;
            for (int j = i; j < i + minLength; j++) {
                int vowelIndex = (word.charAt(j) - 95) / 5;
                if (isVowel(word.charAt(j))) {
                    vowels[vowelIndex] = 1;
                } else {
                    consonants++;
                }
            }

            if (consonants > k) {
                i = i + minLength - 1;
                continue; // optimization possible
            }

            int vowelCount = 0;
            for (int vowel : vowels) {
                vowelCount += vowel;
            }

            if (vowelCount == 5 && consonants == k) {
                result++;
                for (int j = i + minLength; j < word.length(); j++) {
                    if (isVowel(word.charAt(j))) {
                        result++;
                    } else {
                        break;
                    }
                }
                continue; // optimization possible
            }

            for (int j = i + minLength; j < word.length(); j++) {
                int vowelIndex = (word.charAt(j) - 95) / 5;
                if (isVowel(word.charAt(j))) {
                    if (vowels[vowelIndex] == 0) {
                        vowels[vowelIndex] = 1;
                        vowelCount++;
                    }
                } else {
                    consonants++;
                }

                if (vowelCount == 5 && consonants == k) {
                    result++;
                } else {
                    if (consonants > k) {
                        break; // optimization possible
                    }
                }
            }
        }
        return result;
    }

    public boolean isVowel(char ch) {
        if (ch < 'j') { // aei
            if (ch < 'f') { // ae
                if (ch == 'a') { // a
                    return true;
                } else return ch == 'e';
            } else { // i
                return ch == 'i';
            }
        } else { // ou
            if (ch < 'p') { // o
                return ch == 'o';
            } else {
                return ch == 'u';
            }
        }
    }

    public int getType(char ch) {
        if (ch < 'j') { // aei
            if (ch < 'f') { // ae
                if (ch == 'a') { // a
                    return 0;
                }
                if (ch == 'e') {
                    return 1;
                }
            } else { // i
                if (ch == 'i') {
                    return 2;
                }
            }
        } else { // ou
            if (ch < 'p') { // o
                if (ch == 'o') {
                    return 3;
                }
            } else {
                if (ch == 'u') {
                    return 4;
                }
            }
        }
        return 5;
    }

    public static void main(String... args) {
        Solution3306 solution = new Solution3306();
        System.out.println(solution.countOfSubstrings("aeioqq", 1)); // 0
        System.out.println(solution.countOfSubstrings("aeiou", 0)); // 1
        System.out.println(solution.countOfSubstrings("ieaouqqieaouqq", 1)); // 3
        System.out.println(solution.countOfSubstrings("iqeaouqi", 2)); // 3
        System.out.println(solution.countOfSubstrings("eiaaoui", 0)); // 2
        System.out.println(solution.countOfSubstrings("ieaeoau", 0)); // 1
        System.out.println(solution.countOfSubstrings("ieaeoau", 0)); // 1
        System.out.println(solution.countOfSubstrings("qaouieun", 0)); // 2


        String filePath = "resources/Solution3306.txt"; // Change this to your file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(solution.countOfSubstrings(line, 0)); // 1
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
