package leetcode;

public class Solution1007 {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        for (int i = 1; i < 7; i++) {
            int resultTops = 0;
            int resultBottoms = 0;
            int j;
            for (j = 0; j < tops.length; j++) {
                if (tops[j] != i || bottoms[j] != i) {
                    if (tops[j] == i) {
                        resultBottoms++;
                    }
                    if (bottoms[j] == i) {
                        resultTops++;
                    }
                }
                if (tops[j] != i && bottoms[j] != i) {
                    break;
                }
            }
            if (j == tops.length) {
                return Math.min(resultTops, resultBottoms);
            }
        }
        return -1;
    }

    public static void main(String... args) {
        Solution1007 solution = new Solution1007();
        System.out.println(solution.minDominoRotations(new int[] {2,1,2,4,2,2}, new int[] {5,2,6,2,3,2})); // 2
        System.out.println(solution.minDominoRotations(new int[] {3,5,1,2,3}, new int[] {3,6,3,3,4})); // 2
        System.out.println(solution.minDominoRotations(new int[] {1,2,1,1,1,2,2,2}, new int[] {2,1,2,2,2,2,2,2})); // 1
    }
}
