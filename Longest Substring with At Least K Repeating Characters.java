
```
/*
Description
Find the length of the longest substring T of a given string (consists of lowercase letters only)
such that every character in T appears no less than k times.

Thoughts
Solution 1: O(NlgN - N^2) time and O(?) space, using divide and conquer
*/

// Solution 1
class Solution {
    public int longestSubstring(String s, int k) {
        return longestSubstring(s, 0, s.length() - 1, k);
    }
    private int longestSubstring(String s, int start, int end, int k) {
        if (start > end) {
            return 0;
        }
        int[] count = new int[26];
        for (int i = start; i <= end; i++) {
            count[s.charAt(i) - 'a']++;
        }
        // start search from the middle
        for (int i = start + (end - start) / 2, j = i + 1; i >= start || j <= end; i--, j++) {
            if (i >= start && count[s.charAt(i) - 'a'] < k) {
                return Math.max(longestSubstring(s, start, i - 1, k), longestSubstring(s, i + 1, end, k));
            }
            if (j <= end && count[s.charAt(j) - 'a'] < k) {
                return Math.max(longestSubstring(s, start, j - 1, k), longestSubstring(s, j + 1, end, k));
            }
        }
        return end - start + 1;
    }
}
```
