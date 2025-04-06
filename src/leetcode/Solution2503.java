package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution2503 {

    public int[] maxPoints(int[][] grid, int[] queries) {
        int[][] queryMap = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            queryMap[i] = new int[] {queries[i], i};
        }
        Arrays.sort(queryMap, Comparator.comparingInt(a -> a[0]));

        // edge case
        if (grid[0][0] >= queryMap[queryMap.length - 1][0]) {
            return new int[queries.length];
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        Deque<int[]> low = new ArrayDeque<>();

        low.add(new int[] {0, 0, grid[0][0]});
        int cnt = 0;
        boolean[] visited = new boolean[grid.length * grid[0].length];
        visited[0] = true;
        int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] query : queryMap) {
            while (!(low.isEmpty() && pq.isEmpty())) {
                Queue<int[]> q;
                if (low.isEmpty()) {
                    q = pq;
                } else {
                    q = low;
                }
                if (q.peek()[2] < query[0]) {
                    int[] poll = q.poll();
                    cnt++;
                    // add neighbours
                    for (int j = 0; j < 4; j++) {
                        int m = poll[0] + dir[j][0];
                        int n = poll[1] + dir[j][1];
                        if (m > -1 && n > -1 && m < grid.length && n < grid[0].length && !visited[(m * grid[0].length) + n]) {
                            if (grid[m][n] < query[0]) {
                                low.add(new int[]{m, n, grid[m][n]});
                            } else {
                                pq.add(new int[]{m, n, grid[m][n]});
                            }
                            visited[(m * grid[0].length) + n] = true;
                        }
                    }
                } else {
                    break;
                }
            }
            queries[query[1]] = cnt;
        }
        return queries;
    }

    public int[] maxPoints2(int[][] grid, int[] queries) {
        int[][] queryMap = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            queryMap[i] = new int[] {queries[i], i};
        }
        Arrays.sort(queryMap, Comparator.comparingInt(a -> a[0]));

        // edge case
        if (grid[0][0] >= queryMap[queryMap.length - 1][0]) {
            return new int[queries.length];
        }

        List<int[]> low = new ArrayList<>();
        List<int[]> high = new ArrayList<>();
        high.add(new int[] {0, 0, grid[0][0]});

        int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean[] visited = new boolean[grid.length * grid[0].length];
        visited[0] = true;

        int cnt = 0;
        int lowI = 0;
        int highI = 0;
        for (int[] query : queryMap) {
            boolean highAdded = false;
            while (!(lowI == low.size() && highI == high.size())) {
                int[] next;
                if (lowI < low.size()) {
                    next = low.get(lowI);
                } else {
                    next = high.get(highI);
                }
                if (next[2] < query[0]) {
                    cnt++;
                    if (lowI < low.size()) {
                        lowI++;
                    } else {
                        highI++;
                    }
                    // add neighbours
                    for (int j = 0; j < 4; j++) {
                        int m = next[0] + dir[j][0];
                        int n = next[1] + dir[j][1];
                        if (m > -1 && n > -1 && m < grid.length && n < grid[0].length && !visited[(m * grid[0].length) + n]) {
                            if (grid[m][n] < query[0]) {
                                low.add(new int[]{m, n, grid[m][n]});
                            } else {
                                high.add(new int[]{m, n, grid[m][n]});
                                highAdded = true;
                            }
                            visited[(m * grid[0].length) + n] = true;
                        }
                    }
                } else {
                    break;
                }
            }
            queries[query[1]] = cnt;
            if (highAdded) {
                high.subList(highI, high.size()).sort(Comparator.comparingInt(a -> a[2]));
            }
        }
        return queries;
    }

    public int[] maxPoints1(int[][] grid, int[] queries) {
        int[] result = new int[queries.length];
        int[][] queryMap = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            queryMap[i] = new int[] {queries[i], i};
        }
        Arrays.sort(queryMap, Comparator.comparingInt(a -> a[0]));

        // edge case
        if (grid[0][0] >= queryMap[queryMap.length - 1][0]) {
            return result;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        pq.add(new int[] {0, 0, grid[0][0], 0});
        int cnt = 0;
        boolean[] visited = new boolean[grid.length * grid[0].length];
        int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] query : queryMap) {
            while (!pq.isEmpty()) {
                if (pq.peek()[2] < query[0]) {
                    int[] poll = pq.poll();
                    cnt++;
                    // add neighbours
                    for (int j = poll[3], c = 3; c > 0; j++, c--) {
                        int m = poll[0] + dir[j][0];
                        int n = poll[1] + dir[j][1];
                        if (m > -1 && n > -1 && m < grid.length && n < grid[0].length && !visited[(m * grid[0].length) + n]) {
                            pq.add(new int[]{m, n, grid[m][n], (j + 3) % 4});
                            visited[(m * grid[0].length) + n] = true;
                        }
                    }
                } else {
                    break;
                }
            }
            result[query[1]] = cnt;
        }
        return result;
    }

    public static void main(String... args) {
        Solution2503 solution = new Solution2503();
        System.out.println(Arrays.toString(solution.maxPoints(new int[][]{{1, 2, 3}, {2, 5, 7}, {3, 5, 1}}, new int[]{5, 6, 2}))); // [5,8,1]
        System.out.println(Arrays.toString(solution.maxPoints(new int[][]{{5, 2, 1}, {1, 1, 2}}, new int[]{3}))); // [0]
        System.out.println(Arrays.toString(solution.maxPoints(new int[][]{{420766,806051,922751},{181527,815280,904568},{952102,4037,140319},{324081,17907,799523},{176688,90257,83661},{932477,621193,623068},{135839,554701,511427},{227575,450848,178065},{785644,204668,835141},{313774,167359,501496},{641317,620688,74989},{324499,122376,270369},{2121,887154,848859},{456704,7763,662087},{286827,145349,468865},{277137,858176,725551},{106131,93684,576512},{372563,944355,497187},{884187,600892,268120},{576578,515031,807686}}, new int[]{352655,586228,169685,541073,584647,413832,576537,616413}))); // [0,2,0,2,2,0,2,2]
    }
}
