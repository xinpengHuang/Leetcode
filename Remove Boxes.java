```
/*
Description
Given several boxes with different colors represented by different positive numbers. 
You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
Find the maximum points you can get.

Example 1:
Input:

[1, 3, 2, 2, 2, 3, 4, 3, 1]
Output:
23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
----> [1, 3, 3, 3, 1] (1*1=1 points) 
----> [1, 1] (3*3=9 points) 
----> [] (2*2=4 points)
Note: The number of boxes n would not exceed 100.


Thoughts
Solution 1: O(n ^ 4) time and O(n ^ 3) space, using dynamic programming.
*/

// Solution 1
class Solution {
    int[][][] state;
    public int removeBoxes(int[] boxes) {
        // state[i][j][k]: maximum values achieved from index i to j, element at i removed with k times;
        state = new int[boxes.length][boxes.length][boxes.length + 1];
        return helper(boxes, 0, boxes.length - 1, 1);
    }
    private int helper(int[] boxes, int i, int j, int k) {
        if (i > j) {
            return 0;
        }
        if (state[i][j][k] != 0) {
            return state[i][j][k];
        }
        
        // deal with identical consecutive boxes at the front 
        int iNew = i + 1;
        while (iNew <= j && boxes[iNew] == boxes[i]) {
            iNew++;
        }
        iNew--;
        k += iNew - i;
        int res = helper(boxes, iNew + 1, j, 1) + k * k;
        
        // whenever find a boxes[n] == boxes[i], paritition and then merge.
        for (int n = iNew + 1; n <= j; n++) {
            if (boxes[n] == boxes[i]) {
                int each = 0;
                each += helper(boxes, iNew, n - 1, k + 1);
                each += helper(boxes, n + 1, j, 1);
                res = Math.max(res, each);
            }  
        }
        state[i][j][k] = res;
        return state[i][j][k];
    }
}
```
