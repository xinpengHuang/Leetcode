```
/*
Description
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Thoughts
Solution 1: O(4 ^ k * k ?) time and O(4 ^ k ?) space, using BFS with PriorityQueue. 
(k is the number of steps to get to destination with shortest distance)
*/

// Solution 1
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int height = maze.length;
        int width = maze[0].length;
        int s = start[0] * width + start[1];
        int d = destination[0] * width + destination[1];
        Set<Integer> visited = new HashSet<Integer>();
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                return x[1] - y [1];
            }
        });
        queue.add(new int[] {s, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (visited.contains(cur[0])) {
                continue;
            }
            if (cur[0] == d) {
                return cur[1];
            }
            visited.add(cur[0]);
            int row = cur[0] / width;
            int col = cur[0] % width;
            int[] dir = new int[] {-1, 0, 1, 0, -1};
            for (int k = 0; k < 4; k++) {
                int i = row;
                int j = col;
                while (i >= 0 && i < height && j >= 0 && j < width && maze[i][j] == 0) {
                    i += dir[k];
                    j += dir[k + 1];
                }
                i -= dir[k];
                j -= dir[k + 1];
                int c = i * width + j;
                queue.add(new int[] {c, cur[1] + Math.abs(row - i) + Math.abs(col - j)});
            }
        }
        return -1;
    }
}
```
