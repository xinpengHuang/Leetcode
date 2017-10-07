```
/*
Given a string S and a string T, find the minimum window in S which will 
contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

Thoughts
Solution 1: O(N) time and O(1) space, using sliding window with two pointers
*/

// Solution 1
class Solution {
    public String minWindow(String s, String t) {
        int start = 0;
        int total = 0;
        int minStart = 0;
        int length = Integer.MAX_VALUE;
        int[] count = new int[256];
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]--;
            if (count[s.charAt(i)] >= 0) {
                total++;
            }
            while (total == t.length()) {
                if (i - start + 1 < length) {
                    length = i - start + 1;
                    minStart = start;
                }
                if (count[s.charAt(start)] == 0) {
                    total--;
                }
                count[s.charAt(start)]++;
                start++;
            }
        }
        return length == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + length);
    }
}
```
