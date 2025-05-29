package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution3373 {
    // Runtime of 146ms (49.15%)
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        List<List<Integer>> tree1 = buildGraph(n, edges1);
        List<List<Integer>> tree2 = buildGraph(m, edges2);

        // Step 1: preprocess tree2 with BFS
        int[] depth2 = getDepths(tree2, 0);
        int even2 = 0, odd2 = 0;
        for (int d : depth2) {
            if (d % 2 == 0) even2++;
            else odd2++;
        }
        int bestInTree2 = Math.max(even2, odd2);

        // Step 2: preprocess tree1 with DFS
        int[] even = new int[n];
        int[] odd = new int[n];

        dfs(tree1, 0, -1, even, odd);

        // Step 3: rerooting dfs to update counts for all nodes
        int[] result = new int[n];
        reroot(tree1, 0, -1, even, odd, bestInTree2, result);

        return result;
    }

    private List<List<Integer>> buildGraph(int size, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < size; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    private int[] getDepths(List<List<Integer>> graph, int root) {
        int[] depth = new int[graph.size()];
        Arrays.fill(depth, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        depth[root] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (depth[neighbor] == -1) {
                    depth[neighbor] = depth[node] + 1;
                    queue.offer(neighbor);
                }
            }
        }
        return depth;
    }

    // Post-order DFS to compute even/odd subtree counts from root
    private void dfs(List<List<Integer>> tree, int node, int parent, int[] even, int[] odd) {
        even[node] = 1; // node itself at distance 0 (even)
        for (int child : tree.get(node)) {
            if (child != parent) {
                dfs(tree, child, node, even, odd);
                // flip parity for counts when moving up
                even[node] += odd[child];
                odd[node] += even[child];
            }
        }
    }

    // Rerooting DFS to compute result for each node in constant time
    private void reroot(List<List<Integer>> tree, int node, int parent, int[] even, int[] odd, int bestInTree2, int[] result) {
        result[node] = even[node] + bestInTree2;
        for (int child : tree.get(node)) {
            if (child != parent) {
                // backup original values
                int evenNode = even[node], oddNode = odd[node];
                int evenChild = even[child], oddChild = odd[child];

                // Remove child's contribution from parent
                even[node] -= odd[child];
                odd[node] -= even[child];

                // Add parent contribution to child
                even[child] += odd[node];
                odd[child] += even[node];

                reroot(tree, child, node, even, odd, bestInTree2, result);

                // restore original values
                even[node] = evenNode;
                odd[node] = oddNode;
                even[child] = evenChild;
                odd[child] = oddChild;
            }
        }
    }

    public static void main(String[] args) {
        Solution3373 solver = new Solution3373();

        int[][] edges1a = {{0,1},{0,2},{2,3},{2,4}};
        int[][] edges2a = {{0,1},{0,2},{0,3},{2,7},{1,4},{4,5},{4,6}};
        System.out.println(Arrays.toString(solver.maxTargetNodes(edges1a, edges2a))); // [8,7,7,8,8]

        int[][] edges1b = {{0,1},{0,2},{0,3},{0,4}};
        int[][] edges2b = {{0,1},{1,2},{2,3}};
        System.out.println(Arrays.toString(solver.maxTargetNodes(edges1b, edges2b))); // [3,6,6,6,6]
    }
}
