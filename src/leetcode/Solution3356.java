package leetcode;

public class Solution3356 {
    public int minZeroArray(int[] nums, int[][] queries) {
        int[] diff = new int[nums.length + 1];
        diff[0] = nums[0];
        diff[nums.length] = 0;
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }

        int depth = 0;
        int target = diff[0];
        for (int i = 0; i < nums.length; i++) {
            int newDepth = depth;
            while (target > 0) {
                // increase depth until morale improves
                if (newDepth == queries.length) {
                    return -1; // couldn't search any deeper
                }
                if (queries[newDepth][0] <= i && queries[newDepth][1] >= i) {
                    target -= queries[newDepth][2];
                }
                newDepth++;
            }

            // apply all query updates to future diff cells
            for (int j = depth; j < newDepth; j++) {
                if (queries[j][0] > i) {
                    diff[queries[j][0]] -= queries[j][2];
                }
                if (queries[j][1] >= i) {
                    diff[queries[j][1] + 1] += queries[j][2];
                }
            }

            // prep next
            depth = newDepth;
            target += diff[i + 1];
        }

        return depth;
    }

    public int minZeroArray2(int[] nums, int[][] queries) {
        int[][] diff = new int[queries.length + 1][nums.length];

        for (int i = 0; i < queries.length; i++) {
            int count = i + 1;
            while (count < diff.length) {
                diff[count][queries[i][0]] += queries[i][2];
                if (queries[i][1] < nums.length - 1) {
                    diff[count][queries[i][1] + 1] -= queries[i][2];
                }
                count++;
            }
        }

        int depth = 0;
        int delta = 0;
        for (int i = 0; i < nums.length; i++) {
            int target = nums[i];
            int curr = delta + diff[depth][i];

            while (curr < target) {
                // increase depth until morale improves
                if (depth == queries.length) {
                    return -1; // couldn't search any deeper
                }
                if (queries[depth][0] <= i && queries[depth][1] >= i) {
                    curr += queries[depth][2];
                }
                depth++;
            }
            delta = curr;
        }

//        int depth = 0;
//        int delta = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] <= delta) {
//                continue;
//            }
//            do {
//                depth++;
//                if (depth == diff.length) {
//                    return -1; // couldn't search any deeper
//                }
//            } while (nums[i] > delta + diff[depth][i]);
//            delta = diff[depth][i];
//        }
        return depth;
    }

    public static void main(String... args) {
        Solution3356 solution = new Solution3356();
        System.out.println(solution.minZeroArray(new int[] {2,0,2}, new int[][] {{0,2,1},{0,2,1},{1,1,3}})); // 2
        System.out.println(solution.minZeroArray(new int[] {4,3,2,1}, new int[][] {{1,3,2},{0,2,1}}));  // -1
        System.out.println(solution.minZeroArray(new int[] {5}, new int[][] {{0,0,5},{0,0,1},{0,0,3},{0,0,2}})); // 1
        System.out.println(solution.minZeroArray(new int[] {0}, new int[][] {{0,0,2},{0,0,4},{0,0,4},{0,0,3},{0,0,5}})); // 0
        System.out.println(solution.minZeroArray(new int[] {0,8}, new int[][] {{0,1,4},{0,1,1},{0,1,4},{0,1,1},{1,1,5}})); // 3
        System.out.println(solution.minZeroArray(new int[] {1,1}, new int[][] {{0,0,1},{1,1,5},{0,1,5},{1,1,1}})); // 2
    }
}
