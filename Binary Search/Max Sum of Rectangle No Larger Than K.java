```
/*
Description
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

Thoughts
Solution 1: O(n ^ 2 * m ^ 2) time and O(n * m) space, using dynamics programming.
Solution 2: O(m ^ 2 * n * log(n)) and O(m) space, using prefix sum and binary search:
1. For a 1D array, we can store prefix sum and find out the max sum in O (n * log (n)) time.
2. For a 2d matrix, there are O (m ^ 2) numbers of different '1D arrays'.

*/

// Solution 1
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int res = Integer.MIN_VALUE;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] state = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            state[i][1] = matrix[i - 1][0] + state[i - 1][1];
        }
        for (int j = 1; j <= m; j++) {
            state[1][j] = matrix[0][j - 1] + state[1][j - 1];
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                state[i][j] = state[i - 1][j] + state[i][j - 1]- state[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int g = i - 1; g >= 0; g--) {
                    for (int l = j - 1; l >= 0; l--) {
                        int cur = state[i][j] - state[g][j] - state[i][l] + state[g][l];
                        if (cur <= k && cur > res) {
                            res = cur;
                        }
                        if (res == k) {
                            return k;
                        }
                    }
                }
            }
        }
        return res;
    }
}

// Solution 2
```
