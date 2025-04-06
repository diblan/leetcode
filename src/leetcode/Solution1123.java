package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Definition for a binary tree node.
 *public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution1123 {
    int depth = 0;
    int maxDepth = 0;
    int resultDepth = Integer.MAX_VALUE;
    TreeNode result = null;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        postOrderTraverse(root);
        return result;
    }

    public int postOrderTraverse(TreeNode n) {
        if (n == null) {
            return depth;
        }
        depth++;
        if (depth > maxDepth) {
            maxDepth = depth;
            result = null;
        }
        int l = postOrderTraverse(n.left);
        int r = postOrderTraverse(n.right);
        boolean deep = l == r && l == maxDepth;
        if (deep && (result == null || depth < resultDepth)) {
            resultDepth = depth;
            result = n;
        }
        depth--;
        return Math.max(l, r);
    }

    public static TreeNode create(Integer[] input) {
        TreeNode[] ts = new TreeNode[input.length];
        ts[0] = new TreeNode(input[0]);
        for (int i = 1; i < input.length; i++) {
            if (input[i] != null) {
                ts[i] = new TreeNode(input[i]);
                int n = (i - 1) / 2;
                boolean left = i % 2 == 1;
                if (left) {
                    ts[n].left = ts[i];
                } else {
                    ts[n].right = ts[i];
                }
            }
        }
        return ts[0];
    }

    public static void main(String... args) {
        Solution1123 solution = new Solution1123();
        System.out.println(solution.lcaDeepestLeaves(create(new Integer[] {3,5,1,6,2,0,8,null,null,7,4})));
    }
}
