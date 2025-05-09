package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// I got completely cooked on this problem. I used ChatGPT but he wasn't fast enough. Was correct though.
// Eventually used solution from doocs but I don't understand it because I'm not that type of math wizard.
public class Solution3343 {
    static final int MOD = 1_000_000_007;
    static final int MAX = 80;
    static long[] fact = new long[MAX + 1];
    static long[] invFact = new long[MAX + 1];

    static long result = 0;

    static Map<String, Long> memo = new HashMap<>();
    public int countBalancedPermutations(String num) {
        int[] tally = new int[10];
        int total = 0;
        for (int i = 0; i < num.length(); i++) {
            int n = num.charAt(i) - '0';
            total += n;
            tally[n]++;
        }
        if ((total & 1) == 1) { // total can't be odd
            return 0;
        }
        int target = total / 2;
        return Math.toIntExact(countBalanced(tally));
    }

    static void initFactorials() {
        fact[0] = invFact[0] = 1;
        for (int i = 1; i <= MAX; i++) {
            fact[i] = fact[i - 1] * i % MOD;
            invFact[i] = modInverse(fact[i]);
        }
    }

    static long modInverse(long x) {
        return modPow(x, MOD - 2, MOD);
    }

    static long modPow(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % mod;
            }
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }

    static long multinomial(int[] counts) {
        int total = 0;
        for (int c : counts) total += c;
        long res = fact[total];
        for (int c : counts) {
            res = res * invFact[c] % MOD;
        }
        return res;
    }

    static long countBalanced(int[] tally) {
        initFactorials();

        int totalDigits = Arrays.stream(tally).sum();
        int evenCount = (totalDigits + 1) / 2;
        long result = 0;

        Deque<State> stack = new ArrayDeque<>();
        stack.push(new State(0, new int[10], 0, 0));

        while (!stack.isEmpty()) {
            State state = stack.pop();
            int digit = state.digit;
            int[] evenGroup = state.evenGroup;
            int used = state.used;
            int eSum = state.evenSum;

            if (used > evenCount || eSum > 450) continue;

            if (digit == 10) {
                if (used != evenCount) continue;

                int[] oddGroup = new int[10];
                int oSum = 0;
                for (int i = 0; i < 10; i++) {
                    oddGroup[i] = tally[i] - evenGroup[i];
                    if (oddGroup[i] < 0) {
                        oSum = -1; // force fail
                        break;
                    }
                    oSum += oddGroup[i] * i;
                }
                if (oSum != eSum) continue;

                long waysEven = multinomial(evenGroup);
                long waysOdd = multinomial(oddGroup);
                result = (result + waysEven * waysOdd % MOD) % MOD;
                continue;
            }

            int maxTake = Math.min(tally[digit], evenCount - used);
            for (int i = 0; i <= maxTake; i++) {
                int[] nextGroup = Arrays.copyOf(evenGroup, 10);
                nextGroup[digit] = i;
                stack.push(new State(digit + 1, nextGroup, used + i, eSum + i * digit));
            }
        }

        return result;
//        initFactorials();
//        result = 0;
//
//        int totalDigits = Arrays.stream(tally).sum();
//        int evenCount = (totalDigits + 1) / 2;
//
//        backtrack(tally, new int[10], 0, 0, 0, evenCount);
//        return result;
//        return count(tally, 0, 0, 0, totalDigits);
//        return dp(tally, 0, 0, 0, totalDigits);
//        int evenCount = (total + 1) / 2;

//        int[] evenGroup = new int[10];
//        return countWays(tally, evenGroup, 0, evenCount, 0);
//        return backtrack(tally, new int[10], 0, evenCount, 0);
    }

    static class State {
        int digit;
        int[] evenGroup;
        int used;
        int evenSum;

        State(int digit, int[] evenGroup, int used, int evenSum) {
            this.digit = digit;
            this.evenGroup = evenGroup;
            this.used = used;
            this.evenSum = evenSum;
        }
    }

    static void backtrack(int[] tally, int[] evenGroup, int digit, int evenUsed, int evenSum, int evenCount) {
        if (evenUsed > evenCount) return;
        if (evenSum > 450) return; // Max sum of half the digits with 9s is 9*50=450, safe cut

        if (digit == 10) {
            if (evenUsed != evenCount) return;

            int[] oddGroup = new int[10];
            int oddSum = 0;
            for (int i = 0; i < 10; i++) {
                oddGroup[i] = tally[i] - evenGroup[i];
                if (oddGroup[i] < 0) return;
                oddSum += oddGroup[i] * i;
            }

            if (oddSum != evenSum) return;

            long waysEven = multinomial(evenGroup);
            long waysOdd = multinomial(oddGroup);
            result = (result + waysEven * waysOdd % MOD) % MOD;
            return;
        }

        for (int i = 0; i <= Math.min(tally[digit], evenCount - evenUsed); i++) {
            evenGroup[digit] = i;
            backtrack(tally, evenGroup, digit + 1, evenUsed + i, evenSum + i * digit, evenCount);
        }
        evenGroup[digit] = 0;
    }

    static long count(int[] tally, int pos, int evenSum, int oddSum, int totalLeft) {
        if (totalLeft == 0) {
            return evenSum == oddSum ? 1 : 0;
        }

        // Pruning: difference too large to fix with remaining digits
        int maxDigit = 9;
        int sumRem = 0;
        for (int d = 0; d <= 9; d++) {
            sumRem += tally[d] * d;
        }
        if (Math.abs(evenSum - oddSum) > sumRem) return 0;

        String key = Arrays.toString(tally) + "|" + evenSum + "|" + oddSum + "|" + pos;
        if (memo.containsKey(key)) return memo.get(key);

        long total = 0;
        for (int d = 0; d <= 9; d++) {
            if (tally[d] == 0) continue;

            tally[d]--;
            if (pos % 2 == 0) {
                total = (total + count(tally, pos + 1, evenSum + d, oddSum, totalLeft - 1)) % MOD;
            } else {
                total = (total + count(tally, pos + 1, evenSum, oddSum + d, totalLeft - 1)) % MOD;
            }
            tally[d]++;
        }

        memo.put(key, total);
        return total;
    }

    static long dp(int[] tally, int pos, int evenSum, int oddSum, int totalDigits) {
        if (pos == totalDigits) {
            return evenSum == oddSum ? 1 : 0;
        }

        String key = Arrays.toString(tally) + "|" + pos + "|" + evenSum + "|" + oddSum;
        if (memo.containsKey(key)) {
            System.out.println("Key retrieved.");
            return memo.get(key);
        }

        long total = 0;
        for (int d = 0; d <= 9; d++) {
            if (tally[d] > 0) {
                tally[d]--;
                if (pos % 2 == 0) {
                    total = (total + dp(tally, pos + 1, evenSum + d, oddSum, totalDigits)) % MOD;
                } else {
                    total = (total + dp(tally, pos + 1, evenSum, oddSum + d, totalDigits)) % MOD;
                }
                tally[d]++;
            }
        }

        memo.put(key, total);
        return total;
    }

    static long countWays(int[] tally, int[] evenGroup, int digit, int evenLeft, int evenSum) {
        if (digit == 10) {
            if (evenLeft != 0) return 0;

            int[] oddGroup = new int[10];
            int oddSum = 0;
            for (int i = 0; i < 10; i++) {
                oddGroup[i] = tally[i] - evenGroup[i];
                if (oddGroup[i] < 0) return 0;
                oddSum += oddGroup[i] * i;
            }
            if (oddSum != evenSum) return 0;

            long evenPerms = multinomial(evenGroup);
            long oddPerms = multinomial(oddGroup);
            return evenPerms * oddPerms % MOD;
        }

        String memoKey = digit + "," + evenLeft + "," + evenSum + "," + Arrays.toString(evenGroup);
        if (memo.containsKey(memoKey)) return memo.get(memoKey);

        long total = 0;
        for (int i = 0; i <= Math.min(tally[digit], evenLeft); i++) {
            evenGroup[digit] = i;
            total = (total + countWays(tally, evenGroup, digit + 1, evenLeft - i, evenSum + i * digit)) % MOD;
        }
        evenGroup[digit] = 0; // Reset before returning (important for backtracking reuse)
        memo.put(memoKey, total);
        return total;
    }

//    static long backtrack(int[] tally, int[] evenGroup, int digit, int evenLeft, int evenSum) {
//        if (digit == 10) {
//            if (evenLeft != 0) return 0;
//
//            int oddSum = 0;
//            int[] oddGroup = new int[10];
//            int oddCount = 0;
//
//            for (int i = 0; i < 10; i++) {
//                oddGroup[i] = tally[i] - evenGroup[i];
//                if (oddGroup[i] < 0) return 0;
//                oddSum += oddGroup[i] * i;
//                oddCount += oddGroup[i];
//            }
//
//            if (oddSum != evenSum) return 0;
//
//            long evenWays = multinomial(evenGroup);
//            long oddWays = multinomial(oddGroup);
//            return evenWays * oddWays % MOD;
//        }
//
//        long total = 0;
//        for (int i = 0; i <= Math.min(tally[digit], evenLeft); i++) {
//            evenGroup[digit] = i;
//            total = (total + backtrack(tally, evenGroup, digit + 1, evenLeft - i, evenSum + i * digit)) % MOD;
//        }
//        return total;
//    }

    public static void main(String... args) {
        Solution3343 solution = new Solution3343();
        System.out.println(solution.countBalancedPermutations("123")); // 2
        System.out.println(solution.countBalancedPermutations("112")); // 1
        System.out.println(solution.countBalancedPermutations("12345")); // 0
        System.out.println(solution.countBalancedPermutations("12383652737322042")); // 722157412
        System.out.println(solution.countBalancedPermutations("229502156606738136")); // 405525133
        System.out.println(solution.countBalancedPermutations("41769073429969142186780551748607082946123600115912938")); // 405525133
    }
}
