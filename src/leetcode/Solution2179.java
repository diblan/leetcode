package leetcode;

import java.util.HashMap;
import java.util.Map;

// Very hard, I only partially understand Fenwick
// Calculating the right part I don't quite understand
// Main takeaway: Use Fenwick as a boolean[] to count from a certain index what has been processed
public class Solution2179 {
    public long goodTriplets(int[] nums1, int[] nums2) {
        long result = 0;
        int n = nums1.length;
        int[] map = new int[n];
        for (int i = 0; i < n; i++) {
            map[nums2[i]] = i + 1;
        }

        FenwickTree tree = new FenwickTree(n + 1);

        for (int num : nums1) {
            int i = map[num];
            long left = tree.query(i);
            long right = n - i - (tree.query(n) - tree.query(i));
            result += left * right;
            tree.update(i, 1);
        }

        return result;
    }

    static class FenwickTree {
        private final int[] tree;
        private final int size;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1]; // 1-based indexing
        }

        // Adds value to element at index (0-based)
        public void update(int index, int value) {
            index += 1; // convert to 1-based
            while (index <= size) {
                tree[index] += value;
                index += index & -index;
            }
        }

        // Returns sum of elements [0..index] (0-based)
        public int query(int index) {
            index += 1; // convert to 1-based
            int result = 0;
            while (index > 0) {
                result += tree[index];
                index -= index & -index;
            }
            return result;
        }
    }

    // time limit exceeded 103/148
    public long goodTriplets0(int[] nums1, int[] nums2) {
        long result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }
        for (int i = 0; i < nums2.length; i++) {
            nums2[i] = map.get(nums2[i]);
        }

        for (int i = 0; i < nums2.length - 2; i++) {
            for (int j = i + 1; j < nums2.length - 1; j++) {
                for (int k = j + 1; k < nums2.length; k++) {
                    if (nums2[i] < nums2[j] && nums2[j] < nums2[k]) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String... args) {
        Solution2179 solution = new Solution2179();
        System.out.println(solution.goodTriplets(new int[] {2,0,1,3}, new int[] {0,1,2,3})); // 1
        System.out.println(solution.goodTriplets(new int[] {4,0,1,3,2}, new int[] {4,1,0,2,3})); // 4
    }
}
