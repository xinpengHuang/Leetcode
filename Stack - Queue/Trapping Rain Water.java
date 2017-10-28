```
/*
Description
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Thoughts
Solution 1: O(n) time and O(n) space, using stack to store indexes.
*/

// Solution 1
class Solution {
    public int trap(int[] height) {
        int res = 0;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (deque.size() > 0 && height[deque.peek()] < height[i]) {
                int cur = deque.pop();
                if (deque.isEmpty()) {
                    break;
                }
                int vol = (Math.min(height[deque.peek()], height[i]) - height[cur]) * (i - deque.peek() - 1);
                res += vol;
            }
            deque.push(i);
        }
        return res;
    }
}
```
