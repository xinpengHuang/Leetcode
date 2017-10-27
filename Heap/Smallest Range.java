```
/*
Description
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].

Thoughts
Solution 1: O(lgk * N) time and O(k) space, using heap.
*/

// Solution 1
class Solution {
    private class Node {
        int val;
        int row;
        int col;
        Node (int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        Queue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.val - b.val;
            }
        });
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            max = Math.max(max, nums.get(i).get(0));
            pq.offer(new Node(nums.get(i).get(0), i, 0));
        }
        int start = pq.peek().val;
        int length = max - pq.peek().val;
        Node cur = pq.poll();
        
        // if the smallest number in pq is already the last element in its list, seaching shall be terminated
        while (cur.col != nums.get(cur.row).size() - 1) {
            cur.col++;
            cur.val = nums.get(cur.row).get(cur.col);
            pq.offer(cur);
            max = Math.max(max, cur.val);
            if (max - pq.peek().val < length) {
                start = pq.peek().val;
                length = max - pq.peek().val;
            }
            cur = pq.poll();
        }
        return new int[] {start, start + length};
    }
}
```
