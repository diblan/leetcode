package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

// This Solution uses the sieve of Eratosthenes
public class Solution2523 {
    private final int[] primesToCheck = new int[] {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};

    public int[] closestPrimes(int left, int right) {
        int range = 1000000;
        int lastPrime = -10000000;
        int leftResult = -1;
        int rightResult = -1;

        if (left <= 2) {
            left = 3;
            lastPrime = 2;
        }

        if (right == 1) {
            return new int[] {-1, -1};
        }

        if (left % 2 == 0) {
            left++;
        }

        LinkedList<Integer> ll = new LinkedList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997));
        boolean[] sieve = new boolean[((right - left) / 2) + 1];
        for (int i = 0; i < sieve.length; i++) {
            if (sieve[i]) {
                continue; // the number got filtered out
            }
            int num = (i * 2) + left;
            boolean isPrime = true;
            ListIterator<Integer> it = ll.listIterator();
            while (it.hasNext()) {
                int prime = it.next();
                if (num % prime == 0) {
                    isPrime = num == prime;
                    for (int k = i; k < sieve.length; k += ((prime / 2) + 1)) {
                        sieve[k] = true;
                    }
                    it.remove();
                    break;
                }
            }
            if (isPrime) {
                if (num - lastPrime < range) {
                    leftResult = lastPrime;
                    rightResult = num;
                    range = rightResult - leftResult;
                }
                lastPrime = num;
            }
        }
        return new int[] {leftResult, rightResult};
    }

    public int[] closestPrimes3(int left, int right) {
        int range = 1000000;
        int lastPrime = -10000000;
        int leftResult = -1;
        int rightResult = -1;

        if (left == 1) {
            left = 2;
        }

        if (right == 1) {
            return new int[] {-1, -1};
        }

        LinkedList<Integer> ll = new LinkedList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997));
        boolean[] sieve = new boolean[right - left + 1];
        for (int i = 0; i < sieve.length; i++) {
            if (sieve[i]) {
                continue; // the number got filtered out
            }
            int num = i + left;
            boolean isPrime = true;
            ListIterator<Integer> it = ll.listIterator();
            while (it.hasNext()) {
                int prime = it.next();
                if (num % prime == 0) {
                    isPrime = num == prime;
                    for (int k = i; k < sieve.length; k += prime) {
                        sieve[k] = true;
                    }
                    it.remove();
                    break;
                }
            }
            if (isPrime) {
                if (num - lastPrime < range) {
                    leftResult = lastPrime;
                    rightResult = num;
                    range = rightResult - leftResult;
                }
                lastPrime = num;
            }
        }
        return new int[] {leftResult, rightResult};
    }

    public int[] closestPrimes2(int left, int right) {
        int range = 1000000;
        int lastPrime = -10000000;
        int leftResult = -1;
        int rightResult = -1;
        if (left % 2 == 0) {
            left++;
        }
        if (left <= 2) {
            lastPrime = 2;
            left = 3;
        }
        for (int i = left; i <= right; i+=2) {
            boolean isPr = true;
            for (int prime : primesToCheck) {
                if (i == prime) {
                    break;
                }
                if (i % prime == 0) {
                    isPr = false;
                    break;
                }
            }
            if (isPr) {
                if (i - lastPrime < range) {
                    leftResult = lastPrime;
                    rightResult = i;
                    range = rightResult - leftResult;
                }
                lastPrime = i;
            }
        }
        return new int[] {leftResult, rightResult};
    }

    public boolean isPrime(int num) {
        for (int j : primesToCheck) {
            if (num % j == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String... args) {
        Solution2523 solution = new Solution2523();
        System.out.println(Arrays.toString(solution.closestPrimes(1, 1000000)));
        System.out.println(Arrays.toString(solution.closestPrimes(1, 1)));
        System.out.println(Arrays.toString(solution.closestPrimes(84084, 407043)));
        System.out.println(Arrays.toString(solution.closestPrimes(19, 31)));
        System.out.println(Arrays.toString(solution.closestPrimes(10, 19)));
        System.out.println(Arrays.toString(solution.closestPrimes(4, 6)));
    }
}
