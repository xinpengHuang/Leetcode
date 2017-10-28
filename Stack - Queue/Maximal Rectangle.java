```
/*
Description
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.

Thoughts
Solution 1: O(n^2) time and O(n) space, using stack.
*/

// Solution 1
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int res = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[] state = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    state[j]++;
                } else {
                    state[j] = 0;
                }
            }
            res = Math.max(res, largestRectangleArea(state));
        }
        return res;
    }
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i <= heights.length; i++){
            // add a zero height rectangular at the end to pop all remaining rectangulars out
            int bound = i < heights.length ? heights[i] : 0;
            while (!deque.isEmpty() && heights[deque.peek()] > bound) {
                int cur = deque.pop();
                // way to pick low index is critical
                int low = deque.isEmpty() ? -1 : deque.peek();
                res = Math.max(res, heights[cur] * (i - low - 1));
            }
            deque.push(i);
        }
        return res;
    }
}
```
