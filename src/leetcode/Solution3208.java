package leetcode;

public class Solution3208 {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int result = 0;
        // first we need to find the starting point
        int startingPoint = -1;
        if (colors[colors.length - 1] == colors[0]) {
            startingPoint = 0;
        } else {
            for (int i = 1; i < colors.length; i++) {
                if (colors[i - 1] == colors[i]) {
                    startingPoint = i;
                    break;
                }
            }
        }
        // if no starting point is found, the circle is one continuous pattern
        if (startingPoint == -1) {
            return colors.length;
        }


        for (int i = startingPoint; i < colors.length; i++) {
            // find the next end point
            int endPoint = -1;
            int length = -1;
            for (int j = i; j < colors.length - 1; j++) {
                if (colors[j] == colors[j + 1]) {
                    endPoint = j;
                    length = j - i + 1;
                    break;
                }
            }

            // if no end point is found, the pattern continues from the start
            if (endPoint == -1) {
                length = colors.length - i + startingPoint;
                if (length >= k) {
                    result = result + length - k + 1;
                }
                return result;
            }

            // we add the length - k to the result
            if (length >= k) {
                result = result + length - k + 1;
            }

            i = endPoint;

//            // find the next starting point
//            for (int j = endPoint + 1; j < colors.length - 1; j++) {
//                if (colors[j] != colors[j + 1]) {
//                    i = j;
//                    break;
//                }
//            }
        }
        return result;
    }

    public static void main(String... args) {
        Solution3208 solution = new Solution3208();
        System.out.println(solution.numberOfAlternatingGroups(new int[] {0,1,0,1,0}, 3));
        System.out.println(solution.numberOfAlternatingGroups(new int[] {0,1,0,0,1,0,1}, 6));
        System.out.println(solution.numberOfAlternatingGroups(new int[] {1,1,0,1}, 4));
        System.out.println(solution.numberOfAlternatingGroups(new int[] {0,0,0,1}, 3));
        System.out.println(solution.numberOfAlternatingGroups(new int[] {0,1,0,0}, 3));
        System.out.println(solution.numberOfAlternatingGroups(new int[] {0,1,0,1,0,0}, 3));
    }
}
