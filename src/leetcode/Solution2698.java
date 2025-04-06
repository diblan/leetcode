package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution2698 {
    public int punishmentNumber(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (canPartition(i)) {
                sum = sum + (i * i);
                // System.out.println(i + " : " + i * i + " = " + sum);
                System.out.println("map.put(" + i + ", " + sum + ");");
            }
        };
        return sum;
    }

    public boolean canPartition(int i) {
        Stack<Integer> partitionSize = new Stack<>();
        Stack<Integer> goal = new Stack<>();
        Stack<Integer> sub = new Stack<>();
        // initiate the first step
        partitionSize.push(0);
        goal.push(i);
        sub.push(i * i);
        while (true) {
            // check the step if it is the solution
            if (goal.peek().intValue() == sub.peek().intValue()) {
                return true;
            }
            if (sub.peek() > goal.peek() && sub.peek() > 9 ) {
                // we split it further
                partitionSize.push(10);
            } else {
                if (partitionSize.peek() == 0) {
                    return false;
                }
                if (goal.peek() < 0) {
                    // dead end, go back a step and make that step bigger
                    partitionSize.pop();
                    if (partitionSize.isEmpty() || partitionSize.peek() == 0) {
                        return false;
                    }
                    goal.pop();
                    sub.pop();

                }
                goal.pop();
                sub.pop();
                partitionSize.push(partitionSize.pop() * 10);

            }
            goal.push(goal.peek() - (sub.peek() % partitionSize.peek()));
            sub.push(sub.peek() / partitionSize.peek());
        }
    }

    public int cheat(int i) {
        Map<Integer, Integer> map = new HashMap<>(29);
        map.put(1, 1);
        map.put(9, 82);
        map.put(10, 182);
        map.put(36, 1478);
        map.put(45, 3503);
        map.put(55, 6528);
        map.put(82, 13252);
        map.put(91, 21533);
        map.put(99, 31334);
        map.put(100, 41334);
        map.put(235, 96559);
        map.put(297, 184768);
        map.put(369, 320929);
        map.put(370, 457829);
        map.put(379, 601470);
        map.put(414, 772866);
        map.put(657, 1204515);
        map.put(675, 1660140);
        map.put(703, 2154349);
        map.put(756, 2725885);
        map.put(792, 3353149);
        map.put(909, 4179430);
        map.put(918, 5022154);
        map.put(945, 5915179);
        map.put(964, 6844475);
        map.put(990, 7824575);
        map.put(991, 8806656);
        map.put(999, 9804657);
        map.put(1000, 10804657);
        return 0;
    }

    public static void main(String[] args) {
        Solution2698 solution = new Solution2698();
        // System.out.println(solution.punishmentNumber(10)); // 182
        // System.out.println(solution.punishmentNumber(36)); // 1478
        System.out.println(solution.punishmentNumber(1000)); // 1478
    }
}
