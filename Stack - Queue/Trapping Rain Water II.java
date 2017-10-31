```
/*
Description
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.

Thoughts

Solution 1: O(m * n * log (m + n)) time and O(m * n) space, using BFS and PriorityQueue.
*/

// Solution 1
class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0) {
            return 0;
        }
        int height = heightMap.length;
        int width = heightMap[0].length;
        boolean[][] visited = new boolean[height][width];
        int vol = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return heightMap[a / width][a % width] - heightMap[b / width][b % width];
            }
        });
        for (int i = 0; i < height; i++) {
            // visited[i][0] = true;
            // visited[i][width - 1] = true;
            queue.offer(i * width);
            queue.offer(i * width + width - 1);
        }
        for (int j = 1; j < width - 1; j++) {
            // visited[0][j] = true;
            // visited[height - 1][j] = true;
            queue.offer(j);
            queue.offer((height - 1) * width + j);
        }
        int[] dir = new int[] {1, 0, -1, 0, 1};
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int row = cur / width;
            int col = cur % width;
            for (int k = 0; k < 4; k++) {
                int rowNew = row + dir[k];
                int colNew = col + dir[k + 1];
                
                // since elements on the boudary are prohibited to go into the loop below, those visited statements above are not necessary 
                if (rowNew > 0 && rowNew < height - 1 && colNew > 0 && colNew < width - 1 && !visited[rowNew][colNew]) {
                    if (heightMap[row][col] > heightMap[rowNew][colNew]) {
                        vol += heightMap[row][col] - heightMap[rowNew][colNew];
                        
                        // here the matrix is modified; 
                        // if this is not supposed to, height could be stored for each location, which will result in a few more codes 
                        heightMap[rowNew][colNew] = heightMap[row][col]; 
                    }
                    visited[rowNew][colNew] = true;
                    queue.offer(rowNew * width + colNew);
                }
            }
        }
        return vol;
    }
}
```
