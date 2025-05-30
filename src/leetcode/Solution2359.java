package leetcode;

import java.util.Arrays;

public class Solution2359 {
    // Runtime of 10ms (98.94%)
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;

        // Precompute distances from node1 and node2
        int[] dist1 = getDistances(edges, node1, n);
        int[] dist2 = getDistances(edges, node2, n);

        int minMaxDist = Integer.MAX_VALUE;
        int resultNode = -1;

        for (int i = 0; i < n; i++) {
            // Both must be reachable
            if (dist1[i] != -1 && dist2[i] != -1) {
                int maxDist = Math.max(dist1[i], dist2[i]);
                if (maxDist < minMaxDist) {
                    minMaxDist = maxDist;
                    resultNode = i;
                } else if (maxDist == minMaxDist && i < resultNode) {
                    resultNode = i;
                }
            }
        }

        return resultNode;
    }

    private int[] getDistances(int[] edges, int start, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        int current = start;
        int distance = 0;
        while (current != -1 && dist[current] == -1) {
            dist[current] = distance++;
            current = edges[current];
        }
        return dist;
    }

    public static void main(String[] args) {
        Solution2359 solver = new Solution2359();

        // Example 1
        int[] edges1 = {2, 2, 3, -1};
        int node1_1 = 0, node2_1 = 1;
        int result1 = solver.closestMeetingNode(edges1, node1_1, node2_1);
        System.out.println("Example 1 Output: " + result1); // Expected: 2

        // Example 2
        int[] edges2 = {1, 2, -1};
        int node1_2 = 0, node2_2 = 2;
        int result2 = solver.closestMeetingNode(edges2, node1_2, node2_2);
        System.out.println("Example 2 Output: " + result2); // Expected: 2

        // Additional example
        int[] edges3 = {1, 2, 3, 4, -1};
        int node1_3 = 0, node2_3 = 4;
        int result3 = solver.closestMeetingNode(edges3, node1_3, node2_3);
        System.out.println("Example 3 Output: " + result3); // Expected: 4
    }
}
