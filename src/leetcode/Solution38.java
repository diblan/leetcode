package leetcode;

public class Solution38 {
    // this solution is 3ms (56.86%) but there are many more solutions at 2ms
    // EDIT: afters submitting it as second time, I got 2ms
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String cas = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cas.length(); i++) {
            int c = 1;
            char next = i + 1 < cas.length() ? cas.charAt(i + 1) : '-';
            for (int j = i + 1; j < cas.length(); j++) {
                if (cas.charAt(j) == cas.charAt(i)) {
                    c++;
                } else {
                    break;
                }
            }
            sb.append(c).append(cas.charAt(i));
            i += c - 1;
        }
        return sb.toString();
    }

    public static void main(String... args) {
        Solution38 solution = new Solution38();
//        System.out.println(solution.countAndSay(4)); // 1211
//        System.out.println(solution.countAndSay(1)); // 1
        System.out.println(solution.countAndSay(5)); // 111221
    }
}
