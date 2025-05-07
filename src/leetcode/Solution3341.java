package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution3341 {
    public static final int[][] DIRS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // directions
    private int m;
    private int n;

    // Now this is a lot faster at 13ms (20.25%)
    // I got rid of the 2D <-> 1D translations with dividing and modulo
    // I thought I did that by turning it into an adjacency matrix but apparently building that matrix is expensive?
    // Getting rid of path as it is not necessary
    // Keeping track of the distance in the PQ also makes it faster
    // Moving the "getNeighbours" in to the method makes it go from 11ms -> 8ms
    // Using the Cardinal Directions trick did not improve time
    // Using '(a, b) -> a[0] - b[0]' to compare made me go from 8ms -> 6ms (98.75%)
    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length;
        int n = moveTime[0].length;
        int[] dirs = {-1, 0, 1, 0, -1};
        int[][] dist = new int[m][n];  // distance to the cell so far
        for (var row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);   // set distance to all cells to max and improve on that
        }
        dist[0][0] = 0;    // start node is always dist 0

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, 0, 0});
        while (true) {
            int[] cur = pq.poll();
            int d = cur[0];
            int i = cur[1];
            int j = cur[2];
            if (i == m - 1 && j == n - 1) {
                return d;
            }

            for (int k = 0; k < 4; k++) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (0 <= x && 0 <= y && x < m && y < n) {
                    int dis = Math.max(moveTime[x][y] + 1, d + 1);
                    if (dis < dist[x][y]) {
                        dist[x][y] = dis;
                        pq.add(new int[] {dis, x, y});
                    }
                }
            }
        }
    }

    // slightly faster at 141ms (5.61%)
    public int minTimeToReach4(int[][] moveTime) {
        m = moveTime.length;
        n = moveTime[0].length;
        int dim = m * n;
        int[][] adjMatrix = new int[dim][dim]; // adjacency matrix
        for (int i = 0; i < dim; i++) {
            Arrays.fill(adjMatrix[i], -1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = i * n + j;
                int nextLeft = cur + 1;
                int nextUnder = cur + n;
                if (i != m - 1) {
                    adjMatrix[cur][nextUnder] = moveTime[i + 1][j];
                    adjMatrix[nextUnder][cur] = moveTime[i][j];
                }
                if (j != n - 1) {
                    adjMatrix[cur][nextLeft] = moveTime[i][j + 1];
                    adjMatrix[nextLeft][cur] = moveTime[i][j];
                }
            }
        }

        int end = dim - 1;
        boolean[] path = new boolean[dim];  // if the cell is included in the shortest path or not
        int[] dist = new int[dim];  // distance to the cell so far
        Arrays.fill(dist, Integer.MAX_VALUE);   // set distance to all cells to max and improve on that
        dist[0] = 0;    // start node is always dist 0
        while (true) {
            int cur = end;
            int max = Integer.MAX_VALUE;
            for (int i = 0; i < dim; i++) {
                if (!path[i] && dist[i] < max) {
                    cur = i;
                    max = dist[cur];
                }
            }
            if (cur == end) {
                return dist[cur];
            }
            for (int i = 0; i < dim; i++) {
                if (adjMatrix[cur][i] > -1 && !path[i]) {
                    int dis = dist[cur] < adjMatrix[cur][i] ? adjMatrix[cur][i] + 1 : dist[cur] + 1;
                    if (dis < dist[i]) {
                        dist[i] = dis;
                    }
                }
            }
            path[cur] = true;
        }
    }

    // This one was actually much worse than with a PriorityQueue at 217ms (5.61%)
    public int minTimeToReach3(int[][] moveTime) {
        m = moveTime.length;
        n = moveTime[0].length;
        int dim = m * n;
        int[][] adjMatrix = new int[dim][dim]; // adjacency matrix
        for (int i = 0; i < dim; i++) {
            Arrays.fill(adjMatrix[i], -1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = i * n + j;
                int nextLeft = cur + 1;
                int nextUnder = cur + n;
                if (i != m - 1) {
                    adjMatrix[cur][nextUnder] = moveTime[i + 1][j];
                    adjMatrix[nextUnder][cur] = moveTime[i][j];
                }
                if (j != n - 1) {
                    adjMatrix[cur][nextLeft] = moveTime[i][j + 1];
                    adjMatrix[nextLeft][cur] = moveTime[i][j];
                }
            }
        }

        int end = dim - 1;
        boolean[] path = new boolean[dim];  // if the cell is included in the shortest path or not
        int[] dist = new int[dim];  // distance to the cell so far
        Arrays.fill(dist, Integer.MAX_VALUE);   // set distance to all cells to max and improve on that
        dist[0] = 0;    // start node is always dist 0
        LinkedList<Integer> pq = new LinkedList<>();
        pq.add(0);
        while (!pq.isEmpty()) {
            int cur = Collections.min(pq, Comparator.comparing(e -> dist[e]));
            if (cur == end) {
                return dist[cur];
            }
            pq.removeFirstOccurrence(cur);
            for (int i = 0; i < dim; i++) {
                if (adjMatrix[cur][i] > -1 && !path[i]) {
                    int dis = dist[cur] < adjMatrix[cur][i] ? adjMatrix[cur][i] + 1 : dist[cur] + 1;
                    if (dis < dist[i]) {
                        dist[i] = dis;
                        pq.add(i);
                    }
                }
            }
            path[cur] = true;
        }

        return -1;
    }

    // This iteration is much better but still slow at 92ms (5.61%)
    // Maybe an adjacency matrix can make it faster
    public int minTimeToReach2(int[][] moveTime) {
        m = moveTime.length;
        n = moveTime[0].length;
        int dim = m * n;
        int end = dim - 1;
        boolean[] path = new boolean[dim];  // if the cell is included in the shortest path or not
        int[] dist = new int[dim];  // distance to the cell so far
        Arrays.fill(dist, Integer.MAX_VALUE);   // set distance to all cells to max and improve on that
        dist[0] = 0;    // start node is always dist 0
        LinkedList<Integer> pq = new LinkedList<>();
        pq.add(0);
        while (!pq.isEmpty()) {
            int cur = Collections.min(pq, Comparator.comparing(e -> dist[e]));
            if (cur == end) {
                return dist[cur];
            }
            pq.removeFirstOccurrence(cur);
            int i = cur / n;
            int j = cur % n;
            List<int[]> neighbours = getNeighbours(i, j);
            for (int[] neighbour : neighbours) {
                int pos = (neighbour[0] * n) + neighbour[1];
                if (!path[pos]) {
                    int dis = dist[cur] < moveTime[neighbour[0]][neighbour[1]] ? moveTime[neighbour[0]][neighbour[1]] + 1 : dist[cur] + 1;
                    if (dis < dist[pos]) {
                        dist[pos] = dis;
                        pq.add(pos);
                    }
                }
            }
            path[cur] = true;
        }
        return -1;
    }

    // Still really slow, but slightly faster than my previous solution at 139ms (5.61%)
    // I think it's not the modulo operations that make it slow but the PriorityQueue
    public int minTimeToReach1(int[][] moveTime) {
        m = moveTime.length;
        n = moveTime[0].length;
        int dim = m * n;
        int[][] adjMatrix = new int[dim][dim]; // adjacency matrix
        for (int i = 0; i < dim; i++) {
            Arrays.fill(adjMatrix[i], -1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = i * n + j;
                int nextLeft = cur + 1;
                int nextUnder = cur + n;
                if (i != m - 1) {
                    adjMatrix[cur][nextUnder] = moveTime[i + 1][j];
                    adjMatrix[nextUnder][cur] = moveTime[i][j];
                }
                if (j != n - 1) {
                    adjMatrix[cur][nextLeft] = moveTime[i][j + 1];
                    adjMatrix[nextLeft][cur] = moveTime[i][j];
                }
            }
        }

        int end = dim - 1;
        boolean[] path = new boolean[dim];  // if the cell is included in the shortest path or not
        int[] dist = new int[dim];  // distance to the cell so far
        Arrays.fill(dist, Integer.MAX_VALUE);   // set distance to all cells to max and improve on that
        dist[0] = 0;    // start node is always dist 0
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing((Integer e) -> dist[e]));
        pq.add(0);
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            if (cur == end) {
                return dist[cur];
            }
            for (int i = 0; i < dim; i++) {
                if (adjMatrix[cur][i] > -1 && !path[i]) {
                    int dis = dist[cur] < adjMatrix[cur][i] ? adjMatrix[cur][i] + 1 : dist[cur] + 1;
                    if (dis < dist[i]) {
                        dist[i] = dis;
                        pq.add(i);
                    }
                }
            }
            path[cur] = true;
        }

        return -1;
    }

    // this solution works but is incredibly slow at 173ms (5.61%)
    // I take it that all the conversion with modulo slows it down quite a bit
    // I should try with a proper adjacency matrix
    public int minTimeToReach0(int[][] moveTime) {
        m = moveTime.length;
        n = moveTime[0].length;
        int dim = m * n;
        int end = dim - 1;
        boolean[] path = new boolean[dim];  // if the cell is included in the shortest path or not
        int[] dist = new int[dim];  // distance to the cell so far
        Arrays.fill(dist, Integer.MAX_VALUE);   // set distance to all cells to max and improve on that
        dist[0] = 0;    // start node is always dist 0
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing((Integer e) -> dist[e]));
        pq.add(0);
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            if (cur == end) {
                return dist[cur];
            }
            int i = cur / n;
            int j = cur % n;
            List<int[]> neighbours = getNeighbours(i, j);
            for (int[] neighbour : neighbours) {
                int pos = (neighbour[0] * n) + neighbour[1];
                if (!path[pos]) {
                    int dis = dist[cur] < moveTime[neighbour[0]][neighbour[1]] ? moveTime[neighbour[0]][neighbour[1]] + 1 : dist[cur] + 1;
                    if (dis < dist[pos]) {
                        dist[pos] = dis;
                        pq.add(pos);
                    }
                }
            }
            path[cur] = true;
        }
        return -1;
    }

    private List<int[]> getNeighbours(int i, int j) {
        List<int[]> neighbours = new ArrayList<>(4);
        for (int[] dir : DIRS) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (0 <= newI && 0 <= newJ && newI < m && newJ < n) {
                neighbours.add(new int[]{newI, newJ});
            }
        }
        return neighbours;
    }

    public static void main(String... args) {
        Solution3341 solution = new Solution3341();
        System.out.println(solution.minTimeToReach(new int[][] {{0,4},{4,4}})); // 6
        System.out.println(solution.minTimeToReach(new int[][] {{0,0,0},{0,0,0}})); // 3
        System.out.println(solution.minTimeToReach(new int[][] {{0,1},{1,2}})); // 3
        System.out.println(solution.minTimeToReach(new int[][] {{15,58},{67,4}})); // 60
        System.out.println(solution.minTimeToReach(new int[][] {{5,81,98},{74,54,81}})); // 82
        System.out.println(solution.minTimeToReach(new int[][] {{49,102,69,23},{62,67,80,41}})); // 82
    }
}
