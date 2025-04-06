package leetcode;

public class Solution2685 {
    int[] root;
    int[] rank;
    int[] cons;

    public int countCompleteComponents(int n, int[][] edges) {
        int result = 0;
        root = new int[n];
        rank = new int[n];
        cons = new int[n];

        // init parents
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }

        // build disjoint set
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
            cons[edge[0]]++;
            cons[edge[1]]++;
        }

        // count how many of the same root
        int[] size = new int[n];
        for (int roo : root) {
            size[roo]++;
        }

        int[][] agg = new int[n][];
        for (int i = 0; i < root.length; i++) {
            int roo = find(i);
            if (agg[roo] == null) {
                agg[roo] = new int[] {1, cons[roo]};
            } else if (agg[roo][1] != -1) {
                if (agg[roo][1] != cons[i]) {
                    agg[roo][1] = -1;
                } else {
                    agg[roo][0]++;
                }
            }
        }

        for (int[] ag : agg) {
            if (ag != null && (ag[0] - 1) == ag[1]) {
                result++;
            }
        }
        return result;
    }

    public int find(int x) {
        if (root[x] != x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;

        if (rank[rootX] > rank[rootY]) {
            root[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            root[rootX] = rootY;
        } else {
            root[rootY] = rootX;
            rank[rootX]++;
        }
    }

    public static void main(String... args) {
        Solution2685 solution = new Solution2685();
//        System.out.println(solution.countCompleteComponents(6, new int[][] {{0,1},{0,2},{1,2},{3,4}})); // 3
//        System.out.println(solution.countCompleteComponents(6, new int[][] {{0,1},{0,2},{1,2},{3,4},{3,5}})); // 1
        System.out.println(solution.countCompleteComponents(4, new int[][] {{2,0},{3,1},{3,2}})); // 0
    }
}
