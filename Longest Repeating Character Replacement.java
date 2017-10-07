```
/*
Description
Given a string that consists of only uppercase English letters, you can replace any letter in the string 
with another letter at most k times. Find the length of a longest substring containing all repeating letters 
you can get after performing the above operations.

Thoughts
Solution 1: O(N) time and O(1) space, using sliding window with two pointers
*/

// Solution 1
class Solution {
    public int characterReplacement(String s, int k) {
        int start = 0;
        int length = 0;
        int max = 0;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'A']++;
            max = Math.max(max, count[s.charAt(i) - 'A']);
            if (i - start + 1 - max > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            length = Math.max(length, i - start + 1);
        }
        return length;
    }
}
```
