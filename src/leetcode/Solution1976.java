package leetcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1976 {
    public int countPaths(int n, int[][] roads) {
        int mod = (int) (1e9+7); // REALLY???
        long[][] matrix = new long[n][n]; // adjacency matrix
        long[] shrt = new long[n]; // keep track of shortest path to a node
        List<Integer>[] neis = new List[n]; // keep a list of neighbours for each node
        long[] paths = new long[n]; // keep track of how many shortest paths lead to node
        paths[0] = 1; // there's only one path to the starting node
        boolean[] visited = new boolean[n]; // keep track of which nodes we have visited
        visited[visited.length - 1] = false;

        // init neighbours
        for (int i = 0; i < neis.length; i++) {
            neis[i] = new ArrayList<>(15);
        }

        // fill matrix
        for (int[] road : roads) {
            matrix[road[0]][road[1]] = road[2];
            matrix[road[1]][road[0]] = road[2];
            neis[road[0]].add(road[1]);
            neis[road[1]].add(road[0]);
        }

        // init shrt
        for (int i = 1; i < shrt.length; i++) {
            shrt[i] = Long.MAX_VALUE;
        }

        int node = 0;
        while (node >= 0) {
            for (int nei : neis[node]) {
                if (shrt[node] + matrix[node][nei] < shrt[nei]) {
                    shrt[nei] = shrt[node] + matrix[node][nei];
                    paths[nei] = paths[node] % mod;
                } else if (shrt[node] + matrix[node][nei] == shrt[nei]) {
                    paths[nei] = ((paths[nei] % mod) + (paths[node] % mod)) % mod;
                }
            }
            visited[node] = true;
            node = nextNode(shrt, visited);
        }

        return (int) (paths[n - 1] % mod);
    }

    public int nextNode(long[] shrt, boolean[] visited) {
        long min = Long.MAX_VALUE;
        int minNode = -1;
        for (int i = 0; i < shrt.length; i++) {
            if (!visited[i] && shrt[i] < min) {
                min = shrt[i];
                minNode = i;
            }
        }
        return minNode;
    }

    public static void main(String... args) {
        Solution1976 solution = new Solution1976();
        System.out.println(solution.countPaths(7, new int[][] {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}})); // 4
        System.out.println(solution.countPaths(2, new int[][] {{1,0,10}})); // 1


        String filePath = "resources/Solution1976-case50.txt"; // Change this to your file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int[][] roads = new int[3597][];
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                int[] values = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                roads[i] = values;
                i++;
            }
            System.out.println(solution.countPaths(122, roads)); // 211485723
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
