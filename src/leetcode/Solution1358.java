package leetcode;

import java.util.Iterator;
import java.util.LinkedList;

public class Solution1358 {
    public int numberOfSubstrings(String s) {
        int result = 0;
        LinkedList<Integer> as = new LinkedList<>();
        LinkedList<Integer> bs = new LinkedList<>();
        LinkedList<Integer> cs = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 'c') {
                if (s.charAt(i) < 'b') {
                    as.add(i);
                } else {
                    bs.add(i);
                }
            } else {
                cs.add(i);
            }
        }

        Iterator<Integer> ita = as.iterator();
        Iterator<Integer> itb = bs.iterator();
        Iterator<Integer> itc = cs.iterator();

        int anext = -1;
        int bnext = -1;
        int cnext = -1;

        if (ita.hasNext()) {
            anext = ita.next();
        } else {
            return result;
        }

        if (itb.hasNext()) {
            bnext = itb.next();
        } else {
            return result;
        }

        if (itc.hasNext()) {
            cnext = itc.next();
        } else {
            return result;
        }

        while (true) {
            if (anext > bnext) {
                if (anext > cnext) {
                    result += s.length() - anext;
                } else {
                    result += s.length() - cnext;
                }
            } else {
                if (bnext > cnext) {
                    result += s.length() - bnext;
                } else {
                    result += s.length() - cnext;
                }
            }

            if (anext < bnext) {
                if (anext < cnext) {
                    if (ita.hasNext()) {
                        anext = ita.next();
                    } else {
                        return result;
                    }
                } else {
                    if (itc.hasNext()) {
                        cnext = itc.next();
                    } else {
                        return result;
                    }
                }
            } else {
                if (bnext < cnext) {
                    if (itb.hasNext()) {
                        bnext = itb.next();
                    } else {
                        return result;
                    }
                } else {
                    if (itc.hasNext()) {
                        cnext = itc.next();
                    } else {
                        return result;
                    }
                }
            }
        }
    }

    public static void main(String... args) {
        Solution1358 solution = new Solution1358();
        System.out.println(solution.numberOfSubstrings("abcabc"));
        System.out.println(solution.numberOfSubstrings("aaacb"));
        System.out.println(solution.numberOfSubstrings("abc"));
    }
}
