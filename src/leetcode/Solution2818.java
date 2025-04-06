package leetcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// This Solution uses the sieve of Eratosthenes
public class Solution2818 {
    public static long N = 1000000007L; // prime modulo value
    public List<Integer> primes;
    public Set<Integer> lookup;
    Map<Integer, Integer> cache = new HashMap<>();

    public int maximumScore(List<Integer> nums, int k) {
        if (primes == null) {
            primes = new ArrayList<>(9593);
            boolean[] sieve = new boolean[100000];
            for (int i = 2; i < sieve.length; i++) {
                if (!sieve[i]) {
                    primes.add(i);
                    for (int j = i + i; j < sieve.length; j += i) {
                        sieve[j] = true;
                    }
                }
            }
            primes.add(Integer.MAX_VALUE);
        }

        if (lookup == null) {
            lookup = new HashSet<>(primes);
        }

        int[] scores = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            if (cache.containsKey(num)) {
                scores[i] = cache.get(num);
                continue;
            }

            int distPrime = 0; // the number of distinct primes used
            for (int j = 0; num >= primes.get(j); j++) {
                if (lookup.contains(num)) {
                    distPrime++;
                    break;
                }
                if (num % primes.get(j) == 0) {
                    distPrime++;
                    do {
                        num /= primes.get(j);
                    } while (num % primes.get(j) == 0);
                }
            }
            scores[i] = distPrime;
            cache.put(nums.get(i), distPrime);
        }

        System.out.println(cache.size());

        int[] left = new int[nums.size()];
        int[] right = new int[nums.size()];
        Deque<Integer> leftStack = new ArrayDeque<>();
        Deque<Integer> rightStack = new ArrayDeque<>();

        for (int i = 0; i < left.length; i++) {
            while (!leftStack.isEmpty() && scores[leftStack.peek()] < scores[i]) {
                leftStack.pop();
            }
            if (leftStack.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = leftStack.peek();
            }
            leftStack.push(i);
        }

        for (int i = right.length - 1; i > -1; i--) {
            while(!rightStack.isEmpty() && scores[rightStack.peek()] <= scores[i]) {
                rightStack.pop();
            }
            if (rightStack.isEmpty()) {
                right[i] = nums.size();
            } else {
                right[i] = rightStack.peek();
            }
            rightStack.push(i);
        }

        long[][] max = new long[nums.size()][];
        for (int i = 0; i < max.length; i++) {
            max[i] = new long[] {nums.get(i), (long) (i - left[i]) * (right[i] - i)};
        }

        Arrays.sort(max, Comparator.comparingLong(a -> a[0]));

        long r = 1;
        int i = max.length - 1;
        while (k > 0 && i > -1) {
            long ops = Math.min(k, max[i][1]);
            k -= (int) ops;
            long multi = exponentiation(max[i][0], ops);
            r = ((multi * r) % N);
            i--;
        }

        return (int) r;
    }

    public long exponentiation(long base, long exp) {
        if (exp == 0)
            return 1;

        if (exp == 1)
            return base % N;

        long t = exponentiation(base, exp / 2);
        t = (t * t) % N;

        // if exponent is even value
        if (exp % 2 == 0)
            return t;

            // if exponent is odd value
        else
            return ((base % N) * t) % N;
    }

    public static void main(String... args) {
        Solution2818 solution = new Solution2818();
        System.out.println(solution.maximumScore(Arrays.asList(8,3,9,3,8), 2)); // 81
        System.out.println(solution.maximumScore(Arrays.asList(19,12,14,6,10,18), 3)); // 4788
        System.out.println(solution.maximumScore(Arrays.asList(3289,2832,14858,22011), 6)); // 256720975

        String filePath = "resources/Solution2818-case748.txt"; // Change this to your file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<Integer> nums;
            String line;
            while ((line = reader.readLine()) != null) {
                nums = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                System.out.println(solution.maximumScore(nums, 44446)); // 211485723
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        filePath = "resources/Solution2818-case869.txt"; // Change this to your file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<Integer> nums;
            String line;
            while ((line = reader.readLine()) != null) {
                nums = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                System.out.println(solution.maximumScore(nums, 1000000000)); // 153096350
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
