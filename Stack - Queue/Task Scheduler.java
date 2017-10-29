```
/*
Description
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval k that means between two same tasks, there must be at least k intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], k = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].

Thoughts
Solution 1: O(n * log (k)) time and O(?) space, using .
*/

// Solution 1
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int res = 0;
        int len = tasks.length;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        Queue<Integer> queue = new PriorityQueue<Integer>();
        Queue<Integer> waiting = new LinkedList<Integer>();
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int count : map.values()) {
            queue.offer(-count);
        }
        for (int i = 0; i <= n; i++) {
            waiting.offer(-1);
        }
        while (len > 0) {
            int cur = waiting.poll();
            if (cur != -1) {
                queue.offer(-cur);
            }
            if (!queue.isEmpty()) {
                cur = -queue.poll();
                cur--;
                if (cur > 0) {
                    waiting.offer(cur);
                }
                len--;
            }
            if (waiting.size() < n + 1) {
                waiting.offer(-1);
            }
            res++;
        }
        return res;
    }
}
```
