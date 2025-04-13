package leetcode;

public class Solution1922 {
    public static long N = 1000000007L; // prime modulo value
    public int countGoodNumbers(long n) {
        long exp = n / 2;
        long result = 0;
        result = exponentiation(5, exp);
        result *= exponentiation(4, exp);
        result %= N;

        if (n % 2 == 1) {
            result *= 5;
            result %= N;
        }

        return (int) result;
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
        Solution1922 solution = new Solution1922();
        System.out.println(solution.countGoodNumbers(1)); // 5
        System.out.println(solution.countGoodNumbers(4)); // 400
        System.out.println(solution.countGoodNumbers(50)); // 564908303
    }
}
