package leetcode;

public class Solution838 {
    public String pushDominoes(String dominoes) {
        StringBuilder result = new StringBuilder(dominoes);
        int[] l = new int[result.length()];
        int[] r = new int[result.length()];
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == 'R') {
                int k = Integer.MAX_VALUE;
                r[i] = k;
                while (++i < result.length()) {
                    if (result.charAt(i) == '.') {
                        r[i] = --k;
                    } else {
                        i--;
                        break;
                    }
                }
            } else if (result.charAt(i) == 'L') {
                int j = i;
                int k = Integer.MAX_VALUE;
                l[j] = k;
                while (--j > -1) {
                    if (result.charAt(j) == '.') {
                        l[j] = --k;
                    } else {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < result.length(); i++) {
            if (l[i] < r[i]) {
                result.setCharAt(i, 'R');
            } else if (l[i] > r[i]) {
                result.setCharAt(i, 'L');
            } else if (l[i] != 0) {
                result.setCharAt(i, '.');
            }
        }
        return result.toString();
    }

    // this is a worse solution at 25ms (41.89%)
    public String pushDominoes1(String dominoes) {
        StringBuilder result = new StringBuilder(dominoes);
        int[] l = new int[result.length()];
        int[] r = new int[result.length()];
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == 'R') {
                int k = Integer.MAX_VALUE;
                r[i] = k;
                while (++i < result.length()) {
                    if (result.charAt(i) == '.') {
                        r[i] = --k;
                    } else {
                        i--;
                        break;
                    }
                }
            }
        }
        for (int i = result.length() - 1; i > -1; i--) {
            if (result.charAt(i) == 'L') {
                int k = Integer.MAX_VALUE;
                l[i] = k;
                while (--i > -1) {
                    if (result.charAt(i) == '.') {
                        l[i] = --k;
                    } else {
                        i++;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < result.length(); i++) {
            if (l[i] < r[i]) {
                result.setCharAt(i, 'R');
            } else if (l[i] > r[i]) {
                result.setCharAt(i, 'L');
            } else if (l[i] != 0) {
                result.setCharAt(i, '.');
            }
        }
        return result.toString();
    }

    // not bad, not good solution, 21ms (57.36%)
    public String pushDominoes0(String dominoes) {
        StringBuilder result = new StringBuilder(dominoes);
        int[] l = new int[result.length()];
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == 'R') {
                int k = -1;
                while (++i < result.length()) {
                    if (result.charAt(i) == '.') {
                        result.setCharAt(i, 'R');
                        l[i] = k--;
                    } else if (result.charAt(i) == 'R') {
                        i--;
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
        for (int i = result.length() - 1; i > -1; i--) {
            if (result.charAt(i) == 'L') {
                int k = -1;
                while (--i > -1) {
                    if (result.charAt(i) == '.' || result.charAt(i) == 'R' && l[i] < k) {
                        result.setCharAt(i, 'L');
                        k--;
                    } else if (result.charAt(i) == 'R' && l[i] == k) {
                        result.setCharAt(i, '.');
                        break;
                    } else if (result.charAt(i) == 'L') {
                        i++;
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
        return result.toString();
    }

    public static void main(String... args) {
        Solution838 solution = new Solution838();
        System.out.println(solution.pushDominoes("RR.L")); // "RR.L"
        System.out.println(solution.pushDominoes(".L.R...LR..L..")); // "LL.RR.LLRRLL.."
        System.out.println(solution.pushDominoes(".L.R.")); // "LL.RR.LLRRLL.."
    }
}
