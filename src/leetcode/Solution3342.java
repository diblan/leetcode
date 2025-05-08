package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution3342 {
    // Same as 3341 but added the increment to the int we offer to the pq with alternating by doing 3 - inc
    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime[0].length;
        int n = moveTime.length;
        int[][] dist = new int[n][m];
        for (int[] dis : dist) {
            Arrays.fill(dis, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // don't touch, this is fastest
        pq.offer(new int[] {0, 0, 0, 1});
        int[] dirs = new int[] {0, 1, 0, -1, 0}; // little trick on how to rotate between cardinal directions

        while(true) {
            int[] p = pq.poll();
            int d = p[0];
            int i = p[1];
            int j = p[2];
            int inc = p[3];

            if (d > dist[j][i]) {
                continue;
            }
            if (i == m - 1 && j == n - 1) { // escape condition
                return d;
            }

            for (int k = 0; k < 4; k++) {
                int a = i + dirs[k];
                int b = j + dirs[k + 1];
                if (a >= 0 && a < m && b >= 0 && b < n) {
                    int newD = Math.max(d + inc, moveTime[b][a] + inc);
                    if (newD < dist[b][a]) {
                        pq.offer(new int[] {newD, a, b, 3 - inc});
                        dist[b][a] = newD;
                    }
                }
            }
        }
    }

    public static void main(String... args) {
        Solution3342 solution = new Solution3342();
        System.out.println(solution.minTimeToReach(new int[][] {{0,4}, {4,4}})); // 7
        System.out.println(solution.minTimeToReach(new int[][] {{0,0,0,0}, {0,0,0,0}})); // 6
        System.out.println(solution.minTimeToReach(new int[][] {{0,1}, {1,2}})); // 4
    }
}
