package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution3372 {
    // this solution has a very spread out Runtime graph with this being 401ms (40.51%)
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        List<List<Integer>> tree1 = buildTree(edges1, n);
        List<List<Integer>> tree2 = buildTree(edges2, m);

        // Precompute reachable counts within distance k for tree1
        int[] tree1Reach = new int[n];
        for (int i = 0; i < n; i++) {
            tree1Reach[i] = bfsCountWithinDistance(tree1, i, k);
        }

        // Precompute reachable counts within distance (k - 1) for tree2
        int[] tree2Reach = new int[m];
        for (int i = 0; i < m; i++) {
            tree2Reach[i] = bfsCountWithinDistance(tree2, i, k - 1);
        }

        int maxInTree2 = Arrays.stream(tree2Reach).max().orElse(0);

        // Combine results
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = tree1Reach[i] + maxInTree2;
        }

        return result;
    }

    private List<List<Integer>> buildTree(int[][] edges, int n) {
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) tree.add(new ArrayList<>());
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
        return tree;
    }

    private int bfsCountWithinDistance(List<List<Integer>> tree, int start, int maxDist) {
        if (maxDist < 0) return 0;

        boolean[] visited = new boolean[tree.size()];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int dist = curr[1];
            count++;

            if (dist == maxDist) continue;

            for (int neighbor : tree.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(new int[]{neighbor, dist + 1});
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution3372 sol = new Solution3372();

        int[][] edges1_1 = {{0,1},{0,2},{2,3},{2,4}};
        int[][] edges2_1 = {{0,1},{0,2},{0,3},{2,7},{1,4},{4,5},{4,6}};
        int k1 = 2;
        System.out.println("Example 1: " + Arrays.toString(sol.maxTargetNodes(edges1_1, edges2_1, k1)));

        int[][] edges1_2 = {{0,1},{0,2},{0,3},{0,4}};
        int[][] edges2_2 = {{0,1},{1,2},{2,3}};
        int k2 = 1;
        System.out.println("Example 2: " + Arrays.toString(sol.maxTargetNodes(edges1_2, edges2_2, k2)));
    }
}
