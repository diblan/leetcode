package leetcode;

public class Solution2379 {
    public int minimumRecolors(String blocks, int k) {
        int min = k;
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'B') {
                min--;
            }
        }
        int local = min;
        for (int i = k; i < blocks.length(); i++) {
            if (blocks.charAt(i) != blocks.charAt(i - k)) {
                if (blocks.charAt(i) == 'B') {
                    local--;
                    if (local < min) {
                        min = local;
                    }
                } else {
                    local++;
                }
            }
        }
        return min;
    }

    public static void main(String... args) {
        Solution2379 solution = new Solution2379();
        System.out.println(solution.minimumRecolors("WBBWWBBWBW", 7));
        System.out.println(solution.minimumRecolors("WBWBBBW", 2));
    }
}
