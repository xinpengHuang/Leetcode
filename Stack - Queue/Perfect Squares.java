```
/*
Description
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Thoughts
Solution 1: O(k * log(n)) time and O(k * log(n)) space, using BFS. (k is the least number)
*/

// Solution 1
class Solution {
    public int numSquares(int n) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[] {n, 1});
        int len = 1;
        int res = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int j = cur[1];
            while (cur[0] - j * j > 0) {
                queue.offer(new int[] {cur[0] - j * j, j});
                j++;
            }
            if (cur[0] == j * j) {
                return res;
            }
            len--;
            if (len == 0) {
                res++;
                len = queue.size();
            }
        }
        return -1;
    }
}
```
