```
/*
Description
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

Thoughts
Solution 1: O(M * N) time and O(1) space, using intuition...
BFS search from all the letter 'O' on the boundary and flip them to 'B'
iterate through the matrix and flip every 'O' to 'X' and 'B' to 'O'
*/

// Solution 1
class Solution {
    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }
        int height = board.length;
        int width = board[0].length;
        for (int i = 0; i < height; i++) {
            if (board[i][0] == 'O') {
                bfs(board, i * width);
            }
            if (board[i][width - 1] == 'O') {
                bfs(board, i * width + width - 1);
            }
        }
        for (int j = 0; j < width; j++) {
            if (board[0][j] == 'O') {
                bfs(board, j);
            }
            if (board[height - 1][j] == 'O') {
                bfs(board, (height - 1) * width + j);
            }
        }
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    private void bfs(char[][] board, int root) {
        int height = board.length;
        int width = board[0].length;
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(root);
        while (!q.isEmpty()) {
            int cur = q.poll();
            int row = cur / width;
            int col = cur % width;
            if (board[row][col] != 'O') {
                continue;
            }
            board[row][col] = 'B';
            if (row > 0) {
                q.offer(cur - width);
            }
            if (row < height - 1) {
                q.offer(cur + width);
            }
            if (col > 0) {
                q.offer(cur - 1);
            }
            if (row < width - 1) {
                q.offer(cur + 1);
            }
        }
    }
}
```
