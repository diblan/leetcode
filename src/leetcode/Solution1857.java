package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution1857 {
    // Hard problem solved by ChatGPT with a Runtime of 61ms (90.55%)
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];

        // Build the graph
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }

        // Topological Sort queue
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                queue.offer(i);

        int[][] dp = new int[n][26]; // dp[i][c]: max count of color c in any path ending at node i
        int visited = 0;
        int maxColorCount = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;
            int colorIndex = colors.charAt(node) - 'a';

            // Count the color of current node
            dp[node][colorIndex]++;
            maxColorCount = Math.max(maxColorCount, dp[node][colorIndex]);

            for (int neighbor : graph.get(node)) {
                for (int c = 0; c < 26; c++) {
                    dp[neighbor][c] = Math.max(dp[neighbor][c], dp[node][c]);
                }

                indegree[neighbor]--;
                if (indegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }

        return visited == n ? maxColorCount : -1;
    }

    public static void main(String[] args) {
        Solution1857 solution = new Solution1857();

        String colors1 = "abaca";
        int[][] edges1 = {{0,1},{0,2},{2,3},{3,4}};
        System.out.println("Example 1 Output: " + solution.largestPathValue(colors1, edges1)); // Expected: 3

        String colors2 = "a";
        int[][] edges2 = {{0,0}};
        System.out.println("Example 2 Output: " + solution.largestPathValue(colors2, edges2)); // Expected: -1
    }
}
