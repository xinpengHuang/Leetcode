```
/*
Description
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
find the area of largest rectangle in the histogram.

For example,
Given heights = [2,1,5,6,2,3],
return 10.

Thoughts
Solution 1: O(n) time and O(n) space, using stack to store indexes.
*/

// Solution 1
class Solution {
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
