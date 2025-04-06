package leetcode;

import java.util.Arrays;

public class Solution3108 {
    int[] parent;
    int[] rank;
    int[] weights;

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        int r = n + 1;
        // make the set
        parent = new int[r];
        rank = new int[r];
        weights = new int[r];
        for (int i = 0; i < r; i++) {
            parent[i] = i;
            weights[i] = -1;
        }

        // build the disjoint set
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }

        // store the weights
        for (int[] edge : edges) {
            int root = find(edge[0]);
            weights[root] &= edge[2];
        }

        return Arrays.stream(query).mapToInt(q -> {
            int root = find(q[0]);
            if (root != find(q[1])) {
                return -1;
            }
            return weights[root];
        }).toArray();
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;

        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    public static void main(String... args) {
        Solution3108 solution = new Solution3108();
        System.out.println(Arrays.toString(solution.minimumCost(5, new int[][]{{0, 1, 7}, {1, 3, 7}, {1, 2, 1}}, new int[][]{{0, 3}, {3, 4}}))); // [1, -1]
        System.out.println(Arrays.toString(solution.minimumCost(3, new int[][]{{0, 2, 7}, {0, 1, 15}, {1, 2, 6}, {1, 2, 1}}, new int[][]{{1, 2}}))); // [0]
    }
}
