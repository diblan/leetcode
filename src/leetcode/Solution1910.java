package leetcode;

public class Solution1910 {
    public String removeOccurrences(String s, String part) {
        while (s.contains(part)) {
            s = s.replaceFirst(part, "");
        }
        return s;
    }
    public String removeOccurrencesImproved(String s, String part) {
        StringBuilder sb = new StringBuilder(s);
        int i = sb.indexOf(part);
        while (i > -1) {
            sb.delete(i, i + part.length());
            i = sb.indexOf(part);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution1910 solution = new Solution1910();
        System.out.println(solution.removeOccurrencesImproved("eemckxmckx", "emckx"));
        System.out.println(solution.removeOccurrencesImproved("daabcbaabcbc", "abc"));
    }
}
