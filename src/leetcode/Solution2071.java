package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution2071 {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int result = 0;
        Arrays.sort(tasks);
        Arrays.sort(workers);
        List<Integer> leftoverW = new ArrayList<>();
        for (int i = 0, j = 0; i < tasks.length; i++) {
            while (tasks[i] > workers[j]) {
                leftoverW.add(workers[j]);
                if (++j > workers.length) {
                    break;
                }
            }
            if (j > workers.length) {
                break;
            }
            result++;
        }

        int i = 0;
        for (int j = 0; j < workers.length; j++) {
            
        }

        return result;
    }

    public static void main(String... args) {
        Solution2071 solution = new Solution2071();
        System.out.println(solution.maxTaskAssign(new int[] {3,2,1}, new int[] {0,3,3}, 1, 1)); // 3
        System.out.println(solution.maxTaskAssign(new int[] {5,4}, new int[] {0,0,0}, 1, 5)); // 1
        System.out.println(solution.maxTaskAssign(new int[] {10,15,30}, new int[] {0,10,10,10,10}, 3, 10)); // 2
    }
}
