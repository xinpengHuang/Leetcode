```
/*
Description
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

Thoughts
Solution 1: O(n * m) time and O(n * m) space, using DFS and dynamic programming.
*/

// Solution 1
class Solution {
    int[][] state;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        state = new int[matrix.length][matrix[0].length];
        int[] res = new int[1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (state[i][j] == 0) {
                    startWith(matrix, i, j, res);
                }
            }
        }
        return res[0];
    }
    private int startWith(int[][] matrix, int i, int j, int[] res) {
        if (state[i][j] != 0) {
            return state[i][j];
        }
        state[i][j] = 1;
        int[] dir = new int[] {1, 0, -1, 0 ,1};
        for (int k = 0; k < 4; k++) {
            int row = i + dir[k];
            int col = j + dir[k + 1];
            if (!(row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length)
                && matrix[i][j] < matrix[row][col]) {
                state[i][j] = Math.max(state[i][j], 1 + startWith(matrix, row, col, res));
            }
        }
        res[0] = Math.max(res[0], state[i][j]);
        return state[i][j];
    }
}
```
